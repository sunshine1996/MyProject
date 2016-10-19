package com.bm.yogainchina.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yoga.china.http.config.HttpConstant;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, HttpConstant.APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if ((resp.errCode + "").equals("-1")) {
                Toast.makeText(WXPayEntryActivity.this, "微信版本过低或未安装微信出现支付异常", Toast.LENGTH_SHORT).show();
            }
            if ((resp.errCode + "").equals("0")) {
                Toast.makeText(WXPayEntryActivity.this, "支付成功",
                        Toast.LENGTH_SHORT).show();
//				if (ActionSignUpActivity.isActive) {
//					ActionSignUpActivity.mHandlerPay.sendEmptyMessage(resp.errCode);
//				}
//				if (PaymentCardPayActivity.isActive) {
//					PaymentCardPayActivity.mHandlerPay.sendEmptyMessage(resp.errCode);
//				}
//				if (ApplyProjectActivity.isActive) {
//					ApplyProjectActivity.mHandlerPay.sendEmptyMessage(resp.errCode);
//				}
//				if (BuyVideoActivity.isActive) {
//					BuyVideoActivity.mHandlerPay.sendEmptyMessage(resp.errCode);
//				}
//			}
//			Toast.makeText(WXPayEntryActivity.this, resp.errCode, Toast.LENGTH_SHORT).show();
//			PayActivity.handler.sendEmptyMessage(resp.errCode);
//			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			 builder.setTitle(R.string.hint);
//			 builder.setMessage(getString(R.string.pay_result_callback_msg,
//			 resp.errStr +";code=" + String.valueOf(resp.errCode)));
//			 builder.show();
                finish();
            }
        }
    }
}