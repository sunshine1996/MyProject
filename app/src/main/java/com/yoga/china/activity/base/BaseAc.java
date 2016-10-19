package com.yoga.china.activity.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bm.yogainchina.R;
import com.yoga.china.App;
import com.yoga.china.http.config.Config;
import com.yoga.china.util.Tools;
import com.yoga.china.util.ZYActivityTrans;

import cn.zy.inject.ZYInject;

/**
 * 所有activity的基类
 * Created by sunsiyuan on 16/3/8.
 */
public abstract class BaseAc extends Activity{

    /*网络请求dialog*/
    private ProgressDialog pd;

    /*异步回调*/
    protected Handler handler=new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case Config.SUCCESS:
                    httpResSuccess(msg.obj.toString(),msg.getData());
                    dismissPd();
                    break;
                case Config.DEFEAT:
                    httpResFail(msg.obj.toString(),msg.getData());
                    dismissPd();
                    break;
                case Config.SHOWPD:
                    showPD();
                    break;
            }
            return false;
        }
    });
    /*是否可以退出*/
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZYInject.inject(this);
        App.getInstance().addActivity(this);
    }

    /**
     * 显示等待窗
     *
     * @author Michael.Zhang 2013-10-31 下午2:10:29
     * @param content
     */
    protected void showPD(String content) {
        if (null == pd) {
            pd = new ProgressDialog(this);
        }

        if (null != content) {
            pd.setMessage(content);
        }

        if (!pd.isShowing()) {
            pd.show();
        }
    }

    /**
     * 显示网络请求对话框
     *
     *
     * @author 孙思远 2015年8月31日 上午10:35:08
     */
    protected void showPD() {
        showPD(getString(R.string.request_data));
    }

    /**
     * 关闭等待窗
     *
     * @author Michael.Zhang 2013-10-31 下午2:11:26
     */
    protected void dismissPd() {
        if (null != pd && pd.isShowing()) {
            pd.dismiss();
        }
    }



    /**
     * 双击退出
     */
    private void doubleExit(){
        if(!isExit){
            isExit=true;
            showToast(R.string.exit);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit=false;
                }
            },2000);
        }else{
            App.getInstance().exit(1);
        }
    }

    /**
     * 提示
     * @param resId
     */
    public void showToast(int resId){
        showToast(getStr4Res(resId));
    }

    /**
     * 提示
     * @param content
     */
    public void showToast(String content){
        Tools.showToast(this,content);
    }

    /**
     * 根据资源获得
     * @param resId
     * @return
     */
    public String getStr4Res(int resId){
        return getResources().getString(resId);
    }

    /**
     * 启动目标Activity,无参数
     *
     * @param clazz
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年4月24日下午4:43:18
     */
    protected void startAc(Class<?> clazz) {
        startAc(new Intent(this, clazz));
    }

    /**
     * 启动目标Activity,有参数
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年4月24日下午4:43:18
     */
    protected void startAc(Intent intent) {
        ZYActivityTrans.startMove(this, intent, ZYActivityTrans.REQUEST_CODE_NULL);
    }

    /**
     * 启动目标Activity,有参数, 有返回
     *
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年4月24日下午4:43:18
     */
    protected void startAc(Intent intent, int reques_code) {
        ZYActivityTrans.startMove(this, intent, reques_code);
    }

    /**
     * 结束当前Activity, 无参数
     *
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年4月24日下午4:43:48
     */
    protected void finishAc() {
        finishAc(null, ZYActivityTrans.RESUTL_CODE_NULL);
    }

    /**
     * 结束当前Activity, 有参数
     *
     * @param intent
     * @param result_code
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年4月24日下午4:44:03
     */
    protected void finishAc(Intent intent, int result_code) {
        ZYActivityTrans.finishMove(this, intent, result_code);
    }

    /**
     * 接口请求成功
     * @param sign
     * @param bundle
     */
    protected abstract void httpResSuccess(String sign,Bundle bundle);

    /**
     * 接口请求失败
     * @param sign
     * @param bundle
     */
    protected abstract void httpResFail(String sign,Bundle bundle);
}
