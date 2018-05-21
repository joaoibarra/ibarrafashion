package com.joaoibarra.ibarrafashion.feature.product.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joaoibarra.ibarrafashion.R;
import com.joaoibarra.ibarrafashion.data.model.Product;
import com.squareup.picasso.Picasso;

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
            if (!product.getImage().isEmpty()) {
                Picasso.get().load(product.getImage()).into(holder.productImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productImageView)
        ImageView productImageView;

        @BindView(R.id.tvTitle)
        TextView tvProductName;

        @BindView(R.id.cardView)
        CardView cardView;

        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cardView)
        public void cardViewClick(){
           /* EventBus.getDefault().postSticky(list.get(position));
            Intent intent = new Intent(context, ProductActivity.class);
            Pair<View, String> p1 = Pair.create((View) gameImageView, "gameImage");
            Pair<View, String> p2 = Pair.create((View) tvGameName, "gameName");
            Activity activity = (Activity) context;
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity,p1, p2);
            context.startActivity(intent, options.toBundle());*/
        }
    }
}
