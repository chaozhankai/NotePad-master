package com.example.administrator.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);
        //为btn按钮添加一个监听器
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText url = (EditText)findViewById(R.id.url);
                String data = url.getText().toString();
                Intent intent = new Intent();
                //String data = "https://www.baidu.com/?tn=64075107_1_dg";
                //根据指定字符串解析出uri对象
                Uri uri = Uri.parse(data);
                //为intent设置action属性
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
