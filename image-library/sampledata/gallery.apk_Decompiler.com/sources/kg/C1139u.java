package kg;

import Ae.b;
import L1.d;
import Sf.r;
import a.C0068a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import ig.f;
import ig.i;
import ig.k;
import kotlin.jvm.internal.j;
import me.m;
import ne.C1194l;

/* renamed from: kg.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1139u extends T {
    public final k l = k.d;
    public final m m;

    public C1139u(String str, int i2) {
        super(str, (A) null, i2);
        this.m = d.q(new C1138t(i2, str, this));
    }

    public final C0068a b() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (fVar.b() == k.d && this.f4679a.equals(fVar.i()) && j.a(Q.b(this), Q.b(fVar))) {
            return true;
        }
        return false;
    }

    public final f h(int i2) {
        return ((f[]) this.m.getValue())[i2];
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.f4679a.hashCode();
        i iVar = new i(this);
        int i7 = 1;
        while (iVar.hasNext()) {
            int i8 = i7 * 31;
            String str = (String) iVar.next();
            if (str != null) {
                i2 = str.hashCode();
            } else {
                i2 = 0;
            }
            i7 = i8 + i2;
        }
        return (hashCode * 31) + i7;
    }

    public final String toString() {
        return C1194l.R0(new r(1, this), ArcCommonLog.TAG_COMMA, this.f4679a.concat("("), ")", (b) null, 56);
    }
}
