package com.cniao5.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.cniao5.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //赋值
        binding.apply {

            name = null

            address = "Beijing 海淀"

            obName = ObservableField<String>("原始的obName")

            user = User()//普通user
            fUser = FoUser()//个别属性binding
            oUser = ObUser()

            click = View.OnClickListener {
                address = "菜鸟窝！！"
                user?.name = "普通user改name？"

                fUser?.name?.set("属性变化fUser")//Smart cast to 'FoUser' is impossible 因为对象可null

                oUser?.age = 22
                oUser?.name = "改名啦"
            }

            adapter = BdAdapter()
            info = ItemBean(0, "include 的item")

//            imgRes = getDrawable(R.drawable.ic_launcher_foreground)

        }

    }
}

