package y2;

import Gd.a;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.sidesheet.SideSheetBehavior;

/* renamed from: y2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0357a extends a {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2140a;
    public final SideSheetBehavior b;

    public /* synthetic */ C0357a(SideSheetBehavior sideSheetBehavior, int i2) {
        this.f2140a = i2;
        this.b = sideSheetBehavior;
    }

    public final int C() {
        switch (this.f2140a) {
            case 0:
                return this.b.r;
            default:
                return this.b.f1493p;
        }
    }

    public final int D() {
        switch (this.f2140a) {
            case 0:
                return -this.b.f1492o;
            default:
                return w();
        }
    }

    public final int E(View view) {
        switch (this.f2140a) {
            case 0:
                return view.getRight() + this.b.r;
            default:
                return view.getLeft() - this.b.r;
        }
    }

    public final int F(CoordinatorLayout coordinatorLayout) {
        switch (this.f2140a) {
            case 0:
                return coordinatorLayout.getLeft();
            default:
                return coordinatorLayout.getRight();
        }
    }

    public final int H() {
        switch (this.f2140a) {
            case 0:
                return 1;
            default:
                return 0;
        }
    }

    public final boolean L(float f) {
        switch (this.f2140a) {
            case 0:
                if (f > 0.0f) {
                    return true;
                }
                return false;
            default:
                if (f < 0.0f) {
                    return true;
                }
                return false;
        }
    }

    public final boolean N(View view) {
        switch (this.f2140a) {
            case 0:
                if (view.getRight() < (w() - y()) / 2) {
                    return true;
                }
                return false;
            default:
                if (view.getLeft() > (w() + this.b.f1493p) / 2) {
                    return true;
                }
                return false;
        }
    }

    public final boolean P(float f, float f5) {
        switch (this.f2140a) {
            case 0:
                if (Math.abs(f) <= Math.abs(f5) || Math.abs(f) <= ((float) 500)) {
                    return false;
                }
                return true;
            default:
                if (Math.abs(f) <= Math.abs(f5) || Math.abs(f) <= ((float) 500)) {
                    return false;
                }
                return true;
        }
    }

    public final int c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        switch (this.f2140a) {
            case 0:
                return marginLayoutParams.leftMargin;
            default:
                return marginLayoutParams.rightMargin;
        }
    }

    public final float d(int i2) {
        switch (this.f2140a) {
            case 0:
                float y = (float) y();
                return (((float) i2) - y) / (((float) w()) - y);
            default:
                float f = (float) this.b.f1493p;
                return (f - ((float) i2)) / (f - ((float) w()));
        }
    }

    public final boolean g0(float f, View view) {
        switch (this.f2140a) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.b;
                float abs = Math.abs((f * sideSheetBehavior.n) + ((float) view.getLeft()));
                sideSheetBehavior.getClass();
                if (abs > 0.5f) {
                    return true;
                }
                return false;
            default:
                SideSheetBehavior sideSheetBehavior2 = this.b;
                float abs2 = Math.abs((f * sideSheetBehavior2.n) + ((float) view.getRight()));
                sideSheetBehavior2.getClass();
                if (abs2 > 0.5f) {
                    return true;
                }
                return false;
        }
    }

    public final void k0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i7) {
        switch (this.f2140a) {
            case 0:
                if (i2 <= this.b.f1493p) {
                    marginLayoutParams.leftMargin = i7;
                    return;
                }
                return;
            default:
                int i8 = this.b.f1493p;
                if (i2 <= i8) {
                    marginLayoutParams.rightMargin = i8 - i2;
                    return;
                }
                return;
        }
    }

    public final int w() {
        switch (this.f2140a) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.b;
                return Math.max(0, sideSheetBehavior.q + sideSheetBehavior.r);
            default:
                SideSheetBehavior sideSheetBehavior2 = this.b;
                return Math.max(0, (sideSheetBehavior2.f1493p - sideSheetBehavior2.f1492o) - sideSheetBehavior2.r);
        }
    }

    public final int y() {
        switch (this.f2140a) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.b;
                return (-sideSheetBehavior.f1492o) - sideSheetBehavior.r;
            default:
                return this.b.f1493p;
        }
    }
}
