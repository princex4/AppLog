package com.example.applogs.data;

import android.content.Context;

import com.example.applogs.data.local.DBHelper;
import com.example.applogs.data.local.PreferenceHelper;
import com.example.applogs.data.remote.ApiInterface;
import com.example.applogs.data.remote.RetrofitApiClient;
import com.example.applogs.model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DataManager {

    private static DataManager instance = null;
    private static PreferenceHelper preferenceHelper = null;
    private static DBHelper dbHelper = null;
    private static ApiInterface apiInterface = null;

    private DataManager() {
    }

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                    preferenceHelper = PreferenceHelper.getInstance(context);
                    dbHelper = DBHelper.getInstance(context);
                    apiInterface = RetrofitApiClient.getClient().getApiService();
                }
            }
        }
        return instance;
    }

    public Observable<ArrayList<ProductModel>> getProducts() {
        ArrayList<ProductModel> productModelArrayList = dbHelper.getProduct();
        if (productModelArrayList == null || productModelArrayList.isEmpty()) {
            return apiInterface.getProducts()
                    .concatMap(new Function<ArrayList<ProductModel>, Observable<ArrayList<ProductModel>>>() {
                        @Override
                        public Observable<ArrayList<ProductModel>> apply(ArrayList<ProductModel> productModelArrayList) throws Exception {
                            dbHelper.insertProducts(productModelArrayList);
                            return Observable.just(productModelArrayList);
                        }
                    });
        } else {
            return Observable.just(productModelArrayList);
        }
    }


}