package com.yoga.china.activity.appointment;

import android.os.Bundle;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseViewAc;

import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/5/13.
 */
@SetContentView(R.layout.ac_app_details)
public class AppointDetailsAc extends BaseViewAc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.booking_details);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
