package com.yoga.china.bean;

import java.io.Serializable;

/**
 * Created by sunsiyuan on 16/5/26.
 */
public class Album implements Serializable {


    /**
     * create_date : 2016-05-26 16:11:20
     * photo_num : 0
     * user_id : 1
     * aid : 8
     * album_name :
     */

    private String create_date;
    private int photo_num;
    private int user_id;
    private int aid;
    private String album_name;

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getPhoto_num() {
        return photo_num;
    }

    public void setPhoto_num(int photo_num) {
        this.photo_num = photo_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
