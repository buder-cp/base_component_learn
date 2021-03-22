package com.test.aidl_server;

import com.test.aidl_server.Parameter;

interface IOnOperationCompletedListener {

    void onOperationCompleted(in Parameter result);

}
