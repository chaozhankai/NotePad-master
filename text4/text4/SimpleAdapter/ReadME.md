# Read ME

#### 一.实验要求

​	使用SimpleAdapter实现图文列表，使用Toast显示选中的列表

#### 二.实验步骤

##### 1.layout文件

​	定义一个listview用来存储

​	定义一个imageview来存储图片

​	定义一个textview来存储文字

​	并全赋予相应的id

​	例如：

```
<ListView
    android:id="@+id/mylist"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"

    >
</ListView>
```

##### 2.drawable文件

​	放入相应的图片，英语的粘贴为paste不是很适应

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/SimpleAdapter/app/drawable.png" height="400" alt="Screenshot"/>

##### 3.values

​	之前打算用selector实现变色没有实现，整个values没有什么修改



##### 4.MainActvity.java

```
private String[] names = new String[]{
        …………
};

private int[] imageids =new int[]{
        R.drawable.lion,
        ………………
};
private int[] colors = new int[]{0x30FF0000,0xFFFFFF};
```

​	首先定义三个数组，names，imageids，colors分别来存放对应的名字，图片和颜色，我也不知道为什么颜色用的是0x30FF0000,0xFFFFFF这种形式来表示，分别代表的是透明度，红绿蓝。

```
List<Map<String,Object>> listitems = new ArrayList<Map<String, Object>>();
for(int i=0;i<names.length;i++)
{
    Map<String,Object> listitem = new HashMap<String,Object>();
    listitem.put("animal",names[i]);
    listitem.put("picture",imageids[i]);
    listitems.add(listitem);
}
```

​	然后向listitems中放入文字和图片

```
SimpleAdapter simpleAdapter = new SimpleAdapter(this,listitems,R.layout.activity_main,
        new String[]{"animal","picture"},
        new int[]{R.id.animal,R.id.picture}
);
```

​	其次创建simpleadapter需要五个参数，

​		第一个当前上下文；

​		listitems是数据源；

​		R.layout.activity_main是布局文件；

​		new String[]{"animal","picture"}是指出提取作为与key对顶的value的来生成列表项的；

​		new int[]{R.id.animal,R.id.picture}是指需要填充的组件。

```
list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       ……………………
        Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_SHORT).show();
    }

});
```

​	之后来绑定事件监听器，实现激发时的方法

```
if (s1 == s2){
    int colorPos = h%colors.length;
    view.setBackgroundColor(colors[colorPos]);
    h=h+1;
}else if………………
```

​	通过点击每一条的次数，来修改背景颜色

```
Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_SHORT).show();
```

​	通过toast来显示选中的信息

#### 三.实验结果

​	实现了利用simpleadaper来显示界面，并且点击相应的条背景颜色变色，再次点击颜色取消，屏幕中下方显示选中的信息。

​	截图如下：

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/SimpleAdapter/app/SimlpeAdapter.png" height="400" alt="Screenshot"/>

#### 四.心得体会

​	通过本次实验，发现了书上的内容也不一定对，就像书上用了两个layout并用的是linearlayout一样，不一定很好或者适合初学者，也发现很多java基本内容都忘了，就像switch case似乎不能判断string类型一样，写了很长时间才发现问题，还有就是很多内容和格式都是按回车自己出来的，并没有明白都是些什么，比如说：List<Map<String,Object>> listitems = new ArrayList<Map<String, Object>>();所以虽然做出来了，但是好多还是不懂，也难免半知半解说不出来程序代码的解释，表达又欠佳难以言述，日后细细研究再做修改