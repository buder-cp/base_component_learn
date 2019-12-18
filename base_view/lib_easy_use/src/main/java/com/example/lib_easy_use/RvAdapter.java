package com.example.lib_easy_use;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.DataViewHolder> {

    private ArrayList<UserModel> mList;

    public void setData(ArrayList<UserModel> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void insertData(UserModel user, int positon) {
        mList.add(positon, user);
        notifyItemInserted(positon);
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        DataViewHolder holder = new DataViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder viewHolder, int position) {
        UserModel userModel = mList.get(position);
        viewHolder.name.setText(userModel.getName());
        viewHolder.age.setText(userModel.getAge());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            age = itemView.findViewById(R.id.item_age);
        }

    }
}
