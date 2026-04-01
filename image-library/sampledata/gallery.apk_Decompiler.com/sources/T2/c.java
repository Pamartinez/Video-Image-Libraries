package T2;

import com.adobe.internal.xmp.XMPConst;
import java.util.HashMap;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements d, Comparable {

    /* renamed from: A  reason: collision with root package name */
    public static final c f816A = g("Ljava/lang/Character;");
    public static final c B = g("Ljava/lang/Double;");

    /* renamed from: C  reason: collision with root package name */
    public static final c f817C = g("Ljava/lang/Float;");
    public static final c D = g("Ljava/lang/Integer;");
    public static final c E = g("Ljava/lang/Long;");

    /* renamed from: F  reason: collision with root package name */
    public static final c f818F = g("Ljava/lang/Short;");

    /* renamed from: G  reason: collision with root package name */
    public static final c f819G = g("Ljava/lang/Void;");

    /* renamed from: H  reason: collision with root package name */
    public static final c f820H;

    /* renamed from: I  reason: collision with root package name */
    public static final c f821I;

    /* renamed from: J  reason: collision with root package name */
    public static final c f822J;

    /* renamed from: K  reason: collision with root package name */
    public static final c f823K;
    public static final c L;

    /* renamed from: M  reason: collision with root package name */
    public static final c f824M;

    /* renamed from: N  reason: collision with root package name */
    public static final c f825N;

    /* renamed from: O  reason: collision with root package name */
    public static final c f826O;

    /* renamed from: P  reason: collision with root package name */
    public static final c f827P;

    /* renamed from: i  reason: collision with root package name */
    public static final HashMap f828i = new HashMap(500);

    /* renamed from: j  reason: collision with root package name */
    public static final c f829j;
    public static final c k;
    public static final c l;
    public static final c m;
    public static final c n;

    /* renamed from: o  reason: collision with root package name */
    public static final c f830o;

    /* renamed from: p  reason: collision with root package name */
    public static final c f831p;
    public static final c q;
    public static final c r = new c("V", 0);
    public static final c s = new c("<null>", 9);
    public static final c t = new c("<addr>", 10);
    public static final c u = g("Ljava/lang/Class;");
    public static final c v;
    public static final c w = g("Ljava/lang/String;");

    /* renamed from: x  reason: collision with root package name */
    public static final c f832x = g("Ljava/lang/Throwable;");
    public static final c y = g("Ljava/lang/Boolean;");
    public static final c z = g("Ljava/lang/Byte;");
    public final String d;
    public final int e;
    public String f;
    public c g;

    /* renamed from: h  reason: collision with root package name */
    public c f833h;

    static {
        c cVar = new c("Z", 1);
        f829j = cVar;
        c cVar2 = new c("B", 2);
        k = cVar2;
        c cVar3 = new c("C", 3);
        l = cVar3;
        c cVar4 = new c("D", 4);
        m = cVar4;
        c cVar5 = new c("F", 5);
        n = cVar5;
        c cVar6 = new c("I", 6);
        f830o = cVar6;
        c cVar7 = new c("J", 7);
        f831p = cVar7;
        c cVar8 = new c("S", 8);
        q = cVar8;
        h(cVar);
        h(cVar2);
        h(cVar3);
        h(cVar4);
        h(cVar5);
        h(cVar6);
        h(cVar7);
        h(cVar8);
        g("Ljava/lang/annotation/Annotation;");
        g("Ljava/lang/Cloneable;");
        c g3 = g("Ljava/lang/Object;");
        v = g3;
        g("Ljava/io/Serializable;");
        f820H = cVar.c();
        f821I = cVar2.c();
        f822J = cVar3.c();
        f823K = cVar4.c();
        L = cVar5.c();
        f824M = cVar6.c();
        f825N = cVar7.c();
        f826O = g3.c();
        f827P = cVar8.c();
    }

    public c(String str, int i2) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        } else if (i2 < 0 || i2 >= 11) {
            throw new IllegalArgumentException("bad basicType");
        } else {
            this.d = str;
            this.e = i2;
            this.g = null;
            this.f833h = null;
        }
    }

    public static c g(String str) {
        c cVar;
        HashMap hashMap = f828i;
        synchronized (hashMap) {
            cVar = (c) hashMap.get(str);
        }
        if (cVar != null) {
            return cVar;
        }
        try {
            char charAt = str.charAt(0);
            if (charAt == '[') {
                return g(str.substring(1)).c();
            }
            int length = str.length();
            if (charAt == 'L') {
                int i2 = length - 1;
                if (str.charAt(i2) == ';') {
                    int i7 = 1;
                    while (i7 < i2) {
                        char charAt2 = str.charAt(i7);
                        if (!(charAt2 == '(' || charAt2 == ')' || charAt2 == '.')) {
                            if (charAt2 != '/') {
                                if (!(charAt2 == ';' || charAt2 == '[')) {
                                }
                            } else if (i7 == 1 || i7 == i2 || str.charAt(i7 - 1) == '/') {
                                throw new IllegalArgumentException("bad descriptor: ".concat(str));
                            }
                            i7++;
                        }
                        throw new IllegalArgumentException("bad descriptor: ".concat(str));
                    }
                    return h(new c(str, 9));
                }
            }
            throw new IllegalArgumentException("bad descriptor: ".concat(str));
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("descriptor is empty");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static c h(c cVar) {
        HashMap hashMap = f828i;
        synchronized (hashMap) {
            try {
                String str = cVar.d;
                c cVar2 = (c) hashMap.get(str);
                if (cVar2 != null) {
                    return cVar2;
                }
                hashMap.put(str, cVar);
                return cVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String a() {
        String str = this.d;
        int i2 = this.e;
        switch (i2) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "byte";
            case 3:
                return "char";
            case 4:
                return "double";
            case 5:
                return "float";
            case 6:
                return "int";
            case 7:
                return "long";
            case 8:
                return "short";
            case 9:
                if (str.charAt(0) == '[') {
                    return f().a() + XMPConst.ARRAY_ITEM_NAME;
                }
                if (this.f == null) {
                    if (i2 != 9) {
                        throw new IllegalArgumentException("not an object type: ".concat(str));
                    } else if (str.charAt(0) == '[') {
                        this.f = str;
                    } else {
                        this.f = C0280e.d(1, 1, str);
                    }
                }
                return this.f.replace("/", ".");
            default:
                return str;
        }
    }

    public final int b() {
        return this.e;
    }

    public final c c() {
        if (this.g == null) {
            this.g = h(new c("[" + this.d, 9));
        }
        return this.g;
    }

    public final int compareTo(Object obj) {
        return this.d.compareTo(((c) obj).d);
    }

    public final int d() {
        int i2 = this.e;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 6 || i2 == 8) {
            return 6;
        }
        return i2;
    }

    public final int e() {
        int i2 = this.e;
        if (i2 == 4 || i2 == 7) {
            return 2;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        return this.d.equals(((c) obj).d);
    }

    public final c f() {
        if (this.f833h == null) {
            String str = this.d;
            if (str.charAt(0) == '[') {
                this.f833h = g(str.substring(1));
            } else {
                throw new IllegalArgumentException("not an array type: " + str);
            }
        }
        return this.f833h;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return this.d;
    }

    public final c getType() {
        return this;
    }
}
