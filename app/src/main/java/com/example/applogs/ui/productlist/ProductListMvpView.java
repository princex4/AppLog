package com.example.applogs.ui.productlist;

import com.example.applogs.model.ProductModel;
import com.example.applogs.ui.base.MvpView;

import java.util.ArrayList;

public interface ProductListMvpView extends MvpView {

    void showProducts(ArrayList<ProductModel> productModelArrayList);
    void showError(String errorMessage);
    void showProgressBar(boolean isShow);

}
