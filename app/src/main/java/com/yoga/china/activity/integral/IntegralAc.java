package com.yoga.china.activity.integral;

import android.os.Bundle;
import android.widget.GridView;

import com.bm.yogainchina.R;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.adapter.IntegralAdapter;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/4/14.
 */
@SetContentView(R.layout.ac_integral_mall)
public class IntegralAc extends BaseViewAc {

    @FindView
    private PullToRefreshLayout pl_main;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.integral_mall);
        gv= (GridView) pl_main.getPullableView();
        gv.setAdapter(new IntegralAdapter(this));
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
