package com.example.applogs.activity;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applogs.data.local.PreferenceHelper;
import com.example.applogs.R;
import com.example.applogs.ui.productlist.ProductListActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String BUNDLE_USERNAME = "username";
    @BindView(R.id.etx_username)
    EditText etxUserName;
    @BindView(R.id.etx_password)
    EditText etxPassword;
    @BindView(R.id.pb_login)
    ProgressBar pbLogin;
    @BindView(R.id.txt_progressbar)
    TextView txtProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        etxPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    launch();
                }
                return false;
            }
        });
        Log.d(TAG, "OnCreate");
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

    @OnClick(R.id.btn_submit)
    public void launch() {

        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.execute(etxUserName.getText().toString(), etxPassword.getText().toString());
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        private String userName;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLogin.setVisibility(View.VISIBLE);
            txtProgress.setVisibility(View.VISIBLE);
            txtProgress.setText("0");
        }

        @Override
        protected String doInBackground(String... strings) {
            userName = strings[0];
            String passWord = strings[1];


            try {
                // This is getting the url from the string we passed in
                URL url = new URL("https://reqres.in/api/login");

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("POST");


                // OPTIONAL - Sets an authorization header
                //urlConnection.setRequestProperty("Authorization", "someAuthString");
                JSONObject postData = new JSONObject();
                if (userName != null && !userName.isEmpty()) {
                    postData.put("email", userName);
                }
                if (passWord != null && !passWord.isEmpty()) {
                    postData.put("password", passWord);
                }
                Log.d(TAG, "postdata = "+postData.toString());
                // Send the post body
                if (postData != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(postData.toString());
                    writer.flush();
                }

                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);
                    return response;

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    Toast.makeText(LoginActivity.this, "Internet is not working", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            String token = null;
            pbLogin.setVisibility(View.GONE);
            txtProgress.setVisibility(View.GONE);
            Log.d(TAG, "Response "+response);
            try {
                if(response!=null){
                    JSONObject responseJsonObject = new JSONObject(response);
                    token = responseJsonObject.optString("token", null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (token!=null && !token.isEmpty()){
                PreferenceHelper.getInstance(LoginActivity.this).setString(PreferenceHelper.KEY_TOKEN, token);
                Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                startActivity(intent);
                finish();
            } else{
                Toast.makeText(LoginActivity.this, "Credentials are not valid", Toast.LENGTH_SHORT).show();
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

