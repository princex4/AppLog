package com.example.applogs.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.applogs.R;
import com.example.applogs.model.ProductModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    Context context;
    ArrayList<ProductModel> productArrayList;

    public ProductAdapter(Context context, ArrayList<ProductModel> productArrayList) {
         this.context = context;
         this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.txtId.setText("id = "+productArrayList.get(position).getId());
            holder.txtName.setText("name = "+productArrayList.get(position).getName());
        Log.i("xyz", ""+position+" name "+productArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return productArrayList==null?0:productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_id)
        TextView txtId;
        @BindView(R.id.txt_name)
        TextView txtName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
