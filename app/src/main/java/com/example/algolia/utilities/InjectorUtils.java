package com.example.algolia.utilities;

import android.content.Context;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.Repository;
import com.example.algolia.data.network.PostNetworkDataSource;
import com.example.algolia.ui.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    private static Repository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        PostNetworkDataSource networkDataSource =
                PostNetworkDataSource.getInstance(executors);
        return Repository.getInstance(networkDataSource, executors, context);
    }

    public static PostNetworkDataSource provideNetworkDataSource() {
        AppExecutors executors = AppExecutors.getInstance();
        return PostNetworkDataSource.getInstance(executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}