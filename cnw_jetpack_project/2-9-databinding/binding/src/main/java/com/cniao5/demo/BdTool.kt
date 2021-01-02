package com.cniao5.demo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

//这里BindMethods可以放在任何的类上面，重点在于内部属性声明
@BindingMethods(
    BindingMethod(
        type = AppCompatImageView::class,
        attribute = "image",
        method = "setImageDrawable"
    )
)


object BdTool {

    @JvmStatic
    fun getTitle(type: String): String {
        return "type:$type"
    }

}

//直接静态函数，top level的写法
fun getTitle(type: String): String {
    return "type:$type"
}

//top level 就是静态的，如果放在object内需要@jvmStatic
@BindingAdapter("android:textColor", requireAll = false)
fun getColorId(view: TextView, type: Int) {
    val color = when (type) {
        0 -> R.color.colorAccent
        1 -> R.color.colorPrimaryDark
        2 -> android.R.color.holo_red_dark
        3 -> android.R.color.holo_orange_dark
        else -> R.color.colorPrimary
    }
    view.setTextColor(view.context.resources.getColor(color))
}

@BindingConversion
fun converStr2Color(str: String): Drawable {
    return when (str) {
        "red" -> {
            ColorDrawable(Color.RED)
        }
        "blue" -> ColorDrawable(Color.BLUE)
        else -> {
            ColorDrawable(Color.YELLOW)
        }
    }
}