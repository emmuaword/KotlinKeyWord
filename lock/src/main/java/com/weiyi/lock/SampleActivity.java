package com.weiyi.lock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
//        test();
    }
    private void test(){
        final Person person = new Person("张三", 23);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (person) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            Log.e(person.getName(), "我是A" + i);
                            if (i == 3) {
                                person.wait();// 此处将A 暂停
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (person) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            Log.e(person.getName(), "我是B--" + i);
                            if (i == 3) {
                                person.notify();//  此处重新激活A
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "Thread2");
        t1.start();
        t2.start();
    }
}
