package com.example.lib_multi_ly.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lib_multi_ly.model.UserBaseModel;

public abstract class UserBaseItemViewHolder extends RecyclerView.ViewHolder {

    public UserBaseItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void setData(UserBaseModel model, int position);

}
