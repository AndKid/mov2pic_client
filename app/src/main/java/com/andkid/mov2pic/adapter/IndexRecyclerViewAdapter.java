package com.andkid.mov2pic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andkid.mov2pic.R;
import com.andkid.mov2pic.WebSites;
import com.andkid.mov2pic.model.MovieList;
import com.bumptech.glide.Glide;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class IndexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MovieList mMovieList;
    Context mContext;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public IndexRecyclerViewAdapter(MovieList movieList, Context context) {
        this.mMovieList = movieList;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        if(mMovieList != null)
            return mMovieList.getCount();
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_CELL:
                Glide.with(mContext).load(WebSites.DOMAIN + mMovieList.movie_img[position]).into((ImageView) holder.itemView.findViewById(R.id.my_view));
//                Log.i("cyg", mMovieList.movie_img[position]);
                break;
        }
    }

    public void setMovieList(MovieList movieList) {
        mMovieList = movieList;
    }
}