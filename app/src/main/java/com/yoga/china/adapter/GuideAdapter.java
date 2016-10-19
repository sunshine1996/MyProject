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

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.zy.imageloader.core.ImageLoader;

import com.bluemobi.xiaobaogao.R;

public class GuideAdapter extends PagerAdapter {
	Integer[] imageUrl;
	Activity ac;

	public GuideAdapter(Activity ac, Integer[] imageUrl) {
		this.ac = ac;
		this.imageUrl = imageUrl;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		View view = LayoutInflater.from(ac).inflate(R.layout.ac_guide, null);
		final ImageView iv = (ImageView) view.findViewById(R.id.iv_guide);
		// ImageLoader.getInstance().displayImage("drawable://" +
		// imageUrl[position], iv);
		iv.setImageResource(imageUrl[position]);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (position >= getCount() - 1) {
					ac.finish();
				}
			}
		});
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return null == imageUrl ? 0 : imageUrl.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
