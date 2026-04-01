package T2;

import U2.d;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Comparable {

    /* renamed from: h  reason: collision with root package name */
    public static final HashMap f798h = new HashMap(500);
    public final String d;
    public final c e;
    public final b f;
    public b g;

    public a(String str, c cVar, b bVar) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        } else if (cVar != null) {
            this.d = str;
            this.e = cVar;
            this.f = bVar;
            this.g = null;
        } else {
            throw new NullPointerException("returnType == null");
        }
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [U2.d, T2.b] */
    public static a c(String str) {
        a aVar;
        c cVar;
        int i2;
        if (str != null) {
            HashMap hashMap = f798h;
            synchronized (hashMap) {
                aVar = (a) hashMap.get(str);
            }
            if (aVar != null) {
                return aVar;
            }
            int length = str.length();
            if (str.charAt(0) == '(') {
                int i7 = 0;
                int i8 = 1;
                while (true) {
                    if (i8 >= length) {
                        i8 = 0;
                        break;
                    }
                    char charAt = str.charAt(i8);
                    if (charAt == ')') {
                        break;
                    }
                    if (charAt >= 'A' && charAt <= 'Z') {
                        i7++;
                    }
                    i8++;
                }
                if (i8 == 0 || i8 == length - 1) {
                    throw new IllegalArgumentException("bad descriptor");
                } else if (str.indexOf(41, i8 + 1) == -1) {
                    c[] cVarArr = new c[i7];
                    int i10 = 0;
                    int i11 = 1;
                    while (true) {
                        char charAt2 = str.charAt(i11);
                        if (charAt2 == ')') {
                            String substring = str.substring(i11 + 1);
                            HashMap hashMap2 = c.f828i;
                            try {
                                if (substring.equals("V")) {
                                    cVar = c.r;
                                } else {
                                    cVar = c.g(substring);
                                }
                                ? dVar = new d(i10);
                                for (int i12 = 0; i12 < i10; i12++) {
                                    dVar.e(i12, cVarArr[i12]);
                                }
                                return d(new a(str, cVar, dVar));
                            } catch (NullPointerException unused) {
                                throw new NullPointerException("descriptor == null");
                            }
                        } else {
                            int i13 = i11;
                            while (charAt2 == '[') {
                                i13++;
                                charAt2 = str.charAt(i13);
                            }
                            if (charAt2 == 'L') {
                                int indexOf = str.indexOf(59, i13);
                                if (indexOf != -1) {
                                    i2 = indexOf + 1;
                                } else {
                                    throw new IllegalArgumentException("bad descriptor");
                                }
                            } else {
                                i2 = i13 + 1;
                            }
                            cVarArr[i10] = c.g(str.substring(i11, i2));
                            i10++;
                            i11 = i2;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("bad descriptor");
                }
            } else {
                throw new IllegalArgumentException("bad descriptor");
            }
        } else {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static a d(a aVar) {
        HashMap hashMap = f798h;
        synchronized (hashMap) {
            try {
                String str = aVar.d;
                a aVar2 = (a) hashMap.get(str);
                if (aVar2 != null) {
                    return aVar2;
                }
                hashMap.put(str, aVar);
                return aVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public final int compareTo(a aVar) {
        if (this != aVar) {
            c cVar = aVar.e;
            b bVar = aVar.f;
            int compareTo = this.e.d.compareTo(cVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
            b bVar2 = this.f;
            int length = bVar2.e.length;
            int length2 = bVar.e.length;
            int min = Math.min(length, length2);
            for (int i2 = 0; i2 < min; i2++) {
                int compareTo2 = ((c) bVar2.d(i2)).d.compareTo(((c) bVar.d(i2)).d);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            }
            if (length < length2) {
                return -1;
            }
            if (length > length2) {
                return 1;
            }
        }
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: T2.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: U2.d} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: T2.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: T2.b} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T2.b b() {
        /*
            r9 = this;
            T2.b r0 = r9.g
            if (r0 != 0) goto L_0x0039
            T2.b r0 = r9.f
            java.lang.Object[] r1 = r0.e
            int r1 = r1.length
            T2.b r2 = new T2.b
            r2.<init>(r1)
            r3 = 0
            r4 = r3
        L_0x0010:
            if (r3 >= r1) goto L_0x0034
            java.lang.Object r5 = r0.d(r3)
            T2.c r5 = (T2.c) r5
            int r6 = r5.e
            r7 = 1
            if (r6 == r7) goto L_0x002b
            r8 = 2
            if (r6 == r8) goto L_0x002b
            r8 = 3
            if (r6 == r8) goto L_0x002b
            r8 = 6
            if (r6 == r8) goto L_0x002b
            r8 = 8
            if (r6 == r8) goto L_0x002b
            goto L_0x002e
        L_0x002b:
            T2.c r5 = T2.c.f830o
            r4 = r7
        L_0x002e:
            r2.e(r3, r5)
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0034:
            if (r4 == 0) goto L_0x0037
            r0 = r2
        L_0x0037:
            r9.g = r0
        L_0x0039:
            T2.b r9 = r9.g
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: T2.a.b():T2.b");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        return this.d.equals(((a) obj).d);
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return this.d;
    }
}
