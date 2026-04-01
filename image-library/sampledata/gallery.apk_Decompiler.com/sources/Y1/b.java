package Y1;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import h2.s;
import h2.t;
import h2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements s {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ BottomSheetBehavior e;

    public b(BottomSheetBehavior bottomSheetBehavior, boolean z) {
        this.e = bottomSheetBehavior;
        this.d = z;
    }

    public final WindowInsetsCompat b(View view, WindowInsetsCompat windowInsetsCompat, t tVar) {
        boolean z;
        int i2;
        int i7;
        int i8;
        int i10;
        int i11 = tVar.f1778a;
        int i12 = tVar.f1779c;
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        Insets insets2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.mandatorySystemGestures());
        int i13 = insets.top;
        BottomSheetBehavior bottomSheetBehavior = this.e;
        bottomSheetBehavior.z = i13;
        boolean c5 = u.c(view);
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        boolean z3 = bottomSheetBehavior.r;
        if (z3) {
            int systemWindowInsetBottom = windowInsetsCompat.getSystemWindowInsetBottom();
            bottomSheetBehavior.y = systemWindowInsetBottom;
            paddingBottom = systemWindowInsetBottom + tVar.d;
        }
        if (bottomSheetBehavior.s) {
            if (c5) {
                i10 = i12;
            } else {
                i10 = i11;
            }
            paddingLeft = insets.left + i10;
        }
        if (bottomSheetBehavior.t) {
            if (!c5) {
                i11 = i12;
            }
            paddingRight = i11 + insets.right;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        boolean z7 = true;
        if (!bottomSheetBehavior.v || marginLayoutParams.leftMargin == (i8 = insets.left)) {
            z = false;
        } else {
            marginLayoutParams.leftMargin = i8;
            z = true;
        }
        if (bottomSheetBehavior.w && marginLayoutParams.rightMargin != (i7 = insets.right)) {
            marginLayoutParams.rightMargin = i7;
            z = true;
        }
        if (!bottomSheetBehavior.f1428x || marginLayoutParams.topMargin == (i2 = insets.top)) {
            z7 = z;
        } else {
            marginLayoutParams.topMargin = i2;
        }
        if (z7) {
            view.setLayoutParams(marginLayoutParams);
        }
        view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
        boolean z9 = this.d;
        if (z9) {
            bottomSheetBehavior.f1427p = insets2.bottom;
        }
        if (!z3 && !z9) {
            return windowInsetsCompat;
        }
        bottomSheetBehavior.g();
        return windowInsetsCompat;
    }
}
