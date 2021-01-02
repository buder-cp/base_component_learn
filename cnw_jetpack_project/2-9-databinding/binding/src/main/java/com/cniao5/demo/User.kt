package com.cniao5.demo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt


//1、继承BaseObservable的binding数据
class ObUser : BaseObservable() {

    var age = 30

    var name = ""
        set(value) {
            notifyPropertyChanged(BR.name)
            field = value + "<fu>"
        }
        @Bindable
        get() {
            return "$field 菲尔Name"
        }

    var desc = "这是field的User,菲尔"
        set(value) {
            field = value + "<fu>"
            notifyPropertyChanged(BR.desc)
        }
        @Bindable
        get() {
            return "$field 菲尔Desc"
        }
    var str = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.str)
        }
        @Bindable
        get() = "age=${age},name=${name},desc=${field}"
}

//个别属性的binding
class FoUser {
    var age = ObservableInt(20)
    var name = ObservableField("欧泊Name")
    var desc = ObservableField("这是BaseOb的User,欧泊")
    var str = "age=${age.get()},name=${name.get()},desc=${desc.get()}"
}

class User {
    var age = 70
    var name = "七十老牛"
    var desc = "哈哈哈哈，七十的老牛"
}