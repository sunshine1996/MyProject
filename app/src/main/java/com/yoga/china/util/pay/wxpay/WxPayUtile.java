package com.yoga.china.util.pay.wxpay;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.bm.yogainchina.R;
import com.bm.yogainchina.utils.Constant;
import com.bm.yogainchina.wxapi.WXPayEntryActivity;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WxPayUtile {

	private Context context;
	PayReq req;
	final IWXAPI msgApi;
	Map<String, String> resultunifiedorder;
	StringBuffer sb;
	private String total_fee;
	private String notify_url;
	private String body;
	private String outTradNo;

	private static final String TAG = "MicroMsg.SDKSample.PayActivity";

	public WxPayUtile(Context context, String total_fee, String notify_url,
			String body, String outTradNo) {
		super();
		msgApi = WXAPIFactory.createWXAPI(context, null);
		req = new PayReq();
		msgApi.registerApp(Constant.APP_ID);
		sb = new StringBuffer();
		this.context = context;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
		this.body = body;
		this.outTradNo = outTradNo;
	}

	public static WxPayUtile getInstance(Context context, String total_fee,
			String notify_url, String body, String outTradNo) {

		return new WxPayUtile(context, total_fee, notify_url, body, outTradNo);
	}

	public void doPay() {

		GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
		getPrepayId.execute();
	}

	/**
	 * 生成签名
	 */

	private String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constant.API_KEY);

		String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
				.toUpperCase(Locale.CHINA);
		Log.e("orion-packageSign-->", packageSign);
		return packageSign;
	}

	private String genAppSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constant.API_KEY);

		this.sb.append("sign str\n" + sb.toString() + "\n\n");
		String appSign = MD5.getMessageDigest(sb.toString().getBytes())
				.toUpperCase();
		Log.e("orion-appSign-->", appSign);
		return appSign;
	}

	private String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");

			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");

		Log.e("orion-sb--->", sb.toString());
		return sb.toString();
	}

	private class GetPrepayIdTask extends
			AsyncTask<Void, Void, Map<String, String>> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			if (!isWXAppInstalledAndSupported()) {
				Toast.makeText(context, "微信版本过低或未安装微信出现支付异常", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(context, R.string.getting_prepayid, Toast.LENGTH_SHORT).show();
			}
		}
		
		private boolean isWXAppInstalledAndSupported() {
	        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
	                && msgApi.isWXAppSupportAPI();

	        return sIsWXAppInstalledAndSupported;
	    }

		@Override
		protected void onPostExecute(Map<String, String> result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			sb.append("prepay_id\n" + result.get("prepay_id") + "\n\n");
			resultunifiedorder = result;

			Log.e("orion-result_code-->", result.get("return_code"));
			if (result.get("return_code").equals("SUCCESS")) {
				Log.e("orion-result_code-->", "IS SUCCESS!");
				genPayReq();
			}else{
				Message msg = new Message();
				msg.obj = result.get("result_code");
				msg.what = 800;
//				PayActivity.handler.sendMessage(msg);
			}

		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected Map<String, String> doInBackground(Void... params) {

			String url = String
					.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
			String entity = genProductArgs();

			Log.e("orion-entity-->", entity);

			byte[] buf = HttpWxUtile.httpPost(url, entity);

			String content = new String(buf);
			Log.e("orion-content-->", content);
			Map<String, String> xml = decodeXml(content);

			return xml;
		}
	}

	public Map<String, String> decodeXml(String content) {

		try {
			Map<String, String> xml = new HashMap<String, String>();
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(new StringReader(content));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				String nodeName = parser.getName();
				switch (event) {
				case XmlPullParser.START_DOCUMENT:

					break;
				case XmlPullParser.START_TAG:

					if ("xml".equals(nodeName) == false) {
						// 实例化student对象
						xml.put(nodeName, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				event = parser.next();
			}

			return xml;
		} catch (Exception e) {
			Log.e("orion-e--->", e.toString());
		}
		return null;

	}

	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());
	}

	private long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取OutTradNo可根据用户自行更改
	 * 
	 * @return
	 */
	private String genOutTradNo() {
//		 Random random = new Random();
//		 int time = (int) System.currentTimeMillis();
//		 return
//		 MD5.getMessageDigest(String.valueOf(random.nextInt(10000)+time).getBytes());
		return outTradNo;
	}

	//
	private String genProductArgs() {
		StringBuffer xml = new StringBuffer();

		try {
			String nonceStr = genNonceStr();

			xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid", Constant.APP_ID));
			packageParams.add(new BasicNameValuePair("body", body));
			packageParams.add(new BasicNameValuePair("mch_id", Constant.MCH_ID));
			packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
			packageParams.add(new BasicNameValuePair("notify_url", notify_url));
			packageParams.add(new BasicNameValuePair("out_trade_no",genOutTradNo()));
			packageParams.add(new BasicNameValuePair("spbill_create_ip","127.0.0.1"));
			packageParams.add(new BasicNameValuePair("total_fee", total_fee));
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));

			String sign = genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));

			String xmlstring = toXml(packageParams);

			return xmlstring;

		} catch (Exception e) {
			Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
			return null;
		}

	}

	private void genPayReq() {

		req.appId = Constant.APP_ID;
		req.partnerId = Constant.MCH_ID;
		req.prepayId = resultunifiedorder.get("prepay_id");
		req.packageValue = "Sign=WXPay";
		req.nonceStr = genNonceStr();
		req.timeStamp = String.valueOf(genTimeStamp());

		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", req.appId));
		signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
		signParams.add(new BasicNameValuePair("package", req.packageValue));
		signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
		signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
		signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

		req.sign = genAppSign(signParams);

		sb.append("sign\n" + req.sign + "\n\n");

		Log.e("Hcl>>>>>>>>>>>", signParams.toString());
		
		sendPayReq();

	}

	private void sendPayReq() {

		msgApi.registerApp(Constant.APP_ID);
		msgApi.sendReq(req);
	}

}
