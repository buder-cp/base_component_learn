package com.example.iqtest.readFile;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class readFileContent {

    private static final String TAG = "readContent";
    private static HashMap<String, String> mDeviceInfoMap = new HashMap<String, String>();
    private static final String DEVICE_CHIP = "hwserial";
    private static final String DEVICE_MODEL = "model";


//    private static void getDeviceInfo() {
//        String mFilePath = Environment.getDataDirectory().getParentFile() + "tmp/tvinfo";
//        Log.d(TAG, "Get data from file=", mFilePath);
//        if (fileIsExists(mFilePath)) {
//            readFile(mFilePath);
//        } else {
//            Log.e(TAG, "tvinfo file not exist.");
//        }
//    }

    public static void readFile(String filePath) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            String aLine;
            while ((aLine = in.readLine()) != null && aLine.contains("=")) {
                if (aLine.contains(DEVICE_CHIP)) {
                    String[] split = aLine.split("=");
                    mDeviceInfoMap.put(DEVICE_CHIP, split[1]);
                }

                if (aLine.contains(DEVICE_MODEL)) {
                    String[] split = aLine.split("=");
                    mDeviceInfoMap.put(DEVICE_MODEL, split[1]);
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    private static boolean fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
