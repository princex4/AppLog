package com.example.applogs.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.applogs.R;
import com.example.applogs.adapter.ProductDetailViewPagerAdapter;
import com.example.applogs.model.ProductModel;
import com.example.applogs.ui.base.BaseActivity;

public class ProductDetailActivity extends BaseActivity {

    public static final String BUNDLE_PRODUCT = "product";

    @BindView(R.id.vp_product)
    ViewPager vpProduct;

    @BindView(R.id.pts_product)
    PagerTabStrip ptsProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        TAG = ProductDetailActivity.class.getSimpleName();

        Intent intent = getIntent();
        ProductModel product = intent.getParcelableExtra(BUNDLE_PRODUCT);
        Log.d(TAG, product.getName());

        ProductDetailViewPagerAdapter viewPagerAdapter = new ProductDetailViewPagerAdapter(getSupportFragmentManager(), product);
        vpProduct.setAdapter(viewPagerAdapter);

    }
}
