# Read ME

#### 一.实验名称

​	创建自定义布局的AlertDialog

#### 二.实验目的

​	实验如下图:

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/AlertDialog/app/shiyanyaoqiu.png" height="400" alt="Screenshot"/>

#### 三.实验步骤

##### 1.制作用来相应点击事件的按钮

制作按钮的布局文件，用来实现跳转

```
    <Button
        android:id="@+id/show"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/btn1"
        />
```

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/AlertDialog/app/anniu.png" height="400" alt="Screenshot"/>

##### 2.实现AlertDialog

​	新建一个继承AlertDialog的类，引入自定义的布局文件，传入context，在onCreate中加载布局，获取view，为按钮设置点击事件

```
public class WifiDialog extends AlertDialog implements View.OnClickListener {
		………………
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建
    }
    @Override
    public void onClick(View view) {
       //实现点击事件
    }
```

​	自定义的布局文件如下：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        //标题的样式
        /
    <TableLayout
        //设置中间的内容的样式
        >
        <EditText
            //用户名
            />
        <EditText
       		 //密码
            />
    </TableLayout>
    <View
		//按钮的边框	
        >
        </View>
    <LinearLayout
        <Button
            //取消按钮
            />

        <View
			//按钮分割线
		/> 	
        <Button
            //确定按钮
            />
    </LinearLayout>
</LinearLayout>
```

##### 3.主函数的处理

在主函数中调用，并使用show方法，设置按钮的点击事件

```
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
```

#### 三.实验结果

​	点击主页面的AlertDialog按钮，可以弹出自定义样式的对话框

​	截图如下：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/AlertDialog/app/shiyanjietu.png" height="400" alt="Screenshot"/>

#### 四.心得体会

​	开始没有认清实验的难点，写出来的是系统默认的样式，如下图：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/AlertDialog/app/cuo1.png" height="400" alt="Screenshot"/>

后来发现想要修改样式很多都需要重写，白费了不少力气。还有之前的边框用按钮边框做出来是这样的，<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/AlertDialog/app/cuo2.png" height="400" alt="Screenshot"/>看起来很别扭，后来用view画分割线好了很多。很多东西都需要一点点的尝试。

#### 五.参考文献

```
http://www.jb51.net/article/123832.htm
```