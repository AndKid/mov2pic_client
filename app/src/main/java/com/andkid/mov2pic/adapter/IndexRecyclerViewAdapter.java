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
import com.andkid.mov2pic.callback.MovieContentCallback;
import com.andkid.mov2pic.model.MovieContent;
import com.andkid.mov2pic.model.MovieList;
import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

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
        if (mMovieList != null)
            return mMovieList.getCount();
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_layout, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_layout, parent, false);
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
                final FoldingCell cell = (FoldingCell) holder.itemView;
                Glide.with(mFragment).load(WebSites.DOMAIN + mMovieList.movie_img[position]).into((ImageView) cell.findViewById(R.id.my_view));
                TextView title = (TextView) cell.findViewById(R.id.title);
                title.setText(mMovieList.movie_title[position]);
//                final LinearLayout movie = (LinearLayout) holder.itemView.findViewById(R.id.movie);
//                movie.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(mFragment.getActivity(), MovieContentActivity.class);
//                        intent.putExtra(WebSites.PARAMETER_URI, mMovieList.movie_url[position]);
//                        mFragment.getActivity().startActivity(intent);
//                    }
//                });

                TextView movieTitle = (TextView) cell.findViewById(R.id.movie_title);
                movieTitle.setText(mMovieList.movie_title[position]);
                Glide.with(mFragment).load(WebSites.DOMAIN + mMovieList.movie_img[position]).into((ImageView) cell.findViewById(R.id.head_image));
                OkHttpUtils.get()
                        .url(WebSites.MOVIE_CONTENT_URL)
                        .addParams(WebSites.PARAMETER_URI, mMovieList.movie_url[position])
                        .build()
                        .execute(new MovieContentCallback() {

                            @Override
                            public void onError(Call call, Exception e) {

                            }

                            @Override
                            public void onResponse(MovieContent response) {
                                TextView movieSummary = (TextView) cell.findViewById(R.id.content_text);
                                movieSummary.setText(response.getSummary()+response.getSummary()+response.getSummary()+response.getSummary());
                            }
                        });
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cell.toggle(false);
                    }
                });
                break;
        }
    }

    public void setMovieList(MovieList movieList) {
        mMovieList = movieList;
    }
}