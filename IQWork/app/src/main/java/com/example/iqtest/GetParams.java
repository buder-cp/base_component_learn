package com.example.iqtest;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class GetParams {

    public static String getSDPath() {

//        String sdPath = System.getenv("EXTERNAL_STORAGE") + "/tmp/tvinfo";
//        String sdPath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp/tvinfo";
//        String sdPath3 = System.getenv("SECONDARY_STORAGE");

//        Log.e("buder1", fileIsExists(sdPath) + " ");
//        Log.e("buder2", fileIsExists(sdPath2) + " ");
//        Log.e("buder3", sdPath3);

        String sdPath = Environment.getDataDirectory().getParentFile() + "/tmp/tvinfo";
        Log.e("buder1", fileIsExists(sdPath) + " ");
        return "hhe";
    }

    //判断文件是否存在
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
