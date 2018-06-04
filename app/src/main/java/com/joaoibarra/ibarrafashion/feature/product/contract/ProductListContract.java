package com.joaoibarra.ibarrafashion.feature.product.contract;

import android.view.View;

import com.joaoibarra.ibarrafashion.data.model.Product;

import java.util.List;

public interface ProductListContract {
    public interface View<Presenter>{
        void refresh(String message, List<Product> list);

        public void showProgress();

        public void hideProgress();
    }

    public interface Presenter{
        void getProducts(int filter);
        void refresh(int filter);
    }

    public interface Interactor{
        void getProducts(int filter);

    }
    public interface OnGetProductsListener{
        void onSuccess(String message, List<Product> list);
        void onFailure(String message);
    }
}
