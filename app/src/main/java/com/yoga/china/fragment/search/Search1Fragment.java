package com.yoga.china.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bm.yogainchina.R;
import com.google.gson.reflect.TypeToken;
import com.yoga.china.activity.activity.HotActivityAc;
import com.yoga.china.activity.article.YogaLifeAc;
import com.yoga.china.activity.integral.IntegralAc;
import com.yoga.china.activity.teacher.TeacherColumAc;
import com.yoga.china.activity.video.TeacherVideoDetailAc;
import com.yoga.china.adapter.ClassifiAdapter;
import com.yoga.china.adapter.HotRecommendAdapter;
import com.yoga.china.bean.BaseBean;
import com.yoga.china.bean.HotWord;
import com.yoga.china.fragment.base.BaseFragment;
import com.yoga.china.http.Http;
import com.yoga.china.http.config.Config;
import com.yoga.china.http.config.HttpConstant;
import com.yoga.china.view.ExtraGridView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 第一个fragment
 * Created by sunsiyuan on 16/5/23.
 */
public class Search1Fragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ExtraGridView gv_recommend, gv_class;
    private HotRecommendAdapter hotRecommendAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_search1, null);
        gv_class = (ExtraGridView) view.findViewById(R.id.gv_class);
        gv_recommend = (ExtraGridView) view.findViewById(R.id.gv_recommend);
        gv_class.setAdapter(new ClassifiAdapter(getActivity()));
        hotRecommendAdapter = new HotRecommendAdapter(getActivity());
        gv_recommend.setAdapter(hotRecommendAdapter);
        gv_class.setOnItemClickListener(this);
        getHotWords();
        return view;
    }


    /**
     * 获取热点词
     */
    public void getHotWords() {
        LinkedHashMap params = new LinkedHashMap();
        Type type = new TypeToken<BaseBean<HashMap<String, ArrayList<HotWord>>>>() {
        }.getType();
        Http.getInstance().post(HttpConstant.getHotWords, params, type, HttpConstant.getHotWords, handler);
    }

    @Override
    protected void httpResSuccess(String sign, Bundle bundle) {
        HashMap<String, ArrayList<HotWord>> map = (HashMap<String, ArrayList<HotWord>>) bundle.getSerializable(Config.DATA);
        if (map.get("wordList") != null && !map.get("wordList").isEmpty()) {
            hotRecommendAdapter.setList(map.get("wordList"));
        }
    }

    @Override
    protected void httpResFail(String sign, Bundle bundle) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startAc(TeacherColumAc.class);
                break;
            case 1:
                startAc(HotActivityAc.class);
                break;
            case 2:
                startAc(IntegralAc.class);
                break;
            case 3:
                startAc(YogaLifeAc.class);
                break;
            case 4:
                startAc(TeacherVideoDetailAc.class);
                break;
            case 5:

                break;
        }
    }
}
