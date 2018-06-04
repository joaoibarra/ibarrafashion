package com.joaoibarra.ibarrafashion.feature.product.presenter;

import com.joaoibarra.ibarrafashion.data.model.Product;
import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;
import com.joaoibarra.ibarrafashion.feature.product.model.ProductListInteractor;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter,
        ProductListContract.OnGetProductsListener{
    private final ProductListContract.View view;
    private final ProductListInteractor interactor;

    public ProductListPresenter(ProductListContract.View view) {
        this.view = view;
        this.interactor = new ProductListInteractor(this);
    }

    @Override
    public void getProducts(int filter) {
        interactor.getProducts(filter);
    }

    @Override
    public void onSuccess(String message, List<Product> list) {
        view.refresh(message, list);
    }

    @Override
    public void onFailure(String message) {

    }
}
