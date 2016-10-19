package com.yoga.china.pop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.bluemobi.xbg.ZYActivity;
import cn.bluemobi.xbg.home.ShitsAc;
import cn.bluemobi.xbg.net.base.NetField;
import cn.bluemobi.xbg.util.PreferencesUtil;
import cn.bluemobi.xbg.util.Tools;
import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

import com.bluemobi.xiaobaogao.R;
import com.bluemobi.xiaobaogao.wxapi.WXEntryActivity;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;

/**
 * 底部弹出分享菜单
 * 
 * @author BaiKaiWen <br/>
 * 
 *         2014-11-11下午3:17:12
 */
@SetContentView(R.layout.ac_select_pop)
public class ShareAc extends ZYActivity implements IWeiboHandler.Response {
	/** 微博分享的接口实例 */
	private IWeiboShareAPI mWeiboShareAPI;
	boolean isPaused;

	@FindView
	private ImageButton wx_pyq;
	@FindView
	private ImageButton wx;
	@FindView
	private ImageButton sina;
	@FindView
	private ImageButton qq;
	@FindView
	private Button btn_cancle;

	public static final String SINA_APP_KEY = "302132732";
	public static final String SINA_SECRET = "9aed3d8486448c257de96b0e3365d21a";
	public static final String TENCENT_APP_ID = "1104694085";
	public static final String TENCENT_APP_KEY = "Lqh8DesZib6OruSU";
	public static final String SINA_REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";// 应用的回调页
	public static final String SINA_SCOPE = // 应用申请的高级权限
	"email,direct_messages_read,direct_messages_write," + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";
	private String action;
	private String content, title, url;

	private void initAction() {
		switch (getIntent().getIntExtra("action", -1)) {
		case 1:
			action = "FeedbackShareNumber";
			break;
		case 2:
			action = "ReportShareNumber";
			break;
		case 3:
			action = "RidiculeShareNumber";
			break;
		}
	}

	@Override
	public void finish() {
		overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
		super.finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initAction();
		// 创建微博 SDK 接口实例
		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, SINA_APP_KEY);
		mWeiboShareAPI.registerApp();
		mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
		title = getIntent().getStringExtra("title");
		content = getIntent().getStringExtra("content");
		url = getIntent().getStringExtra("url");
	}

	/**
	 * 初始化控件
	 * */

	/**
	 * 捕捉触摸事件
	 * */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		finishAc();
