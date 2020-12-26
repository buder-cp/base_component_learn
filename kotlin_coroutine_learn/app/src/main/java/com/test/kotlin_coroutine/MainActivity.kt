package com.test.kotlin_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var image1: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image1 = findViewById(R.id.image1)

        CoroutineScope(Dispatchers.Main).launch {
            var bitmap = withContext(Dispatchers.IO) {
                //异步获取图片后，设置到image1上
            }
//            image1?.setImageBitmap(bitmap)


        }

    }
}