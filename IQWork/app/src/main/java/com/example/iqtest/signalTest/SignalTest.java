package com.example.iqtest.signalTest;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import com.changhong.tvos.atv.IATVPlayer;
import com.changhong.tvos.common.ISourceManager;
import com.changhong.tvos.common.ITVPlayer;
import com.changhong.tvos.common.TVManager;
import com.changhong.tvos.common.exception.DTVNotSupportException;
import com.changhong.tvos.common.exception.SourceNotFoundException;
import com.changhong.tvos.common.exception.TVManagerNotInitException;
import com.changhong.tvos.model.EnumAudioSystem;
import com.changhong.tvos.model.EnumInputSource;
import com.changhong.tvos.model.SourceInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SignalTest {

    private static String TAG = "TAGsignal";

    //新获取信源列表
    public static List<SourceInfo> getSignalNew(Context context) {
        List<SourceInfo> list = null;
        try {
            ITVPlayer tvm = TVManager.getInstance(context).getTVPlayer();
            list = tvm.getSourceInfoList();
            for (SourceInfo info : list) {
                Log.d(TAG, info.mSourceName + " " + info.mSourceEnum + " " + info.mPluged);
            }
        } catch (TVManagerNotInitException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取源列表
    public static void getSignal(Context context) {
        try {
            ISourceManager sourceManager = TVManager.getInstance(context).getSourceManager();
            List<EnumInputSource> inputSourceList = sourceManager.getInputSourceList();
            Log.d(TAG, inputSourceList.size() + " ");
            for (EnumInputSource inputSource : inputSourceList) {
                Log.e(TAG, inputSource.name() + " ");
            }
        } catch (TVManagerNotInitException e) {
            e.printStackTrace();
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getSignalIsOk(Context context) {
        try {
            IATVPlayer atvPlayer = TVManager.getInstance(context).getATVPlayer();
            ISourceManager sourceManager = TVManager.getInstance(context).getSourceManager();
            List<EnumInputSource> inputSourceList = sourceManager.getInputSourceList();
            for (EnumInputSource inputSource : inputSourceList) {
                boolean sourcePlugOn = atvPlayer.isSourcePlugOn(inputSource);
                if (sourcePlugOn) {
                    Log.e(TAG, inputSource.name());
                }
            }

        } catch (TVManagerNotInitException e) {
            e.printStackTrace();
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    //切换不同信号源
//    public static void switchSignal(Context context) {
//        try {
//            ISourceManager sourceManager = TVManager.getInstance(context).getSourceManager();
//            List<EnumInputSource> inputSourceList = sourceManager.getInputSourceList();
//            ITVPlayer itp = TVManager.getInstance(context).getTVPlayer();
//            itp.startInputSource(inputSourceList.get(0), -1);
//        } catch (TVManagerNotInitException e) {
//            e.printStackTrace();
//        } catch (SourceNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


//    public static boolean isPlugin(Context context, EnumInputSource inputSource) {
//        IATVPlayer player = TVManager.getInstance(context).getATVPlayer();
//        if (inputSource.equals(EnumInputSource.E_INPUT_SOURCE_DTV)) {
//            int count = 0;
//            try {
//                count = player.getDTVChannelList().size();
//            } catch (DTVNotSupportException e) {
//                e.printStackTrace();
//            }
//            return count > 0;
//        } else if (inputSource.equals(EnumInputSource.E_INPUT_SOURCE_ATV)) {
//            String mAtvFileName = "/data/data/com.changhong.tvap.atv.activity/files/1.jpeg";
//            File file = new File(mAtvFileName);
//            if (!file.exists()) return false;
//            return true;
//        } else if (inputSource.equals(EnumInputSource.E_INPUT_SOURCE_OTHER)) {
//
//            return true;
//        } else if (inputSource.equals(EnumInputSource.E_INPUT_SOURCE_MEDIAPLAYER)) {
//            boolean mUsbPlug = findUsbSource(context);
//            return mUsbPlug;
//        } else if (inputSource.equals(EnumInputSource.E_INPUT_SOURCE_NETPLAYER)) {
//            //判断网络是否连通 联通返回true 不联通返回false
//        } else {
//            boolean isPlug = player.isSourcePlugOn(inputSource);
//            return isPlug;
//        }
//    }

//    private static boolean findUsbSource(Context context) {
//        ArrayList<StorageVolume> deviceLabel = new ArrayList<StorageVolume>();
//        StorageVolume[] storageLabel;
//        StorageManager storageManager = (StorageManager) context.getApplicationContext().getSystemService(Context.STORAGE_SERVICE);
//        storageLabel = storageManager.getVolumeList();
////        ChLogger.print(true, TAG + "usb length ==" + storageLabel.length);
//        for (int i = 0; i < storageLabel.length; i++) {
//            StorageVolume mStorageVolume = storageLabel[i];
//            if (mStorageVolume.getState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
//                deviceLabel.add(mStorageVolume);
//            }
//        }
//        StorageVolume mPoint = null;
//        String usbDir = "/mnt/usb";
//        if (mSVersion.contains("ZLS73Gi")) {
//            usbDir = "/storage/sdcard0";
//        }
//        for (StorageVolume point : deviceLabel) {
//            if (point.getPath().contains(usbDir)) {
////                ChLogger.print(true, TAG + "is usb source");
//            } else {
//                mPoint = point;
//                break;
//            }
//        }
//        if (mPoint != null) {
//            deviceLabel.remove(mPoint);
//        }
//        return deviceLabel.size() > 0;
//    }

//    private static boolean findChangeSource(Context context) {
//        try {
//            IATVPlayer player = TVManager.getInstance(context).getATVPlayer();
//            player.registerStatusListener(new ITVPlayer.IPlayerStatusListener() {
//
//                @Override
//                public void onSignalLock() {
//
//                }
//
//                @Override
//                public void onSignalLost() {
//
//                }
//
//                @Override
//                public void onSignalUnsupported() {
//
//                }
//
//                @Override
//                public void onSignalBlocked() {
//
//                }
//
//                @Override
//                public void onChannelInfoChange(EnumAudioSystem enumAudioSystem) {
//
//                }
//
//                @Override
//                public void onSourcePlugOnOff(boolean b, EnumInputSource enumInputSource) {
//                }
//            }
//        } catch (TVManagerNotInitException e) {
//            e.printStackTrace();
//        }
//    }


}
