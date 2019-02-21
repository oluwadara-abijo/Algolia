package com.example.algolia.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.algolia.R;
import com.example.algolia.data.model.Post;
import com.example.algolia.utilities.InjectorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //UI Elements
    @BindView(R.id.rv_posts)
    RecyclerView mRecyclerView;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private PostAdapter mAdapter;
    private int mPosition = RecyclerView.NO_POSITION;

    private int numberOfPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind views
        ButterKnife.bind(this);

        //Get an instance of LinearLayoutManager
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        //Associate the LayoutManager with the RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        //List of all posts
        List<Post> mList = new ArrayList<>();

        mAdapter = new PostAdapter(mList);

        //Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        MainActivityViewModel mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        if (isNetworkAvailable()) {
            mViewModel.getPosts().observe(this, posts -> {
                mAdapter.setPosts(posts);
                if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
                mRecyclerView.smoothScrollToPosition(mPosition);

                // Show the post list or the loading screen based on whether the post data exists
                // and is loaded
                if (posts != null && posts.size() != 0) {
                    showData();
                    numberOfPosts = posts.size();
                } else showLoading();

            });

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager)
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void showData() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

}
