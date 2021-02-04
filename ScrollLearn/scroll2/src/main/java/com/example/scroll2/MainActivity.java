package com.example.scroll2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * https://www.jianshu.com/p/c5f1867e7df1
 *
 * https://github.com/bluesky466/NestedScrollDemo/tree/sdk22
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View item = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.recyclerview_item, parent, false);
                return new Holder(item);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder h = (Holder) holder;
                h.textView.setText("item " + position);
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class Holder extends RecyclerView.ViewHolder {
                TextView textView;

                public Holder(View itemView) {
                    super(itemView);
                    textView = (TextView) itemView.findViewById(R.id.text);
                }
            }
        });
    }
}