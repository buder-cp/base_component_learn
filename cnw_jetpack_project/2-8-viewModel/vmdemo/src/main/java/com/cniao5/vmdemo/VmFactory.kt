package com.cniao5.vmdemo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VmFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VmOne::class.java)) {
            return VmOne() as T
        } else if (modelClass.isAssignableFrom(VmTwo::class.java)) {
            return VmTwo(application) as T
        } else {
            throw ClassNotFoundException("class $modelClass 没有注册到工厂类这个viewModel啊")
        }
    }
}

class VmFc2 : ViewModelProvider.NewInstanceFactory() {

}