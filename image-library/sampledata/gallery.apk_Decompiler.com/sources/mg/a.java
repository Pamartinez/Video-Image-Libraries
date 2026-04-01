package mg;

import Ed.b;
import a.C0068a;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import ig.e;
import ig.f;
import ig.k;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import jg.c;
import kg.V;
import kg.f0;
import kotlin.jvm.internal.w;
import lg.C;
import lg.C1174b;
import lg.C1176d;
import lg.i;
import lg.j;
import lg.l;
import lg.m;
import lg.s;
import lg.v;
import lg.y;
import ne.C1194l;
import ne.C1195m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a implements j, c, jg.a {
    public final ArrayList d = new ArrayList();
    public boolean e;
    public final C1174b f;
    public final i g;

    public a(C1174b bVar) {
        this.f = bVar;
        this.g = bVar.f4894a;
    }

    public final char A(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return J(S(v, i2));
    }

    public final String B() {
        return P(U());
    }

    public final l C() {
        l i2;
        String str = (String) C1194l.U0(this.d);
        if (str == null || (i2 = i(str)) == null) {
            return T();
        }
        return i2;
    }

    public boolean D() {
        return !(C() instanceof v);
    }

    public final c E(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return M(S(v, i2), v.h(i2));
    }

    public final C1174b F() {
        return this.f;
    }

    public final byte G() {
        return I(U());
    }

    public final boolean H(Object obj) {
        Boolean bool;
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        C R = R(str);
        try {
            int i2 = m.f4903a;
            String i7 = R.i();
            String[] strArr = t.f4949a;
            kotlin.jvm.internal.j.e(i7, "<this>");
            if (i7.equalsIgnoreCase(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE)) {
                bool = Boolean.TRUE;
            } else if (i7.equalsIgnoreCase(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE)) {
                bool = Boolean.FALSE;
            } else {
                bool = null;
            }
            if (bool != null) {
                return bool.booleanValue();
            }
            V("boolean");
            throw null;
        } catch (IllegalArgumentException unused) {
            V("boolean");
            throw null;
        }
    }

    public final byte I(Object obj) {
        Byte b;
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        try {
            int a7 = m.a(R(str));
            if (-128 > a7 || a7 > 127) {
                b = null;
            } else {
                b = Byte.valueOf((byte) a7);
            }
            if (b != null) {
                return b.byteValue();
            }
            V("byte");
            throw null;
        } catch (IllegalArgumentException unused) {
            V("byte");
            throw null;
        }
    }

    public final char J(Object obj) {
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        try {
            String i2 = R(str).i();
            kotlin.jvm.internal.j.e(i2, "<this>");
            int length = i2.length();
            if (length == 0) {
                throw new NoSuchElementException("Char sequence is empty.");
            } else if (length == 1) {
                return i2.charAt(0);
            } else {
                throw new IllegalArgumentException("Char sequence has more than one element.");
            }
        } catch (IllegalArgumentException unused) {
            V("char");
            throw null;
        }
    }

    public final double K(Object obj) {
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        C R = R(str);
        try {
            int i2 = m.f4903a;
            double parseDouble = Double.parseDouble(R.i());
            i iVar = this.f.f4894a;
            if (!Double.isInfinite(parseDouble) && !Double.isNaN(parseDouble)) {
                return parseDouble;
            }
            throw h.a(Double.valueOf(parseDouble), str, C().toString());
        } catch (IllegalArgumentException unused) {
            V("double");
            throw null;
        }
    }

    public final float L(Object obj) {
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        C R = R(str);
        try {
            int i2 = m.f4903a;
            float parseFloat = Float.parseFloat(R.i());
            i iVar = this.f.f4894a;
            if (!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat)) {
                return parseFloat;
            }
            throw h.a(Float.valueOf(parseFloat), str, C().toString());
        } catch (IllegalArgumentException unused) {
            V("float");
            throw null;
        }
    }

    public final c M(Object obj, f fVar) {
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        kotlin.jvm.internal.j.e(fVar, "inlineDescriptor");
        if (s.a(fVar)) {
            return new c(new b(R(str).i()), this.f);
        }
        this.d.add(str);
        return this;
    }

    public final long N(Object obj) {
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        C R = R(str);
        try {
            int i2 = m.f4903a;
            return new b(R.i()).j();
        } catch (d e7) {
            throw new NumberFormatException(e7.getMessage());
        } catch (IllegalArgumentException unused) {
            V("long");
            throw null;
        }
    }

    public final short O(Object obj) {
        Short sh;
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        try {
            int a7 = m.a(R(str));
            if (-32768 > a7 || a7 > 32767) {
                sh = null;
            } else {
                sh = Short.valueOf((short) a7);
            }
            if (sh != null) {
                return sh.shortValue();
            }
            V("short");
            throw null;
        } catch (IllegalArgumentException unused) {
            V("short");
            throw null;
        }
    }

    public final String P(Object obj) {
        s sVar;
        String str = (String) obj;
        kotlin.jvm.internal.j.e(str, "tag");
        C R = R(str);
        i iVar = this.f.f4894a;
        if (R instanceof s) {
            sVar = (s) R;
        } else {
            sVar = null;
        }
        if (sVar == null) {
            throw h.c(-1, "Unexpected 'null' literal when non-nullable string was expected");
        } else if (!sVar.d) {
            throw h.d(-1, C0212a.m("String literal for key '", str, "' should be quoted.\nUse 'isLenient = true' in 'Json {}' builder to accept non-compliant JSON."), C().toString());
        } else if (!(R instanceof v)) {
            return R.i();
        } else {
            throw h.d(-1, "Unexpected 'null' value instead of string literal", C().toString());
        }
    }

    public String Q(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return fVar.f(i2);
    }

    public final C R(String str) {
        C c5;
        kotlin.jvm.internal.j.e(str, "tag");
        l i2 = i(str);
        if (i2 instanceof C) {
            c5 = (C) i2;
        } else {
            c5 = null;
        }
        if (c5 != null) {
            return c5;
        }
        throw h.d(-1, "Expected JsonPrimitive at " + str + ", found " + i2, C().toString());
    }

    public final String S(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "<this>");
        String Q = Q(fVar, i2);
        kotlin.jvm.internal.j.e(Q, "nestedName");
        String str = (String) C1194l.U0(this.d);
        return Q;
    }

    public abstract l T();

    public final Object U() {
        ArrayList arrayList = this.d;
        Object remove = arrayList.remove(C1195m.p0(arrayList));
        this.e = true;
        return remove;
    }

    public final void V(String str) {
        throw h.d(-1, C0212a.m("Failed to parse literal as '", str, "' value"), C().toString());
    }

    public jg.a a(f fVar) {
        boolean z;
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        l C5 = C();
        C0068a b = fVar.b();
        if (kotlin.jvm.internal.j.a(b, ig.l.e)) {
            z = true;
        } else {
            z = b instanceof ig.c;
        }
        C1174b bVar = this.f;
        if (!z) {
            Class<y> cls = y.class;
            if (kotlin.jvm.internal.j.a(b, ig.l.f)) {
                f e7 = h.e(fVar.h(0), bVar.b);
                C0068a b5 = e7.b();
                if (!(b5 instanceof e) && !kotlin.jvm.internal.j.a(b5, k.d)) {
                    throw h.b(e7);
                } else if (C5 instanceof y) {
                    return new n(bVar, (y) C5);
                } else {
                    StringBuilder sb2 = new StringBuilder("Expected ");
                    w wVar = kotlin.jvm.internal.v.f4727a;
                    sb2.append(wVar.b(cls));
                    sb2.append(" as the serialized body of ");
                    sb2.append(fVar.i());
                    sb2.append(", but had ");
                    sb2.append(wVar.b(C5.getClass()));
                    throw h.c(-1, sb2.toString());
                }
            } else if (C5 instanceof y) {
                return new l(bVar, (y) C5, (String) null, (f) null);
            } else {
                StringBuilder sb3 = new StringBuilder("Expected ");
                w wVar2 = kotlin.jvm.internal.v.f4727a;
                sb3.append(wVar2.b(cls));
                sb3.append(" as the serialized body of ");
                sb3.append(fVar.i());
                sb3.append(", but had ");
                sb3.append(wVar2.b(C5.getClass()));
                throw h.c(-1, sb3.toString());
            }
        } else if (C5 instanceof C1176d) {
            return new m(bVar, (C1176d) C5);
        } else {
            StringBuilder sb4 = new StringBuilder("Expected ");
            w wVar3 = kotlin.jvm.internal.v.f4727a;
            sb4.append(wVar3.b(C1176d.class));
            sb4.append(" as the serialized body of ");
            sb4.append(fVar.i());
            sb4.append(", but had ");
            sb4.append(wVar3.b(C5.getClass()));
            throw h.c(-1, sb4.toString());
        }
    }

    public void b(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
    }

    public final int c(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "enumDescriptor");
        String str = (String) U();
        kotlin.jvm.internal.j.e(str, "tag");
        return h.j(fVar, this.f, R(str).i(), "");
    }

    public final l e() {
        return C();
    }

    public final int f() {
        String str = (String) U();
        kotlin.jvm.internal.j.e(str, "tag");
        try {
            return m.a(R(str));
        } catch (IllegalArgumentException unused) {
            V("int");
            throw null;
        }
    }

    public final Object g(gg.a aVar) {
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        return h.h(this, aVar);
    }

    public final short h(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return O(S(v, i2));
    }

    public abstract l i(String str);

    public final long j() {
        return N(U());
    }

    public final byte k(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return I(S(v, i2));
    }

    public final String l(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return P(S(fVar, i2));
    }

    public final double m(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return K(S(fVar, i2));
    }

    public final Object n(f fVar, int i2, gg.a aVar, Object obj) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        String S = S(fVar, i2);
        f0 f0Var = new f0(this, aVar, obj, 1);
        this.d.add(S);
        Object invoke = f0Var.invoke();
        if (!this.e) {
            U();
        }
        this.e = false;
        return invoke;
    }

    public final short o() {
        return O(U());
    }

    public final float p() {
        return L(U());
    }

    public final Object q(f fVar, int i2, gg.a aVar, Object obj) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        kotlin.jvm.internal.j.e(aVar, "deserializer");
        String S = S(fVar, i2);
        f0 f0Var = new f0(this, aVar, obj, 0);
        this.d.add(S);
        Object invoke = f0Var.invoke();
        if (!this.e) {
            U();
        }
        this.e = false;
        return invoke;
    }

    public final boolean r(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return H(S(fVar, i2));
    }

    public final double s() {
        return K(U());
    }

    public final long t(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        return N(S(fVar, i2));
    }

    public final c u(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        if (C1194l.U0(this.d) != null) {
            return M(U(), fVar);
        }
        return new k(this.f, T()).u(fVar);
    }

    public final boolean v() {
        return H(U());
    }

    public final char w() {
        return J(U());
    }

    public final int x(f fVar, int i2) {
        kotlin.jvm.internal.j.e(fVar, "descriptor");
        try {
            return m.a(R(S(fVar, i2)));
        } catch (IllegalArgumentException unused) {
            V("int");
            throw null;
        }
    }

    public final B0.a y() {
        return this.f.b;
    }

    public final float z(V v, int i2) {
        kotlin.jvm.internal.j.e(v, "descriptor");
        return L(S(v, i2));
    }
}
