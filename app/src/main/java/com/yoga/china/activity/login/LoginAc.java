package com.yoga.china.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bm.yogainchina.R;
import com.yoga.china.App;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.activity.home.HomeAc;
import com.yoga.china.http.Http;
import com.yoga.china.http.config.Config;
import com.yoga.china.http.config.HttpConstant;
import com.yoga.china.util.AppContact;
import com.yoga.china.util.PreContact;
import com.yoga.china.util.PreUtil;
import com.yoga.china.util.Tools;

import java.util.LinkedHashMap;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/3/9.
 */
@SetContentView(R.layout.ac_login)
public class LoginAc extends BaseViewAc {

    @FindView
    private ImageView iv_head;
    @FindView
    private EditText et_account,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.login);
        autoScreen();
    }

    /**
     * 适配屏幕
     */
    private void autoScreen(){
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) iv_head.getLayoutParams();
        params.height=AppContact.SCREEN_W/5*4;
        iv_head.setLayoutParams(params);
    }

    /**
     * 登陆
     * @param view
     */
    public void login(View view){
        String account =et_account.getText().toString(); //PreUtil.getInstance().getString(PreContact.ACCOUNT);
        String pwd =et_password.getText().toString(); //PreUtil.getInstance().getString(PreContact.PASSWORD);
        if(Tools.isNull(account)){
            showToast(R.string.input_account);
            et_account.requestFocus();
            return;
        }else if(Tools.isNull(pwd)){
            showToast(R.string.input_account);
            et_password.requestFocus();
            return;
        }
        LinkedHashMap params = new LinkedHashMap();
        params.put("telephone", account);
        params.put("password", pwd);
        Http.getInstance().post(HttpConstant.URL_LOGIN, params, Http.defaultType, "login", handler);
    }

    /**
     * 注册
     * @param view
     */
    public void regiest(View view){

    }

    /**
     * 忘记密码
     * @param view
     */
    public void forgetPwd(View view){

    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {
        PreUtil.getInstance().putString(PreContact.ACCOUNT,et_account.getText().toString());
        PreUtil.getInstance().putString(PreContact.PASSWORD,et_password.getText().toString());
        showToast(R.string.login_success);
        startAc(HomeAc.class);
    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {
        showToast(bundle.getString(Config.MSG));
    }
}
