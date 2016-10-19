/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.bm.yogainchina.wxapi;

import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.yoga.china.activity.base.BaseAc;

/** 微信客户端回调activity示例 */
public class WXEntryActivity extends BaseAc implements IWXAPIEventHandler{





	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {

	}

	@Override
	protected void httpResSuccess(String sign, Bundle bundle) {

	}

	@Override
	protected void httpResFail(String sign, Bundle bundle) {

	}
}
