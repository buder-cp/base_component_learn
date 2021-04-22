package com.example.databinding.bindingadapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.databinding.R;
import com.squareup.picasso.Picasso;

public class ImageViewBindingAdapter {
    private static String TAG = "ImageViewBindingAdapter";

    /**
     * 加载网络图片
     */
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
        } else {
            imageView.setBackgroundColor(Color.DKGRAY);
        }
    }

    /**
     * 加载资源文件中的图片
     */
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, int imageResource) {
        imageView.setImageResource(imageResource);
    }

    /**
     * 加载网络图片，多个参数的情况
     */
    @BindingAdapter(value = {"image", "defaultImageResource"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, int imageResource) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
        } else {
            imageView.setImageResource(imageResource);
        }
    }

    /**
     * 演示旧参数，新参数
     */
    @BindingAdapter("padding")
    public static void setPadding(View view, int oldPadding, int newPadding) {
        Log.e(TAG, "oldPadding:" + oldPadding + " newPadding:" + newPadding);
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, newPadding, newPadding, newPadding);
        }
    }
}
