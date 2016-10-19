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

package com.yoga.china.adapter;

import cn.bluemobi.xbg.MyApplication;

import com.bluemobi.xiaobaogao.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityAdapter extends BaseAdapter {

	private String city[];
	Context context;

	public CityAdapter(Context context) {
		this.context = context;
		city = context.getResources().getStringArray(R.array.hot_city);
	}

	@Override
	public int getCount() {
		return city != null ? city.length : 0;
	}

	@Override
	public Object getItem(int position) {
		return city[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int offset = context.getResources().getDimensionPixelSize(R.dimen.dim150);
		int height = (MyApplication.SCREEN_W - offset) / 3;
		convertView=LayoutInflater.from(context).inflate(R.layout.item_city, null);
		
		TextView textView = (TextView) convertView.findViewById(R.id.tv_city);
		LinearLayout.LayoutParams params =(LinearLayout.LayoutParams) textView.getLayoutParams();
		params.width=height;
		textView.setLayoutParams(params);
		textView.setGravity(Gravity.CENTER);
		textView.setText(city[position]);
		return convertView;
	}

}
