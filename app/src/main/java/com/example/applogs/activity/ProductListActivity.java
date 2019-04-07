package com.example.applogs.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.applogs.adapter.ProductAdapter;
import com.example.applogs.model.DimensionsModel;
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
        ProductAsync productAsync = new ProductAsync("https://my-json-server.typicode.com/princex11/AppLog/productData");
        productAsync.execute();
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

    private class ProductAsync extends AsyncTask<String, Void, String> {

        private String urlEndpoint;


        public ProductAsync(String urlEndpoint) {
            this.urlEndpoint = urlEndpoint;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // This is getting the url from the string we passed in
                URL url = new URL(urlEndpoint);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


               // urlConnection.setDoInput(true);
               // urlConnection.setDoOutput(true);

               // urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("GET");


                // OPTIONAL - Sets an authorization header
                //urlConnection.setRequestProperty("Authorization", "someAuthString");
//                JSONObject postData = new JSONObject();
//                if (userName != null && !userName.isEmpty()) {
//                    postData.put("email", userName);
//                }
//                if (passWord != null && !passWord.isEmpty()) {
//                    postData.put("password", passWord);
//                }
//                Log.d(TAG, "postdata = "+postData.toString());
//                // Send the post body
//                if (postData != null) {
//                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
//                    writer.write(postData.toString());
//                    writer.flush();
//                }

                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);
                    return response;

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                    return null;
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            pbProduct.setVisibility(View.GONE);
            ArrayList<ProductModel> productArrayList = new ArrayList<>();
            // Log.d(TAG, "Response "+response);
            try {
                if (response != null) {
                    JSONArray productJArray = new JSONArray(response);
                    for (int i = 0; i < productJArray.length(); i++) {
                        JSONObject productJObject = productJArray.getJSONObject(i);

                        ProductModel productModel = new ProductModel();

                        String productName = productJObject.optString("name");
                        productModel.setName(productName);

                        String productId = productJObject.optString("productId");
                        productModel.setId(productId);

                        JSONObject dimensionsJObject = productJObject.getJSONObject("dimensions");
                        float length =(float) dimensionsJObject.optDouble("length");
                        float width =(float) dimensionsJObject.optDouble("width");
                        float height =(float) dimensionsJObject.optDouble("height");
                        DimensionsModel dimensionsModel = new DimensionsModel(length, width, height);
                        productModel.setDimensionsModel(dimensionsModel);

                        JSONArray tagsJArray = productJObject.getJSONArray("tags");
                        ArrayList<String> tagsArrayList = new ArrayList<>();
                        for (int j = 0; j < tagsJArray.length() ; j++) {
                            tagsArrayList.add(tagsJArray.optString(j));
                        }
                        productModel.setTagArrayList(tagsArrayList);

                        productArrayList.add(productModel);

                        Log.d(TAG, "At index" + i + " " + productName);
                    }
                    rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
                    ProductAdapter productAdapter = new ProductAdapter(ProductListActivity.this, productArrayList);
                    rvProduct.setAdapter(productAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < productArrayList.size(); i++) {
                ProductModel tempProductModel = productArrayList.get(i);
                Log.d(TAG, "Product id: " +tempProductModel.getId() + "Product Name "+tempProductModel.getName()
                + " Length " +tempProductModel.getDimensionsModel().getLength());
                for (int j = 0; j < tempProductModel.getTagArrayList().size(); j++) {
                    Log.d(TAG, "Tags: " +tempProductModel.getTagArrayList().get(j));
                }
            }
        }

        private String convertInputStreamToString(InputStream inputStream) {
            try {
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }
                return total.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


}


