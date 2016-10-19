package com.yoga.china.image.util;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.ssy.movehouse.R;

/**
 * 获取图片的辅助类
 * 
 * @author sunsy 2016年3月9日
 * 
 */
public class GetImageUtil {

	/**
	 * 单例
	 */
	private String path;
	private Activity activity;
	private String local;

	/**
	 * 初始化
	 * 
	 * @author sunsy 2016年3月12日
	 * @param context
	 */
	public GetImageUtil(Activity activity) {
		this.activity = activity;
		local = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + activity.getResources().getString(R.string.app_name)
				+ "/tempImage/";
		File file = new File(local);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 
	 * @author sunsy 2016年3月12日
	 */
	private void first() {
		path = local + System.currentTimeMillis() + ".jpg";
		System.out.println("path==" + path);
	}

	public String getPath() {
		return path;
	}

	public Uri getPathUri() {
		return Uri.parse("file://" + path);
	}

	/**
	 * 获得图片
	 * 
	 * @author sunsy 2016年3月12日
	 * @param code
	 */
	public void getPhoto(int code) {
		switch (code) {
		case Config.GOTO_CAMERA:
			first();
			gotoCamera();
			break;
		case Config.GOTO_ALBUM:
			goToGallery();
			break;
		case Config.GOTO_CROP:
			cropImageUri();
			break;
		}
	}

	/**
	 * 去相机
	 * 
	 * @author sunsy 2016年3月12日
	 */
	private void gotoCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + path));
		activity.startActivityForResult(intent, Config.GOTO_CAMERA);
	}

	/**
	 * 去相册
	 * 
	 * @author sunsy 2016年3月12日
	 */
	private void goToGallery() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, Config.GOTO_ALBUM);
	}

	/**
	 * 处理从相机获得的图片
	 * 
	 * @author sunsy 2016年3月12日
	 * @return
	 */
	public String saveBitmapFromCamera() {
		String temp = path;
		path = SaveImageUtil.getInstance().savaBitmap2SDCard(activity, Uri.parse("file://" + path), local);
		/* 重新保存之后，删除源文件 */
		File file = new File(temp);
		if (file.exists()) {
			file.delete();
		}
		return path;
	}

	/**
	 * 从相册获得的图片要保存到SD卡
	 * 
	 * @author sunsy 2016年3月12日
	 * @param intent
	 * @return
	 */
	public String saveBitmapFromGallery(Intent intent) {
		Uri originalUri = intent.getData();
		try {
			path = SaveImageUtil.getInstance().savaBitmap2SDCard(activity, originalUri, local);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 裁剪图片
	 * 
	 * @author Michael.Zhang 2013-11-1 上午11:13:32
	 */
	private void cropImageUri() {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(Uri.parse("file://" + path), "image/*");
		intent.putExtra("crop", true);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", false);
		intent.putExtra("aspectX", 300);
		intent.putExtra("aspectY", 300);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + path));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		activity.startActivityForResult(intent, Config.GOTO_CROP);
	}

	public class Config {
		public static final int GOTO_CAMERA = 0X10000;
		public static final int GOTO_ALBUM = 0X10086;
		public static final int GOTO_CROP = 0X10010;
	}

}
