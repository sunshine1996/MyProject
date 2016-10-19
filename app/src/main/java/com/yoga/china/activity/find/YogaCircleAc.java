package com.yoga.china.activity.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.yogainchina.R;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.activity.home.SearchAc;
import com.yoga.china.adapter.YogaCircleAdapter;
import com.yoga.china.view.RoundImageView;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * 瑜伽圈
 */
@SetContentView(R.layout.ac_yoga_circle)
public class YogaCircleAc extends BaseViewAc implements View.OnClickListener, AdapterView.OnItemClickListener {

    @FindView
    private FloatingActionButton fab_edit;
    @FindView
    private RoundImageView iv_head;
    @FindView
    private ImageView iv_banner;
    @FindView
    private PullToRefreshLayout pl_main;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab_edit.setOnClickListener(this);
        setTitle(R.string.yoga_circle);
        lv = (ListView) pl_main.getPullableView();
        lv.setAdapter(new YogaCircleAdapter(this));
        lv.setOnItemClickListener(this);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {

    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, SearchAc.class);
        String transitionName = getString(R.string.trans_anim);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, transitionName);
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }
}
