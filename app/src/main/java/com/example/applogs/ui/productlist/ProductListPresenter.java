package com.example.applogs.ui.productlist;

import android.view.View;

import com.example.applogs.MyApplication;
import com.example.applogs.data.DataManager;
import com.example.applogs.model.ProductModel;
import com.example.applogs.ui.base.BasePresenter;
import com.example.applogs.ui.base.MvpView;
import com.example.applogs.ui.base.Presenter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenter implements Presenter<ProductListMvpView> {

    ProductListMvpView productListMvpView;
    DataManager dataManager;

    public ProductListPresenter() {
        dataManager = DataManager.getInstance(MyApplication.getAppContext());
    }

    @Override
    public void attachView(ProductListMvpView mvpView) {
        productListMvpView= mvpView;
    }

    @Override
    public void detachView() {
        productListMvpView=null;
    }

    public void getProducts(){
        productListMvpView.showProgressBar(true);
        Observable<ArrayList<ProductModel>> productApiObservable = dataManager.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        productApiObservable
                .subscribe(new Observer<ArrayList<ProductModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ProductModel> productModels) {
                        productListMvpView.showProducts(productModels);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        productListMvpView.showError(e.getMessage());
                        productListMvpView.showProgressBar(false);
                    }

                    @Override
                    public void onComplete() {

                        productListMvpView.showProgressBar(false);
                    }
                });

    }
}
