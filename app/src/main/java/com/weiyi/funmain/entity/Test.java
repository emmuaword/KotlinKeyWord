package com.weiyi.funmain.entity;

import android.util.Log;

/**
 * @author TW
 * @date 2020/6/1 15:29
 * @description
 * @mail 2278671454@qq.com
 */
public class Test {

    private void setListener() {
        TestBean testBean = new TestBean();
        testBean.setListener(data -> {
            Log.i("Test", data);
        });
        testBean.setListener(data -> {

        });
    }
}
