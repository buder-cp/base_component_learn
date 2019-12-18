package com.example.lib_multi_ly;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UserModelId {

    public static final int LAYOUT_ID_DIVIDER = 0x00;
    public static final int LAYOUT_ID_USER_SINGLE = 0x01;
    public static final int LAYOUT_ID_USER_MULTI = 0X02;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            LAYOUT_ID_USER_SINGLE,
            LAYOUT_ID_USER_MULTI,
            LAYOUT_ID_DIVIDER,
    })
    public @interface GAME_MODEL_ID {
    }
}
