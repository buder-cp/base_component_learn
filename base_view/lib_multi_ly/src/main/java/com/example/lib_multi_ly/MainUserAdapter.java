package com.example.lib_multi_ly;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib_multi_ly.holder.UserBaseItemViewHolder;
import com.example.lib_multi_ly.holder.UserDividerViewHolder;
import com.example.lib_multi_ly.holder.UserMultiViewHodler;
import com.example.lib_multi_ly.holder.UserSingleViewHodler;
import com.example.lib_multi_ly.model.UserBaseModel;

import java.util.ArrayList;

public class MainUserAdapter extends RecyclerView.Adapter<UserBaseItemViewHolder> {

    private ArrayList<UserBaseModel> mItems = new ArrayList<>();

    public void setDatas(ArrayList<UserBaseModel> gameItems) {
        this.mItems = gameItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutId();
    }

    @NonNull
    @Override
    public UserBaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserBaseItemViewHolder itemViewHolder = null;

        switch (viewType) {
            case UserModelId.LAYOUT_ID_USER_SINGLE:
                View singleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_layout, parent, false);
                itemViewHolder = new UserSingleViewHodler(singleView);
                break;
            case UserModelId.LAYOUT_ID_USER_MULTI:
                View multiView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_multi_layout, parent, false);
                itemViewHolder = new UserMultiViewHodler(multiView);
                break;
            case UserModelId.LAYOUT_ID_DIVIDER:
                View dividerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_divider_layout, parent, false);
                itemViewHolder = new UserDividerViewHolder(dividerView);
                break;
            default:
                break;
        }

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserBaseItemViewHolder holder, int position) {
        holder.setData(mItems.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
