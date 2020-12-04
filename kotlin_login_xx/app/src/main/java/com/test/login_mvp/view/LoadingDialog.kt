package com.test.login_mvp.view

import android.app.Dialog
import android.content.Context
import com.test.login_mvp.R

object LoadingDialog {
    private var dialog: Dialog? = null

    fun show(context: Context){
        cancle()
        dialog = Dialog(context)
        dialog.also {
            it?.setContentView(R.layout.dialog_loading)
            it?.setCancelable(false)
            it?.setCanceledOnTouchOutside(false)
            it
        }?.show()
        dialog?.setCancelable(false)
        dialog?.run {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            this
        }?.show()

    }

    fun cancle(){
        dialog?.dismiss()
    }
}