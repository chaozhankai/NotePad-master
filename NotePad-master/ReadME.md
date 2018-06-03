# Read ME

# 一.实验名称

​	“&nbsp;” “&nbsp;” NOTEPAD笔记本应用

# 二.实验要求与目的

-基本要求

1.NoteList中显示条目增加时间戳显示 

2.添加笔记查询功能（根据标题查询）

+附加功能

1.UI美化

2.文本字体大小颜色修改

# 三.实验步骤

## （1）.程序大致理解

程序组成结构如下图：

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/mulu.png" height="400" alt="Screenshot"/>

### 1.Class

类NoteEditor是用来修改记事条目标题的类，NotePad是用来声名Static常量的类，NotePadProvider是ContentProvider类，也是最重要的一个类，NoteSearch是增加的用来实现搜索的类，NoteList是显示在主页面上的内容，TitleEditor是用来编辑标题的类。

### 2.layout

#### (1).note_editor.xml

用来实现NoteEditor的布局文件，其内容只包含一个<View>

#### （2）.note_search.xml

新增加的用来实现搜索界面的页面，是一个线性布局，其中包含一个SearchView和一个ListView，分别用来实现搜索功能和显示搜索出来的条目

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <SearchView
        android:id="@+id/search_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:queryHint="请输入搜索内容..."
        >
    </SearchView>

    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    </ListView>
</LinearLayout>
```

#### (3).noteslist_item.xml

NoteList中用来显示每一条笔记的布局的布局文件，其中包含两个TextView，一个用来存放标题，另一个用来存放时间。

#### （4）.title_editor.xml

修改标题时显示的页面，包含一个EditText用来修改标题，一个Button用来提交修改的标题

### 3.Menu

#### (1).editor_options_menu.xml

进入文本修改界面上方显示的menu，包含save,revert,delete,front_size,front_color几个选项

```
android:showAsAction="ifRoom|withText"
```

如果空间足够的话默认会和图标一并显示出来

#### （2）.list_context_menu.xml

长按时会显示的界面，包含open,copy,delete

#### （3）.list_options_menu.xml

主页面上方显示的目录，包含新增加笔记，复制和搜索

## 2.时间戳显示的实现

首先我们先看一下创建的数据库，数据库中已经存在了COLUMN_NAME_CREATE_DATE，COLUMN_NAME_MODIFICATION_DATE，创建的时间和修改的时间都已经在数据库中。

```
public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + NotePad.Notes.TABLE_NAME + " ("
            + NotePad.Notes._ID + " INTEGER PRIMARY KEY,"
            + NotePad.Notes.COLUMN_NAME_TITLE + " TEXT,"
            + NotePad.Notes.COLUMN_NAME_NOTE + " TEXT,"
            + NotePad.Notes.COLUMN_NAME_CREATE_DATE + " INTEGER,"
            + NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE + " INTEGER"
            + NotePad.Notes.COLUMN_NAME_TEXT_COLOR + "INTEGER" //新增加的修改字体颜色
            + NotePad.Notes.COLUMN_NAME_TEXT_SIZE + "INTEGER" //新增加的修改字体大小
            + ");");
}
```

那我们需要的就是在创建和修改的时候对数据库中的时间进行相应的显示。

  

创建时显示的时间是在NotePadProvider的insert函数实现的，

```
Long now = Long.valueOf(System.currentTimeMillis());
```

```
if (values.containsKey(NotePad.Notes.COLUMN_NAME_CREATE_DATE) == false) {
    values.put(NotePad.Notes.COLUMN_NAME_CREATE_DATE, now);
}
// If the values map doesn't contain the modification date, sets the value to the current time.
if (values.containsKey(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE) == false) {
    values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, now);
}
```

我们需要对它进行修改

```
Date date = new Date(now);
SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
String dateTime = format.format(date);
```

然后用dateTime替换now

接下来我们需要实现时间的显示，先在noteslist_item.xml中增加一个用来显示时间的TextView

```
<TextView
    android:id="@+id/text2"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:textColor="@color/yellow"
    android:textSize="20sp"
    />
