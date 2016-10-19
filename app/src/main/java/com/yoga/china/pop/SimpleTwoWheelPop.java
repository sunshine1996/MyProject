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
import com.gghl.view.wheelview.OnWheelChangedListener;
import com.gghl.view.wheelview.WheelAdapter;
import com.gghl.view.wheelview.WheelView;

/**
 * 简单的二级联动wheelpop
 * 
 * 
 * @author 孙思远 2015年9月16日 下午1:35:27
 */
public class SimpleTwoWheelPop extends PopupWindow implements OnClickListener {

	private PopListener popListener;
	private int tag;
	private String value = "";
	private WheelView wv_left, wv_right;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * 
	 * @author 孙思远 2015年9月16日 下午2:12:31
	 */
	public SimpleTwoWheelPop(Context context) {
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
	public SimpleTwoWheelPop(Context context, int tag) {
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
	public SimpleTwoWheelPop(Context context, int tag, PopListener popListener) {
		this.tag = tag;
		this.popListener = popListener;
		View view = LayoutInflater.from(context).inflate(R.layout.pop_two_wheel, null);
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
		wv_left = (WheelView) view.findViewById(R.id.wv_left);
		wv_left.TEXT_SIZE = MyApplication.SCREEN_W / 20;
		wv_left.setVisibleItems(5);
		wv_right = (WheelView) view.findViewById(R.id.wv_right);
		wv_right.TEXT_SIZE = MyApplication.SCREEN_W / 20;
		wv_right.setVisibleItems(5);
		setContentView(view);
		setWidth(MyApplication.SCREEN_W);
		setHeight(MyApplication.SCREEN_H);
		setFocusable(true);
		setOutsideTouchable(false);
	}

	public void addWeelChangingListen(OnWheelChangedListener listener) {
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
		return value;
	}

	public int getTag() {
		return tag;
	}

	public void setWheelAdapter(WheelAdapter data_left, WheelAdapter data_right) {
		if (data_left != null) {
			// wv_left.setAdapter(data_left);
			setLeftWheelAdapter(data_left);
		}
		if (data_right != null) {
			// wv_right.setAdapter(data_right);
			setRightWheelAdapter(data_right);
		}
	}

	/**
	 * 设置右侧的处理器
	 * 
	 * @param dataLeft
	 * 
	 * @author 孙思远 2015年10月13日 下午4:22:46
	 */
	public void setRightWheelAdapter(WheelAdapter dataLeft) {
		wv_right.setAdapter(dataLeft);
	}

	/**
	 * 设置左侧的处理器
	 * 
	 * @param dataLeft
	 * 
	 * @author 孙思远 2015年10月13日 下午4:22:46
	 */
	public void setLeftWheelAdapter(WheelAdapter dataLeft) {
		wv_left.setAdapter(dataLeft);
	}

	public void show(View parent) {
		showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_confirm) {// 选中的值赋值给控件
			if ("现在上门".equals(wv_left.getAdapter().getItem(wv_left.getCurrentItem()))) {
				value = "现在上门";
			} else {
				value = wv_left.getAdapter().getItem(wv_left.getCurrentItem()) + "  "
						+ wv_right.getAdapter().getItem(wv_right.getCurrentItem());
			}
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

}
