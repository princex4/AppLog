package com.example.applogs.data.remote;

import com.example.applogs.model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/princex11/AppLog/productData")
    Observable<ArrayList<ProductModel>> getProducts();

}
