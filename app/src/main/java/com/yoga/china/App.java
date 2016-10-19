package com.yoga.china;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bm.yogainchina.R;
import com.yoga.china.util.AppContact;
import com.yoga.china.util.PreUtil;
import com.yoga.china.util.Tools;

import java.util.ArrayList;

import cn.zy.imageloader.core.DisplayImageOptions;
import cn.zy.imageloader.core.ImageLoader;
import cn.zy.imageloader.core.ImageLoaderConfiguration;
import cn.zy.imageloader.core.assist.QueueProcessingType;
import cn.zy.imageloader.core.display.FadeInBitmapDisplayer;


/**
 * Created by sunsiyuan on 16/3/9.
 */
public class App extends Application {

    /*单例*/
    private static App instance;
    private ArrayList<Activity> activities=new ArrayList();
    private LocationClient mLocationClient;
    /**
     * 获取单例
     *
     * @return
     */
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        initImageLoader();
        PreUtil.getInstance().init(this);
        //初始化百度定位
        initLocation();
    }

    /**
     * 初始化百度定位
     */
    private void initLocation(){
        mLocationClient=new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new MyLocationListener()); // 注册监听函数
        mLocationClient.start();
    }

    /**
     * 定位监听
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // Receive Location
            if (AppContact.city.length() > 0) {
                mLocationClient.stop();
            }
            AppContact.LATITUDE = location.getLatitude();
            AppContact.LONGITUDE = location.getLongitude();
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                //showToast("定位失败");
                Tools.showToast(getApplicationContext(),getResources().getString(R.string.geolocation_failure));
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                Tools.showToast(getApplicationContext(),getResources().getString(R.string.check_network));
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                Tools.showToast(getApplicationContext(),getResources().getString(R.string.check_system_geolocation_service));
            }
        }
    }

    /**
     * 添加
     * @param ac
     */
    public void addActivity(Activity ac){
        if(!activities.contains(ac)) {
            activities.add(ac);
        }
    }

    /**
     * 退出应用
     * @param code
     */
    public void exit(int code){
        for (Activity activity:activities) {
            activity.finish();
        }
        System.exit(code);
    }

    /**
     * 初始化imageloader
     */
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取默认的图片参数
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_launcher).
                bitmapConfig(Bitmap.Config.RGB_565).
                showImageOnLoading(R.mipmap.ic_launcher).
                showImageOnFail(R.mipmap.ic_launcher).
                cacheOnDisk(true).
                displayer(new FadeInBitmapDisplayer(300)).build();
        return options;
    }

}
