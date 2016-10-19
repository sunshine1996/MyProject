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
import java.util.HashMap;

public class ValueMap<K, V> extends HashMap<K, V> {

	HashMap<K, ArrayList<V>> map = new HashMap<K, ArrayList<V>>();

	@Override
	public V put(K key, V value) {
		ArrayList<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>();
		}
		list.add(value);
		map.put(key, list);
		return super.put(key, value);
	}

	public HashMap<K, ArrayList<V>> getMap() {
		return map;
	}

	// /**
	// * 去重
	// *
	// * @param list
	// * @return
	// *
	// * @author 孙思远 2015年6月2日 下午5:36:54
	// */
	// private ArrayList<V> getList(ArrayList<V> list) {
	// ArrayList<V> tempList = new ArrayList<V>();
	// for (V i : list) {
	// if (!tempList.contains(i)) {
	// tempList.add(i);
	// }
	// }
	// list.clear();
	// list.addAll(tempList);
	// return list;
	// }

}
