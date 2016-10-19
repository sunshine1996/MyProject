package com.yoga.china.util.city;

import java.util.Comparator;

/**
 * 
 * @author
 * 
 */
public class PinyinComparator implements Comparator<SortModel> {

	public int compare(SortModel o1, SortModel o2) {
		if (o1.getSortLetters().equals("GPS") || o2.getSortLetters().equals("热门")) {
			return -1;
		} else if (o1.getSortLetters().equals("热门") || o2.getSortLetters().equals("GPS")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
