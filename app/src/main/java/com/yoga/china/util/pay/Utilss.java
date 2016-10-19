package com.yoga.china.util.pay;

import android.app.Activity;
import android.app.ProgressDialog;

public class Utilss {

	public static Activity activity;

	public static HttpTask getHttpTask(String url, String content) {
		HttpTask httpTask = null;
		switch (UmsGlobalInfo.netWorkClass) {
		case 0:
			httpTask = new HttpTask(null, url, content);
			break;
		}
		return httpTask;
	}

	public static ProgressDialog progressDialog;

	public static void showProgressDialog() {
		progressDialog = ProgressDialog.show(activity, "Loading...", "Please wait...", true, false);
		progressDialog.setCanceledOnTouchOutside(false);
	}

	public static void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

	}
}
