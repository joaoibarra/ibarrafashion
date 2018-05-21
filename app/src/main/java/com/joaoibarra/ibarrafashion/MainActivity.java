package com.joaoibarra.ibarrafashion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.joaoibarra.ibarrafashion.feature.product.contract.ProductListContract;
import com.joaoibarra.ibarrafashion.feature.product.presenter.ProductListPresenter;
import com.joaoibarra.ibarrafashion.feature.product.view.ProductFragmentListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    ProductListContract.Presenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ProductFragmentListActivity selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = ProductFragmentListActivity.newInstance();
                    presenter = new ProductListPresenter(selectedFragment);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts();
                    return true;
                case R.id.navigation_dashboard:
                    selectedFragment = ProductFragmentListActivity.newInstance();
                    presenter = new ProductListPresenter(selectedFragment);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts();
                    return true;
                case R.id.navigation_notifications:
                    selectedFragment = ProductFragmentListActivity.newInstance();
                    presenter = new ProductListPresenter(selectedFragment);
                    selectedFragment.attachPresenter(presenter);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    presenter.getProducts();
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

}
