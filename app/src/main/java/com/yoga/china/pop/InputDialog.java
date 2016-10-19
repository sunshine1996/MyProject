/**
 * Copyright (C) 2015 Michael Zhang (zhangyi_0820@qq.com) 2015年5月20日 上午11:31:05
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bm.yogainchina.R;
import com.yoga.china.util.AppContact;

/**
 * 两个按钮的dialog
 *
 * @author Michael Zhang (zhangyi_0820@qq.com) <br>
 *         2015年5月20日上午11:31:05
 */
public class InputDialog extends Dialog {
    private View.OnClickListener mListener;
    private TextView tv_content;
    private Button btn_concel, btn_confirm;

    public InputDialog(Context context, View.OnClickListener listener) {
        super(context, R.style.dialog_base);
        mListener = listener;
        //setCancelable(false);
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dg_two_btn, null);
        this.setContentView(view, new ViewGroup.LayoutParams(AppContact.SCREEN_W - getContext().getResources().getDimensionPixelOffset(R.dimen.dim40), ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_concel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_concel.setOnClickListener(mListener);
        btn_confirm.setOnClickListener(mListener);

        tv_content.setText("题目未做完是否交卷");
        btn_concel.setText("返回");
        btn_confirm.setText("交卷");
    }

    /**
     * 显示
     *
     * @param content
     *
     * @author Michael Zhang (zhangyi_0820@qq.com) <br>
     *         2015年5月20日上午11:35:36
     */
    public void show(String content, String btn_confirm_text, String btn_cancel_text) {
        super.show();
        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }

        if (!TextUtils.isEmpty(btn_confirm_text)) {
            btn_confirm.setText(btn_confirm_text);
        }

        if (!TextUtils.isEmpty(btn_cancel_text)) {
            btn_concel.setText(btn_cancel_text);
        }

    }
}
