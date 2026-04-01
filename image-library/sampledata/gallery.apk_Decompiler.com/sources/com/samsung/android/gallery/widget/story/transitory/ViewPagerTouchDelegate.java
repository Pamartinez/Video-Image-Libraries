package com.samsung.android.gallery.widget.story.transitory;

import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewPagerTouchDelegate implements InterceptDispatchTouchListener {
    private final OnTouchEventListener mListener;
    private float mTouchDownPoint = -1.0f;
    private final ViewPager2 mViewPager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTouchEventListener {
        void onTouchCancel();

        void onTouchUp();

        void swipe();
    }

    public ViewPagerTouchDelegate(ViewPager2 viewPager2, OnTouchEventListener onTouchEventListener) {
        this.mViewPager = viewPager2;
        this.mListener = onTouchEventListener;
    }

    private void clear() {
        this.mTouchDownPoint = -1.0f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 != 6) goto L_0x00d1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptDispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            java.lang.String r2 = "ViewPagerTouchDelegate"
            if (r0 == 0) goto L_0x00b8
            r3 = 1
            if (r0 == r3) goto L_0x008a
            r4 = 2
            if (r0 == r4) goto L_0x0044
            r3 = 3
            if (r0 == r3) goto L_0x0023
            r3 = 5
            if (r0 == r3) goto L_0x001c
            r3 = 6
            if (r0 == r3) goto L_0x008a
            goto L_0x00d1
        L_0x001c:
            java.lang.String r6 = "ACTION_POINTER_DOWN"
            com.samsung.android.gallery.support.utils.Log.d(r2, r6)
            goto L_0x00d1
        L_0x0023:
            androidx.viewpager2.widget.ViewPager2 r7 = r6.mViewPager
            r7.requestDisallowInterceptTouchEvent(r1)
            com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate$OnTouchEventListener r7 = r6.mListener
            java.util.Optional r7 = java.util.Optional.ofNullable(r7)
            W8.a r0 = new W8.a
            r2 = 24
            r0.<init>(r2)
            r7.ifPresent(r0)
            com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate$OnTouchEventListener r7 = r6.mListener
            if (r7 == 0) goto L_0x003f
            r7.onTouchUp()
        L_0x003f:
            r6.clear()
            goto L_0x00d1
        L_0x0044:
            float r0 = r6.mTouchDownPoint
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00d1
            float r0 = r7.getX()
            float r5 = r6.mTouchDownPoint
            float r0 = r0 - r5
            float r0 = java.lang.Math.abs(r0)
            r5 = 1106247680(0x41f00000, float:30.0)
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x00d1
            r6.mTouchDownPoint = r4
            com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate$OnTouchEventListener r0 = r6.mListener
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            W8.a r4 = new W8.a
            r5 = 25
            r4.<init>(r5)
            r0.ifPresent(r4)
            androidx.viewpager2.widget.ViewPager2 r6 = r6.mViewPager
            r6.requestDisallowInterceptTouchEvent(r3)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "ACTION_MOVE : setSwipeMode = "
            r6.<init>(r0)
            float r7 = r7.getX()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.samsung.android.gallery.support.utils.Log.d(r2, r6)
            goto L_0x00d1
        L_0x008a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "ACTION_UP : "
            r0.<init>(r3)
            float r7 = r7.getX()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.d(r2, r7)
            androidx.viewpager2.widget.ViewPager2 r7 = r6.mViewPager
            r7.requestDisallowInterceptTouchEvent(r1)
            com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate$OnTouchEventListener r7 = r6.mListener
            java.util.Optional r7 = java.util.Optional.ofNullable(r7)
            W8.a r0 = new W8.a
            r2 = 23
            r0.<init>(r2)
            r7.ifPresent(r0)
            r6.clear()
            goto L_0x00d1
        L_0x00b8:
            float r7 = r7.getX()
            r6.mTouchDownPoint = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "ACTION_DOWN : "
            r7.<init>(r0)
            float r6 = r6.mTouchDownPoint
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.samsung.android.gallery.support.utils.Log.d(r2, r6)
        L_0x00d1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate.onInterceptDispatchTouchEvent(android.view.MotionEvent):boolean");
    }
}