//		return true;
//	}

	/**
	 * 点击事件
	 * */
	public void onBtnClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.wx: // 分享微信好友
			Intent intent = new Intent(this, WXEntryActivity.class);
			intent.putExtra("content", content);
			intent.putExtra("title", title);
			intent.putExtra("url", url);
			intent.putExtra("action", action);
			intent.putExtra("type", 1);
			intent.putExtra("scene", SendMessageToWX.Req.WXSceneSession);
			startActivity(intent);
			// showShare(true, "Wechat", false);
			break;
		case R.id.wx_pyq:
			Intent intent1 = new Intent(this, WXEntryActivity.class);
			intent1.putExtra("content", content);
			intent1.putExtra("title", title);
			intent1.putExtra("type", 1);
			intent1.putExtra("url", url);
			intent1.putExtra("action", action);
			intent1.putExtra("scene", SendMessageToWX.Req.WXSceneTimeline);
			startActivity(intent1);
			// showShare(true, "WechatMoments", false);
			break;
		case R.id.qq:
			// shareToQzone();
			if (Tools.isApkInstalled(this, "com.tencent.mobileqq")) {
				onClickShare();
			} else {
				showToast("您没有安装QQ");
			}
			break;
		case R.id.sina:
			shareSina();
			break;
		}
		// ZYActivityTrans.finish(this, null, ZYActivityTrans.RESUTL_CODE_NULL,
		// R.anim.push_bottom_in, R.anim.push_bottom_out);
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isPaused) {
			finish();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isPaused = true;
	}

	/**
	 * 
	 * @Title: shareSina
	 * @Description: 分享新浪微博
	 * @author sunsy
	 * @return void 返回类型
	 */
	private void shareSina() {
		sendMessage();
	}

	/**
	 * 第三方应用发送请求消息到微博，唤起微博分享界面。
	 * 
	 * @see {@link #sendMultiMessage} 或者 {@link #sendSingleMessage}
	 */
	private void sendMessage() {
		sendMultiMessage();
	}

	/**
	 * 第三方应用发送请求消息到微博，唤起微博分享界面。 注意：当
	 * {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条消息，
	 * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
	 * 
	 * @param hasText
	 *            分享的内容是否有文本
	 * @param hasImage
	 *            分享的内容是否有图片
	 * @param hasWebpage
	 *            分享的内容是否有网页
	 * @param hasMusic
	 *            分享的内容是否有音乐
	 * @param hasVideo
	 *            分享的内容是否有视频
	 * @param hasVoice
	 *            分享的内容是否有声音
	 */
	private void sendMultiMessage() {
		MobclickAgent.onEvent(ShareAc.this, action);
		// 1. 初始化微博的分享消息
		WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		/* 内容文字对象 */
		// TextObject textObject = new TextObject();
		// textObject.text = content;
		// textObject.title = title;
		// textObject.actionUrl = url;
		/* 网页对象 */
		WebpageObject webpageObject = new WebpageObject();
		webpageObject.actionUrl = url;
		webpageObject.identify = Utility.generateGUID();
		webpageObject.defaultText = content;
		webpageObject.description = content;
		webpageObject.title = title;
		webpageObject.setThumbImage(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
		weiboMessage.mediaObject = webpageObject;
		// weiboMessage.textObject = textObject; // getTextObj();
		// 2. 初始化从第三方到微博的消息请求
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		// 用transaction唯一标识一个请求
		request.transaction = String.valueOf(System.currentTimeMillis());
		request.multiMessage = weiboMessage;

		// 3. 发送请求消息到微博，唤起微博分享界面
		AuthInfo authInfo = new AuthInfo(this, SINA_APP_KEY, SINA_REDIRECT_URL, SINA_SCOPE);
		Oauth2AccessToken accessToken = PreferencesUtil.readAccessToken(getApplicationContext());
		String token = "";
		if (accessToken != null) {
			token = accessToken.getToken();
		}
		mWeiboShareAPI.sendRequest(this, request, authInfo, token, new WeiboAuthListener() {

			@Override
			public void onWeiboException(WeiboException arg0) {
				arg0.printStackTrace();
			}

			@Override
			public void onComplete(Bundle bundle) {
				// TODO Auto-generated method stub
				Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
				PreferencesUtil.writeAccessToken(getApplicationContext(), newToken);
				finish();
			}

			@Override
			public void onCancel() {
			}
		});

	}

	/**
	 * 
	 * @Title: shareToQzone
	 * @Description:空间分享
	 * @author sunsy
	 * @return void 返回类型
	 */
	private void shareToQzone() {
		Bundle params = new Bundle();
		params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
		params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.gjgolf.cn/");
		params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "测试数据");// 必填
		params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "这是一条测试数据");// 选填
		params.putString(QzoneShare.SHARE_TO_QQ_APP_NAME, "小报告");
		params.putString(QzoneShare.SHARE_TO_QQ_SITE, "小报告");
		doShareToQzone(params);
	}

	private void onClickShare() {
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
		ArrayList<String> imageUrls = new ArrayList<String>();
		imageUrls.add(NetField.SITE + "/img/shareImg/1024.png");
		params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, getResources().getString(R.string.app_name));
		doShareToQzone(params);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.REQUEST_QQ_SHARE) {
			if (resultCode == Constants.ACTIVITY_OK) {
				Tencent.handleResultData(data, qZoneShareListener);
			}
		}
	}

	/**
	 * 用异步方式启动分享
	 * 
	 * @param params
	 */
	private void doShareToQzone(final Bundle params) {
		final Activity activity = ShareAc.this;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MobclickAgent.onEvent(ShareAc.this, action);
				// HomeAc.mTencent.shareToQzone(activity, params,
				// qZoneShareListener);
				ShitsAc.mTencent.shareToQQ(activity, params, qZoneShareListener);
			}
		}).start();
	}

	IUiListener qZoneShareListener = new IUiListener() {

		@Override
		public void onCancel() {
		}

		@Override
		public void onError(UiError e) {
		}

		@Override
		public void onComplete(Object response) {
		}

	};

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// // TODO Auto-generated method stub
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// finishAc();
	// }
	// return super.onKeyDown(keyCode, event);
	// }

	@Override
	public void onResponse(BaseResponse baseResp) {
		switch (baseResp.errCode) {
		case WBConstants.ErrorCode.ERR_OK:
			Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
			break;
		case WBConstants.ErrorCode.ERR_CANCEL:
			Toast.makeText(this, "取消分享", Toast.LENGTH_LONG).show();
			break;
		case WBConstants.ErrorCode.ERR_FAIL:
			Toast.makeText(this, "分享失败" + baseResp.errMsg, Toast.LENGTH_LONG).show();
			break;
		}
	}

	/**
	 * 
	 * @Title: showShare
	 * @Description: 分享
	 * @author sunsy
	 * @param silent
	 * @param platform
	 * @param captureView
	 * @return void 返回类型
	 */
	// private void showShare(boolean silent, String platform, boolean
	// captureView) {
	// final OnekeyShare oks = new OnekeyShare();
	// // oks.setNotification(R.drawable.icon,
	// // this.getString(R.string.app_name));
	// // oks.setAddress("12345678901");
	// // oks.setTitle(title);
	// // oks.setTitleUrl(url);
	// // oks.setText(content);
	// // oks.setUrl(url);
	// oks.setSilent(silent);
	// oks.setPlatform(platform);
	// oks.setImagePath(TEST_IMAGE);
	// // 去自定义不同平台的字段内容
	// // oks.setShareContentCustomizeCallback(new
	// // ShareContentCustomizeDemo());
	// oks.show(this);
	// }

	// /**
	// *
	// * @Title: initImagePath
	// * @Description: 初始化icon的本地路径
	// * @author sunsy
	// * @return void 返回类型
	// */
	// private void initImagePath() {
	// try {
	// String cachePath = cn.sharesdk.framework.utils.R.getCachePath(this,
	// null);
	// TEST_IMAGE = cachePath + FILE_NAME;
	// File file = new File(TEST_IMAGE);
	// if (!file.exists()) {
	// file.createNewFile();
	// Bitmap pic = BitmapFactory.decodeResource(getResources(),
	// R.drawable.ic_launcher);
	// FileOutputStream fos = new FileOutputStream(file);
	// pic.compress(CompressFormat.JPEG, 100, fos);
	// fos.flush();
	// fos.close();
	// }
	// } catch (Throwable t) {
	// t.printStackTrace();
	// TEST_IMAGE = null;
	// }
	// }
}
