package com.joaoibarra.ibarrafashion.feature.product.contract;

import com.joaoibarra.ibarrafashion.data.model.Product;

import java.util.List;

public interface ProductListContract {
    public interface View<Presenter>{
        void attachPresenter(
                ProductListContract.Presenter presenter);
        void refresh(String message, List<Product> list);
    }

    public interface Presenter{
        void getProducts();
    }

    public interface Interactor{
        void getProducts();

    }
    public interface OnGetProductsListener{
        void onSuccess(String message, List<Product> list);
        void onFailure(String message);
    }
}
