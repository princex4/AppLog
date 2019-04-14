package com.example.applogs.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.applogs.adapter.ProductAdapter;
import com.example.applogs.data.ApiInterface;
import com.example.applogs.data.DBHelper;
import com.example.applogs.data.RetrofitApiClient;
import com.example.applogs.utils.PreferenceHelper;
import com.example.applogs.model.ProductModel;
import com.example.applogs.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    public static final String TAG = ProductListActivity.class.getSimpleName();
    private String username;
    @BindView(R.id.txt_username)
    TextView txtUserName;
    @BindView(R.id.pb_product)
    ProgressBar pbProduct;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        Log.d(TAG, "OnCreate");
        txtUserName.setText(username);
        username = PreferenceHelper.getInstance(ProductListActivity.this).getString(PreferenceHelper.KEY_USERNAME);
        txtUserName.setText(username);

        ArrayList<ProductModel> productModelArrayList = DBHelper.getInstance(this).getProduct();
        if (productModelArrayList == null || productModelArrayList.isEmpty()) {
            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<ProductModel>> productApiCall = apiInterface.getProducts();
            productApiCall.enqueue(new Callback<ArrayList<ProductModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                    pbProduct.setVisibility(View.GONE);
                    ArrayList<ProductModel> productArrayList = response.body();
                    setAdapter(productArrayList);
                    DBHelper.getInstance(ProductListActivity.this).insertProducts(productArrayList);
                }

                @Override
                public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                    pbProduct.setVisibility(View.GONE);
                    t.printStackTrace();
                }
            });

        }else{
            setAdapter(productModelArrayList);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @OnClick(R.id.btn_logout)
    public void logout(View view) {
        PreferenceHelper.getInstance(ProductListActivity.this).setString(PreferenceHelper.KEY_USERNAME, null);

        Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_productlist, menu);
         return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_logout:
                PreferenceHelper.getInstance(ProductListActivity.this).setString(PreferenceHelper.KEY_USERNAME, null);

                Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter(ArrayList<ProductModel> productArrayList){
        rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        ProductAdapter productAdapter = new ProductAdapter(ProductListActivity.this, productArrayList);
        rvProduct.setAdapter(productAdapter);
    }
}