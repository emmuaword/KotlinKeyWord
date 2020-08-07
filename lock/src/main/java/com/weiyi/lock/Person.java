package com.weiyi.lock;

import android.util.Log;

/**
 * @author TW
 * @date 2020/7/29 17:36
 * @description
 * @mail 2278671454@qq.com
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}