package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.RotateDrawable;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.DrawUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\tH\u0016J#\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0016J \u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0016J \u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0016¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/MovingRtlStartHandle;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawStrategy;", "<init>", "()V", "getRotateDrawable", "Landroid/graphics/drawable/RotateDrawable;", "context", "Landroid/content/Context;", "color", "", "isGradientEnabled", "", "getShadowDrawable", "getTouchableAreaRect", "Landroid/graphics/Rect;", "drawAreaRect", "touchableArea", "rotationAngle", "getRotatedDefaultRect", "", "Landroid/graphics/Point;", "angle", "defaultRect", "(ILandroid/graphics/Rect;)[Landroid/graphics/Point;", "getDefaultRect", "handleDrawInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawInfo;", "drawableWidth", "drawableHeight", "getDefaultRectForAnimation", "getEffectiveTouchPoint", "touchPoint", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MovingRtlStartHandle implements HandleDrawStrategy {
    public Rect getDefaultRect(HandleDrawInfo handleDrawInfo, int i2, int i7) {
        j.e(handleDrawInfo, "handleDrawInfo");
        Point calcBoundedPoint = VectorUtil.INSTANCE.calcBoundedPoint(handleDrawInfo.getTouchPoint(), handleDrawInfo.getBoundingRect());
        int i8 = calcBoundedPoint.x;
        int i10 = calcBoundedPoint.y;
        return new Rect(i8, i10, i2 + i8, i7 + i10);
    }

    public Rect getDefaultRectForAnimation(HandleDrawInfo handleDrawInfo, int i2, int i7) {
        j.e(handleDrawInfo, "handleDrawInfo");
        return getDefaultRect(handleDrawInfo, i2, i7);
    }

    public Point getEffectiveTouchPoint(Point point, int i2, int i7) {
        j.e(point, "touchPoint");
        int i8 = i2 / 2;
        return new Point(point.x - i8, point.y - i8);
    }

    public RotateDrawable getRotateDrawable(Context context, int i2, boolean z) {
        j.e(context, "context");
        return DrawUtil.INSTANCE.createRightHandleDrawable(context, i2, z);
    }

    public Point[] getRotatedDefaultRect(int i2, Rect rect) {
        j.e(rect, "defaultRect");
        return PointUtil.INSTANCE.getRotatedRect(rect, i2, new Point(rect.left, rect.top));
    }

    public RotateDrawable getShadowDrawable(Context context) {
        j.e(context, "context");
        return DrawUtil.INSTANCE.createRightHandleShadowDrawable(context);
    }

    public Rect getTouchableAreaRect(Rect rect, Rect rect2, int i2) {
        j.e(rect, "drawAreaRect");
        j.e(rect2, "touchableArea");
        PointUtil pointUtil = PointUtil.INSTANCE;
        return pointUtil.getRectContainingPoly(pointUtil.getRotatedRect(rect2, i2, new Point(rect.left, rect.top)));
    }
}
