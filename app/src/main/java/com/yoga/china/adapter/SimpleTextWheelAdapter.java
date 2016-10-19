/**
 * Copyright (C) 2015 孙思远
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yoga.china.adapter;

import com.yoga.china.view.wheelview.WheelAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SimpleWheelAdapter implements WheelAdapter {

    private List<String> list = new ArrayList<String>();

    public SimpleWheelAdapter(String[] data) {
        list.addAll(Arrays.asList(data));
    }

    public SimpleWheelAdapter(List<String> data) {
        list.addAll(data);
    }

    // public SimpleTextWheelAdapter(ArrayList<String> data) {
    // list.addAll(data);
    // }

    public List<String> getList() {
        return list;
    }

    @Override
    public int getItemsCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public String getItem(int index) {
        return list.get(index);
    }

    @Override
    public int getMaximumLength() {
        return 15;
    }

}
