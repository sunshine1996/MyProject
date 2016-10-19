package com.yoga.china.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bm.yogainchina.R;
import com.yoga.china.activity.base.BaseAc;
import com.yoga.china.adapter.ClubSearchAdapter;
import com.yoga.china.fragment.base.BaseFragment;
import com.yoga.china.util.db.DBDaoUtil;
import com.yoga.china.view.ExtraListView;

import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

/**
 * Created by sunsiyuan on 16/5/5.
 */
public class SearchHistoryFragment extends BaseFragment implements View.OnClickListener {

    private ExtraListView lv_list;
    private ClubSearchAdapter adapter;
    private DBDaoUtil daoUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ac_club_search, null);
        view.findViewById(R.id.btn_clear).setOnClickListener(this);
        lv_list = (ExtraListView) view.findViewById(R.id.lv_list);
        daoUtil = new DBDaoUtil(getActivity());
        adapter = new ClubSearchAdapter(getActivity());
        adapter.setList(daoUtil.getHistory());
        lv_list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroy() {
        daoUtil.clostDB();
        super.onDestroy();
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
}
