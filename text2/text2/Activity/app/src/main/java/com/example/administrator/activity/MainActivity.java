package com.example.administrator.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"onCreate is invoke!!!");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart is invoke!!!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart is invoke!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume is invoke!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop is invoke!!!");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy is invoke!!!");
    }
}
