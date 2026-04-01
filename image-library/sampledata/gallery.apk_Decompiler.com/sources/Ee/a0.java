package ee;

import B1.a;
import D1.f;
import E2.k;
import E2.v;
import He.F;
import i.C0212a;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a0 {
    public static final List d;
    public static final a0 e = Y.OK.b();
    public static final a0 f = Y.CANCELLED.b();
    public static final a0 g = Y.UNKNOWN.b();

    /* renamed from: h  reason: collision with root package name */
    public static final a0 f4286h = Y.DEADLINE_EXCEEDED.b();

    /* renamed from: i  reason: collision with root package name */
    public static final a0 f4287i = Y.PERMISSION_DENIED.b();

    /* renamed from: j  reason: collision with root package name */
    public static final a0 f4288j = Y.UNAUTHENTICATED.b();
    public static final a0 k = Y.RESOURCE_EXHAUSTED.b();
    public static final a0 l = Y.FAILED_PRECONDITION.b();
    public static final a0 m = Y.UNIMPLEMENTED.b();
    public static final a0 n = Y.INTERNAL.b();

    /* renamed from: o  reason: collision with root package name */
    public static final a0 f4289o = Y.UNAVAILABLE.b();

    /* renamed from: a  reason: collision with root package name */
    public final Y f4290a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Throwable f4291c;

    static {
        TreeMap treeMap = new TreeMap();
        Y[] values = Y.values();
        int length = values.length;
        int i2 = 0;
        while (i2 < length) {
            Y y = values[i2];
            a0 a0Var = (a0) treeMap.put(Integer.valueOf(y.c()), new a0(y, (String) null, (Throwable) null));
            if (a0Var == null) {
                i2++;
            } else {
                throw new IllegalStateException("Code value duplication between " + a0Var.f4290a.name() + " & " + y.name());
            }
        }
        d = Collections.unmodifiableList(new ArrayList(treeMap.values()));
        Y.INVALID_ARGUMENT.b();
        Y.NOT_FOUND.b();
        Y.ALREADY_EXISTS.b();
        Y.ABORTED.b();
        Y.OUT_OF_RANGE.b();
        Y.DATA_LOSS.b();
        new K("grpc-status", false, new Z(6));
        new K("grpc-message", false, new Z(0));
    }

    public a0(Y y, String str, Throwable th) {
        F.n(y, "code");
        this.f4290a = y;
        this.b = str;
        this.f4291c = th;
    }

    public static String c(a0 a0Var) {
        String str = a0Var.b;
        Y y = a0Var.f4290a;
        if (str == null) {
            return y.toString();
        }
        return y + ": " + a0Var.b;
    }

    public static a0 d(int i2) {
        if (i2 >= 0) {
            List list = d;
            if (i2 < list.size()) {
                return (a0) list.get(i2);
            }
        }
        return g.g("Unknown code " + i2);
    }

    public final c0 a() {
        return new c0(this, (M) null);
    }

    public final a0 b(String str) {
        if (str == null) {
            return this;
        }
        Throwable th = this.f4291c;
        Y y = this.f4290a;
        String str2 = this.b;
        if (str2 == null) {
            return new a0(y, str, th);
        }
        return new a0(y, C0212a.B(str2, "\n", str), th);
    }

    public final boolean e() {
        if (Y.OK == this.f4290a) {
            return true;
        }
        return false;
    }

    public final a0 f(Throwable th) {
        if (f.p(this.f4291c, th)) {
            return this;
        }
        return new a0(this.f4290a, this.b, th);
    }

    public final a0 g(String str) {
        if (f.p(this.b, str)) {
            return this;
        }
        return new a0(this.f4290a, str, this.f4291c);
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4290a.name(), "code");
        V.a(this.b, "description");
        Throwable th = this.f4291c;
        Object obj = th;
        if (th != null) {
            Object obj2 = v.f176a;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        V.a(obj, "cause");
        return V.toString();
    }
}
