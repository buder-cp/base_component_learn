package com.test.concurrency_tools_practice;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LeakApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        BlockCanary.install(this, new BlockCanaryContext() {
            @Override
            public String getQualifier() {
                return null;
            }

            @Override
            public String getUid() {
                return null;
            }

            @Override
            public String getNetworkType() {
                return null;
            }

            @Override
            public int getConfigDuration() {
                return 0;
            }

            @Override
            public int getConfigBlockThreshold() {
                return 0;
            }

            @Override
            public boolean isNeedDisplay() {
                return false;
            }

            @Override
            public String getLogPath() {
                return null;
            }

            @Override
            public boolean zipLogFile(File[] src, File dest) {
                return false;
            }

            @Override
            public void uploadLogFile(File zippedFile) {

            }
        }).start();
    }
}
