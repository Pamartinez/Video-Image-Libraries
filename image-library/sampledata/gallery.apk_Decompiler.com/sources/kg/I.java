package kg;

import Tf.u;
import a.C0068a;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import ig.f;
import ig.l;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class I implements f {

    /* renamed from: a  reason: collision with root package name */
    public final f f4670a;

    public I(f fVar) {
        this.f4670a = fVar;
    }

    public final C0068a b() {
        return l.e;
    }

    public final boolean c() {
        return false;
    }

    public final int d(String str) {
        j.e(str, "name");
        Integer n02 = u.n0(str);
        if (n02 != null) {
            return n02.intValue();
        }
        throw new IllegalArgumentException(str.concat(" is not a valid list index"));
    }

    public final int e() {
        return 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof I)) {
            return false;
        }
        I i2 = (I) obj;
        if (!j.a(this.f4670a, i2.f4670a) || !j.a(i(), i2.i())) {
            return false;
        }
        return true;
    }

    public final String f(int i2) {
        return String.valueOf(i2);
    }

    public final List g(int i2) {
        if (i2 >= 0) {
            return C1202t.d;
        }
        StringBuilder o2 = C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA);
        o2.append(i());
        o2.append(" expects only non-negative indices");
        throw new IllegalArgumentException(o2.toString().toString());
    }

    public final List getAnnotations() {
        return C1202t.d;
    }

    public final f h(int i2) {
        if (i2 >= 0) {
            return this.f4670a;
        }
        StringBuilder o2 = C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA);
        o2.append(i());
        o2.append(" expects only non-negative indices");
        throw new IllegalArgumentException(o2.toString().toString());
    }

    public final int hashCode() {
        return i().hashCode() + (this.f4670a.hashCode() * 31);
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean j(int i2) {
        if (i2 >= 0) {
            return false;
        }
        StringBuilder o2 = C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA);
        o2.append(i());
        o2.append(" expects only non-negative indices");
        throw new IllegalArgumentException(o2.toString().toString());
    }

    public final String toString() {
        return i() + '(' + this.f4670a + ')';
    }
}
