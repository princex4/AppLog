package com.example.applogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcitivityTwo extends AppCompatActivity {

    public static final String Tag = AcitivityTwo.class.getSimpleName();
    private String username;
    @BindView(R.id.txt_username)
    TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_two);
        ButterKnife.bind(this);
        Log.d(Tag, "OnCreate");
        txtUserName.setText(username);


        username = PreferenceHelper.getInstance(AcitivityTwo.this).getString(PreferenceHelper.KEY_USERNAME);
        txtUserName.setText(username);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag, "onRestart");
    }


    @OnClick(R.id.btn_logout)
    public void logout(View view) {


        PreferenceHelper.getInstance(AcitivityTwo.this).setString(PreferenceHelper.KEY_USERNAME, null);

        Intent intent = new Intent(AcitivityTwo.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
