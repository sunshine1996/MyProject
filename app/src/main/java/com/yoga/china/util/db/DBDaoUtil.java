/**
 * Copyright (C) 2015 孙思远 
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

package com.yoga.china.util.db;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库操作类
 * 
 * 
 * @author 孙思远 2015年6月1日 下午5:01:37
 */
public class DBDaoUtil {

	DBHelper helper;
	Context context;
	SQLiteDatabase db;

	public DBDaoUtil(Context context) {
		this(context, 1);
	}

	public DBDaoUtil(Context context, int version) {
		helper = new DBHelper(context, version);
		db = helper.getWritableDatabase();
	}

	/**
	 * 获取城市列表
	 * 
	 * @return ArrayList<String>
	 * 
	 * @author 孙思远 2015年6月2日 上午10:24:15
	 */
	public ArrayList<String> getCity() {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select city_name FROM " + DBHelper.CITY_DB;
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			list.add(cursor.getString(0));
		}
		return list;
	}

	/**
	 * 模糊查询
	 * 
	 * @param s
	 * @return
	 * 
	 * @author 孙思远 2015年8月5日 下午1:22:22
	 */
	public ArrayList<String> getCity(String s) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select city_name FROM " + DBHelper.CITY_DB + " where city_name like '%" + s + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			list.add(cursor.getString(0));
		}
		return list;
	}

	/**
	 * 添加城市
	 * 
	 * @param city
	 * 
	 * @author 孙思远 2015年8月5日 下午1:14:40
	 */
	public void insertCity(String city) {
		String sql = "insert into " + DBHelper.CITY_DB + "(city_name) values(?)";
		db.execSQL(sql, new Object[] { city });
	}

	public void clostDB() {
		db.close();
	}

}
