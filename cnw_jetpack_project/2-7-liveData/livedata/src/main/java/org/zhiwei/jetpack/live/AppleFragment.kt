package org.zhiwei.jetpack.live

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import kotlinx.android.synthetic.main.fg_apple.*

class AppleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AppleFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fg_apple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //在inActive状态下，是不会感知数据的，但是一旦resume，就会得到最新的数据
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as LiveActivity).apply {
            liveAppleData.observe(viewLifecycleOwner, Observer {
                tv_live_apple.text = it
                Log.i("AppleFragment", "LiveData在AppleFragment中 $it")
            })

            //map换后的数值：
            val liveMapApple = liveAppleData.map {
                Log.d("AppleFragment", "LiveData在AppleFragment中 map $it")
                "it mapped it ${it.takeLast(4)}"
            }

            liveMapApple.observe(viewLifecycleOwner, Observer {
                val s = "mapped 后的值: $it"
                tv_mapped_live_apple.setText(s)
                Log.w("AppleFragment", "LiveData在AppleFragment中 map后的数据 $it")
            })

            //中介者MediatorLiveData使用演示：
            mediatorLive.observe(viewLifecycleOwner, Observer {
                //如果在inactive状态下，one two都变化了，它resume后只接受 代码顺序 最新添加的 source 的最后的值
                val s = "mediator it $it"
                tv_media_live_apple.setText(s)
                Log.w("AppleFragment", "AppleFragment中 mediatorLive ---> $it")
            })


            //switch map 结合mediator，通过条件，控制选择数据源,这里模拟的是，it的数字奇偶，控制最终输出
            val swLive = mediatorLive.switchMap {
                if (it.second.takeLast(1).toInt() % 2 == 0) liveOne else liveTwo
            }
            //UI可以看出，不论是one，还是 two，改变的话，只有满足条件，才会生效。
            swLive.observe(viewLifecycleOwner, Observer {
                tv_switch_live_apple.text = it
                Log.w("AppleFragment", "AppleFragment中 switchMap ---> $it")
            })

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("AppleFragment", "onPause")
    }


    override fun onPause() {
        super.onPause()
        Log.d("AppleFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AppleFragment", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AppleFragment", "onDestroy")
    }
}