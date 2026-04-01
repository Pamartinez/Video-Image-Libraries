package kg;

import Tf.u;
import a.C0068a;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import ig.f;
import ig.l;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B implements f {

    /* renamed from: a  reason: collision with root package name */
    public final String f4662a;
    public final f b;

    /* renamed from: c  reason: collision with root package name */
    public final f f4663c;

    public B(String str, f fVar, f fVar2) {
        this.f4662a = str;
        this.b = fVar;
        this.f4663c = fVar2;
    }

    public final C0068a b() {
        return l.f;
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
        throw new IllegalArgumentException(str.concat(" is not a valid map index"));
    }

    public final int e() {
        return 2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof B)) {
            return false;
        }
        B b5 = (B) obj;
        if (j.a(this.f4662a, b5.f4662a) && j.a(this.b, b5.b) && j.a(this.f4663c, b5.f4663c)) {
            return true;
        }
        return false;
    }

    public final String f(int i2) {
        return String.valueOf(i2);
    }

    public final List g(int i2) {
        if (i2 >= 0) {
            return C1202t.d;
        }
        throw new IllegalArgumentException(C0212a.p(C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA), this.f4662a, " expects only non-negative indices").toString());
    }

    public final List getAnnotations() {
        return C1202t.d;
    }

    public final f h(int i2) {
        if (i2 >= 0) {
            int i7 = i2 % 2;
            if (i7 == 0) {
                return this.b;
            }
            if (i7 == 1) {
                return this.f4663c;
            }
            throw new IllegalStateException("Unreached");
        }
        throw new IllegalArgumentException(C0212a.p(C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA), this.f4662a, " expects only non-negative indices").toString());
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        return this.f4663c.hashCode() + ((hashCode + (this.f4662a.hashCode() * 31)) * 31);
    }

    public final String i() {
        return this.f4662a;
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean j(int i2) {
        if (i2 >= 0) {
            return false;
        }
        throw new IllegalArgumentException(C0212a.p(C0086a.o(i2, "Illegal index ", ArcCommonLog.TAG_COMMA), this.f4662a, " expects only non-negative indices").toString());
    }

    public final String toString() {
        return this.f4662a + '(' + this.b + ArcCommonLog.TAG_COMMA + this.f4663c + ')';
    }
}
