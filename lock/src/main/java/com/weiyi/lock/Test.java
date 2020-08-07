package com.weiyi.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author TW
 * @date 2020/7/30 10:38
 * @description
 * @mail 2278671454@qq.com
 */
public class Test {
    public static void main(String[] args) {
//        syTest();
//        lockTest();
        rwLockTest();
    }

    private static void syTest() {
        final Person person = new Person("张三", 23);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (person) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            System.out.print(person.getName() + "A" + i + "\n");
                            if (i == 5) {
                                person.wait();// 此处将A 暂停
                            }
                        }
                    } catch (Exception e) {
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
                        for (int i = 0; i < 10; i++) {
                            Thread.sleep(1000);
                            System.out.print(person.getName() + "B" + i + "\n");
                            if (i == 5) {
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

    private static void lockTest() {
        final Outputter1 output = new Outputter1();
        new Thread() {
            public void run() {
                output.output("zhangsan\n");
            }
        }.start();
        new Thread() {
            public void run() {
                output.output("lisi\n");
            }
        }.start();
    }

    private static void rwLockTest() {
        final Data data = new Data();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }

}


class Outputter1 {
    private Lock lock = new ReentrantLock();// 锁对象

    public void output(String name) {
        lock.lock();// 得到锁
        try {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}

class Data {
    private int data;// 共享数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    //    public synchronized void set(int data) {
    public void set(int data) {
        try {
            rwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    //    public synchronized void get() {
    public void get() {
        try {
            rwl.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }
}

