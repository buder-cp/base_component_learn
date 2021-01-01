package com.cniao5.vmdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val vm by ViewModelLazy<VmOne>(VmOne::class,
        { viewModelStore },
        { defaultViewModelProviderFactory })

    val vm1: VmOne by viewModels<VmOne> { defaultViewModelProviderFactory }

    //android view model
    val vmodel2 by viewModels<VmTwo> { VmFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm2 = ViewModelProvider(
            viewModelStore,
            defaultViewModelProviderFactory
        ).get(VmOne::class.java)

//        ViewModelProvider.NewInstanceFactory()
//        ViewModelProvider.AndroidViewModelFactory(application)
//        ViewModelProvider.Factory

        //输出结果
        Log.i("MainActivity", "vm.getNum ${vm.getNum()}")
        Log.i("MainActivity", "vm1.getNum ${vm1.getNum()}")
        Log.i("MainActivity", "vm2.getNum ${vm2.getNum()}")
        Log.i("MainActivity", "vmodel2.getNum ${vmodel2.getNum()}")

        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}

