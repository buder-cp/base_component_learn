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
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as LiveActivity).apply {
            liveAppleData.observe(viewLifecycleOwner, Observer {
                tv_live_apple.text = it
                Log.i("AppleFragment", "LiveData在AppleFragment中 $it")
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