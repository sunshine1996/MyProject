package com.yoga.china.activity.mine.setting;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseViewAc;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/4/27.
 */
@SetContentView(R.layout.ac_message_setting)
public class AlertAc extends BaseViewAc implements CompoundButton.OnCheckedChangeListener {

    @FindView
    private Switch sv_message, sv_commend, sv_funs, sv_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.alerts);
        sv_commend.setOnCheckedChangeListener(this);
        sv_course.setOnCheckedChangeListener(this);
        sv_funs.setOnCheckedChangeListener(this);
        sv_message.setOnCheckedChangeListener(this);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sv_commend:

                break;
            case R.id.sv_course:

                break;
            case R.id.sv_funs:

                break;
            case R.id.sv_message:

                break;
        }
    }
}
