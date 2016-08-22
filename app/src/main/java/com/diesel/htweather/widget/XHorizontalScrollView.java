package com.diesel.htweather.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class XHorizontalScrollView extends HorizontalScrollView {

    private OnScrollListener onScrollListener;

    private OnScrollChanged mOnScrollChanged;

    /**
     * 主要是用在用户手指离开MyHorizontalScrollView，MyHorizontalScrollView还在继续滑动，我们用来保存滚动的距离，然后做比较
     */
    private int lastScrollX;

    public XHorizontalScrollView(Context context) {
        this(context, null);
    }

    public XHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置滚动接口
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setOnScrollChanged(OnScrollChanged chaged) {
        this.mOnScrollChanged = chaged;
    }

    /**
     * 设置滚动改变接口
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mOnScrollChanged.onChanged(l);
        super.onScrollChanged(l, t, oldl, oldt);
    }


    /**
     * 用于用户手指离开MyHorizontalScrollView的时候获取MyHorizontalScrollView滚动的距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollX = XHorizontalScrollView.this.getScrollX();
            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollX != scrollX) {
                lastScrollX = scrollX;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollX);
            }

        }

        ;

    };

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onScrollListener != null) {
            onScrollListener.onScroll(lastScrollX = this.getScrollX());
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = ev.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                if (ev.getRawX() - lastX > 0) {
                    //按下有滑动
                    handler.sendMessageDelayed(handler.obtainMessage(), 5);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    float lastX = 0;


    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {

        /**
         * 回调方法， 返回ScrollView滑动的x方向距离
         */
        public void onScroll(int scrollX);
    }

    /**
     * 滚动发生变化接口
     *
     * @author: 2015-4-9 下午2:51:51
     */
    public interface OnScrollChanged {

        public void onChanged(int left);
    }

}
