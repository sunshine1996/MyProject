package com.yoga.china.util.pay;

import java.net.URLEncoder;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.bluemobi.hengdongkeji.net.NetField;

/**
 * 支付帮助类
 * 
 * @author ZhangYi 2014年8月27日 下午3:19:42
 * 
 */
public class PayUtil {
	public static final int RQF_PAY = 1;

	private Activity mActivity;
	private Handler mHandler;
	private String mOrderId; // 外部订单号
	private String mSubject; // 订单名称
	private String mBody; // 订单内容
	private double mFee; // 订单费用

	public PayUtil(Activity activity, Handler handler) {
		mActivity = activity;
		mHandler = handler;
	}

	public void pay(String orderId, String subject, String body, double fee) {
		mOrderId = orderId;
		mSubject = subject;
		mBody = body;
		mFee = fee;
		try {
			String info = getNewOrderInfo();
			String sign = Rsa.sign(info, Keys.PRIVATE);
			sign = URLEncoder.encode(sign);
			info += "&sign=\"" + sign + "\"&" + getSignType();
			final String orderInfo = info;
			new Thread() {
				public void run() {
					PayTask alipay = new PayTask(mActivity);
					String result = alipay.pay(orderInfo);
					Message msg = new Message();
					msg.what = RQF_PAY;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			}.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 拼接商品信息
	 * 
	 * @author ZhangYi 2014年8月27日 下午3:26:39
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getNewOrderInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(Keys.DEFAULT_PARTNER);
		sb.append("\"&out_trade_no=\"");
		sb.append(mOrderId);
		sb.append("\"&subject=\"");
		sb.append(mSubject);
		sb.append("\"&body=\"");
		sb.append(mBody);
		sb.append("\"&total_fee=\"");
		sb.append(mFee);
		sb.append("\"&notify_url=\"");
		sb.append(URLEncoder.encode(NetField.PAY_CALLBACK_ZFB));// 网址需要做URL编码
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));// 网址需要做URL编码
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(Keys.DEFAULT_SELLER);
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");

		return new String(sb);
	}

	private String getSignType() {
		return "sign_type=\"RSA\"";
	}
}
