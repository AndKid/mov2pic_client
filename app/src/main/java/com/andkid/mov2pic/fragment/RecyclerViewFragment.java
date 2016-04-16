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
    private MovieList movieList;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mIndexAdapter = new IndexRecyclerViewAdapter(movieList, this.getContext());
        mAdapter = new RecyclerViewMaterialAdapter(mIndexAdapter);
        mRecyclerView.setAdapter(mAdapter);

        {
            for (int i = 0; i < ITEM_COUNT; ++i)
                mContentItems.add(new Object());
            mAdapter.notifyDataSetChanged();
        }


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    @Override
    public void refreshContent(Object jsonObject) {
//        Log.i("cyg", jsonObject.toString());
        MovieList movieList = (MovieList) jsonObject;
        if (movieList != null) {
            mIndexAdapter.setMovieList(movieList.trim());
            mAdapter.notifyDataSetChanged();
        }
    }
}
