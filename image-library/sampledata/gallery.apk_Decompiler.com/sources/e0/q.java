package E0;

import android.graphics.PointF;
import c0.C0086a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f147a;
    public PointF b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f148c;

    public q(PointF pointF, boolean z, List list) {
        this.b = pointF;
        this.f148c = z;
        this.f147a = new ArrayList(list);
    }

    public final void a(float f, float f5) {
        if (this.b == null) {
            this.b = new PointF();
        }
        this.b.set(f, f5);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ShapeData{numCurves=");
        C0086a.A(sb2, this.f147a, "closed=");
        return C0086a.n(sb2, this.f148c, '}');
    }

    public q() {
        this.f147a = new ArrayList();
    }
}
