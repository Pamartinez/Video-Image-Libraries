package com.samsung.android.gallery.widget.story.transitory;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapViewPager extends StoriesViewPager {
    boolean mIsLastItem = false;
    private ViewGroup mParentRecyclerView;
    float mTouchDownX = Float.MAX_VALUE;

    public RecapViewPager(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* access modifiers changed from: private */
    public boolean allowParentTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = true;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return false;
                    }
                } else if (!this.mIsLastItem || this.mTouchDownX <= motionEvent.getX() || this.mTouchDownX - motionEvent.getX() <= 5.0f) {
                    return false;
                } else {
                    return true;
                }
            }
            this.mIsLastItem = false;
            this.mTouchDownX = Float.MAX_VALUE;
            return true;
        }
        if (this.mAdapter.getItemCount() - 1 != getCurrentItem()) {
            z = false;
        }
        this.mIsLastItem = z;
        if (z) {
            this.mTouchDownX = motionEvent.getX();
        } else {
            this.mTouchDownX = Float.MAX_VALUE;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.ViewGroup findParentRecyclerView(androidx.viewpager2.widget.ViewPager2 r3) {
        /*
            r2 = this;
            android.view.ViewGroup r0 = r2.mParentRecyclerView
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            android.view.ViewParent r3 = r3.getParent()
        L_0x0009:
            boolean r0 = r3 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x0018
            boolean r1 = r3 instanceof androidx.viewpager2.widget.ViewPager2
            if (r1 != 0) goto L_0x0018
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            android.view.ViewParent r3 = r3.getParent()
            goto L_0x0009
        L_0x0018:
            if (r0 == 0) goto L_0x0026
            androidx.viewpager2.widget.ViewPager2 r3 = (androidx.viewpager2.widget.ViewPager2) r3
            r0 = 0
            android.view.View r3 = r3.getChildAt(r0)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            r2.mParentRecyclerView = r3
            return r3
        L_0x0026:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.story.transitory.RecapViewPager.findParentRecyclerView(androidx.viewpager2.widget.ViewPager2):android.view.ViewGroup");
    }

    public InterceptDispatchTouchListener createTouchInterceptor() {
        return new InterceptDispatchTouchListener() {
            public boolean onInterceptDispatchTouchEvent(MotionEvent motionEvent) {
                ViewPagerTouchDelegate viewPagerTouchDelegate;
                Supplier<Boolean> supplier = RecapViewPager.this.mTouchable;
                if ((supplier != null && !supplier.get().booleanValue()) || ((viewPagerTouchDelegate = RecapViewPager.this.mTouchDelegate) != null && viewPagerTouchDelegate.onInterceptDispatchTouchEvent(motionEvent))) {
                    return true;
                }
                RecapViewPager recapViewPager = RecapViewPager.this;
                ViewGroup h5 = recapViewPager.findParentRecyclerView(recapViewPager.mViewPager);
                if (h5 != null) {
                    h5.requestDisallowInterceptTouchEvent(true ^ RecapViewPager.this.allowParentTouch(motionEvent));
                }
                return RecapViewPager.this.mViewPager.dispatchTouchEvent(motionEvent);
            }

            public boolean supportHover() {
                return RecapViewPager.this.mAccessibilityState.isVoiceServiceEnabled();
            }
        };
    }

    public PageTransformer getTransformer() {
        return new RecapPageTransformer();
    }

    public void setPageViewInfo(int i2, int i7, int i8, int i10) {
        super.setPageViewInfo(i2, i7, i8, i10);
        if (this.mPageTransformer == null) {
            this.mViewPager.setPageTransformer(getTransformer());
        }
    }
}
