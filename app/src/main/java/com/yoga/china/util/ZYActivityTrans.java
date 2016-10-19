/**
 * Copyright (C) 2015 Michael Zhang (zhangyi_0820@qq.com)
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

package com.yoga.china.util;

import android.app.Activity;
import android.content.Intent;

import com.bm.yogainchina.R;


/**
 * Activity 跳转动画
 * 
 * @author Michael Zhang 2015年4月10日 下午2:35:44
 */
public class ZYActivityTrans {

	/**
	 * 无请求码
	 */
	public static final int REQUEST_CODE_NULL = -123455;

	/**
	 * 无结果码
	 */
	public static final int RESUTL_CODE_NULL = -123456;

	/**
	 * 无动画
	 */
	public static final int TRANS_NULL = -1;

	/**
	 * 移动动画
	 */
	public static final int TRANS_MOVE = 0;

	/**
	 * 缩放
	 */
	public static final int TRANS_SCALE = 1;

	/**
	 * 启动目标Activity 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param clzz
	 *            目标Activity
	 * 
	 * @author Michael Zhang 2015年4月10日 下午2:38:38
	 */
	public static void startMove(Activity activity, Class<?> clzz) {
		startMove(activity, new Intent(activity, clzz));
	}

	/**
	 * 启动目标Activity 有参数 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 * @author Michael Zhang 2015年4月10日 15:30:49
	 */
	public static void startMove(Activity activity, Intent intent) {
		startMove(activity, intent, REQUEST_CODE_NULL);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 * @author Michael Zhang 2015年4月10日 15:30:49
	 */
	public static void startMove(Activity activity, Intent intent, int reques_code) {
		start(activity, intent, reques_code, TRANS_MOVE);
	}

	/**
	 * 启动目标Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param clzz
	 *            目标Activity
	 * 
	 * @author Michael Zhang 2015年4月10日 15:15:24
	 */
	public static void startScale(Activity activity, Class<?> clzz) {
		startMove(activity, new Intent(activity, clzz));
	}

	/**
	 * 启动目标Activity 有参数 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 * @author Michael Zhang 2015年4月10日 15:32:59
	 */
	public static void startScale(Activity activity, Intent intent) {
		startMove(activity, intent, REQUEST_CODE_NULL);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 * @author Michael Zhang 2015年4月10日 15:32:59
	 */
	public static void startScale(Activity activity, Intent intent, int request_code) {
		start(activity, intent, request_code, TRANS_SCALE);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 区分动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            Intent
	 * @param request_code
	 *            请求码
	 * @param trans
	 *            动画方式
	 * 
	 * @author Michael Zhang 2015年4月10日 下午2:38:38
	 */
	public static void start(Activity activity, Intent intent, int request_code, int trans) {
		if (TRANS_MOVE == trans) {
			start(activity, intent, request_code, R.anim.push_right_in, R.anim.push_right_out);
		} else if (TRANS_SCALE == trans) {
			start(activity, intent, request_code, R.anim.alpha_scale_in, R.anim.alpha_scale_out);
		} else {
			start(activity, intent, request_code, 0, 0);
		}
	}

	/**
	 * 启动目标Activity 有参数 有请求码 有动画
	 * 
	 * @param activity
	 *            当前activity
	 * @param intent
	 *            intent
	 * @param request_code
	 *            请求码
	 * @param push_in
	 *            入场动画
	 * @param push_out
	 *            出场动画
	 * 
	 * @author 孙思远 2015年7月27日 下午3:25:29
	 */
	public static void start(Activity activity, Intent intent, int request_code, int push_in, int push_out) {
		if (REQUEST_CODE_NULL == request_code) {
			activity.startActivity(intent);
		} else {
			activity.startActivityForResult(intent, request_code);
		}
		activity.overridePendingTransition(push_in, push_out);
	}

	/**
	 * finish当前Activity 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * 
	 * @author Michael Zhang 2015年4月10日 下午2:57:36
	 */
	public static void finishMove(Activity activity) {
		finishMove(activity, null, RESUTL_CODE_NULL);
	}

	/**
	 * finish当前Activity 有参数 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            参数
	 * @param result_code
	 *            结果码
	 * 
	 * @author Michael Zhang 2015-4-10 15:41:04
	 */
	public static void finishMove(Activity activity, Intent intent, int result_code) {
		finish(activity, intent, result_code, TRANS_MOVE);
	}

	/**
	 * finish当前Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * 
	 * @author Michael Zhang 2015-4-10 15:16:05
	 */
	public static void finishScale(Activity activity) {
		finishScale(activity, null, RESUTL_CODE_NULL);
	}

	/**
	 * finish当前Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            参数
	 * @param result_code
	 *            结果码
	 * 
	 * @author Michael Zhang 2015-4-10 15:42:31
	 */
	public static void finishScale(Activity activity, Intent intent, int result_code) {
		finish(activity, intent, result_code, TRANS_SCALE);
	}

	/**
	 * finish当前Activity 有参数 区分动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            Intent
	 * @param result_code
	 *            结果码
	 * @param trans
	 *            动画方式
	 * 
	 * @author Michael Zhang 2015年4月10日 下午2:57:36
	 */
	public static void finish(Activity activity, Intent intent, int result_code, int trans) {

		if (TRANS_MOVE == trans) {
			finish(activity, intent, result_code, R.anim.push_right_in, R.anim.push_right_out);
		} else if (TRANS_SCALE == trans) {
			finish(activity, intent, result_code, R.anim.alpha_scale_in, R.anim.alpha_scale_out);
		} else {
			// activity.overridePendingTransition(0, 0);
			finish(activity, intent, result_code, 0, 0);
		}
	}

	public static void finish(Activity activity, Intent intent, int result_code, int push_in, int push_out) {
		if (null != intent) {
			activity.setResult(result_code, intent);
		}
		activity.finish();
		activity.overridePendingTransition(push_in, push_out);
	}
}
