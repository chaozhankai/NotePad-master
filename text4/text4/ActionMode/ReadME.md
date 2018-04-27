# Read ME

#### 一.实验名称

​	创建上下文操作模式(ActionMode)的上下文菜单

#### 二.实验目的

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/ActionMode/app/shiyanmudi.png" height="400" alt="Screenshot"/>

#### 三.实验步骤

##### 1.实现listview的列表

​	与之前的实验类似，不在赘述

​	使用listview创建list，为listitem创建actionmode形式的上下文菜单

​	点击进入actionmode模式，临时占用了actionbar

​	<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/ActionMode/app/listviewliebiao.png" height="400" alt="Screenshot"/>

##### 2.相应点击事件，实现actionmode

设置模式为多选模式，实现callback，调用startactionmode

```
ListView list = (ListView)findViewById(R.id.mylist);
list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
ListView.MultiChoiceModeListener callback1 = new mymultichoicemodelistener();
list.setMultiChoiceModeListener(callback1);
list.setAdapter(simpleAdapter);
```

##### 

#### 三.实验结果

​	长按相应的列表进入actionmode模式，未实现多选及显示选中个数的功能

​	实验截图如下：

​	

<img src="https://github.com/chaozhankai/AS-product/blob/master/text4/text4/ActionMode/app/acshiyanjietu.png" height="400" alt="Screenshot"/>

​	

#### 四.心得体会

​	本次实验没有实现多选，虽然研究了CHOICE_MODE_MULTIPLE_MODAL这个模式，知道它的下面有实现选择，判断是否选择和选择个数的函数，但是很难学习，选择了selector实现选择。但是只实现了单选，再选择别的之前的就会取消，主要是android:state_activated="true"没法实现，只能实现android:state_pressed="true"，还在思考原因。

#### 五.参考文献

https://www.2cto.com/kf/201606/514933.html

http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1105/1906.html

http://www.jb51.net/article/106637.htm

https://www.cnblogs.com/guojinyu/p/6664746.html

https://blog.csdn.net/tw19911005/article/details/48352183

https://blog.csdn.net/u014450015/article/details/50220671

