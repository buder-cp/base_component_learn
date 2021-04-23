package com.example.paging.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paging.R;
import com.example.paging.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    /**
     * 用于计算列表中两个非空项之间的差异的回调。
     * <p>
     * 之前数据更新了，需要通过notifyDataSetChanged()通知整个RecyclerView，效率不高
     * 使用DiffUtil只会更新需要更新的Item，不需要刷新整个RecyclerView，并且可以在Item删除的时候加上动画效果
     */
    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        /**
         * 当DiffUtil想要检测两个对象是否代表同一个Item时，调用该方法进行判断
         * */
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id.equals(newItem.id);
        }

        /**
         * 当DiffUtil想要检测两个Item是否有一样的数据时，调用该方法进行判断
         * 内容如果更新了，展示给用户看的东西可能也需要更新，所以需要这个判断
         * */
        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = getItem(position);
        if (movie != null) {
            Picasso.get()
                    .load(movie.images.small)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.ivImage);

            holder.tvTitle.setText(movie.title);
            holder.tvYear.setText("上映年份:" + movie.year);
        } else {
            holder.ivImage.setImageResource(R.drawable.ic_launcher_background);
            holder.tvTitle.setText("");
            holder.tvYear.setText("");
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvYear;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
        }
    }
}
