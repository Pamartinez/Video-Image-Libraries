package H0;

import D0.e;
import I0.b;
import I0.c;
import android.graphics.Color;
import android.graphics.PointF;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    public static final e f321a = e.S("x", "y");

    public static int a(c cVar) {
        cVar.a();
        int j2 = (int) (cVar.j() * 255.0d);
        int j3 = (int) (cVar.j() * 255.0d);
        int j8 = (int) (cVar.j() * 255.0d);
        while (cVar.h()) {
            cVar.s();
        }
        cVar.f();
        return Color.argb(ScoverState.TYPE_NFC_SMART_COVER, j2, j3, j8);
    }

    public static PointF b(c cVar, float f) {
        int i2 = n.f320a[cVar.n().ordinal()];
        if (i2 == 1) {
            float j2 = (float) cVar.j();
            float j3 = (float) cVar.j();
            while (cVar.h()) {
                cVar.s();
            }
            return new PointF(j2 * f, j3 * f);
        } else if (i2 == 2) {
            cVar.a();
            float j8 = (float) cVar.j();
            float j10 = (float) cVar.j();
            while (cVar.n() != b.END_ARRAY) {
                cVar.s();
            }
            cVar.f();
            return new PointF(j8 * f, j10 * f);
        } else if (i2 == 3) {
            cVar.c();
            float f5 = 0.0f;
            float f8 = 0.0f;
            while (cVar.h()) {
                int q = cVar.q(f321a);
                if (q == 0) {
                    f5 = d(cVar);
                } else if (q != 1) {
                    cVar.r();
                    cVar.s();
                } else {
                    f8 = d(cVar);
                }
            }
            cVar.g();
            return new PointF(f5 * f, f8 * f);
        } else {
            throw new IllegalArgumentException("Unknown point starts with " + cVar.n());
        }
    }

    public static ArrayList c(c cVar, float f) {
        ArrayList arrayList = new ArrayList();
        cVar.a();
        while (cVar.n() == b.BEGIN_ARRAY) {
            cVar.a();
            arrayList.add(b(cVar, f));
            cVar.f();
        }
        cVar.f();
        return arrayList;
    }

    public static float d(c cVar) {
        b n = cVar.n();
        int i2 = n.f320a[n.ordinal()];
        if (i2 == 1) {
            return (float) cVar.j();
        }
        if (i2 == 2) {
            cVar.a();
            float j2 = (float) cVar.j();
            while (cVar.h()) {
                cVar.s();
            }
            cVar.f();
            return j2;
        }
        throw new IllegalArgumentException("Unknown value for token of type " + n);
    }
}
