package com.example.administrator.simpleadapter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] names = new String[]{
            "Lion","Tiger","Monkey","Dog","Cat","Elephant"
    };

    private int[] imageids =new int[]{
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
    };
    private int[] colors = new int[]{0x30FF0000,0xFFFFFF};
    //private int[] colors = new int[]{0x30FF0000, 0x300000FF,R.color.red,R.color.green};

    private int h,i,j,k,n,m;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String,Object>> listitems = new ArrayList<Map<String, Object>>();
        for(int i=0;i<names.length;i++)
        {
            Map<String,Object> listitem = new HashMap<String,Object>();
            listitem.put("animal",names[i]);
            listitem.put("picture",imageids[i]);
            listitems.add(listitem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listitems,R.layout.activity_main,
                new String[]{"animal","picture"},
                new int[]{R.id.animal,R.id.picture}
        );
        ListView list = (ListView) findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position]+"被单击了");
                String s1 = names[position];
                String s2 = "Lion";
                String s3 = "Tiger";
                String s4 = "Monkey";
                String s5 = "Dog";
                String s6 = "Cat";
                String s7 = "Elephant";
                String s8 = "asd";

                if (s1 == s2){
                    int colorPos = h%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    h=h+1;
                }else if(s1 == s3){
                    int colorPos = i%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    i=i+1;
                }else if(s1 == s4){
                    int colorPos = j%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    j=j+1;
                }else if(s1 == s5){
                    int colorPos = k%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    k=k+1;
                }else if(s1 == s6){
                    int colorPos = n%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    n=n+1;
                }else if(s1 == s7){
                    int colorPos = m%colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    m=m+1;
                }else if(s1 == s8){
                    System.out.print("asd");
                }


                //view.setBackgroundColor(Color.rgb(255,0,0));

                Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_SHORT).show();
            }

        });



//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
//        Button btn = (Button)this.findViewById(R.id.button1);
//        btn.setText("点击我");
//        btn.setOnClickListener(new MyOnClickListener()) ;
//    }
//    private class MyOnClickListener implements OnClickListener{
//        public void onClick(View v){
//            Toast.makeText(MainActivity.this,"你好",Toast.LENGTH_LONG).show();
//        }
    }
}


