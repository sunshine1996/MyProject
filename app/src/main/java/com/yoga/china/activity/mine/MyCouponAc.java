package com.yoga.china.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bm.yogainchina.R;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.adapter.CouponAdapter;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * 我的优惠券
 * Created by sunsiyuan on 16/4/26.
 */
@SetContentView(R.layout.ac_list)
public class MyCouponAc extends BaseViewAc {

    @FindView
    private PullToRefreshLayout pl_main;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.my_coupon);
        lv = (ListView) pl_main.getPullableView();
        lv.setAdapter(new CouponAdapter(this));
        lv.setDivider(getResources().getDrawable(R.color.trans));
        adHeadView();
    }

    /**
     * 添加一个空白的头部
     */
    private void adHeadView() {
        View view = new View(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dim30));
        view.setLayoutParams(params);
        lv.addHeaderView(view);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
