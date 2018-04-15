package com.example.administrator.actionmode;

import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mList;
    private String[] names = new String[]{
            "One","Two","Three","Four","Five"
    };
    private int[] picture = new int[]{
            R.drawable.anzhuoxiaoren,
            R.drawable.anzhuoxiaoren,
            R.drawable.anzhuoxiaoren,
            R.drawable.anzhuoxiaoren,
            R.drawable.anzhuoxiaoren,
    };
    private ListView mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String,Object>>lists = new ArrayList<Map<String, Object>>();
        for (int i=0;i<names.length;i++){
            Map<String,Object>list = new HashMap<String,Object>();
            list.put("number",names[i]);
            list.put("picture",picture[i]);
            lists.add(list);
        }

//        ArrayAdapter adapter = new ArrayAdapter<string>(this,
//                android.R.layout.simple_list_item_multiple_choice, datalist);
//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//
//        ListView.MultiChoiceModeListener callback = new MyMultiChoiceModeListener();
//        lv.setMultiChoiceModeListener(callback);
//        lv.setAdapter(adapter);


        final SimpleAdapter simpleAdapter = new SimpleAdapter(this,lists,R.layout.activity_main,
                new String[]{"number","picture"},
                new int[]{R.id.number,R.id.picture}
                );
        ListView list = (ListView)findViewById(R.id.mylist);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ListView.MultiChoiceModeListener callback1 = new mymultichoicemodelistener();
        list.setMultiChoiceModeListener(callback1);
        list.setAdapter(simpleAdapter);
//        ListView list = (ListView)findViewById(R.id.mylist);

//        list.setAdapter(simpleAdapter);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?>parent,View view,int position,long id){
//                startActionMode(new MyCallback());
//                System.out.println(names[position]+"被单击了");
//                Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_SHORT).show();
//                simpleAdapter.notifyDataSetChanged();//点击item之后通知adapter重新加载view
//                //updateSeletedCount();
//
//                //mylist.setSelector(R.color.colorAccent);
//                //mylist.setBackgroundColor(Color.RED);
//
//            }
//        });



        //mylist = (ListView)findViewById(R.id.mylist);
        //ListView lista = (ListView)findViewById(R.id.mylist);
//        lista.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActionMode(new MyCallback());
//            }
//        });
    }

    private class mymultichoicemodelistener implements ListView.MultiChoiceModeListener{
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            //添加列表项被点击后的响应
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //这里返回true
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            //这里返回true
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            //这里返回true
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }

    public boolean onCreateOptionsMenu(Menu menu){ //加载menu xml文件
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    //处理菜单被选中运行后的事件处理
    public boolean onOptionsItemSelected(MenuItem item){
        //mylist.setBackgroundColor(Color.RED);
       // mylist.getResources().getColor(R.color.colorPrimary);
       // mylist.setSelector(R.color.colorPrimary);
        return true;
    }

    private class  MyCallback implements ActionMode.Callback{
        public boolean onCreateActionMode(ActionMode mode,Menu menu){
            //mode.getMenuInflater().inflate(R.menu.actionmode_menu,menu);//action mode的menu
            return true;
        }
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.option_menu1:
                    Toast.makeText(MainActivity.this,"按钮1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.option_menu2:
                    Toast.makeText(MainActivity.this,"按钮2",Toast.LENGTH_SHORT).show();
                    break;
            }
            actionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    }
}
