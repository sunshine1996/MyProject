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
import com.bluemobi.hengdongkeji.utils.Tools;
import com.gghl.view.wheelview.NumericWheelAdapter;
import com.gghl.view.wheelview.WheelAdapter;
import com.gghl.view.wheelview.WheelView;

/**
 * 简单的wheelpop
 * 
 * 
 * @author 孙思远 2015年9月16日 下午1:35:27
 */
public class SimpleWheelPop extends PopupWindow implements OnClickListener {

	private PopListener popListener;
	private int tag;
	private String value = "";
	private WheelView wv_data;
	private Context context;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * 
	 * @author 孙思远 2015年9月16日 下午2:12:31
	 */
	public SimpleWheelPop(Context context) {
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
	public SimpleWheelPop(Context context, int tag) {
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
	public SimpleWheelPop(Context context, int tag, PopListener popListener) {
		this.context = context;
		this.tag = tag;
		this.popListener = popListener;
		View view = LayoutInflater.from(context).inflate(R.layout.pop_wheel, null);
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.setOnKeyListener(new OnKeyListener() {

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
		wv_data = (WheelView) view.findViewById(R.id.wv_data);
		wv_data.TEXT_SIZE = MyApplication.SCREEN_W / 20;
		wv_data.setVisibleItems(7);
		setContentView(view);
		setWidth(MyApplication.SCREEN_W);
		setHeight(MyApplication.SCREEN_H);
		setFocusable(true);
		setOutsideTouchable(false);
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

	// /**
	// * 背景是否可见
	// *
	// * @param visibility
	// *
	// * @author 孙思远 2015年10月8日 下午4:06:56
	// */
	// public void setBgVisible(int visibility) {
	// getContentView().findViewById(R.id.bg).setVisibility(visibility);
	// }

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
		return value;
	}

	public int getTag() {
		return tag;
	}

	public void setWheelAdapter(WheelAdapter wheelAdapter) {
		if (wheelAdapter != null) {
			wv_data.setAdapter(wheelAdapter);
		}
	}

	public void show(View parent) {
		if (wv_data.getAdapter() != null && wv_data.getAdapter().getItemsCount() > 0) {
			showAtLocation(parent, Gravity.CENTER, 0, 0);
		} else {
			Tools.showToast(context, "未找到数据");
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_confirm) {
			value = wv_data.getAdapter().getItem(wv_data.getCurrentItem());
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
	}

	public interface PopListener {
		public void onClick(int tag, String value);

		public void dismiss(int tag, String value);
	}
}
