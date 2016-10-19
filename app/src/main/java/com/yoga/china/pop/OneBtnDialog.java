/**
 * Copyright (C) 2015 Michael Zhang (zhangyi_0820@qq.com) 2015年5月20日 上午11:31:05
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

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.bluemobi.wendu.R;

/**
 * 两个按钮的dialog
 * 
 * @author Michael Zhang (zhangyi_0820@qq.com) <br>
 *         2015年5月20日上午11:31:05
 */
public class OneBtnDialog extends Dialog {
	private View.OnClickListener mListener;
	private TextView tv_content;
	private Button btn_confirm;

	public OneBtnDialog(Context context, View.OnClickListener listener) {
		super(context, R.style.dialog_base);
		mListener = listener;
	}

	/**
	 * @see Dialog#onCreate(Bundle)
	 * 
	 * @author Michael Zhang (zhangyi_0820@qq.com) <br>
	 *         2015年5月20日上午11:32:05
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dg_one_btn);

		tv_content = (TextView) findViewById(R.id.tv_content);
		btn_confirm = (Button) findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(mListener);
		if (null == mListener) {
			btn_confirm.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
		}
	}

	/**
	 * 显示
	 * 
	 * @param content
	 * 
	 * @author Michael Zhang (zhangyi_0820@qq.com) <br>
	 *         2015年5月20日上午11:35:36
	 */
	public void show(String content, String btn_confirm_text) {
		super.show();
		if (!TextUtils.isEmpty(content)) {
			tv_content.setText(content);
		}

		if (!TextUtils.isEmpty(btn_confirm_text)) {
			btn_confirm.setText(btn_confirm_text);
		}
	}
	public void show(String content) {
		super.show();
		if (!TextUtils.isEmpty(content)) {
			tv_content.setText(content);
		}

		
			btn_confirm.setVisibility(View.GONE);
	
	}
}
