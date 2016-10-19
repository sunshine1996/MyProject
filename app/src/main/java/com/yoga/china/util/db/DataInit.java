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

package com.yoga.china.util.db;

import android.content.Context;

import com.bm.yogainchina.R;

/**
 * 第一次的时候初始化一些必要的数据
 *
 * @author 孙思远 2015年6月2日 下午2:32:03
 */
public class DataInit {

    public static DataInit instance;
    Context mContext;
    DBDaoUtil dbDaoUtil;

    public static DataInit getInstance() {
        if (instance == null) {
            instance = new DataInit();
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @author 孙思远 2015年6月2日 下午2:32:45
     */
    public void init(Context mContext) {
        this.mContext = mContext;
        dbDaoUtil = new DBDaoUtil(mContext);
    }

}
