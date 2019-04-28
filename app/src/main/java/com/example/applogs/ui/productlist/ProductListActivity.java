package com.example.applogs.ui.productlist;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applogs.activity.LoginActivity;
import com.example.applogs.adapter.ProductAdapter;
import com.example.applogs.data.DataManager;
import com.example.applogs.data.local.PreferenceHelper;
import com.example.applogs.model.ProductModel;
import com.example.applogs.R;
import com.example.applogs.ui.base.BaseActivity;

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

public class ProductListActivity extends BaseActivity implements ProductListMvpView {

    //public static final String TAG =
    private String username;
    @BindView(R.id.txt_username)
    TextView txtUserName;
    @BindView(R.id.pb_product)
    ProgressBar pbProduct;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;

    private ProductListPresenter productListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        TAG = ProductListActivity.class.getSimpleName();
        ButterKnife.bind(this);
        productListPresenter = new ProductListPresenter();
        productListPresenter.attachView(this);
        Log.d(TAG, "OnCreate");
        txtUserName.setText(username);
        username = PreferenceHelper.getInstance(ProductListActivity.this).getString(PreferenceHelper.KEY_USERNAME);
        txtUserName.setText(username);

        productListPresenter.getProducts();

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
        productListPresenter.detachView();
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



    @Override
    public void showProducts(ArrayList<ProductModel> productModelArrayList) {
        setAdapter(productModelArrayList);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showProgressBar(boolean isShow) {
                if(isShow){
                    pbProduct.setVisibility(View.VISIBLE);
                }else{
                    pbProduct.setVisibility(View.GONE);
                }
    }
}