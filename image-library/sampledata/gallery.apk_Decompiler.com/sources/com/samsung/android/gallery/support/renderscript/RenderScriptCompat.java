package com.samsung.android.gallery.support.renderscript;

import android.content.Context;
import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RenderScriptCompat {
    private static RenderScriptCompatible compat;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LutInfo {
        Bitmap mBitmap;
        int[] mData;

        public LutInfo(Bitmap bitmap, int[] iArr) {
            this.mBitmap = bitmap;
            this.mData = iArr;
        }
    }

    static {
        init();
    }

    public static Bitmap getBlurAppliedBitmap(Context context, Bitmap bitmap, float f, boolean z) {
        return compat.getBlurAppliedBitmap(context, bitmap, f, z);
    }

    public static Bitmap getLutAppliedBitmap(Bitmap bitmap, LutInfo lutInfo) {
        return compat.getLUTAppliedBitmap(bitmap, lutInfo);
    }

    private static void init() {
        compat = new RenderScriptCompatImplR();
    }
}
