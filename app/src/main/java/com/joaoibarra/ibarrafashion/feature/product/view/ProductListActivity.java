package com.joaoibarra.ibarrafashion.feature.product.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.joaoibarra.ibarrafashion.R;
import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;
import com.joaoibarra.ibarrafashion.feature.product.presenter.ProductListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity  implements ProductListContract.View{

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.progressBarGroup)
    ConstraintLayout progressBarGroup;

    ProductListContract.Presenter presenter;

    ProductFragmentListActivity selectedFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = ProductFragmentListActivity.newInstance().setFilter(0);
                    presenter = new ProductListPresenter(ProductListActivity.this);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts(0);
                    return true;
                case R.id.navigation_sale:
                    selectedFragment = ProductFragmentListActivity.newInstance().setFilter(1);
                    presenter = new ProductListPresenter(ProductListActivity.this);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts(1);
                    return true;
                case R.id.navigation_fav:
                    selectedFragment = ProductFragmentListActivity.newInstance().setFilter(2);
                    presenter = new ProductListPresenter(ProductListActivity.this);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void refresh(String message, List list) {
        if(selectedFragment!=null) {
            ProductAdapter productAdapter = new ProductAdapter(this, list);
            selectedFragment.setProductAdapter(productAdapter);
        }
        hideProgress();
    }

    @Override
    public void showProgress(){
        TransitionManager.beginDelayedTransition(progressBarGroup);
        progressBarGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(){
        TransitionManager.beginDelayedTransition(progressBarGroup);
        progressBarGroup.setVisibility(View.GONE);
    }

}
