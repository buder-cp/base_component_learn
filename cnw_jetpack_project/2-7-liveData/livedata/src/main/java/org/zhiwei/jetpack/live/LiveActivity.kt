package org.zhiwei.jetpack.live

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveActivity : AppCompatActivity() {

    //声明变量live data
    val liveAppleData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        //Fragment
        val appleFragment = AppleFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container_live, appleFragment)
            .commit()
        //// TODO 注意 hide 和 show不会改变fragment的生命周期状态 所以用attach detach
        //显示fragment
        btn_create_fg_live.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .attach(appleFragment)
                .commit()
            Log.d("LiveActivity", "onCreate: 显示fg ${appleFragment.isVisible}")
        }
        //隐藏
        btn_destroy_fg_live.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .detach(appleFragment)
                .commit()
            Log.w("LiveActivity", "onCreate: 隐藏fg ${appleFragment.isVisible}")

        }
        //变更livedata的值
        btn_change_live.setOnClickListener {
            liveAppleData.value = "当前liveData的值：${System.currentTimeMillis()}"
        }

        //观察值
        liveAppleData.observe(this, Observer {
            tv_live_data_activity.text = it
            Log.d("LiveActivity", "LiveData在LiveActivity中 $it")
        })
    }

}