package com.example.timeconsumingprinter;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Printer;

import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;

import java.util.concurrent.ConcurrentHashMap;


public class DemoApplication extends Application {

    private long startTime = 0;
    private static ConcurrentHashMap<Message, String> sMsgDetail = new ConcurrentHashMap<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //获取耗时方案一
        methodOne();
        //获取耗时方案二
        methodTwo();
    }

    /**
     * 方案二：可以打印耗时消息以及耗时时间
     */
    private void methodTwo() {
        final long[] startTime = {0};
        //hook sendMessageAtTime，具体msg消息是谁
        DexposedBridge.findAndHookMethod(Handler.class, "sendMessageAtTime", Message.class, long.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                sMsgDetail.put((Message) param.args[0], Log.getStackTraceString(new Throwable()).replace("java.lang.Throwable", ""));
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });

        //hook dispatchMessage，打印耗时时间
        DexposedBridge.findAndHookMethod(Handler.class, "dispatchMessage", Message.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                startTime[0] = System.currentTimeMillis();
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                long costTime = System.currentTimeMillis() - startTime[0];
                String stackMessage = null;
                //时间阈值可以自己定义，这里是100
                if (costTime > 100) {
                    stackMessage = sMsgDetail.get(param.args[0]);
                    Log.i("buder", costTime + "***********");
                    Log.e("buder", stackMessage);
                }
            }
        });
    }

    /**
     * 方案一：只能打印耗时消息，无法知道具体是哪个消息耗时
     */
    private void methodOne() {
        outputMainLooper();
    }

    private void outputMainLooper() {
        Looper.getMainLooper().setMessageLogging(new Printer() {
            @Override
            public void println(String x) {
                if (x.startsWith(">>>>>")) {
                    startTime = System.currentTimeMillis();
                } else if (x.startsWith("<<<<<")) {
                    long end = System.currentTimeMillis();
                    //时间阈值可以自己定义，这里是100
                    if ((end - startTime) > 100) {
                        Log.i("buder mainLoop ------ ：", (end - startTime)+ " ");
                    }
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
