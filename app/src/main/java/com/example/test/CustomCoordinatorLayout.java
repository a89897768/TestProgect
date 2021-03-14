package com.example.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

public class CustomCoordinatorLayout extends CoordinatorLayout {
    private GestureDetector mGestureDetector = new GestureDetector(getContext(), createGestureDetectorListener());
    private CustomAppBarLayout mAppBarLayout;
    private CustomWebView mWebView;
    private boolean mIsExpanded = true;
    private boolean mIsCollapsed = false;

    public CustomCoordinatorLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public CustomCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mLastY = 0;
    private boolean mHasChange = false;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        mGestureDetector.onTouchEvent(ev);
//        if (onInterceptTouchEvent(ev)) {
//            return onTouchEvent(ev);
//        } else {
//            return super.dispatchTouchEvent(ev);
//        }
        Log.i("CustomCoordinatorLayout", String.valueOf(ev.getAction()));
//        if(mIsCollapsed){
//            MotionEvent motionEvent = MotionEvent.obtain(ev);
//            motionEvent.setAction(MotionEvent.ACTION_DOWN);
//            mWebView.dispatchTouchEvent(motionEvent);
//           return mWebView.dispatchTouchEvent(ev);
//        }else {
//        mWebView.requestDisallowInterceptTouchEvent(true);
//            return super.dispatchTouchEvent(ev);
//        }

//        return mAppBarLayout.dispatchTouchEvent(ev);

//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN: {
////                mIsScroll = false;
//                mLastY = ev.getY();
//                break;
//            }
//
//            case MotionEvent.ACTION_MOVE: {
////                float newY = ev.getY() - mLastY;
////                if (!mIsScroll && Math.abs(newY) > mTouchSlop) {
////                    mIsScroll = true;
////                }
//                if(ev.getY() < mLastY && !mIsCollapsed){
//                    Log.i("CustomCoordinatorLayout", "false");
//                    mWebView.requestDisallowInterceptTouchEvent(false);
//                }else {
//                    Log.i("CustomCoordinatorLayout", "true");
//                    mWebView.requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//            }
//        }
//
////        if (mIsScroll) {
//            if (mIsCollapsed && ev.getY() - mLastY < 0) {
//                mWebView.requestDisallowInterceptTouchEvent(true);
////                if(mHasChange){
////                    mHasChange = false;
////                    MotionEvent motionEvent = MotionEvent.obtain(ev);
////                    motionEvent.setAction(MotionEvent.ACTION_DOWN);
////                    mWebView.dispatchTouchEvent(motionEvent);
////                }
//                mWebView.dispatchTouchEvent(ev);
//            } else {
//                mWebView.requestDisallowInterceptTouchEvent(false);
//            }
////        }
//
        mLastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("CustomCoordinatorLayout","onTouchEvent");
//        if(ev.getAction() == MotionEvent.ACTION_DOWN){
//            return false;
//        }

        return super.onTouchEvent(ev);
    }

    public void setExpanded(boolean isExpanded) {
        mIsExpanded = isExpanded;
    }

    public void setCollapsed(boolean isCollapsed) {
//        requestDisallowInterceptTouchEvent(true);
        if(!mHasChange && isCollapsed){
            mHasChange = true;
        }

        mIsCollapsed = isCollapsed;
    }

    private GestureDetector.OnGestureListener createGestureDetectorListener() {
        return new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("Test", String.valueOf(distanceY));
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        };
    }

    public void setWebView(CustomWebView webView) {
        mWebView = webView;
    }

    public void setAppBarLayout(CustomAppBarLayout appBarLayout) {
        mAppBarLayout = appBarLayout;
    }
}
