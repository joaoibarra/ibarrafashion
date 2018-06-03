package com.joaoibarra.ibarrafashion.feature.product.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joaoibarra.ibarrafashion.R;
import com.joaoibarra.ibarrafashion.data.model.Product;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity{
    @BindView(R.id.productImageView)
    ImageView productImageView;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvSubTitle)
    TextView tvSubTitle;

    @BindView(R.id.tvDescriptionTitle)
    TextView tvDescriptionTitle;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.product_detail));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void setProduct(Product product) {
        if(product.getImage() != null &&
                !product.getImage().equalsIgnoreCase("")) {
            Glide.
                    with(this)
                    .load(product.getImage())
                    .apply(new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(productImageView);
        }
        tvTitle.setText(product.getName());
        tvSubTitle.setText(product.getRegularPrice());
        tvDescription.setText(product.getActualPrice());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
