package com.yoga.china.pop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import cn.bluemobi.xbg.MyApplication;

import com.bluemobi.xiaobaogao.R;

public class SimpleListPop extends PopupWindow implements OnItemClickListener {

	Context context;
	int tag;
	ArrayList<String> list;
	PopListen callBack;
	ArrayAdapter<String> adapter;
	ListView lv;
	private boolean isFill;

	/**
	 * 
	 * 
	 * @param activity
	 * @param tag
	 * @param list
	 * @param callBack
	 * @param isFill
	 *            是否拉伸填充
	 * 
	 * @author 孙思远 2015年9月21日 下午4:11:37
	 */
	public SimpleListPop(Context activity, int tag, ArrayList<String> list, PopListen callBack, boolean isFill) {
		this.context = activity;
		this.isFill = isFill;
		this.tag = tag;
		this.list = list;
		this.callBack = callBack;
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lv = new ListView(context);
		lv.setBackgroundColor(Color.parseColor("#ffffff"));
		// lv.setBackgroundResource(R.drawable.layer_list);
		// lv.setBackgroundResource(R.drawable.bg_white_trans_corner);
		lv.getBackground().setAlpha(242);
		lv.setDivider(context.getResources().getDrawable(R.color.grey_shallow));
		lv.setDividerHeight(2);
		lv.setSelector(context.getResources().getDrawable(R.color.transparent));
		// lv.setLayoutParams(new LayoutParams(MyApplication.SCREEN_W - 60,
		// LayoutParams.MATCH_PARENT));
		adapter = new ArrayAdapter<String>(context, R.layout.simple_list_item, R.id.text, this.list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		setFocusable(true);
		ll.addView(lv);
		setContentView(ll);
		setHeight(LayoutParams.WRAP_CONTENT);
		setWidth(MyApplication.SCREEN_W);
		setOutsideTouchable(true);
		setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
	}

	public void setadapter(ArrayList<String> list) {
		this.list = list;
		adapter.notifyDataSetChanged();
	}

	public void show(View view) {
		if (adapter.getCount() > 0) {
			int dimen = adapter.getCount() > 3 ? R.dimen.dim350 : R.dimen.dim300;
			int height = context.getResources().getDimensionPixelSize(dimen);
			if (isFill) {
				height = 0;
				for (int i = 0; i < adapter.getCount(); i++) {
					height += 100;
				}
			}
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lv.getLayoutParams();
			params.width = MyApplication.SCREEN_W - 80;
			params.height = height;
			params.setMargins(10, 0, 10, 0);
			lv.setLayoutParams(params);
			showAsDropDown(view, 0, 0);
		}
	}

	public void showSameWidth(View view) {
		setWidth(view.getWidth());
		show(view);
	}

	public void setContentViewBg(int resid) {
		lv.setBackgroundResource(resid);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		callBack.callBack(position, tag);
		// TextView tv_text = (TextView) view.findViewById(R.id.text);
		// tv_text.setTextColor(activity.getResources().getColor(R.color.btn_bg_red));
		if (isShowing()) {
			dismiss();
		}
	}

	@Override
	public void dismiss() {
		callBack.dismiss(0, tag);
		super.dismiss();
	}
}
