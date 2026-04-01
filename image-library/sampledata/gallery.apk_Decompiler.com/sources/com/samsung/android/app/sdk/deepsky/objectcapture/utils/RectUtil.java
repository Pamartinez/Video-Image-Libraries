package com.samsung.android.app.sdk.deepsky.objectcapture.utils;

import android.graphics.Rect;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ&\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/utils/RectUtil;", "", "<init>", "()V", "getScaledRect", "Landroid/graphics/RectF;", "rect", "scaleFactor", "", "offsetX", "offsetY", "Landroid/graphics/Rect;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RectUtil {
    public static final RectUtil INSTANCE = new RectUtil();

    private RectUtil() {
    }

    public final RectF getScaledRect(RectF rectF, float f, float f5, float f8) {
        j.e(rectF, "rect");
        return new RectF((rectF.left * f) + f5, (rectF.top * f) + f8, (rectF.right * f) + f5, (rectF.bottom * f) + f8);
    }

    public final Rect getScaledRect(Rect rect, float f, float f5, float f8) {
        j.e(rect, "rect");
        return new Rect((int) ((((float) rect.left) * f) + f5), (int) ((((float) rect.top) * f) + f8), (int) ((((float) rect.right) * f) + f5), (int) ((((float) rect.bottom) * f) + f8));
    }
}
