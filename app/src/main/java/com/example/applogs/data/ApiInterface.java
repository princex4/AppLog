package com.example.applogs.data;

import com.example.applogs.model.ProductModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/princex11/AppLog/productData")
    Call<ArrayList<ProductModel>> getProducts();

}
