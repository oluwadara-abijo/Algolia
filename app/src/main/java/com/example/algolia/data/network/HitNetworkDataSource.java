package com.example.algolia.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.model.Hit;
import com.example.algolia.data.model.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HitNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = HitNetworkDataSource.class.getSimpleName();
    private static HitNetworkDataSource sInstance;

    private final AppExecutors mExecutors;

    //Constructor
    private HitNetworkDataSource(AppExecutors executors) {
        mExecutors = executors;
    }

    /**
     * Get the singleton for this class
     */
    public static HitNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new HitNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    //Gets the hits from network
    public LiveData<List<Hit>> getHits() {
        final MutableLiveData<List<Hit>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            HitInterface mHitInterface = HitClient.getClient();

            mHitInterface.getHits().enqueue(new Callback<NetworkResponse>() {
                @Override
                public void onResponse(Call<NetworkResponse> call, Response<NetworkResponse> response) {
                    Log.d(LOG_TAG, String.valueOf(response));
                    NetworkResponse networkResponse = response.body();
                    if (networkResponse != null) {
                        List<Hit> hitList = networkResponse.getmHits();
                        mutableLiveData.postValue(hitList);

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
