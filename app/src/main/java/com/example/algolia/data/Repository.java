package com.example.algolia.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.model.Post;
import com.example.algolia.data.network.PostNetworkDataSource;
import com.example.algolia.utilities.InjectorUtils;

import java.util.List;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private final Context mContext;

    //Constructor
    private Repository(PostNetworkDataSource PostNetworkDataSource,
                       AppExecutors executors, Context context) {
        PostNetworkDataSource mPostNetworkDataSource = PostNetworkDataSource;
        AppExecutors mExecutors = executors;
        mContext = context;
    }

    //Singleton
    public synchronized static Repository getInstance(
            PostNetworkDataSource PostNetworkDataSource, AppExecutors executors,
            Context context) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(PostNetworkDataSource,
                        executors, context);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    //Network related operation
<<<<<<< HEAD
    public LiveData<List<Post>> getposts() {
        PostNetworkDataSource networkDataSource = InjectorUtils
||||||| merged common ancestors
    public LiveData<List<Hit>> getHits() {
        HitNetworkDataSource networkDataSource = InjectorUtils
=======
    public LiveData<List<Post>> getHits() {
        PostNetworkDataSource networkDataSource = InjectorUtils
>>>>>>> 8d532be2b5beffea8669007a98b7cc2918e6f9e0
                .provideNetworkDataSource();
        return networkDataSource.getposts();
    }
}
