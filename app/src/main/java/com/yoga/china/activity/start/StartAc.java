package com.yoga.china.activity.start;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yoga.china.activity.base.BaseAc;
import com.yoga.china.activity.home.HomeAc;
import com.yoga.china.http.Http;
import com.yoga.china.http.config.HttpConstant;
import com.yoga.china.util.AppContact;
import com.yoga.china.util.PreContact;
import com.yoga.china.util.PreUtil;
import com.yoga.china.util.Tools;

import java.util.LinkedHashMap;

/**
 * 启动页
 * Created by sunsiyuan on 16/3/9.
 */
public class StartAc extends BaseAc {

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(!isFinishing()) {
                        startAc(HomeAc.class);
                        finishAc();
                    }
                    break;
                case 2:
                    if (!isFinishing()) {
                        if (PreUtil.getInstance().getBoolean(PreContact.ISFIRST)) {
                            startAc(GuideAc.class);
                        } else {
                            startAc(LoginAc.class);
                        }
                        finishAc();
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Tools.isNull(PreUtil.getInstance().getString(PreContact.ACCOUNT))) {
            silentLogin();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(AppContact.isLogin){
                    handler.sendEmptyMessage(1);
                }else {
                    handler.sendEmptyMessage(2);
                }
            }
        }, 3000);
    }

    /**
     * 静默登陆
     */
    private void silentLogin() {
        String account = PreUtil.getInstance().getString(PreContact.ACCOUNT);
        String pwd = PreUtil.getInstance().getString(PreContact.PASSWORD);
        LinkedHashMap params = new LinkedHashMap();
        params.put("telephone", account);
        params.put("password", pwd);
        Http.getInstance().post(HttpConstant.URL_LOGIN, params, Http.defaultType, "login", handler);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {
        AppContact.isLogin=true;
//        handler.sendEmptyMessage(1);
    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {
        handler.sendEmptyMessage(2);
    }
}
