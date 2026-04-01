package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.RotateDrawable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H&J#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\fH&¢\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H&J \u0010\u001b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H&J \u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H&¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawStrategy;", "", "getRotateDrawable", "Landroid/graphics/drawable/RotateDrawable;", "context", "Landroid/content/Context;", "color", "", "isGradientEnabled", "", "getShadowDrawable", "getTouchableAreaRect", "Landroid/graphics/Rect;", "drawAreaRect", "touchableArea", "rotationAngle", "getRotatedDefaultRect", "", "Landroid/graphics/Point;", "angle", "defaultRect", "(ILandroid/graphics/Rect;)[Landroid/graphics/Point;", "getDefaultRect", "handleDrawInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawInfo;", "drawableWidth", "drawableHeight", "getDefaultRectForAnimation", "getEffectiveTouchPoint", "touchPoint", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface HandleDrawStrategy {
    Rect getDefaultRect(HandleDrawInfo handleDrawInfo, int i2, int i7);

    Rect getDefaultRectForAnimation(HandleDrawInfo handleDrawInfo, int i2, int i7);

    Point getEffectiveTouchPoint(Point point, int i2, int i7);

    RotateDrawable getRotateDrawable(Context context, int i2, boolean z);

    Point[] getRotatedDefaultRect(int i2, Rect rect);

    RotateDrawable getShadowDrawable(Context context);

    Rect getTouchableAreaRect(Rect rect, Rect rect2, int i2);
}
