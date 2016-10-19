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

import cn.bluemobi.xbg.util.GetImgUtil;

import com.bluemobi.xiaobaogao.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * 获取图片的pop
 * 
 * 
 * @author 孙思远 2015年6月9日 上午11:20:32
 */
public class ImgPop extends PopupWindow implements OnClickListener {

	Context context;
	PopListen callBack;

	public ImgPop(Context context, PopListen callBack) {
		this.context = context;
		this.callBack = callBack;
		View popView = LayoutInflater.from(context).inflate(R.layout.popview_head_icon, null);
		View bg_view = popView.findViewById(R.id.bg_view);
		bg_view.setOnClickListener(this);
		/* 拍照 */
		Button bt_tack_photo_upload = (Button) popView.findViewById(R.id.bt_tack_photo_upload);
		/* 相册 */
		Button bt_native_upload = (Button) popView.findViewById(R.id.bt_native_upload);
		/* 取消 */
		Button btn_cancel = (Button) popView.findViewById(R.id.btn_cancel);
		/* 设置监听 */
		bt_native_upload.setOnClickListener(this);
		bt_tack_photo_upload.setOnClickListener(this);

		btn_cancel.setOnClickListener(this);
		setContentView(popView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		setFocusable(true);
		setOutsideTouchable(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/* 拍照 */
		case R.id.bt_tack_photo_upload:
			callBack.callBack(-1, 1);
			break;
		/* 相册 */
		case R.id.bt_native_upload:
			callBack.callBack(-1, 2);
			break;
		}
		dismiss();
	}

	@Override
	public void dismiss() {
		callBack.dismiss(0, 0);
		super.dismiss();
	}
}
