package com.yoga.china.activity.start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.bluemobi.view.ClearEditText;
import cn.bluemobi.view.ExtraGridView;
import cn.bluemobi.view.SideBar;
import cn.bluemobi.view.SideBar.OnTouchingLetterChangedListener;
import cn.bluemobi.xbg.ZYActivity;
import cn.bluemobi.xbg.adapter.CityAdapter;
import cn.bluemobi.xbg.adapter.SortAdapter;
import cn.bluemobi.xbg.config.Constants;
import cn.bluemobi.xbg.pop.PopListen;
import cn.bluemobi.xbg.pop.SimpleListPop;
import cn.bluemobi.xbg.util.Tools;
import cn.zy.inject.inter.FindView;
import cn.zy.inject.inter.SetContentView;

import com.bluemobi.xbg.db.util.DBDaoUtil;
import com.bluemobi.xiaobaogao.CharacterParser;
import com.bluemobi.xiaobaogao.PinyinComparator;
import com.bluemobi.xiaobaogao.R;
import com.bluemobi.xiaobaogao.SortModel;

/**
 * 城市选择
 * 
 * 
 * @author 邓靖 2015年6月11日 下午2:08:53
 */
@SetContentView(R.layout.fragment_city)
public class CityAc extends ZYActivity implements OnClickListener, OnItemClickListener, PopListen {

	@FindView
	private TextView tv_title;
	@FindView
	private ListView sortListView;
	@FindView
	private SideBar sideBar;
	@FindView
	private TextView dialog;
	private SortAdapter adapter;
	@FindView
	private ClearEditText clearEditText1;

	View head1, head2;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private TextView tv_location;
	private ExtraGridView gv_city;
	ArrayList<String> date = new ArrayList<String>();
	ArrayList<String> list = new ArrayList<String>();
	DBDaoUtil dbDaoUtil;
	SimpleListPop listPop;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	private String[] unKnow = { "热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbDaoUtil = new DBDaoUtil(this);
		date = dbDaoUtil.getCity();
		listPop = new SimpleListPop(this, 0, list, this, true);
		initHead1();
		initHead2();
		addHeadView();
		initview();
	}

	private void initHead1() {
		head1 = LayoutInflater.from(this).inflate(R.layout.city_head1, null);
		tv_location = (TextView) head1.findViewById(R.id.tv_location);
		tv_location.setText("定位城市：" + Constants.addressStr);
		tv_location.setOnClickListener(this);
	}

	private void initHead2() {
		head2 = LayoutInflater.from(this).inflate(R.layout.city_head2, null);
		gv_city = (ExtraGridView) head2.findViewById(R.id.gv_city);
		CityAdapter cityAdapter = new CityAdapter(this);
		gv_city.setAdapter(cityAdapter);
		gv_city.setOnItemClickListener(this);
	}

	private void addHeadView() {
		sortListView.addHeaderView(head1);
		sortListView.addHeaderView(head2);
	}

	private void initview() {
		tv_title.setText("选择城市");
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position + 2);
				}
				if (s.equals("GPS")) {
					sortListView.setSelection(0);
				} else if (s.equals("热门")) {
					sortListView.setSelection(1);
				}
			}
		});

		// sortListView.setOnItemClickListener();
		SourceDateList = filledData();

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		// 根据输入框输入值的改变z来过滤搜索
		clearEditText1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				// filterData(s.toString());
				if (!Tools.isNull(s.toString())) {
					list.clear();
					list.addAll(dbDaoUtil.getCity(s.toString()));
					listPop.setadapter(list);
					listPop.show(clearEditText1);
				} else {
					if (listPop.isShowing()) {
						listPop.dismiss();
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

	}

	private boolean judgeUnKnow(String args) {
		for (int i = 0; i < unKnow.length; i++) {
			if (args.equals(unKnow[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData() {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.size(); i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date.get(i));

			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i));
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			mSortList.add(sortModel);
		}
		return mSortList;
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_location:
			Intent intent = new Intent();
			intent.putExtra("city", Constants.addressStr);
			finishAc(intent, RESULT_OK);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.putExtra("city", String.valueOf(parent.getAdapter().getItem(position)));
		finishAc(intent, RESULT_OK);
	}

	@Override
	public void dismiss(int position, int tag) {
	}

	@Override
	public void callBack(int position, int tag) {
		Intent intent = new Intent();
		intent.putExtra("city", String.valueOf(list.get(position)));
		finishAc(intent, RESULT_OK);
	}
}
