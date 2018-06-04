package com.joaoibarra.ibarrafashion.feature.product.model;

import com.joaoibarra.ibarrafashion.data.api.FashionApi;
import com.joaoibarra.ibarrafashion.data.model.Product;
import com.joaoibarra.ibarrafashion.data.model.ResponseData;
import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListInteractor implements ProductListContract.Interactor{
    ProductListContract.OnGetProductsListener onGetProductsListener;

    public ProductListInteractor(ProductListContract.OnGetProductsListener onGetProductsListener) {
        this.onGetProductsListener = onGetProductsListener;
    }

    @Override
    public void getProducts(int filter){
        retrofit2.Call<ResponseData> call = FashionApi.getApi().getProducts();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                onGetProductsListener.onSuccess(response.message(), filterProducts(response.body().getProducts(), filter));
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                onGetProductsListener.onFailure(t.getMessage());
            }
        });
    }

    public List<Product> filterProducts(List<Product> products, int filter){
        switch (filter){
            case 0:
                return products;
            case 1:
                return filterProductsOnSale(products);
            default:
                return products;
        }
    }

    public List<Product> filterProductsOnSale(List<Product> products){
        List<Product> productsOnSale = new ArrayList<>();
        for(Product product: products){
            if(product.isOnSale()){
                productsOnSale.add(product);
            }
        }
        return productsOnSale;
    }


}
