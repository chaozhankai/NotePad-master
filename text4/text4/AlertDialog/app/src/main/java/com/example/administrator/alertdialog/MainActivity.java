package com.example.administrator.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mDialogBtn;
    private WifiDialog mDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDialogBtn = (Button) findViewById(R.id.show);
        mDialogBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.show) {
            mDialog = new WifiDialog(this);
            mDialog.show();
        }
    }
}

//http://www.jb51.net/article/123832.htm
//参考地址



