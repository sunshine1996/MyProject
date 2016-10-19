package com.yoga.china.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseViewAc;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * 收货地址，添加收货地址
 * Created by sunsiyuan on 16/4/25.
 */
@SetContentView(R.layout.ac_ship_address)
public class EditAddressAc extends BaseViewAc {

    @FindView
    private TextView tv_name, tv_tel, tv_zip, tv_area, tv_address;
    @FindView
    private EditText et_name, et_tel, et_zip, et_area, et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("isEidt", false)) {
            setTitle(R.string.save);
            setRightText(R.string.save);
            tv_address.setVisibility(View.GONE);
            tv_area.setVisibility(View.GONE);
            tv_name.setVisibility(View.GONE);
            tv_tel.setVisibility(View.GONE);
            tv_zip.setVisibility(View.GONE);
        } else {
            setTitle(R.string.ship_address);
            setRightText(R.string.edit);
            et_address.setVisibility(View.GONE);
            et_area.setVisibility(View.GONE);
            et_name.setVisibility(View.GONE);
            et_tel.setVisibility(View.GONE);
            et_zip.setVisibility(View.GONE);
        }
    }

    /**
     * 默认
     */
    public void setDefault(View view) {
        Button btn = (Button) view;
        if (btn.getText().equals(getStr4Res(R.string.edit))) {
            et_address.setVisibility(View.VISIBLE);
            et_area.setVisibility(View.VISIBLE);
            et_name.setVisibility(View.VISIBLE);
            et_tel.setVisibility(View.VISIBLE);
            et_zip.setVisibility(View.VISIBLE);
            tv_address.setVisibility(View.GONE);
            tv_area.setVisibility(View.GONE);
            tv_name.setVisibility(View.GONE);
            tv_tel.setVisibility(View.GONE);
            tv_zip.setVisibility(View.GONE);
            setRightText(R.string.save);
        } else if (btn.getText().equals(getStr4Res(R.string.save))) {
            if (getIntent().getBooleanExtra("isEdit", false)) {
                et_address.setVisibility(View.GONE);
                et_area.setVisibility(View.GONE);
                et_name.setVisibility(View.GONE);
                et_tel.setVisibility(View.GONE);
                et_zip.setVisibility(View.GONE);
                tv_address.setVisibility(View.VISIBLE);
                tv_area.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.VISIBLE);
                tv_tel.setVisibility(View.VISIBLE);
                tv_zip.setVisibility(View.VISIBLE);
                setRightText(R.string.edit);
            } else {
                finishAc();
            }
        }
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
