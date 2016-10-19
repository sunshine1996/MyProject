package com.yoga.china.http;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.http.base.HttpHelper;
import com.http.base.HttpResult;
import com.http.base.HttpStringResult;
import com.yoga.china.bean.BaseBean;
import com.yoga.china.http.config.Config;
import com.yoga.china.util.Tools;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 网络请求
 * Created by sunsiyuan on 16/2/22.
 */
public class Http extends HttpBase {

    public static Type defaultType = new TypeToken<BaseBean<?>>() {
    }.getType();

    private static Http instance;

    public static Http getInstance() {
        if (instance == null) {
            instance = new Http();
        }
        return instance;
    }


    /**
     * 是否需要进度条
     *
     * @param showPd
     * @param url
     * @param params
     * @param type
     * @param sign
     * @param handler
     */
    public void post(boolean showPd, String url, LinkedHashMap params, final Type type, final String sign, final Handler handler) {
        if (showPd)
            handler.sendEmptyMessage(Config.SHOWPD);
        HttpHelper.asyncPost(url, params, new HttpHelper.HttpStringHandler() {

            @Override
            public void handleResponse(HttpStringResult httpStringResult) {
                dealData(httpStringResult, handler, sign, type);
            }
        });
    }

    /**
     * 是否显示进度条
     *
     * @param showPd
     * @param url
     * @param params
     * @param type
     * @param sign
     * @param handler
     */
    public void get(boolean showPd, String url, LinkedHashMap params, final Type type, final String sign, final Handler handler) {
        if (showPd)
            handler.sendEmptyMessage(Config.SHOWPD);
        HttpHelper.asyncGet(url, params, new HttpHelper.HttpHandler() {
            @Override
            public void handleResponse(HttpResult httpResult) {
                HttpStringResult result = new HttpStringResult();
                result.msg = httpResult.msg;
                result.resCode = httpResult.resCode;
                result.result = httpResult.result;
                dealData(result, handler, sign, type);
            }
        });
    }


    /**
     * post 处理
     *
     * @param url
     * @param params
     * @param type
     * @param sign
     * @param handler
     */
    public void post(String url, LinkedHashMap params, final Type type, final String sign, final Handler handler) {
        post(false, url, params, type, sign, handler);
    }

    /**
     * get处理
     *
     * @param url
     * @param params
     * @param type
     * @param sign
     * @param handler
     */
    public void get(String url, LinkedHashMap params, final Type type, final String sign, final Handler handler) {
        get(false, url, params, type, sign, handler);
    }

    /**
     * 所有上传文件的接口都要走这个方法
     *
     * @param url
     * @param handler
     * @param params
     * @param files
     * @author sunsy 2014年11月10日下午3:35:41
     */
    private void sendFile(String url, LinkedHashMap params, HashMap<String, File> files, final Type type, final String sign, final Handler handler
    ) {
        HttpHelper.asyncFormPost(url, params, files, new HttpHelper.HttpStringHandler() {
            @Override
            public void handleResponse(HttpStringResult httpStringResult) {
                dealData(httpStringResult, handler, sign, type);
            }
        });
    }


    /**
     * 数据处理
     *
     * @param httpStringResult
     * @param handler
     * @param sign
     */
    private void dealData(HttpStringResult httpStringResult, Handler handler, String sign, Type type) {
        {
            {
                BaseBean baseBean = null;
                /*如果回调值为空，发送空数据，返回*/
                if (Tools.isNull(httpStringResult)) {
                    sendHandler(handler, baseBean, sign);
                    return;
                }
                Log.e("info",httpStringResult.result);
                Gson gson = new Gson();
                String context = httpStringResult.result;
                baseBean = new BaseBean();
                baseBean.setCode(Config.DEFEAT);
                /*如果后台返回值为空,失败，*/
                if (Tools.isNull(context)) {
                    baseBean.setMsg(httpStringResult.msg);
                    sendHandler(handler, baseBean, sign);
                    return;
                }
                /*判断data是否为空，如果data为空，返回*/
                if (judgeData(gson, context)) {
                    baseBean = getBean(gson, context);
                    sendHandler(handler, baseBean, sign);
                    return;
                }
                baseBean = gson.fromJson(context, type);
                sendHandler(handler, baseBean, sign);
            }
        }
    }

}
