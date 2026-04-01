package com.samsung.android.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Vu2ContainerLayout extends RelativeLayout {
    public final int RECYCLER_VIEW_TOUCH_SLOP;
    private boolean mDisabled = false;
    private boolean mEnableAssertion = false;
    private ViewPager2.OnPageChangeCallback mFakedDragViewCallback;
    private float mInitialX = Float.MAX_VALUE;
    private float mInitialY = Float.MAX_VALUE;
    private ViewPager2 mViewPager;

    public Vu2ContainerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.RECYCLER_VIEW_TOUCH_SLOP = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    private void disableViewPagerIfSlideUpDown(MotionEvent motionEvent) {
        if (this.mViewPager == null) {
            this.mViewPager = (ViewPager2) findViewById(R$id.view_pager);
        }
        if (motionEvent.getAction() == 0) {
            this.mInitialX = motionEvent.getX();
            this.mInitialY = motionEvent.getY();
            this.mEnableAssertion = true;
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.mInitialX = Float.MAX_VALUE;
            this.mInitialY = Float.MAX_VALUE;
            if (this.mDisabled && !this.mViewPager.isUserInputEnabled()) {
                this.mViewPager.setUserInputEnabled(true);
                this.mDisabled = false;
                Log.i("Vu2Container", "VP.setUserInputEnabled(true)");
            }
            this.mEnableAssertion = false;
        } else {
            if (motionEvent.getAction() == 2 && this.mInitialY != Float.MAX_VALUE) {
                float f = this.mInitialX;
                if (f != Float.MAX_VALUE) {
                    float abs = Math.abs(f - motionEvent.getX());
                    float abs2 = Math.abs(this.mInitialY - motionEvent.getY());
                    if (abs >= ((float) this.RECYCLER_VIEW_TOUCH_SLOP) && abs2 > 2.0f * abs && this.mViewPager.isUserInputEnabled()) {
                        this.mViewPager.setUserInputEnabled(false);
                        this.mDisabled = true;
                        this.mEnableAssertion = false;
                        Log.i("Vu2Container", "VP.setUserInputEnabled(false)" + Logger.v(Float.valueOf(abs), Float.valueOf(abs2)));
                        return;
                    }
                    return;
                }
            }
            this.mEnableAssertion = false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        disableViewPagerIfSlideUpDown(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setFakeDragViewCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        if (this.mViewPager == null) {
            this.mViewPager = (ViewPager2) findViewById(R$id.view_pager);
        }
        if (onPageChangeCallback != null) {
            this.mFakedDragViewCallback = onPageChangeCallback;
            this.mViewPager.registerOnPageChangeCallback(onPageChangeCallback);
            this.mFakedDragViewCallback.onPageSelected(this.mViewPager.getCurrentItem());
            return;
        }
        this.mViewPager.unregisterOnPageChangeCallback(this.mFakedDragViewCallback);
        this.mFakedDragViewCallback = null;
    }
}
