package Y1;

import B2.A;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import t1.C0278c;
import v1.h;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f950a = 1;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public int f951c;
    public Object d;
    public Object e;

    public /* synthetic */ f() {
    }

    public f a() {
        boolean z;
        if (((h) this.d) != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return new f(this, (C0278c[]) this.e, this.b, this.f951c);
        }
        throw new IllegalArgumentException("execute parameter required");
    }

    public void b(int i2) {
        switch (this.f950a) {
            case 0:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.e;
                WeakReference weakReference = bottomSheetBehavior.f1418X;
                if (weakReference != null && weakReference.get() != null) {
                    this.f951c = i2;
                    if (!this.b) {
                        ViewCompat.postOnAnimation((View) bottomSheetBehavior.f1418X.get(), (A) this.d);
                        this.b = true;
                        return;
                    }
                    return;
                }
                return;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.e;
                WeakReference weakReference2 = sideSheetBehavior.s;
                if (weakReference2 != null && weakReference2.get() != null) {
                    this.f951c = i2;
                    if (!this.b) {
                        ViewCompat.postOnAnimation((View) sideSheetBehavior.s.get(), (l) this.d);
                        this.b = true;
                        return;
                    }
                    return;
                }
                return;
        }
    }

    public f(f fVar, C0278c[] cVarArr, boolean z, int i2) {
        this.e = fVar;
        this.d = cVarArr;
        boolean z3 = false;
        if (cVarArr != null && z) {
            z3 = true;
        }
        this.b = z3;
        this.f951c = i2;
    }

    public f(SideSheetBehavior sideSheetBehavior) {
        this.e = sideSheetBehavior;
        this.d = new l(2, this);
    }

    public f(BottomSheetBehavior bottomSheetBehavior) {
        this.e = bottomSheetBehavior;
        this.d = new A(2, (Object) this);
    }
}
