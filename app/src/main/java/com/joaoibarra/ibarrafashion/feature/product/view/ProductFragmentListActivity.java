package com.joaoibarra.ibarrafashion.feature.product.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joaoibarra.ibarrafashion.R;
import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragmentListActivity extends Fragment implements ProductListContract.View{

    @BindView(R.id.productRecyclerView)
    RecyclerView productRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    protected ProductListContract.Presenter presenter;
    ProductAdapter productAdapter;

    private int filter;

    public static ProductFragmentListActivity newInstance(){
        return new ProductFragmentListActivity();
    }

    public ProductFragmentListActivity setFilter(int filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public void attachPresenter(
            ProductListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        productRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void refresh(String message, List list) {
        productAdapter = new ProductAdapter(getContext(), list);
        productRecyclerView.setAdapter(productAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    protected SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            presenter.getProducts(0);
        }
    };
}
