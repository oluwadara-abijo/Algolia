package com.example.algolia.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.model.Hit;
import com.example.algolia.data.model.NetworkResponse;
import com.example.algolia.data.network.HitNetworkDataSource;
import com.example.algolia.utilities.InjectorUtils;

import java.util.List;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private final Context mContext;

    //Constructor
    private Repository(HitNetworkDataSource recipeNetworkDataSource,
                       AppExecutors executors, Context context) {
        HitNetworkDataSource mRecipeNetworkDataSource = recipeNetworkDataSource;
        AppExecutors mExecutors = executors;
        mContext = context;
    }

    //Singleton
    public synchronized static Repository getInstance(
            HitNetworkDataSource recipeNetworkDataSource, AppExecutors executors,
            Context context) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(recipeNetworkDataSource,
                        executors, context);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    //Network related operation
    public LiveData<List<Hit>> getHits() {
        HitNetworkDataSource networkDataSource = InjectorUtils
                .provideNetworkDataSource();
        return networkDataSource.getHits();
    }
}