```

然后在NotesList的数据定义中增加修改时间

```
private static final String[] PROJECTION = new String[] {
        NotePad.Notes._ID, // 0
        NotePad.Notes.COLUMN_NAME_TITLE, // 1
        NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,//在这里加入了修改时间的显示
};
```

查询数据还是按原来的方法，不需要修改

```
//NoteList使用SimpleCursorAdapter来装配数据，首先查询数据库的内容，
// 如下代码所示，这里使用ContentProvider默认的URI。
Cursor cursor = managedQuery(
    getIntent().getData(),            // Use the default content URI for the provider.
    PROJECTION,                       // Return the note ID and title for each note.
    null,                             // No where clause, return all records.
    null,                             // No where clause, therefore no where column values.
    NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
);
```

装配的时候需要装配相应的日期，所以dataColumns,viewIDs这两个参数需要加入时间

```
String[] dataColumns = {
        NotePad.Notes.COLUMN_NAME_TITLE,
        NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE
} ;
```

```
int[] viewIDs = { android.R.id.text1 ,R.id.text2};
```

然后通过SimpleCursorAdapter来进行装配

```
// Creates the backing adapter for the ListView.
//然后通过SimpleCursorAdapter来进行装配：
SimpleCursorAdapter adapter
    = new SimpleCursorAdapter(
              this,                             // The Context for the ListView
              R.layout.noteslist_item,          // Points to the XML for a list item
              cursor,                           // The cursor to get items from
              dataColumns,
              viewIDs
      );
```

时间就会显示在相应的条目上

如图：  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/shijian.png" height="400" alt="Screenshot"/>

这时候可能会发现时间差了8小时，或者发现虚拟机的时间和真实的时间差了八小时，即使把时间改过来下次时间还是错误的，这是由于时区的问题带来的，可以在代码中增加如下的内容：

```
format.setTimeZone(TimeZone.getTimeZone("GMT"));
```

这样的话就会采用中国北京的标准时区，时间就会改正过来，或者在手机中修改相应的时区，光修改时间是没用的  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/shuqu.png" height="400" alt="Screenshot"/>

## 3.添加笔记查询功能

首先，我们要在list_options_menu.xml中新建一个查询的按钮，作为查询功能实现的媒介

```
<item
    android:id="@+id/menu_search"
    android:icon="@drawable/search"
    android:title="@string/menu_search"
    android:showAsAction="always"
    />
```

这时候查询按钮就会显示在NotesList的主界面上  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/search%20(2).png" height="400" alt="Screenshot"/>

然后增加点击之后的反应，在onOptionsItemSelected的switch (item.getItemId())中添加对应menu_search的case：

```
case R.id.menu_search:
    startActivity(new Intent(Intent.ACTION_SEARCH,getIntent().getData()));
    return true;
```

在其中加入隐式Intent的跳转，方式仿照上面的写法

```
startActivity(new Intent(Intent.ACTION_SEARCH,getIntent().getData()));
```

然后在AndroidManifest.xml中加入相应的声名

```
<activity
    android:name=".NoteSearch"
    android:label="NoteSearch"
    >

    <intent-filter>
        <action android:name="android.intent.action.NoteSearch" />
        <action android:name="android.intent.action.SEARCH" />
        <action android:name="android.intent.action.SEARCH_LONG_PRESS" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:mimeType="vnd.android.cursor.dir/vnd.google.note" />
        <!--1.vnd.android.cursor.dir代表返回结果为多列数据-->
        <!--2.vnd.android.cursor.item 代表返回结果为单列数据-->
    </intent-filter>
