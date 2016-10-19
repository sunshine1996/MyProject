/**
 * Copyright (C) 2015 孙思远 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yoga.china.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.PopupWindow;

import com.bluemobi.hengdongkeji.R;
import com.bluemobi.hengdongkeji.application.MyApplication;
import com.bluemobi.hengdongkeji.popwindow.SimpleWheelPop.PopListener;
import com.bluemobi.hengdongkeji.utils.Tools;
import com.gghl.view.wheelview.ArrayWheelAdapter;
import com.gghl.view.wheelview.OnWheelChangedListener;
import com.gghl.view.wheelview.WheelAdapter;
import com.gghl.view.wheelview.WheelView;

/**
 * 简单的二级联动wheelpop
 * 
 * 
 * @author 孙思远 2015年9月16日 下午1:35:27
 */
public class SimpleThreeWheelPop extends PopupWindow implements OnClickListener {

	private PopListener popListener;
	private int tag;
	private String value = "";
	private WheelView wv_left, wv_right, wv_3;
	private Context context;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * 
	 * @author 孙思远 2015年9月16日 下午2:12:31
	 */
	public SimpleThreeWheelPop(Context context) {
		this(context, -1);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param tag
	 * 
	 * @author 孙思远 2015年9月16日 下午2:12:39
	 */
	public SimpleThreeWheelPop(Context context, int tag) {
		this(context, tag, null);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param tag
	 * @param popListener
	 * 
	 * @author 孙思远 2015年9月16日 下午2:12:48
	 */
	public SimpleThreeWheelPop(Context context, int tag, PopListener popListener) {
		this.tag = tag;
		this.context = context;
		this.popListener = popListener;
		View view = LayoutInflater.from(context).inflate(R.layout.pop_three_wheel, null);
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.findViewById(R.id.ll_bg).setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					return true;
				}
				return false;
			}
		});
		view.findViewById(R.id.btn_cancel).setOnClickListener(this);
		view.findViewById(R.id.btn_confirm).setOnClickListener(this);
		view.findViewById(R.id.bg).setOnClickListener(this);
		wv_left = (WheelView) view.findViewById(R.id.wv_1);
		int text_size = MyApplication.SCREEN_W / 20;
		wv_left.TEXT_SIZE = text_size;
		wv_left.setVisibleItems(5);
		wv_right = (WheelView) view.findViewById(R.id.wv_2);
		wv_right.TEXT_SIZE = text_size;
		wv_right.setVisibleItems(5);
		wv_3 = (WheelView) view.findViewById(R.id.wv_3);
		wv_3.TEXT_SIZE = text_size;
		wv_3.setVisibleItems(5);
		setContentView(view);
		setWidth(MyApplication.SCREEN_W);
		setHeight(MyApplication.SCREEN_H);
		setFocusable(true);
		setOutsideTouchable(false);
	}

	/**
	 * 添加监听
	 * 
	 * @param listener
	 * 
	 * @author 孙思远 2015年10月14日 上午11:09:06
	 */
	public void addWeelChangingListen(OnWheelChangedListener listener) {
		wv_3.addChangingListener(listener);
		wv_left.addChangingListener(listener);
		wv_right.addChangingListener(listener);
	}

	/**
	 * 设置Tag
	 * 
	 * @param tag
	 * 
	 * @author 孙思远 2015年9月16日 下午2:13:06
	 */
	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * 设置监听
	 * 
	 * @param popListener
	 * 
	 * @author 孙思远 2015年9月16日 下午2:13:16
	 */
	public void setPopListener(PopListener popListener) {
		this.popListener = popListener;
	}

	public String getValue() {
		if (Tools.isNull(value)) {
			onClick(getContentView().findViewById(R.id.btn_confirm));
		}
		return value;
	}

	public int getTag() {
		return tag;
	}

	/**
	 * 设置中间的处理器
	 * 
	 * @param dataLeft
	 * 
	 * @author 孙思远 2015年10月13日 下午4:22:46
	 */
	public void setWheelAdapter2(WheelAdapter dataLeft) {
		if (dataLeft.getItemsCount() == 0) {
			wv_right.setVisibility(View.GONE);
		} else {
			wv_right.setVisibility(View.VISIBLE);
		}
		wv_right.setAdapter(dataLeft);
	}

	/**
	 * 设置左侧的处理器
	 * 
	 * @param dataLeft
	 * 
	 * @author 孙思远 2015年10月13日 下午4:22:46
	 */
	public void setWheelAdapter1(WheelAdapter dataLeft) {
		if (dataLeft.getItemsCount() == 0) {
			wv_left.setVisibility(View.GONE);
		} else {
			wv_left.setVisibility(View.VISIBLE);
		}
		wv_left.setAdapter(dataLeft);
	}

	/**
	 * 设置右侧的处理器
	 * 
	 * @param adapter
	 * 
	 * @author 孙思远 2015年10月13日 下午4:46:11
	 */
	public void setWheelAdapter3(WheelAdapter adapter) {
		if (adapter.getItemsCount() == 0) {
			wv_3.setVisibility(View.GONE);
		} else {
			wv_3.setVisibility(View.VISIBLE);
		}
		wv_3.setAdapter(adapter);
	}

	public void show(View parent) {
		if (wv_3.getAdapter().getItemsCount() == 0 && wv_left.getAdapter().getItemsCount() == 0
				&& wv_right.getAdapter().getItemsCount() == 0) {
			Tools.showToast(context, "未找到数据");
			return;
		}
		showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_confirm) {// 选中的值赋值给控件
			String left = wv_left != null && wv_left.getAdapter() != null && wv_left.getAdapter().getItemsCount() > 0 ? wv_left
					.getAdapter().getItem(wv_left.getCurrentItem()) : "";
			String center = wv_right != null && wv_right.getAdapter() != null && wv_right.getAdapter().getItemsCount() > 0 ? wv_right
					.getAdapter().getItem(wv_right.getCurrentItem()) : "";
			String right = wv_3 != null && wv_3.getAdapter() != null && wv_3.getAdapter().getItemsCount() > 0 ? wv_3.getAdapter().getItem(
					wv_3.getCurrentItem()) : "";
			value = left + center + right;
			if (popListener != null)
				popListener.onClick(tag, value);
		}
		dismiss();
	}

	@Override
	public void dismiss() {
		super.dismiss();
		if (popListener != null) {
			popListener.dismiss(tag, value);
		}
		value = "";
	}
}
