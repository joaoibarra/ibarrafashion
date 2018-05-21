package com.joaoibarra.ibarrafashion.data.api;

import com.joaoibarra.ibarrafashion.data.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/v2/59b6a65a0f0000e90471257d")
    Call<ResponseData> getProducts();
}
