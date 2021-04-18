package com.test.ppjoke.model;

import android.text.TextUtils;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class TagList extends BaseObservable implements Serializable {

    /**
     * id : 8
     * icon :
     * background :
     * activityIcon :
     * title : 纹身技术哪家强
     * intro : “你怎么纹了一个电风扇？”
     * “那叫四叶草！”
     * feedNum : 1234
     * tagId : 126137
     * enterNum : 79788
     * followNum : 79151
     * hasFollow : false
     */

    public int id;
    public String icon;
    public String background;
    public String activityIcon;
    public String title;
    public String intro;
    public int feedNum;
    public long tagId;
    public int enterNum;
    public int followNum;
    public boolean hasFollow;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof TagList))
            return false;
        TagList newOne = (TagList) obj;
        return id == newOne.id
                && TextUtils.equals(icon, newOne.icon)
                && TextUtils.equals(background, newOne.background)
                && TextUtils.equals(activityIcon, newOne.activityIcon)
                && TextUtils.equals(title, newOne.title)
                && TextUtils.equals(intro, newOne.intro)
                && feedNum == newOne.feedNum
                && tagId == newOne.tagId
                && enterNum == newOne.enterNum
                && followNum == newOne.followNum
                && hasFollow == newOne.hasFollow;
    }

    @Bindable
    public boolean isHasFollow() {
        return hasFollow;
    }

    public void setHasFollow(boolean follow) {
        this.hasFollow = follow;
        notifyPropertyChanged(com.test.ppjoke.BR._all);
    }
}
