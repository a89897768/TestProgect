package com.example.test;

import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.AppBarLayout;

public class CustomAppBarLayout extends AppBarLayout {
    private GestureDetector mGestureDetector = new GestureDetector(getContext(), createGestureDetectorListener());
    private boolean mIsExpanded = true;
    private boolean mIsCollapsed = false;
    private CustomWebView mWebView;

    public CustomAppBarLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public CustomAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnOffsetChangedListener(createOffsetChangedListener());
    }

    private int mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    private boolean mIsScroll = false;
    private float mLastY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        requestDisallowInterceptTouchEvent(true);
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN: {
//                mIsScroll = false;
//                mLastY = ev.getY();
//                break;
//            }
//
//            case MotionEvent.ACTION_MOVE: {
//                float newY = ev.getY() - mLastY;
//                Log.i("Test CustomAppBarLayout",String.valueOf(newY));
//                if (!mIsScroll && Math.abs(newY) > mTouchSlop) {
//                    mIsScroll = true;
//                }
//                break;
//            }
//        }
//
//        if (mIsScroll) {
//            if (mIsCollapsed && ev.getY() - mLastY < 0) {
//                mWebView.requestDisallowInterceptTouchEvent(true);
//            } else {
//                mWebView.requestDisallowInterceptTouchEvent(false);
//            }
//        }
//
//        mLastY = ev.getY();
//        requestDisallowInterceptTouchEvent(true);
        Log.i("CustomAppBarLayout", String.valueOf(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//            if(event.getAction() == MotionEvent.ACTION_DOWN){
//                return false;
//            }
//        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
//            super.onTouchEvent(event);
//            return false;
//        }
        return super.onTouchEvent(event);
    }

//        @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//        return super.onInterceptTouchEvent(ev);
//    }

    private AppBarLayout.OnOffsetChangedListener createOffsetChangedListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mIsExpanded = verticalOffset == 0;
                mIsCollapsed = Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange();
            }
        };
    }

    private GestureDetector.OnGestureListener createGestureDetectorListener() {
        return new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("Test CustomAppBarLayout", String.valueOf(distanceY));
//                if(distanceY > 0 && getScrollY()!=0){
//                    requestDisallowInterceptTouchEvent(true);
//                }else {
//                    requestDisallowInterceptTouchEvent(false);
//                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        };
    }

    public void setWebView(CustomWebView webView) {
        mWebView = webView;
    }
}
