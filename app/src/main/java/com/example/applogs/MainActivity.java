package com.example.applogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
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
        setContentView(R.layout.activity_main);
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

    private class LoginAsyncTask extends AsyncTask<String, Integer, Boolean> {

        private String userName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLogin.setVisibility(View.VISIBLE);
            txtProgress.setVisibility(View.VISIBLE);
            txtProgress.setText("0");
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            userName = strings[0];
            String passWord = strings[1];

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if ("admin".equals(userName) && ("pass123".equals(passWord))) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtProgress.setText("" + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            pbLogin.setVisibility(View.GONE);
            txtProgress.setVisibility(View.GONE);
            if (aBoolean == true) {
                PreferenceHelper.getInstance(MainActivity.this).setString(PreferenceHelper.KEY_USERNAME, userName);
                Intent intent = new Intent(MainActivity.this, AcitivityTwo.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Credentials are not valid", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
