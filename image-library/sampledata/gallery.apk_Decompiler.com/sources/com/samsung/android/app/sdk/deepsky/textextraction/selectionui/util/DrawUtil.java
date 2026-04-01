package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util;

import Ge.c;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1202t;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0012\u001a\u00020\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0019\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001bJ%\u0010!\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\t¢\u0006\u0004\b!\u0010\"J%\u0010#\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\t¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/DrawUtil;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroid/graphics/drawable/Drawable;", "createShadowDrawable", "(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;", "", "dp", "", "convertDpToPx", "(Landroid/content/Context;Ljava/lang/Float;)I", "", "Landroid/graphics/Point;", "poly", "", "isLeftToRightText", "([Landroid/graphics/Point;)Z", "color", "isGradientEnabled", "Landroid/graphics/drawable/RotateDrawable;", "createLeftHandleDrawable", "(Landroid/content/Context;IZ)Landroid/graphics/drawable/RotateDrawable;", "createRightHandleDrawable", "createLeftHandleShadowDrawable", "(Landroid/content/Context;)Landroid/graphics/drawable/RotateDrawable;", "createRightHandleShadowDrawable", "Landroid/graphics/Rect;", "start", "end", "fraction", "interpolateRect", "(Landroid/graphics/Rect;Landroid/graphics/Rect;F)Landroid/graphics/Rect;", "interpolateInt", "(IIF)I", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DrawUtil {
    public static final DrawUtil INSTANCE = new DrawUtil();

    private DrawUtil() {
    }

    private final Drawable createShadowDrawable(Context context) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setFlags(1);
        shapeDrawable.getPaint().setColor(0);
        shapeDrawable.getPaint().setShadowLayer(10.0f, 0.0f, 5.0f, context.getColor(R$color.textextraction_handle_shadow_color));
        return shapeDrawable;
    }

    public final int convertDpToPx(Context context, Float f) {
        j.e(context, "context");
        j.b(f);
        return (int) (TypedValue.applyDimension(1, f.floatValue(), context.getResources().getDisplayMetrics()) + 0.5f);
    }

    public final RotateDrawable createLeftHandleDrawable(Context context, int i2, boolean z) {
        j.e(context, "context");
        RotateDrawable rotateDrawable = new RotateDrawable();
        if (z) {
            rotateDrawable.setDrawable(ResourcesCompat.getDrawable(context.getResources(), R$drawable.copypaste_text_select_handle_mtrl_ai_select_01, (Resources.Theme) null));
        } else {
            rotateDrawable.setDrawable(ResourcesCompat.getDrawable(context.getResources(), R$drawable.copypaste_text_select_handle_mtrl_01, (Resources.Theme) null));
            rotateDrawable.setTint(i2);
        }
        rotateDrawable.setPivotX(1.0f);
        rotateDrawable.setPivotY(0.0f);
        return rotateDrawable;
    }

    public final RotateDrawable createLeftHandleShadowDrawable(Context context) {
        j.e(context, "context");
        RotateDrawable rotateDrawable = new RotateDrawable();
        rotateDrawable.setDrawable(INSTANCE.createShadowDrawable(context));
        rotateDrawable.setPivotX(1.0f);
        rotateDrawable.setPivotY(0.0f);
        return rotateDrawable;
    }

    public final RotateDrawable createRightHandleDrawable(Context context, int i2, boolean z) {
        j.e(context, "context");
        RotateDrawable rotateDrawable = new RotateDrawable();
        if (z) {
            rotateDrawable.setDrawable(ResourcesCompat.getDrawable(context.getResources(), R$drawable.copypaste_text_select_handle_mtrl_ai_select_02, (Resources.Theme) null));
        } else {
            rotateDrawable.setDrawable(ResourcesCompat.getDrawable(context.getResources(), R$drawable.copypaste_text_select_handle_mtrl_02, (Resources.Theme) null));
            rotateDrawable.setTint(i2);
        }
        rotateDrawable.setPivotX(0.0f);
        rotateDrawable.setPivotY(0.0f);
        return rotateDrawable;
    }

    public final RotateDrawable createRightHandleShadowDrawable(Context context) {
        j.e(context, "context");
        RotateDrawable rotateDrawable = new RotateDrawable();
        rotateDrawable.setDrawable(INSTANCE.createShadowDrawable(context));
        rotateDrawable.setPivotX(0.0f);
        rotateDrawable.setPivotY(0.0f);
        return rotateDrawable;
    }

    public final int interpolateInt(int i2, int i7, float f) {
        return (int) ((((float) (i7 - i2)) * f) + ((float) i2));
    }

    public final Rect interpolateRect(Rect rect, Rect rect2, float f) {
        j.e(rect, "start");
        j.e(rect2, "end");
        int i2 = rect.left;
        float f5 = (float) i2;
        int i7 = rect.top;
        float f8 = (float) i7;
        int i8 = rect.right;
        float f10 = (float) i8;
        int i10 = rect.bottom;
        return new Rect((int) ((((float) (rect2.left - i2)) * f) + f5), (int) ((((float) (rect2.top - i7)) * f) + f8), (int) ((((float) (rect2.right - i8)) * f) + f10), (int) ((((float) (rect2.bottom - i10)) * f) + ((float) i10)));
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [Ge.c, Ge.e] */
    public final boolean isLeftToRightText(Point[] pointArr) {
        List list;
        j.e(pointArr, "poly");
        ? cVar = new c(0, 2, 1);
        if (cVar.isEmpty()) {
            list = C1202t.d;
        } else {
            list = C1192j.a0(C1192j.i0(pointArr, 0, cVar.e + 1));
        }
        Point point = (Point) list.get(0);
        Point point2 = (Point) list.get(1);
        Point point3 = (Point) list.get(2);
        int i2 = point2.y;
        int i7 = point3.x;
        int i8 = point2.x;
        if (((i7 - i8) * (i2 - point.y)) - ((point3.y - i2) * (i8 - point.x)) < 0) {
            return true;
        }
        return false;
    }
}