</activity>
```

之后就可以跳转到相应的搜索界面  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/sousuoliebiao.png" height="400" alt="Screenshot"/>

搜索采用SearchView实现，并通过实现implements SearchView.OnQueryTextListener接口来完成查询

实现方法类似与之前，首先定义一个数组来存放数据

```
private static final String[] PROJECTION = new String[]{
        NotePad.Notes._ID, // 0
        NotePad.Notes.COLUMN_NAME_TITLE, // 1
        NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,//在这里加入了修改时间的显示
};
```

然后获取Intent

```
Intent intent = getIntent();// Gets the intent that started this Activity.
// If there is no data associated with the Intent, sets the data to the default URI, which
// accesses a list of notes.
if (intent.getData()==null){
    intent.setData(NotePad.Notes.CONTENT_URI);
}
getListView().setOnCreateContextMenuListener(this);
```

之后对Search的样式做相应的修改

```
mSearchView = (SearchView)findViewById(R.id.search_view);//注册监听器
mSearchView.setIconifiedByDefault(false); //显示搜索的天幕，默认只有一个放大镜图标
mSearchView.setSubmitButtonEnabled(true); //显示搜索按钮
mSearchView.setBackgroundColor(getResources().getColor(R.color.blue_green)); //设置背景颜色
mSearchView.setOnQueryTextListener(this);
```

实现相应的接口

```
@Override
public boolean onQueryTextSubmit(String s) {
    return false;
}
```

可以进行一些关于键盘弹出收回时的操作，这里采用默认，没有进行修改

```
 @Override
    public boolean onQueryTextChange(String s) { //Test改变的时候执行的内容
        //Text发生改变时执行的内容
        String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";//查询条件
        String[] selectionArgs = { "%"+s+"%" };//查询条件参数，配合selection参数使用,%通配多个字符

        //查询数据库中的内容,当我们使用 SQLiteDatabase.query()方法时，就会得到Cursor对象， Cursor所指向的就是每一条数据。
        //managedQuery(Uri, String[], String, String[], String)等同于Context.getContentResolver().query()
        Cursor cursor = managedQuery(
                getIntent().getData(),            // Use the default content URI for the provider.用于ContentProvider查询的URI，从这个URI获取数据
                PROJECTION,                       // Return the note ID and title for each note. and modifcation date.用于标识uri中有哪些columns需要包含在返回的Cursor对象中
                selection,                        // 作为查询的过滤参数，也就是过滤出符合selection的数据，类似于SQL的Where语句之后的条件选择
                selectionArgs,                    // 查询条件参数，配合selection参数使用
                NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.查询结果的排序方式，按照某个columns来排序，例：String sortOrder = NotePad.Notes.COLUMN_NAME_TITLE
        );

        //一个简单的适配器，将游标中的数据映射到布局文件中的TextView控件或者ImageView控件中
        String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,  NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE };
        int[] viewIDs = { android.R.id.text1 , R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,                   //context:上下文
                R.layout.noteslist_item,         //layout:布局文件，至少有int[]的所有视图
                cursor,                          //cursor：游标
                dataColumns,                     //from：绑定到视图的数据
                viewIDs                          //to:用来展示from数组中数据的视图
                                                 //flags：用来确定适配器行为的标志，Android3.0之后淘汰
        );
        setListAdapter(adapter);
        return true;
    }
}
```

这里实现数据的查询，首先进行数据库的查询

```
String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";//查询条件
String[] selectionArgs = { "%"+s+"%" };//查询条件参数，配合selection参数使用,%通配多个字符

//查询数据库中的内容,当我们使用 SQLiteDatabase.query()方法时，就会得到Cursor对象， Cursor所指向的就是每一条数据。
//managedQuery(Uri, String[], String, String[], String)等同于Context.getContentResolver().query()
Cursor cursor = managedQuery(
        getIntent().getData(),            // Use the default content URI for the provider.用于ContentProvider查询的URI，从这个URI获取数据
        PROJECTION,                       // Return the note ID and title for each note. and modifcation date.用于标识uri中有哪些columns需要包含在返回的Cursor对象中
        selection,                        // 作为查询的过滤参数，也就是过滤出符合selection的数据，类似于SQL的Where语句之后的条件选择
        selectionArgs,                    // 查询条件参数，配合selection参数使用
        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.查询结果的排序方式，按照某个columns来排序，例：String sortOrder = NotePad.Notes.COLUMN_NAME_TITLE
);
```

方法备注已经说明的很详细

之后写一个适配器，把游标中的数据映射到ListView中

```
//一个简单的适配器，将游标中的数据映射到布局文件中的TextView控件或者ImageView控件中
String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,  NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE };
int[] viewIDs = { android.R.id.text1 , R.id.text2 };
SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        this,                   //context:上下文
        R.layout.noteslist_item,         //layout:布局文件，至少有int[]的所有视图
        cursor,                          //cursor：游标
        dataColumns,                     //from：绑定到视图的数据
        viewIDs                          //to:用来展示from数组中数据的视图
                                         //flags：用来确定适配器行为的标志，Android3.0之后淘汰
);
setListAdapter(adapter);
return true;
```

到此搜索功能也已经实现了  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/sosuoshixian.png" height="400" alt="Screenshot"/>

## 4.附加功能的实现

首先进行界面的美化，为界面换一个Theme

```
android:theme="@android:style/Theme.Black"
```

修改一下条目显示的颜色，以及鼠标点击之后的颜色

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_focused="true" android:drawable="@color/orange"/>
    <item android:state_pressed="true" android:drawable="@color/yellow" />
    <item android:drawable="@color/light_blue"/>
</selector>
```

  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/dianjibianse.png" height="400" alt="Screenshot"/>

