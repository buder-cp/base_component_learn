package com.test.opensourceframework.retrofit;

import android.util.Log;

public class Translation {
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.e("buder", status + "");
        Log.e("buder", content.from);
        Log.e("buder", content.to);
        Log.e("buder", content.vendor);
        Log.e("buder", content.out);
        Log.e("buder", content.errNo + "");
    }
}
