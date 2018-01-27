package com.sukhov.android.ainsoft;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sukhov on 25.01.2018.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>{

    private ArrayList<Product> mProduct;

    public AdapterRecyclerView(ArrayList<Product> product){
        this.mProduct = product;
    }

    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterRecyclerView.ViewHolder holder, final int position) {
        final Product p = mProduct.get(position);
        holder.name.setText(p.getName());
        holder.price.setText(p.getPrice());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent i = new Intent(context, DialogActivity.class);
                i.putExtra("old_price", p.getPrice());
                i.putExtra("position", position);
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (mProduct == null)
            return 0;
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = itemView.findViewById(R.id.product_item_name);
            price = itemView.findViewById(R.id.product_item_price);
        }
    }
}
