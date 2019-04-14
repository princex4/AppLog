package com.example.applogs.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applogs.R;
import com.example.applogs.model.ProductModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductWebFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PRODUCT = "product";

    // TODO: Rename and change types of parameters
    ProductModel productModel;

    public ProductWebFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProductWebFragment newInstance(ProductModel productModel) {
        ProductWebFragment fragment = new ProductWebFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, productModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productModel = getArguments().getParcelable(ARG_PRODUCT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_web, container, false);
    }

}
