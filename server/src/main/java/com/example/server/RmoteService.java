package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class RmoteService extends Service{

    private Person mPerson;
    private String mUserName;
    private String mUserAge;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPerson = new Person("buder_mm", "18");
        return new RomteBinder();
    }

    private class RomteBinder extends IRomteAidlInterface.Stub {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getPersonUserName() throws RemoteException {
            mUserName = mPerson.getmUserName();
            return mUserName;
        }

        @Override
        public String getPersonUserAge() throws RemoteException {
            mUserAge = mPerson.getmUserAge();
            return mUserAge;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
