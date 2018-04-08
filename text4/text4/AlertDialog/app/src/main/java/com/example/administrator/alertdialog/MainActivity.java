package com.example.administrator.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.btn) ;
    }
    public void customView(View source){
    TableLayout login =(TableLayout)getLayoutInflater()
            .inflate(R.layout.login,null);
    new AlertDialog.Builder(this)
            .setTitle("ANDROID")
            .setView(login)
            .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int whitch) {

                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //此处填写取消登录做的事情，同样啥都没做
                }
            })
            .create()
            .show();
}

}



