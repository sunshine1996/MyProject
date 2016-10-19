package com.yoga.china.activity.video;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.yogainchina.R;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.yoga.china.activity.base.BaseAc;
import com.yoga.china.activity.base.BaseViewAc;
import com.yoga.china.adapter.TeacherVideoAdapter;
import com.yoga.china.pop.ShareAc;
import com.yoga.china.util.AnimUtil;
import com.yoga.china.util.AppContact;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * 教学视频详情
 * Created by sunsiyuan on 16/4/20.
 */
@SetContentView(R.layout.ac_video_detail)
public class TeacherVideoDetailAc extends BaseAc implements RadioGroup.OnCheckedChangeListener {

    @FindView
    private TextView tv_title_name, tv_name, tv_parise, tv_collect,tv_title;
    @FindView
    private ImageView iv_img;
    @FindView
    private RadioGroup rg_check;
    @FindView
    private PullToRefreshLayout pl_main;
    private ListView lv;
    @FindView
    private WebView wv;
    @FindView
    private LinearLayout btn_buy;
    @FindView
    private View v_rg_line;
    @FindView
    private RelativeLayout rl_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.teach_video);
        tv_title.setText(R.string.teach_video);
        rg_check.setOnCheckedChangeListener(this);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDisplayZoomControls(false);
        lv = (ListView) pl_main.getPullableView();
        lv.setAdapter(new TeacherVideoAdapter(this));
        wv.loadUrl("http://www.baidu.com");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        auto();
    }

    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finishAc();
    }

    /**
     * 收藏
     *
     * @param view
     */
    public void collect(View view) {
        showToast("没有已收藏切图");
    }

    /**
     * 分享
     *
     * @param view
     */
    public void share(View view) {
        startAc(ShareAc.class);
    }

    /**
     * 适配
     */
    private void auto() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_main.getLayoutParams();
        params.height = AppContact.SCREEN_W / 16*9;
        rl_main.setLayoutParams(params);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) v_rg_line.getLayoutParams();
        params1.width = AppContact.SCREEN_W / 2;
        v_rg_line.setLayoutParams(params1);
    }

    /***
     * 购买
     *
     * @param view
     */
    public void buy(View view) {

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
    private void anim(boolean isRight) {
        float endX = AppContact.SCREEN_W / 2;
        if (isRight) {
            v_rg_line.startAnimation(AnimUtil.getInstance().getTranslateAnimation(0, 0, endX, 0));
        } else {
            v_rg_line.startAnimation(AnimUtil.getInstance().getTranslateAnimation(endX, 0, 0, 0));
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_all:
                pl_main.setVisibility(View.VISIBLE);
                btn_buy.setVisibility(View.VISIBLE);
                wv.setVisibility(View.GONE);
                anim(false);
                break;
            case R.id.rb_detail:
                pl_main.setVisibility(View.GONE);
                wv.setVisibility(View.VISIBLE);
                btn_buy.setVisibility(View.GONE);
                anim(true);
                break;
        }
    }
}
