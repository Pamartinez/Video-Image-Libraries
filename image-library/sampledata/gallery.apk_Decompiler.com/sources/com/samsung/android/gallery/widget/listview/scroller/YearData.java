package com.samsung.android.gallery.widget.listview.scroller;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class YearData {
    private final int mScrollAmount;
    private final String mScrollTag;
    private final View mView;
    private final float mY;

    public YearData(View view, String str, int i2, float f) {
        this.mView = view;
        this.mScrollTag = str;
        this.mScrollAmount = i2;
        this.mY = f;
        view.setY(f);
        ((TextView) view.findViewById(R$id.date)).setText(str);
    }

    public View getView() {
        return this.mView;
    }

    public float getY() {
        return this.mY;
    }

    public String toString() {
        return "YearData{" + this.mScrollTag + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mScrollAmount + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mY + "}";
    }
}
