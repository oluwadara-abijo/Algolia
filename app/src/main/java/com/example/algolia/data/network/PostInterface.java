package com.example.algolia.data.network;

import com.example.algolia.data.model.NetworkResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("search_by_date?tags=story&page=1")
    Call<NetworkResponse> getPosts();
}
