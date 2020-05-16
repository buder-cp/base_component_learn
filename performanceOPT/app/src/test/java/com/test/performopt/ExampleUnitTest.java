package com.test.performopt;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testPhantomReference() throws InterruptedException {
        //虚引用：功能，不会影响到对象的生命周期的，
        // 但是能让程序员知道该对象什么时候被 回收了
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object phantomObject = new Object();
        PhantomReference phantomReference = new PhantomReference(phantomObject, referenceQueue);
        phantomObject = null;
        System.out.println("GC回收前");
        System.out.println("phantomReference：" + phantomReference.get());//虚引用无法获取到对象，返回null
        System.out.println("referenceQueue：" + referenceQueue.poll());//引用回收队列中还未回收掉，返回null
        System.gc();
        Thread.sleep(2000);
        System.out.println("GC回收后");
        System.out.println("phantomReference：" + phantomReference.get());//虚引用无法获取到对象，返回null
        //referenceQueue：最后能够拿到一个被回收掉的对象的信息，而不是原来的对象，原来的对象已经被回收掉了
        System.out.println("referenceQueue：" + referenceQueue.poll());
    }

    @Test
    public void testWeakReference() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object weakObject = new Object();
        //弱引用，可以将这个引用记录到引用队列 referenceQueue 中去
        WeakReference weakReference = new WeakReference(weakObject, referenceQueue);
        System.out.println("GC回收前");
        System.out.println("WeakReference:" + weakReference.get());//弱引用中可以拿到该对象
        System.out.println("referenceQueue:" + referenceQueue.poll());//引用回收队列中没有对象的任何信息

        weakObject = null;
        System.gc();
        Thread.sleep(2000);
        System.out.println("GC回收后");
        //对象被回收掉返回null
        System.out.println("WeakReference:" + weakReference.get());
        //被回收掉对象的信息，在回收队列中referenceQueue 可以查询到
        System.out.println("referenceQueue:" + referenceQueue.poll());
    }


}