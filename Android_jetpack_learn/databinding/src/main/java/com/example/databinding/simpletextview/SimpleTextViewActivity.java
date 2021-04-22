package com.example.databinding.simpletextview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.BR;
import com.example.databinding.Book;
import com.example.databinding.R;
import com.example.databinding.databinding.ActivitySimpleTextViewBinding;

/**
 * 演示简单的TextView绑定
 */
public class SimpleTextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySimpleTextViewBinding activitySimpleTextViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_simple_text_view);

        Book book = new Book("Android编程", "buder");
        book.rating = 3;

        //方式一
        activitySimpleTextViewBinding.setBook(book);//将对象传递到layout中

        //方式二
//        activitySimpleTextViewBinding.setVariable(BR.book, book);//将对象传递到layout中的另外一种设置方式
//        activitySimpleTextViewBinding.tvTitle.setText(book.title);
//        activitySimpleTextViewBinding.tvAuthor.setText(book.author);
    }
}
