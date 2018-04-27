package com.example.administrator.preferecefragment;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

//preferenceActivity只负责加载选项设置列表的布局文件，
// preferenceFragment才负责加载选项设置的布局文件
//在Android3.0之前。PreferenceActivity是用来呈现Preference Screens的，所以假设要支持Android3.0之前的系统，
// 还是要继承PreferenceActivity类来呈现Preference Screens

public class PreferenceActivityTest extends PreferenceActivity{
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);//加载布局文件
        //在左上角设置一个标题按钮
//        if (hasHeaders()){
//            Button button = new Button(this);
//            button.setText(R.string.PrefereceFragment);
//            setListFooter(button);//将该按钮添加到该界面上
//        }
    }
    //setContentView(R.xml.preferences);
    //重写了该方法，用来加载布局文件
    // 为了能够显示出 headers 中的布局列表，需要在继承的 PreferenceActivity 类中实现 onBuildHeaders() 回调方法：
//    public void onBuildHeaders(List<Header>target){
//       //加载选项设置列表的布局文件
//        loadHeadersFromResource(R.xml.preference_headers,target);
//    }

}
