package com.joaoibarra.ibarrafashion.feature.product.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joaoibarra.ibarrafashion.R;
import com.joaoibarra.ibarrafashion.data.model.Product;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> list = new ArrayList<>();
    public ProductAdapter(Context context, List<Product> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_item,parent,false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        holder.position = position;
        Product product = list.get(position);
        if(product != null) {
            holder.tvProductName.setText(product.getName());
            setPrice(holder, product);
            if (!product.getImage().isEmpty()) {
                Glide.
                        with(context)
                        .load(product.getImage())
                        .apply(new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(holder.productImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setPrice(ProductAdapter.ViewHolder holder, Product product){
        if(product.isOnSale()){
            holder.tvNormalPrice.setText(product.getRegularPrice());
            holder.tvNormalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvRealPrice.setText(product.getActualPrice());
        }else{
            holder.tvNormalPrice.setText(" ");
            holder.tvRealPrice.setText(product.getActualPrice());
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productImageView)
        ImageView productImageView;

        @BindView(R.id.tvName)
        TextView tvProductName;

        @BindView(R.id.tvNormalPrice)
        TextView tvNormalPrice;

        @BindView(R.id.tvRealPrice)
        TextView tvRealPrice;

        @BindView(R.id.cardView)
        CardView cardView;

        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cardView)
        public void cardViewClick(){
            EventBus.getDefault().postSticky(list.get(position));
            Intent intent = new Intent(context, ProductActivity.class);
            Pair<View, String> p1 = Pair.create(productImageView, "productImage");
            Pair<View, String> p2 = Pair.create(tvProductName, "productTitle");
            Activity activity = (Activity) context;
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity,p1, p2);
            context.startActivity(intent, options.toBundle());
        }
    }
}
