package com.example.algolia.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.algolia.data.Repository;
import com.example.algolia.data.model.Hit;
import com.example.algolia.data.model.NetworkResponse;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Hit>> mHits;

    MainActivityViewModel(Repository repository) {
        mHits = repository.getHits();
    }

    LiveData<List<Hit>> getHits() {
        return mHits;
    }
}
