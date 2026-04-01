package com.samsung.android.gallery.widget.photoview;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Tile {
    /* access modifiers changed from: package-private */
    public Bitmap bitmap;
    Rect borders = new Rect();
    boolean canRecycle = true;
    Rect fileSRect;
    volatile boolean loading;
    Rect sRect;
    int sampleSize;
    Rect vRect;
    /* access modifiers changed from: package-private */
    public volatile boolean visible;

    public String toString() {
        char c5;
        char c6;
        char c8;
        StringBuilder sb2 = new StringBuilder("Tile{");
        if (this.visible) {
            c5 = 'V';
        } else {
            c5 = 'v';
        }
        sb2.append(c5);
        if (this.loading) {
            c6 = 'L';
        } else {
            c6 = 'l';
        }
        sb2.append(c6);
        if (this.bitmap != null) {
            c8 = 'B';
        } else {
            c8 = 'b';
        }
        sb2.append(c8);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.sampleSize);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.sRect);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.fileSRect);
        sb2.append("}");
        return sb2.toString();
    }
}
