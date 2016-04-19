package com.andkid.mov2pic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andkid.mov2pic.R;
import com.andkid.mov2pic.WebSites;
import com.andkid.mov2pic.model.MovieContent;
import com.bumptech.glide.Glide;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ContentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MovieContent mMovieContent;
    Context mContext;

    static final int TYPE_HEADER = 0;
    static final int TYPE_TEXT = 1;
    static final int TYPE_IMAGE = 2;

    public ContentRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        else if(mMovieContent.isImageContent(mMovieContent.content[position-1])){
            return TYPE_IMAGE;
        } else {
            return TYPE_TEXT;
        }
    }

    @Override
    public int getItemCount() {
        if (mMovieContent == null) return 0;
        return mMovieContent.content.length + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_movie_header, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_IMAGE: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_movie_body_image, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_TEXT: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_movie_body_text, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                TextView title = (TextView) holder.itemView.findViewById(R.id.title);
                TextView summary = (TextView) holder.itemView.findViewById(R.id.summary);
                title.setText(mMovieContent.title);
                summary.setText(mMovieContent.getSummary());
                break;
            case TYPE_IMAGE:
                Glide.with(mContext).load(WebSites.DOMAIN + mMovieContent.content[position - 1]).into((ImageView) holder.itemView.findViewById(R.id.content_image));
                break;
            case TYPE_TEXT:
                TextView textContentView = (TextView) holder.itemView.findViewById(R.id.content_text);
                textContentView.setText(mMovieContent.content[position - 1]);
        }
    }

    public void setMovieContent(MovieContent movieContent) {
        this.mMovieContent = movieContent;
    }

}