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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bluemobi.hengdongkeji.R;
import com.bluemobi.hengdongkeji.application.MyApplication;
import com.bluemobi.hengdongkeji.popwindow.SimpleWheelPop.PopListener;

/**
 * 时间选择
 * 
 * 
 * @author 孙思远 2015年6月11日 下午5:55:06
 */
public class DatePop extends PopupWindow implements OnClickListener {

	PopListener callBack;
	private Button btn_cancel, btn_confirm;
	StartDateWheel startDateWheel;
	String content;
	int tag;

	public DatePop(Context context, int tag, PopListener callBack) {
		this.callBack = callBack;
		this.tag = tag;
		LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pop_three_wheel, null);
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
		initView(view);
		LinearLayout ll_main = (LinearLayout) view.findViewById(R.id.ll_main);
		startDateWheel = new StartDateWheel(ll_main);
		startDateWheel.initDateTimePicker();
		setContentView(view);
		setWidth(MyApplication.SCREEN_W);
		setHeight(LayoutParams.MATCH_PARENT);
		setOutsideTouchable(true);
		setFocusable(true);
		setBackgroundDrawable(context.getResources().getDrawable(R.color.touming));
	}

	private void initView(View view) {
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(this);
		btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(this);
		view.findViewById(R.id.bg).setOnClickListener(this);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_confirm:
			content = startDateWheel.getTime();
			callBack.onClick(tag, content);
			break;
		}
		dismiss();
	}

	public void show(View view) {
		showAtLocation(view, Gravity.CENTER, 0, 0);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		callBack.dismiss(tag, content);
	}

}
