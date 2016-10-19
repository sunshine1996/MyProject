package com.yoga.china.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.bm.yogainchina.R;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.util.AnimUtil;
import com.yoga.china.util.AppContact;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * 我的消息
 * Created by sunsiyuan on 16/4/12.
 */
@SetContentView(R.layout.ac_message)
public class MessageAc extends BaseViewAc implements RadioGroup.OnCheckedChangeListener {

    @FindView
    private RadioGroup rg_check;
    @FindView
    private View line;
    @FindView
    private PullToRefreshLayout pl_main;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.message);
        lv = (ListView) pl_main.getPullableView();
        rg_check.setOnCheckedChangeListener(this);
        auto();
    }

    private void auto() {
        int width = AppContact.SCREEN_W / 2 - 200;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) line.getLayoutParams();
        params.width = width;
        line.setLayoutParams(params);
        line.setX(100);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }

    /**
     * 线条的动画
     */
    private void anim(boolean isLeft) {
        float endX = AppContact.SCREEN_W / 2;
        if (isLeft) {
            line.startAnimation(AnimUtil.getInstance().getTranslateAnimation(0, 0, endX, 0));
        } else {
            line.startAnimation(AnimUtil.getInstance().getTranslateAnimation(endX, 0, 0, 0));
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_system:
                anim(false);
                break;
            case R.id.rb_activity:
                anim(true);
                break;
        }
    }
}
