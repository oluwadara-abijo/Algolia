package com.example.algolia.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.algolia.data.Repository;
import com.example.algolia.data.model.Post;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Post>> mPosts;

    MainActivityViewModel(Repository repository) {
<<<<<<< HEAD
        mPosts = repository.getposts();
||||||| merged common ancestors
        mHits = repository.getHits();
=======
        mPosts = repository.getHits();
>>>>>>> 8d532be2b5beffea8669007a98b7cc2918e6f9e0
    }

    LiveData<List<Post>> getPosts() {
        return mPosts;
    }
}
