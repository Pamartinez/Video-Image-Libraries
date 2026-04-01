package com.samsung.android.gallery.support.renderscript;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.support.renderscript.RenderScriptCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface RenderScriptCompatible {
    Bitmap getBlurAppliedBitmap(Context context, Bitmap bitmap, float f, boolean z);

    Bitmap getLUTAppliedBitmap(Bitmap bitmap, RenderScriptCompat.LutInfo lutInfo);
}
