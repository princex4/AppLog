package com.example.applogs.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.applogs.adapter.ProductAdapter;
import com.example.applogs.data.DataManager;
import com.example.applogs.data.remote.ApiInterface;
import com.example.applogs.data.local.DBHelper;
import com.example.applogs.data.remote.RetrofitApiClient;
import com.example.applogs.data.local.PreferenceHelper;
import com.example.applogs.model.ProductModel;
import com.example.applogs.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        getProductList();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_productlist, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_logout:
                PreferenceHelper.getInstance(ProductListActivity.this).setString(PreferenceHelper.KEY_TOKEN, null);

                Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter(ArrayList<ProductModel> productArrayList) {
        rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        ProductAdapter productAdapter = new ProductAdapter(ProductListActivity.this, productArrayList);
        rvProduct.setAdapter(productAdapter);
    }

    private void getProductList() {
        Observable<ArrayList<ProductModel>> productApiObservable = DataManager.getInstance(ProductListActivity.this).getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        productApiObservable
                .subscribe(new Observer<ArrayList<ProductModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ProductModel> productModels) {
                        setAdapter(productModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pbProduct.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        pbProduct.setVisibility(View.GONE);
                    }
                });
    }
}