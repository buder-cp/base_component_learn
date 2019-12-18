package com.example.lib_multi_ly.model;

import com.example.lib_multi_ly.UserModelId;

public class UserSingleModel extends UserBaseModel {
    @Override
    public int getLayoutId() {
        return UserModelId.LAYOUT_ID_USER_SINGLE;
    }
}