在文本编辑界面中添加改变字体大小和字体颜色的功能

首先在editor_options_menu.xml中添加对应的菜单

```
<item
    android:id="@+id/font_size"
    android:title="@string/font_size">
    <!--子菜单-->
    <menu>
        <!--定义一组单选菜单项-->
        <group>
            <!--定义多个菜单项-->
            <item
                android:id="@+id/font_10"
                android:title="@string/font10"
                />

            <item
                android:id="@+id/font_16"
                android:title="@string/font16" />
            <item
                android:id="@+id/font_20"
                android:title="@string/font20" />
        </group>
    </menu>
</item>

<item
    android:title="@string/font_color"
    android:id="@+id/font_color"
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
```

对应的菜单就会在界面上方显示  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/ziti.png" height="400" alt="Screenshot"/>

然后在NoteEditor的onOptionsItemSelected(MenuItem item)中添加相应的case来相应事件

```
case R.id.font_10:

    mText.setTextSize(20);
    Toast toast =Toast.makeText(NoteEditor.this,"修改成功",Toast.LENGTH_SHORT);
    toast.show();
    finish();
    break;

case R.id.font_16:
    mText.setTextSize(32);
    Toast toast2 =Toast.makeText(NoteEditor.this,"修改成功",Toast.LENGTH_SHORT);
    toast2.show();
    finish();
    break;
case R.id.font_20:
    mText.setTextSize(40);
    Toast toast3 =Toast.makeText(NoteEditor.this,"修改成功",Toast.LENGTH_SHORT);
    toast3.show();
    finish();
    break;
case R.id.red_font:
    mText.setTextColor(Color.RED);
    Toast toast4 =Toast.makeText(NoteEditor.this,"修改成功",Toast.LENGTH_SHORT);
    toast4.show();
    finish();
    break;
case R.id.black_font:
    mText.setTextColor(Color.BLACK);
    Toast toast5 =Toast.makeText(NoteEditor.this,"修改成功",Toast.LENGTH_SHORT);
    toast5.show();
    finish();
    break;
```

这时候修改功能已经实现，但是下次进入时还是原来的字体大小，我们需要在数据库中加入相应的参数来保存字体大小和颜色的信息

```
db.execSQL("CREATE TABLE " + NotePad.Notes.TABLE_NAME + " ("
        + NotePad.Notes._ID + " INTEGER PRIMARY KEY,"
        + NotePad.Notes.COLUMN_NAME_TITLE + " TEXT,"
        + NotePad.Notes.COLUMN_NAME_NOTE + " TEXT,"
        + NotePad.Notes.COLUMN_NAME_CREATE_DATE + " INTEGER,"
        + NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE + " INTEGER"
        + NotePad.Notes.COLUMN_NAME_TEXT_COLOR + "INTEGER" //新增加的修改字体颜色
        + NotePad.Notes.COLUMN_NAME_TEXT_SIZE + "INTEGER" //新增加的修改字体大小
        + ");");
```

具体的实现方法和搜索一样，这里不再赘述，最终效果如下：  

<img src="https://github.com/chaozhankai/NotePad-master/blob/master/NotePad-master/app/xiugaizhihou.png" height="400" alt="Screenshot"/>

## 5.参考文献

```
https://www.cnblogs.com/jeffen/p/6958235.html searchView讲解
https://blog.csdn.net/u010376098/article/details/51461363  SearchView实时搜索初体验
```

