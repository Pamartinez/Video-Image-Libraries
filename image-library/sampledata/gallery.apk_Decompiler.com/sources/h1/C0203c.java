package h1;

import V0.c;

/* renamed from: h1.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0203c extends c {

    /* renamed from: j  reason: collision with root package name */
    public static final C0204d f1733j = C0204d.f;

    /* renamed from: i  reason: collision with root package name */
    public final C0204d f1734i;

    public C0203c(Class cls, C0204d dVar) {
        super(cls);
        this.f1734i = dVar == null ? f1733j : dVar;
    }

    public static C0203c a0(Class cls) {
        return new C0203c(cls, (C0204d) null);
    }

    public final StringBuilder Y(StringBuilder sb2) {
        c cVar;
        Class cls = this.g;
        if (!cls.isPrimitive()) {
            sb2.append('L');
            String name = cls.getName();
            int length = name.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = name.charAt(i2);
                if (charAt == '.') {
                    charAt = '/';
                }
                sb2.append(charAt);
            }
        } else if (cls == Boolean.TYPE) {
            sb2.append('Z');
        } else if (cls == Byte.TYPE) {
            sb2.append('B');
        } else if (cls == Short.TYPE) {
            sb2.append('S');
        } else if (cls == Character.TYPE) {
            sb2.append('C');
        } else if (cls == Integer.TYPE) {
            sb2.append('I');
        } else if (cls == Long.TYPE) {
            sb2.append('J');
        } else if (cls == Float.TYPE) {
            sb2.append('F');
        } else if (cls == Double.TYPE) {
            sb2.append('D');
        } else if (cls == Void.TYPE) {
            sb2.append('V');
        } else {
            throw new IllegalStateException("Unrecognized primitive type: ".concat(cls.getName()));
        }
        C0204d dVar = this.f1734i;
        int length2 = dVar.d.length;
        if (length2 > 0) {
            sb2.append('<');
            for (int i7 = 0; i7 < length2; i7++) {
                if (i7 >= 0) {
                    c[] cVarArr = dVar.d;
                    if (i7 < cVarArr.length) {
                        cVar = cVarArr[i7];
                        sb2 = cVar.Y(sb2);
                    }
                } else {
                    dVar.getClass();
                }
                cVar = null;
                sb2 = cVar.Y(sb2);
            }
            sb2.append('>');
        }
        sb2.append(';');
        return sb2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: h1.c} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String Z() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.Class r1 = r5.g
            java.lang.String r2 = r1.getName()
            r0.append(r2)
            h1.d r5 = r5.f1734i
            V0.c[] r2 = r5.d
            int r2 = r2.length
            if (r2 <= 0) goto L_0x004b
            java.lang.reflect.TypeVariable[] r1 = r1.getTypeParameters()
            int r1 = r1.length
            if (r1 != r2) goto L_0x004b
            r1 = 60
            r0.append(r1)
            r1 = 0
        L_0x0022:
            if (r1 >= r2) goto L_0x0046
            if (r1 < 0) goto L_0x002f
            V0.c[] r3 = r5.d
            int r4 = r3.length
            if (r1 < r4) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            r3 = r3[r1]
            goto L_0x0033
        L_0x002f:
            r5.getClass()
        L_0x0032:
            r3 = 0
        L_0x0033:
            if (r1 <= 0) goto L_0x003a
            r4 = 44
            r0.append(r4)
        L_0x003a:
            h1.c r3 = (h1.C0203c) r3
            java.lang.String r3 = r3.Z()
            r0.append(r3)
            int r1 = r1 + 1
            goto L_0x0022
        L_0x0046:
            r5 = 62
            r0.append(r5)
        L_0x004b:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.C0203c.Z():java.lang.String");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != C0203c.class) {
            return false;
        }
        C0203c cVar = (C0203c) obj;
        if (cVar.g != this.g) {
            return false;
        }
        return this.f1734i.equals(cVar.f1734i);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("[simple type, class ");
        sb2.append(Z());
        sb2.append(']');
        return sb2.toString();
    }

    public C0203c(Class cls) {
        this(cls, C0204d.f);
    }
}
