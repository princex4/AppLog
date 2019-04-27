package com.example.applogs.activity;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.applogs.utils.PreferenceHelper;
import com.example.applogs.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rxFunc();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//
//                String userName = PreferenceHelper.getInstance(SplashActivity.this).getString(PreferenceHelper.KEY_TOKEN);
//                Intent intent;
//                if (userName != null) {
//                    intent = new Intent(SplashActivity.this, ProductListActivity.class);
//                } else {
//                    intent = new Intent(SplashActivity.this, LoginActivity.class);
//                }
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
    }

    private void rxFunc() {
        Observable<String> observable = Observable
                .just(1,22,25, 2, 3)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Thread.sleep(3000);
                        return integer * 5;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        switch (integer) {
                            case 5:
                                return "Student 1";
                            case 10:
                                return "Student 2";
                            case 15:
                                return "Student 3";
                        }
                        return "xyz"+integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String string) {
                Log.d("Splash Activity", string);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Splash Activity", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("Splash Activity", "onComplete");
            }
        };
        observable.subscribe(observer);
    }


}
