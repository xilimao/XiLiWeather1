### 希狸天气总结
1. 使用SharedPreferences进行存储和读取信息。编辑了一个工具类SaveArrayListUtil。根据功能需求有如下几个方法：保存、读取全部数组、读取第一位数组、删除指定位置数据。
2. 通过查阅解决了ListView和ScrollView的滑动冲突，可以使手指在ListView上时滑动Listview，不在listView时滑动ScrollView。
- 使用自定义ListView - MyList
- 有关触摸事件： [看到的一个讲解，分享下](http://www.jianshu.com/p/35a8309b9597)
    ```
    public class MyList extends ListView {
    public MyList(Context context) {
        super(context);
    }

    public MyList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写 拦截TouchEvent
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //事件在当前ViewGroup中处理，不再向下传递，即返回不到MainActivity,所以不会触发ScrollVIew的触摸事件
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //不拦截
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(ev);
        }
    }
    ```
- 在布局中引用自定义ListView
    ```
     <com.example.sxhs.xiliweather.DisplayXinxi.MyList
                android:id="@+id/week_list"
                android:layout_width="match_parent"
                android:layout_height="250dp"></com.example.sxhs.xiliweather.DisplayXinxi.MyList>

    ```
3.使用自定义组合控件
