package com.andkid.mov2pic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andkid.mov2pic.R;
import com.andkid.mov2pic.adapter.IndexRecyclerViewAdapter;
import com.andkid.mov2pic.model.MovieList;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment implements FragmentBase{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private IndexRecyclerViewAdapter mIndexAdapter;

    private static final int ITEM_COUNT = 100;

    private List<Object> mContentItems = new ArrayList<>();

    private MovieList mMovieList;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRecyclerView == null)
            return inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mRecyclerView != null) return;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mIndexAdapter = new IndexRecyclerViewAdapter(mMovieList, this);
        mAdapter = new RecyclerViewMaterialAdapter(mIndexAdapter);
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    @Override
    public void refreshContent(Object jsonObject) {
        MovieList movieList = (MovieList) jsonObject;
        if (movieList != null) {
            mIndexAdapter.setMovieList(movieList.trim());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setMovieList(MovieList movieList) {
        this.mMovieList = movieList;
    }

    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }

}
