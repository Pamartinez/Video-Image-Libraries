package com.samsung.android.vexfwk.extensions;

import android.graphics.Point;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"toArrayOfPoints", "", "Landroid/graphics/Point;", "Landroid/graphics/Rect;", "(Landroid/graphics/Rect;)[Landroid/graphics/Point;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RectKt {
    public static final Point[] toArrayOfPoints(Rect rect) {
        j.e(rect, "<this>");
        return new Point[]{new Point(rect.left, rect.top), new Point(rect.right, rect.top), new Point(rect.right, rect.bottom), new Point(rect.left, rect.bottom)};
    }
}
