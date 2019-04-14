package com.example.applogs.adapter;

import com.example.applogs.activity.ProductDetailActivity;
import com.example.applogs.fragments.ProductDetailFragment;
import com.example.applogs.fragments.ProductMapFragment;
import com.example.applogs.fragments.ProductWebFragment;
import com.example.applogs.model.ProductModel;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ProductDetailViewPagerAdapter extends FragmentStatePagerAdapter {

    private ProductModel productModel;

    public ProductDetailViewPagerAdapter(FragmentManager fm, ProductModel productModel) {
        super(fm);
        this.productModel = productModel;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return ProductDetailFragment.newInstance(productModel);

            case 1:
                return ProductWebFragment.newInstance(productModel);

            case 2:
                return ProductMapFragment.newInstance(productModel);
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Details";

            case 1:
                return "Web";

            case 2:
                return "Map";
        }


        return super.getPageTitle(position);
    }
}
