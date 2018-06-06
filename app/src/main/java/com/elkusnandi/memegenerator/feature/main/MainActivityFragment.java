package com.elkusnandi.memegenerator.feature.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.util.RecyclerViewItemListener;
import com.elkusnandi.memegenerator.adapter.MainRecyclerViewAdapter;
import com.elkusnandi.memegenerator.data.Repository;
import com.elkusnandi.memegenerator.data.model.LanguageCode;
import com.elkusnandi.memegenerator.data.model.MemePeriod;
import com.elkusnandi.memegenerator.data.model.MemePopularRespond;
import com.elkusnandi.memegenerator.data.model.Result;
import com.elkusnandi.memegenerator.feature.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainContract.View, SwipeRefreshLayout.OnRefreshListener,
        RecyclerViewItemListener {

    private static final String ARG_SAVED_INSTANCE = "items";

    @BindView(R.id.recycler_view_meme_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MainPresenter presenter;
    private MainRecyclerViewAdapter adapter;

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        //put something
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (presenter == null) {
            presenter = new MainPresenter(this, Repository.getRepository());

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (adapter.getItemCount() > 0) {
            outState.putParcelableArrayList(ARG_SAVED_INSTANCE, (ArrayList<? extends Parcelable>) adapter.getItems());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        adapter = new MainRecyclerViewAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(getContext(), 2); // number of column in Recyclerview
        } else {
            gridLayoutManager = new GridLayoutManager(getContext(), 4); // number of column in Recyclerview
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // if it is not configuration change
        if (savedInstanceState == null) {
            // load movie
            presenter.loadPopularMeme(LanguageCode.iso.ENGLISH, 0, MemePeriod.Time.ALL_TIME);
        } else {
            // load from saved instance
            if (savedInstanceState.containsKey(ARG_SAVED_INSTANCE)) {
                List<Result> list = savedInstanceState.getParcelableArrayList(ARG_SAVED_INSTANCE);
                adapter.addItems(list);
            } else {

            }
        }
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDataLoaded(MemePopularRespond respond) {
        adapter.addItems(respond.getResult());
    }

    @Override
    public void onClick(Result result) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_RESULT, result);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        presenter.loadPopularMeme(LanguageCode.iso.ENGLISH, 0, MemePeriod.Time.ALL_TIME);
    }
}
