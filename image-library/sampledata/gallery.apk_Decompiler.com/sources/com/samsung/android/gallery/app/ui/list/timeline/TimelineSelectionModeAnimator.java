package com.samsung.android.gallery.app.ui.list.timeline;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TimelineSelectionModeAnimator {
    private boolean mIsRtl;
    private int mWidth;

    public TimelineSelectionModeAnimator(Resources resources) {
        this.mIsRtl = resources.getBoolean(R.bool.is_right_to_left);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.date_location_divider_checkbox_start_margin);
        this.mWidth = resources.getDimensionPixelSize(R.dimen.check_box_width) + resources.getDimensionPixelSize(R.dimen.date_location_divider_checkbox_end_margin) + dimensionPixelOffset;
    }

    private void startTranslateXAnim(View view, boolean z) {
        if (view != null) {
            float x9 = view.getX();
            int i2 = this.mWidth;
            if (z) {
                i2 = -i2;
            }
            view.setX(x9 + ((float) i2));
            ViewPropertyAnimator animate = view.animate();
            int i7 = this.mWidth;
            if (!z) {
                i7 = -i7;
            }
            animate.translationXBy((float) i7).setDuration(200).start();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        if (r3.mIsRtl != false) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4, boolean r5) {
        /*
            r3 = this;
            if (r5 == 0) goto L_0x0006
            boolean r0 = r3.mIsRtl     // Catch:{ IllegalArgumentException -> 0x004c }
            if (r0 == 0) goto L_0x000c
        L_0x0006:
            if (r5 != 0) goto L_0x000e
            boolean r5 = r3.mIsRtl     // Catch:{ IllegalArgumentException -> 0x004c }
            if (r5 == 0) goto L_0x000e
        L_0x000c:
            r5 = 1
            goto L_0x000f
        L_0x000e:
            r5 = 0
        L_0x000f:
            android.widget.CheckBox r0 = r4.getCheckbox()     // Catch:{ IllegalArgumentException -> 0x004c }
            r1 = 0
            r0.setScaleX(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            android.widget.CheckBox r0 = r4.getCheckbox()     // Catch:{ IllegalArgumentException -> 0x004c }
            r0.setScaleY(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            android.widget.CheckBox r0 = r4.getCheckbox()     // Catch:{ IllegalArgumentException -> 0x004c }
            android.view.ViewPropertyAnimator r0 = r0.animate()     // Catch:{ IllegalArgumentException -> 0x004c }
            r1 = 1065353216(0x3f800000, float:1.0)
            android.view.ViewPropertyAnimator r0 = r0.scaleXBy(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            android.view.ViewPropertyAnimator r0 = r0.scaleYBy(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            r1 = 100
            android.view.ViewPropertyAnimator r0 = r0.setDuration(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            android.view.ViewPropertyAnimator r0 = r0.setStartDelay(r1)     // Catch:{ IllegalArgumentException -> 0x004c }
            r0.start()     // Catch:{ IllegalArgumentException -> 0x004c }
            android.widget.TextView r0 = r4.getDateTextView()     // Catch:{ IllegalArgumentException -> 0x004c }
            r3.startTranslateXAnim(r0, r5)     // Catch:{ IllegalArgumentException -> 0x004c }
            android.widget.TextView r4 = r4.getLocationTextView()     // Catch:{ IllegalArgumentException -> 0x004c }
            r3.startTranslateXAnim(r4, r5)     // Catch:{ IllegalArgumentException -> 0x004c }
            return
        L_0x004c:
            r3 = move-exception
            java.lang.String r4 = "TimelineSelectionModeAnimator"
            java.lang.String r3 = r3.toString()
            com.samsung.android.gallery.support.utils.Log.e(r4, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.timeline.TimelineSelectionModeAnimator.startAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, boolean):void");
    }
}
