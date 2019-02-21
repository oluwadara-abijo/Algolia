package com.example.algolia.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.model.Post;
import com.example.algolia.data.model.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = PostNetworkDataSource.class.getSimpleName();
    private static PostNetworkDataSource sInstance;

    private final AppExecutors mExecutors;

    //Constructor
    private PostNetworkDataSource(AppExecutors executors) {
        mExecutors = executors;
    }

    /**
     * Get the singleton for this class
     */
    public static PostNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new PostNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    //Gets the posts from network
    public LiveData<List<Post>> getposts() {
        final MutableLiveData<List<Post>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            PostInterface mPostInterface = PostClient.getClient();

            mPostInterface.getPosts().enqueue(new Callback<NetworkResponse>() {
                @Override
                public void onResponse(Call<NetworkResponse> call, Response<NetworkResponse> response) {
                    Log.d(LOG_TAG, String.valueOf(response));
                    NetworkResponse networkResponse = response.body();
                    if (networkResponse != null) {
                        List<Post> postList = networkResponse.getmPosts();
                        mutableLiveData.postValue(postList);

                    }
                }

                @Override
                public void onFailure(Call<NetworkResponse> call, Throwable t) {
                    Log.d(LOG_TAG, "An error occurred");

                }
            });

        });
        return mutableLiveData;
    }
}
