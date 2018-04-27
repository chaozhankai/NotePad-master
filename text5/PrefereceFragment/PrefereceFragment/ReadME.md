# Read ME

#### 一.实验名称

​	实现设置Activity

#### 二.实验目的

​	按要求实现目录界面，并实现相应的功能

#### 三.实验步骤

##### 1.创建xml目录并新建xml文件作为主目录

​	例：

```
<PreferenceCategory android:title="Dialog-based preferences">
    输入文字
    <EditTextPreference
        android:key="myEditTextPreference"
        android:title="Edit Text Preference"
        android:summary="An example that uses an edit text preference"
        >
    </EditTextPreference>
    <ListPreference
        android:key="myListPreference"
        android:title="List preference"
        android:summary="An example that uses a list preference"
        android:entries="@array/list"
        android:entryValues="@array/list_value"
        android:defaultValue="@array/list_value2"
        android:dialogTitle="Choose One"
        >
        android:entries 弹出的对话框中，列表显示的文本内容，这里是弹出一个数组
        android:entryValues 与android:entries相对应的值
        android:defaultValue 当对应值不存在时的默认值
        android:dialogTitle 弹出的对话框中的标题
        要想让程序记住之前选择的状态，必须设置key的值，PreferenceManager可以以其为参数通过 findPreference 获取指定的 preference
    </ListPreference>
</PreferenceCategory>
```

##### 2.调用相应布局文件

```
addPreferencesFromResource(R.xml.preferences);
```

##### 3.实现listperference的对话框

```
<resources>
    用来实现ListPreference弹出来的对话框
    <string-array name="list">
        <item>Alpha Option 01</item>
        <item>Beta Option 02</item>
        <item>Charlie Option 03</item>
    </string-array>
…………
    </string-array>
```

##### 4.实现跳转的preferencescreen

主文件：

```
public class MainActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        addPreferencesFromResource(R.xml.new_preferencescreen);
```

布局文件：

```
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <PreferenceCategory android:title="In-line preferences">
        弹出的界面
        <CheckBoxPreference
            android:title="CheckBoxPreference"
            android:summary="This is a checkbox"
            android:key="myCheckBoxPreference"
            >
        </CheckBoxPreference>
    </PreferenceCategory>
</PreferenceScreen>
```

调用：

```
弹出新的preferenceScreen，这里用目标文件的包名和类名进行关联，包名详见左侧的文件目录
<intent
    android:targetPackage="com.example.administrator.preferecefragment"
    android:targetClass="com.example.administrator.preferecefragment.MainActivity"
    />
```

##### 5.在AndroidManifest.xml中声名

```
<activity android:name=".MainActivity">
</activity>
<activity android:name=".PreferenceActivityTest">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

#### 三.实验结果

主界面：  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/zhujiemian.png" height="400" alt="Screenshot"/>

Edit Text  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/edit%20text.png" height="400" alt="Screenshot"/>

List Perference  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/list.png" height="400" alt="Screenshot"/>

Screen Preference  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/tiaozhuan.png" height="400" alt="Screenshot"/>

Intent Preference  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/wangye.png" height="400" alt="Screenshot"/>

Parent-Child Perference  

<img src="https://github.com/chaozhankai/AS-product/blob/master/text5/PrefereceFragment/PrefereceFragment/app/fuzikuang.png" height="400" alt="Screenshot"/>

​	

#### 四.心得体会

​	书上说是preferenceActivity只负责加载选项设置列表的布局文件， preferenceFragment才负责加载选项设置的布局文件，但是preference-headers好多都做不到，也没用到preference-headers

