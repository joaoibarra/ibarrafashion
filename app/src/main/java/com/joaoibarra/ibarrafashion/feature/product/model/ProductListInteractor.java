package com.joaoibarra.ibarrafashion.feature.product.model;

import com.joaoibarra.ibarrafashion.data.api.FashionApi;
import com.joaoibarra.ibarrafashion.data.model.Product;
import com.joaoibarra.ibarrafashion.data.model.ResponseData;
import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListInteractor implements ProductListContract.Interactor{
    ProductListContract.OnGetProductsListener onGetProductsListener;

    public ProductListInteractor(ProductListContract.OnGetProductsListener onGetProductsListener) {
        this.onGetProductsListener = onGetProductsListener;
    }

    @Override
    public void getProducts(){
        retrofit2.Call<ResponseData> call = FashionApi.getApi().getProducts();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                onGetProductsListener.onSuccess(response.message(), response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                onGetProductsListener.onFailure(t.getMessage());
            }
        });
    }
}
