package com.test.aidl_server;

import com.test.aidl_server.Parameter;
import com.test.aidl_server.IOnOperationCompletedListener;

interface IOperationManager {

   Parameter operation(in Parameter parameter1 , in Parameter parameter2);

   void registerListener(in IOnOperationCompletedListener listener);

   void unregisterListener(in IOnOperationCompletedListener listener);

}
