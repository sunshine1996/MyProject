package com.yoga.china.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * Created by sunsiyuan on 16/3/9.
 */
public class PreUtil {

    private static PreUtil instance;
    private SharedPreferences reader;
    private SharedPreferences.Editor editor;
//    private Context context;

    /**
     * 单例
     * @return
     */
    public static PreUtil getInstance() {
        if (instance == null) {
            instance = new PreUtil();
        }
        return instance;
    }

    /**
     * 初始化
     * @param context
     */
    public void init(Context context){
        instance.editor=context.getSharedPreferences(PreContact.APP_NAME,Context.MODE_PRIVATE).edit();
        instance.reader=context.getSharedPreferences(PreContact.APP_NAME,Context.MODE_PRIVATE);
    }

    /**
     * 设置string内容
     * @param key
     * @param value
     */
    public void putString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 获得string数据
     * @param key
     * @return
     */
    public String getString(String key){
        return reader.getString(key,"");
    }

    /**
     * 设置boolean数据
     * @param key
     * @param value
     */
    public void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 获取boolean 数据
     * @param key
     */
    public boolean getBoolean(String key){
        return reader.getBoolean(key,false);
    }

}
