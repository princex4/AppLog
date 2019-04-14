package com.example.applogs.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.applogs.R;
import com.example.applogs.model.ProductModel;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String BUNDLE_PRODUCT = "product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        ProductModel product = intent.getParcelableExtra(BUNDLE_PRODUCT);
        Log.d("Product Detail Activity", product.getName());


    }
}
