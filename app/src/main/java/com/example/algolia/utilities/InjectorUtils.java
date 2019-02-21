package com.example.algolia.utilities;

import android.content.Context;

import com.example.algolia.AppExecutors;
import com.example.algolia.data.Repository;
import com.example.algolia.data.network.HitNetworkDataSource;
import com.example.algolia.ui.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    private static Repository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        HitNetworkDataSource networkDataSource =
                HitNetworkDataSource.getInstance(executors);
        return Repository.getInstance(networkDataSource, executors, context);
    }

    public static HitNetworkDataSource provideNetworkDataSource() {
        AppExecutors executors = AppExecutors.getInstance();
        return HitNetworkDataSource.getInstance(executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}