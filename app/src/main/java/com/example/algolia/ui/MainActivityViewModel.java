package com.example.algolia.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.algolia.data.Repository;
import com.example.algolia.data.model.Post;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Post>> mPosts;

    MainActivityViewModel(Repository repository) {
        mPosts = repository.getPosts();
    }

    LiveData<List<Post>> getPosts() {
        return mPosts;
    }
}
