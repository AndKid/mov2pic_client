package com.andkid.mov2pic.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andkid.mov2pic.MovieContentActivity;
import com.andkid.mov2pic.R;
import com.andkid.mov2pic.WebSites;
import com.andkid.mov2pic.model.MovieList;
import com.bumptech.glide.Glide;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class IndexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MovieList mMovieList;
    Fragment mFragment;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public IndexRecyclerViewAdapter(MovieList movieList, Fragment fragment) {
        this.mMovieList = movieList;
        this.mFragment = fragment;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_CELL:
                Glide.with(mFragment).load(WebSites.DOMAIN + mMovieList.movie_img[position]).into((ImageView) holder.itemView.findViewById(R.id.my_view));
                TextView title = (TextView) holder.itemView.findViewById(R.id.title);
                title.setText(mMovieList.movie_title[position]);
                final LinearLayout movie = (LinearLayout) holder.itemView.findViewById(R.id.movie);
                movie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mFragment.getActivity(), MovieContentActivity.class);
                        intent.putExtra(WebSites.PARAMETER_URI, mMovieList.movie_url[position]);
                        mFragment.getActivity().startActivity(intent);
                    }
                });
                break;
        }
    }

    public void setMovieList(MovieList movieList) {
        mMovieList = movieList;
    }
}