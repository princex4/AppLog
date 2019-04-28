package com.example.applogs.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static RetrofitApiClient retrofitApiClient = null;
    private static ApiInterface apiInterface = null;

   public static RetrofitApiClient getClient(){

       if(retrofitApiClient==null){
           retrofitApiClient = new RetrofitApiClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

           apiInterface = retrofit.create(ApiInterface.class);
        }
        return retrofitApiClient;
    }

    public ApiInterface getApiService(){
       return apiInterface;
    }
}
