package com.cniao5.vmdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 *  ViewModel的创建和使用
 */
class VmOne : ViewModel() {

    init {
        Log.i("VmOne", "VmOne创建")
    }

    internal fun getNum(): String {
        return "￥ vmOne ￥ ${System.currentTimeMillis()}"
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("VmOne", "VmOne 销毁 onClear")
    }
}


class VmTwo(application: Application) : AndroidViewModel(application) {

    init {
        Log.i("VmTwo", "VmTwo 创建")
    }

    internal fun getNum(): String {
        return "￥ VmTwo ￥ ${System.currentTimeMillis()}"
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("VmTwo", "VmTwo 销毁 onClear")
    }
}