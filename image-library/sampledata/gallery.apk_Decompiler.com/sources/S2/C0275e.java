package s2;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewGroupKt;
import androidx.core.widget.SeslScrollable;
import q2.r;

/* renamed from: s2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface C0275e {
    static boolean d(View view) {
        if (view instanceof ViewGroup) {
            for (View visibility : ViewGroupKt.getChildren((ViewGroup) view)) {
                if (visibility.getVisibility() == 0) {
                    return false;
                }
            }
            return true;
        } else if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text == null || text.length() == 0) {
                return true;
            }
            return false;
        } else if (view.getClass() != View.class) {
            return false;
        } else {
            return true;
        }
    }

    void a();

    boolean b(int i2, int i7, int i8) {
        return false;
    }

    boolean c(SeslScrollable seslScrollable) {
        Rect seslGetAvailableBounds = seslScrollable.seslGetAvailableBounds();
        if (seslGetAvailableBounds == null || seslGetAvailableBounds.bottom <= 0) {
            return false;
        }
        return true;
    }

    void e(r rVar);

    void f(r rVar);

    SeslScrollable g() {
        return null;
    }
}
