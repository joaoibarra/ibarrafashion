package com.joaoibarra.ibarrafashion.feature.product.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvNormalPrice)
    TextView tvNormalPrice;

    @BindView(R.id.tvRealPrice)
    TextView tvRealPrice;

    @BindView(R.id.colorImageView)
    ImageView colorImageView;

    @BindView(R.id.sizeGroup)
    LinearLayout sizeGroup;

    @BindView(R.id.content)
    ConstraintLayout content;

    Product product;


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
        this.product = product;
        if(product.getImage() != null &&
                !product.getImage().equalsIgnoreCase("")) {
            Glide.
                    with(this)
                    .load(product.getImage())
                    .apply(new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(productImageView);
        }

        Glide.
                with(this)
                .asGif()
                .load(getString(R.string.color_url, product.getCodeColor()))
                .apply(RequestOptions.circleCropTransform())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(colorImageView);

        initView();
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

    public void initView() {
        tvName.setText(product.getName());
        if (product.isOnSale()) {
            tvNormalPrice.setText(product.getRegularPrice());
            tvNormalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tvRealPrice.setText(product.getActualPrice());
        } else {
            tvNormalPrice.setText(product.getInstallments());
            tvRealPrice.setText(product.getActualPrice());
        }

        for (int i = 0; i < product.getSizes().size(); i++) {
            if (product.getSizes().get(i).isAvailable()) {
                TextView tv = new TextView(this);
                tv.setGravity(Gravity.CENTER);
                tv.setBackground(getDrawable(R.drawable.circle));
                tv.setText(product.getSizes().get(i).getSize());
                tv.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                tv.setLayoutParams(params);

                sizeGroup.addView(tv);
            }
        }
        TransitionManager.beginDelayedTransition(content);
        content.setVisibility(View.VISIBLE);
    }
}
