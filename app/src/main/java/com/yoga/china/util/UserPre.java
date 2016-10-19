package com.yoga.china.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.yoga.china.bean.UserBean;

/**
 * Created by sunsiyuan on 16/5/26.
 */
public class UserPre {

    private static UserPre instance;
    private SharedPreferences reader;
    private SharedPreferences.Editor editor;
    private String mid = "mid";
    private String group = "group";
//    private Context context;

    /**
     * 单例
     *
     * @return
     */
    public static UserPre getInstance() {
        if (instance == null) {
            instance = new UserPre();
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        instance.editor = context.getSharedPreferences(PreContact.USER_NAME, Context.MODE_PRIVATE).edit();
        instance.reader = context.getSharedPreferences(PreContact.USER_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 设置用户
     *
     * @param bean
     */
    public void putUser(UserBean bean) {
        editor.putString(mid, bean.getMid());
        editor.putInt(group, bean.getGroup());
        editor.commit();
    }

    public String getMid() {
        return reader.getString(mid, "");
    }

}
