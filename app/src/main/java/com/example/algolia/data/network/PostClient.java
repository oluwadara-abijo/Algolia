package com.example.algolia.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static final String BASE_URL = "https://hn.algolia.com/api/v1/";
    private static PostInterface retrofit = null;

    static PostInterface getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PostInterface.class);
        }
        return retrofit;
    }
}
