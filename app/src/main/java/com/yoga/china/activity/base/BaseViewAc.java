package com.yoga.china.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.yogainchina.R;

/**
 * 所有有头部标签的基类
 * Created by sunsiyuan on 16/3/9.
 */
public abstract class BaseViewAc extends BaseAc {


    @Override
    public void setContentView(int layoutResID) {
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.in_title, null);
        View child = getLayoutInflater().inflate(layoutResID, null);
        layout.addView(child);
        System.out.println("我是setContentView");
        super.setContentView(layout);
    }

    /**
     * 左上角返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 设置返回按钮隐藏
     */
    protected void setLeftGone() {
        findViewById(R.id.ibtn_back).setVisibility(View.GONE);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        setTitle(getStr4Res(resId));
    }

    /*设置标题*/
    public void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    /**
     * 设置右侧标题按钮
     *
     * @param resId
     */
    protected void setRightText(int resId) {
        setRightText(getStr4Res(resId));
    }

    /**
     * 设置右侧标题按钮
     *
     * @param text
     */
    protected void setRightText(String text) {
        Button btn = (Button) findViewById(R.id.btn_right);
        if (!btn.isShown()) {
            btn.setVisibility(View.VISIBLE);
        }
        btn.setText(text);
    }

    /**
     * 设置右侧图片按钮的图片
     *
     * @param resId
     */
    protected void setRightIbtnRes(int resId) {
        ImageButton ibtn = (ImageButton) findViewById(R.id.ibtn_right);
        ibtn.setImageResource(resId);
    }


}
