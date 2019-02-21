package com.example.algolia.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HitClient {
    private static final String BASE_URL = "https://hn.algolia.com/api/v1/";
    private static HitInterface retrofit = null;

    static HitInterface getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(HitInterface.class);
        }
        return retrofit;
    }
}
