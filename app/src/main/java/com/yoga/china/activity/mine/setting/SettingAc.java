package com.yoga.china.activity.mine.setting;

import android.os.Bundle;
import android.view.View;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseViewAc;

import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/4/19.
 */
@SetContentView(R.layout.ac_setting)
public class SettingAc extends BaseViewAc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.settings);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
