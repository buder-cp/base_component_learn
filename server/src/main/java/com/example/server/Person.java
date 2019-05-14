package com.example.server;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

    private String mUserName;
    private String mUserAge;

    public Person(String userName, String userAge) {
        mUserName = userName;
        mUserAge = userAge;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserAge() {
        return mUserAge;
    }

    public void setmUserAge(String mUserAge) {
        this.mUserAge = mUserAge;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // 这里的读的顺序必须与writeToParcel(Parcel dest, int flags)方法中写的顺序一致
    // 否则数据会有差错
    public Person(Parcel in) {
        mUserName = in.readString();
        mUserAge = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        // 从Parcel中读取数据
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //作用：把值写入Parcel中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserAge);
        dest.writeString(mUserAge);
    }
}
