package com.yoga.china.activity.start;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseAc;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.adapter.GuideAdapter;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/3/9.
 */
@SetContentView(R.layout.ac_guide)
public class GuideAc extends BaseAc {

    @FindView
    private ViewPager vp_guide;
    private Integer[] resId={R.mipmap.guidance_1,R.mipmap.guidance_2,R.mipmap.guidance_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vp_guide.setAdapter(new GuideAdapter(this,resId));
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }
}
