package com.elkusnandi.memegenerator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.util.RecyclerViewItemListener;
import com.elkusnandi.memegenerator.data.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = MainRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<Result> list;
    private RecyclerViewItemListener listener;

    public MainRecyclerViewAdapter(Context context, RecyclerViewItemListener listener) {
        this.mContext = context;
        this.listener = listener;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_meme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result item = list.get(position);

        Picasso.get().load(item.getImageUrl()).into(holder.imageViewThumbnail);

        holder.textViewTitle.setText(item.getDisplayName());
        holder.itemView.setOnClickListener(v -> listener.onClick(item));
        int totalScore = item.getTotalVotesScore() / 1000;
        if (totalScore > 5) {
            totalScore = 5;
        }
        holder.imageViewRating.setImageLevel(totalScore);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Result> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    public List<Result> getItems() {
        return list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_thumbnail)
        ImageView imageViewThumbnail;
        @BindView(R.id.image_view_rating)
        ImageView imageViewRating;
        @BindView(R.id.text_view_meme_title)
        TextView textViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}