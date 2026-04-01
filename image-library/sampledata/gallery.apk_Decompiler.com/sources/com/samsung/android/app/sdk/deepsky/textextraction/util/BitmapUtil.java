package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/BitmapUtil;", "", "<init>", "()V", "getCroppedBitmap", "Landroid/graphics/Bitmap;", "validRect", "Landroid/graphics/Rect;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BitmapUtil {
    public static final BitmapUtil INSTANCE = new BitmapUtil();

    private BitmapUtil() {
    }

    public final Bitmap getCroppedBitmap(Bitmap bitmap, Rect rect) {
        j.e(bitmap, "<this>");
        j.e(rect, "validRect");
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        j.d(createBitmap, "createBitmap(...)");
        return createBitmap;
    }
}
