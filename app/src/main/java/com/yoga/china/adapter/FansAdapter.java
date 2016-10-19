package com.yoga.china.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bm.yogainchina.R;
import com.yoga.china.util.HolderUtil;
import com.yoga.china.view.RoundImageView;

/**
 * Created by sunsiyuan on 16/4/18.
 */
public class FansAdapter extends BaseAdapter {

    /*0学生，1老师*/
    private int tag = 1;

    public FansAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_funs, null);
            holder = new Holder();
            HolderUtil.getClassInfo(holder, convertView, context);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_seniority.setText(Html.fromHtml(context.getResources().getString(R.string.seniority) + ": <font color='#1FA039'>10年</font>"));
        holder.tv_course.setText(Html.fromHtml(context.getResources().getString(R.string.main_course) + ": <font color='#1FA039'>阴瑜伽</font>"));
        if (position % 2 == 0) {
            holder.tv_tag.setBackgroundResource(R.drawable.bg_white_fragme_green_corner);
            holder.tv_tag.setTextColor(context.getResources().getColor(R.color.base_green));
        } else {
            holder.tv_tag.setBackgroundResource(R.drawable.bg_green_corner);
            holder.tv_tag.setTextColor(context.getResources().getColor(R.color.white));
        }
        return convertView;
    }

    private class Holder {
        public RoundImageView iv_head;
        public TextView tv_name, tv_seniority, tv_club, tv_course, tv_tag;
    }

}
