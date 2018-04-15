# Read ME

#### 一.实验名称

​	使用XML定义菜单

#### 二.实验目的

​	使用XML定义菜单，写出三个子菜单，分别是字体大小，普通菜单项，字体颜色菜单。字体大小下包含大中小三个选项对应实现把字体大小改变为10号字，16号字，20号字。普通菜单选项点击后弹出Toast提示。字体颜色下有红色黑色两个选项，点击后修改字体的颜色，默认的颜色为灰色。

#### 三.实验步骤

##### 1.写出主页显示的文字

​	自定义一个linearLoyout，实现一个Textview来显示文字

```
<TextView
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:text="@string/text"
    android:textSize="15pt"
    android:id="@+id/txt">
</TextView>
```

##### 2.写出menu界面

​	新建一个 menu目录，在下面新建一个menu文件，来实现菜单的显示，这里定义了三个菜单以及他们下面的子菜单

```
<?xml version="1.0" encoding="utf-8"?>
<!--根元素，无任何属性-->
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <!--定义菜单项-->
<item
    android:title="@string/font_size"
    >
    <!--子菜单-->
    <menu>
        <!--定义一组单选菜单项-->
        <group >
            <!--定义多个菜单项-->
            <item
                android:id="@+id/font_10"
                android:title="@string/font10"/>
            <item
                android:id="@+id/font_16"
                android:title="@string/font16"/>
            <item
                android:id="@+id/font_20"
                android:title="@string/font20"/>
        </group>
    </menu>
</item>

    <!--定义一个普通菜单项-->
    <item
        android:id="@+id/plain_item"
        android:title="@string/plain_item"/>

    <item
        android:title="@string/font_color"
        >
        <menu>
            <!--定义一组普通菜单项-->
            <group>
                <!--定义两个菜单项-->
                <item
                    android:id="@+id/red_font"
                    android:title="@string/red_title" />
                <item
                    android:title="@string/black_title"
                    android:id="@+id/black_font"/>
            </group>
        </menu>
    </item>


</menu>
```

##### 3.java实现变换操作

编写MainActivity，实现业务逻辑

```
public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.txt);
        registerForContextMenu(txt);//为文本框注册上下文菜单
    }

    //在JAVA文件中对资源文件进行加载
    public boolean onCreateOptionsMenu(Menu menu)//开发选项菜单重写的方法
    {
        MenuInflater inflater = new MenuInflater(this);//菜单动态加载类
        inflater.inflate(R.menu.menu_main,menu);//调用inflate方法解析菜单文件
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.font_10:
                txt.setTextSize(20);
                break;
            case R.id.font_16:
                txt.setTextSize(32);
                break;
            case R.id.font_20:
                txt.setTextSize(40);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                txt.setTextColor(Color.BLACK);
                break;
            case R.id.plain_item:
                Toast toast =Toast.makeText(MainActivity.this,"这是普通单击项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }
}
```

#### 三.实验结果

​	点击右上角的三个点，可以弹出菜单，点击菜单下的选项可以实现相应的功能。

​	实验截图如下：

​	程序主菜单：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/XMLmenu/app/zhucaidan.png" height="400" alt="Screenshot"/>

​	字体大小变换：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/XMLmenu/app/zitidaxiao.png" height="400" alt="Screenshot"/>

​	普通点击项：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/XMLmenu/app/putongdianjixiang.png" height="400" alt="Screenshot"/>

​	字体颜色变换：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/XMLmenu/app/zitiyanse.png" height="400" alt="Screenshot"/>

#### 四.心得体会

​	本实验相对简单，主要需要学会的是在res下建立menu子目录，并在该子目录下定义菜单资源文件，需要注意的就是setTextSize和setTextColor的使用

