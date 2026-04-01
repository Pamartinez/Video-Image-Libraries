package vf;

import Hf.C0754c;
import Hf.I;
import Hf.M;
import Jf.h;
import Jf.l;
import L1.d;
import Ne.i;
import Qe.C0819i;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.m;
import ne.C1194l;
import ne.C1202t;

/* renamed from: vf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1336p implements M {

    /* renamed from: a  reason: collision with root package name */
    public final Set f5161a;
    public final m b = d.q(new Object());

    /* JADX WARNING: type inference failed for: r0v2, types: [Ae.a, java.lang.Object] */
    public C1336p(Set set) {
        I.e.getClass();
        I i2 = I.f;
        j.e(i2, "attributes");
        C0754c.v(l.a(h.INTEGER_LITERAL_TYPE_SCOPE, true, "unknown integer literal type"), i2, this, C1202t.d, false);
        this.f5161a = set;
    }

    public final i f() {
        throw null;
    }

    public final C0819i g() {
        return null;
    }

    public final List getParameters() {
        return C1202t.d;
    }

    public final Collection h() {
        return (List) this.b.getValue();
    }

    public final boolean i() {
        return false;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("IntegerLiteralType");
        sb2.append("[" + C1194l.R0(this.f5161a, GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, C1333m.d, 30) + ']');
        return sb2.toString();
    }
}
