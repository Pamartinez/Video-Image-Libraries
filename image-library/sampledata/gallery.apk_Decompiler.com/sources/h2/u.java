package h2;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import androidx.core.view.ViewCompat;
import ge.W0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class u {
    /* JADX WARNING: type inference failed for: r0v0, types: [h2.t, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v2, types: [java.lang.Object, android.view.View$OnAttachStateChangeListener] */
    public static void a(View view, s sVar) {
        int paddingStart = ViewCompat.getPaddingStart(view);
        int paddingTop = view.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(view);
        int paddingBottom = view.getPaddingBottom();
        ? obj = new Object();
        obj.f1778a = paddingStart;
        obj.b = paddingTop;
        obj.f1779c = paddingEnd;
        obj.d = paddingBottom;
        ViewCompat.setOnApplyWindowInsetsListener(view, new W0(sVar, obj, false, 2));
        if (ViewCompat.isAttachedToWindow(view)) {
            ViewCompat.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new Object());
        }
    }

    public static float b(Context context, int i2) {
        return TypedValue.applyDimension(1, (float) i2, context.getResources().getDisplayMetrics());
    }

    public static boolean c(View view) {
        if (ViewCompat.getLayoutDirection(view) == 1) {
            return true;
        }
        return false;
    }

    public static PorterDuff.Mode d(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
