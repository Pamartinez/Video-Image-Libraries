package oe;

import Be.c;
import L2.a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1184b;
import ne.C1187e;

/* renamed from: oe.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1217f implements Map, Serializable, c {
    public static final C1217f q;
    public Object[] d;
    public Object[] e;
    public int[] f;
    public int[] g;

    /* renamed from: h  reason: collision with root package name */
    public int f4979h;

    /* renamed from: i  reason: collision with root package name */
    public int f4980i;

    /* renamed from: j  reason: collision with root package name */
    public int f4981j;
    public int k;
    public int l;
    public C1218g m;
    public C1219h n;

    /* renamed from: o  reason: collision with root package name */
    public C1218g f4982o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f4983p;

    static {
        C1217f fVar = new C1217f(0);
        fVar.f4983p = true;
        q = fVar;
    }

    public C1217f() {
        this(8);
    }

    public final int a(Object obj) {
        c();
        while (true) {
            int l8 = l(obj);
            int i2 = this.f4979h * 2;
            int length = this.g.length / 2;
            if (i2 > length) {
                i2 = length;
            }
            int i7 = 0;
            while (true) {
                int[] iArr = this.g;
                int i8 = iArr[l8];
                if (i8 <= 0) {
                    int i10 = this.f4980i;
                    Object[] objArr = this.d;
                    if (i10 >= objArr.length) {
                        h(1);
                    } else {
                        int i11 = i10 + 1;
                        this.f4980i = i11;
                        objArr[i10] = obj;
                        this.f[i10] = l8;
                        iArr[l8] = i11;
                        this.l++;
                        this.k++;
                        if (i7 > this.f4979h) {
                            this.f4979h = i7;
                        }
                        return i10;
                    }
                } else if (j.a(this.d[i8 - 1], obj)) {
                    return -i8;
                } else {
                    i7++;
                    if (i7 > i2) {
                        n(this.g.length * 2);
                        break;
                    }
                    int i12 = l8 - 1;
                    if (l8 == 0) {
                        l8 = this.g.length - 1;
                    } else {
                        l8 = i12;
                    }
                }
            }
        }
    }

    public final C1217f b() {
        c();
        this.f4983p = true;
        if (this.l > 0) {
            return this;
        }
        C1217f fVar = q;
        j.c(fVar, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return fVar;
    }

    public final void c() {
        if (this.f4983p) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        c();
        int i2 = this.f4980i - 1;
        if (i2 >= 0) {
            int i7 = 0;
            while (true) {
                int[] iArr = this.f;
                int i8 = iArr[i7];
                if (i8 >= 0) {
                    this.g[i8] = 0;
                    iArr[i7] = -1;
                }
                if (i7 == i2) {
                    break;
                }
                i7++;
            }
        }
        a.v(this.d, 0, this.f4980i);
        Object[] objArr = this.e;
        if (objArr != null) {
            a.v(objArr, 0, this.f4980i);
        }
        this.l = 0;
        this.f4980i = 0;
        this.k++;
    }

    public final boolean containsKey(Object obj) {
        if (j(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        if (k(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final void d(boolean z) {
        int i2;
        Object[] objArr = this.e;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            i2 = this.f4980i;
            if (i7 >= i2) {
                break;
            }
            int[] iArr = this.f;
            int i10 = iArr[i7];
            if (i10 >= 0) {
                Object[] objArr2 = this.d;
                objArr2[i8] = objArr2[i7];
                if (objArr != null) {
                    objArr[i8] = objArr[i7];
                }
                if (z) {
                    iArr[i8] = i10;
                    this.g[i10] = i8 + 1;
                }
                i8++;
            }
            i7++;
        }
        a.v(this.d, i8, i2);
        if (objArr != null) {
            a.v(objArr, i8, this.f4980i);
        }
        this.f4980i = i8;
    }

    public final boolean e(Collection collection) {
        j.e(collection, "m");
        for (Object next : collection) {
            if (next != null) {
                try {
                    if (!f((Map.Entry) next)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final Set entrySet() {
        C1218g gVar = this.f4982o;
        if (gVar != null) {
            return gVar;
        }
        C1218g gVar2 = new C1218g(this, 0);
        this.f4982o = gVar2;
        return gVar2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.l != map.size() || !e(map.entrySet())) {
            return false;
        }
        return true;
    }

    public final boolean f(Map.Entry entry) {
        j.e(entry, "entry");
        int j2 = j(entry.getKey());
        if (j2 < 0) {
            return false;
        }
        Object[] objArr = this.e;
        j.b(objArr);
        return j.a(objArr[j2], entry.getValue());
    }

    public final Object get(Object obj) {
        int j2 = j(obj);
        if (j2 < 0) {
            return null;
        }
        Object[] objArr = this.e;
        j.b(objArr);
        return objArr[j2];
    }

    public final void h(int i2) {
        Object[] objArr;
        Object[] objArr2 = this.d;
        int length = objArr2.length;
        int i7 = this.f4980i;
        int i8 = length - i7;
        int i10 = i7 - this.l;
        int i11 = 1;
        if (i8 >= i2 || i8 + i10 < i2 || i10 < objArr2.length / 4) {
            int i12 = i7 + i2;
            if (i12 < 0) {
                throw new OutOfMemoryError();
            } else if (i12 > objArr2.length) {
                C1184b bVar = C1187e.Companion;
                int length2 = objArr2.length;
                bVar.getClass();
                int d2 = C1184b.d(length2, i12);
                Object[] objArr3 = this.d;
                j.e(objArr3, "<this>");
                Object[] copyOf = Arrays.copyOf(objArr3, d2);
                j.d(copyOf, "copyOf(...)");
                this.d = copyOf;
                Object[] objArr4 = this.e;
                if (objArr4 != null) {
                    objArr = Arrays.copyOf(objArr4, d2);
                    j.d(objArr, "copyOf(...)");
                } else {
                    objArr = null;
                }
                this.e = objArr;
                int[] copyOf2 = Arrays.copyOf(this.f, d2);
                j.d(copyOf2, "copyOf(...)");
                this.f = copyOf2;
                if (d2 >= 1) {
                    i11 = d2;
                }
                int highestOneBit = Integer.highestOneBit(i11 * 3);
                if (highestOneBit > this.g.length) {
                    n(highestOneBit);
                }
            }
        } else {
            d(true);
        }
    }

    public final int hashCode() {
        int i2;
        int i7;
        C1215d dVar = new C1215d(this, 0);
        int i8 = 0;
        while (dVar.hasNext()) {
            int i10 = dVar.e;
            C1217f fVar = dVar.d;
            if (i10 < fVar.f4980i) {
                dVar.e = i10 + 1;
                dVar.f = i10;
                Object obj = fVar.d[i10];
                if (obj != null) {
                    i2 = obj.hashCode();
                } else {
                    i2 = 0;
                }
                Object[] objArr = fVar.e;
                j.b(objArr);
                Object obj2 = objArr[dVar.f];
                if (obj2 != null) {
                    i7 = obj2.hashCode();
                } else {
                    i7 = 0;
                }
                dVar.b();
                i8 += i2 ^ i7;
            } else {
                throw new NoSuchElementException();
            }
        }
        return i8;
    }

    public final boolean isEmpty() {
        if (this.l == 0) {
            return true;
        }
        return false;
    }

    public final int j(Object obj) {
        int l8 = l(obj);
        int i2 = this.f4979h;
        while (true) {
            int i7 = this.g[l8];
            if (i7 == 0) {
                return -1;
            }
            if (i7 > 0) {
                int i8 = i7 - 1;
                if (j.a(this.d[i8], obj)) {
                    return i8;
                }
            }
            i2--;
            if (i2 < 0) {
                return -1;
            }
            int i10 = l8 - 1;
            if (l8 == 0) {
                l8 = this.g.length - 1;
            } else {
                l8 = i10;
            }
        }
    }

    public final int k(Object obj) {
        int i2 = this.f4980i;
        while (true) {
            i2--;
            if (i2 < 0) {
                return -1;
            }
            if (this.f[i2] >= 0) {
                Object[] objArr = this.e;
                j.b(objArr);
                if (j.a(objArr[i2], obj)) {
                    return i2;
                }
            }
        }
    }

    public final Set keySet() {
        C1218g gVar = this.m;
        if (gVar != null) {
            return gVar;
        }
        C1218g gVar2 = new C1218g(this, 1);
        this.m = gVar2;
        return gVar2;
    }

    public final int l(Object obj) {
        int i2;
        if (obj != null) {
            i2 = obj.hashCode();
        } else {
            i2 = 0;
        }
        return (i2 * -1640531527) >>> this.f4981j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0032, code lost:
        r3[r0] = r6;
        r5.f[r2] = r0;
        r2 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void n(int r6) {
        /*
            r5 = this;
            int r0 = r5.k
            int r0 = r0 + 1
            r5.k = r0
            int r0 = r5.f4980i
            int r1 = r5.l
            r2 = 0
            if (r0 <= r1) goto L_0x0010
            r5.d(r2)
        L_0x0010:
            int[] r0 = new int[r6]
            r5.g = r0
            int r6 = java.lang.Integer.numberOfLeadingZeros(r6)
            int r6 = r6 + 1
            r5.f4981j = r6
        L_0x001c:
            int r6 = r5.f4980i
            if (r2 >= r6) goto L_0x0050
            int r6 = r2 + 1
            java.lang.Object[] r0 = r5.d
            r0 = r0[r2]
            int r0 = r5.l(r0)
            int r1 = r5.f4979h
        L_0x002c:
            int[] r3 = r5.g
            r4 = r3[r0]
            if (r4 != 0) goto L_0x003a
            r3[r0] = r6
            int[] r1 = r5.f
            r1[r2] = r0
            r2 = r6
            goto L_0x001c
        L_0x003a:
            int r1 = r1 + -1
            if (r1 < 0) goto L_0x0048
            int r4 = r0 + -1
            if (r0 != 0) goto L_0x0046
            int r0 = r3.length
            int r0 = r0 + -1
            goto L_0x002c
        L_0x0046:
            r0 = r4
            goto L_0x002c
        L_0x0048:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r5.<init>(r6)
            throw r5
        L_0x0050:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: oe.C1217f.n(int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void o(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.d
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.j.e(r0, r1)
            r1 = 0
            r0[r12] = r1
            java.lang.Object[] r0 = r11.e
            if (r0 == 0) goto L_0x0010
            r0[r12] = r1
        L_0x0010:
            int[] r0 = r11.f
            r0 = r0[r12]
            int r1 = r11.f4979h
            int r1 = r1 * 2
            int[] r2 = r11.g
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L_0x0020
            r1 = r2
        L_0x0020:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L_0x0024:
            int r5 = r0 + -1
            if (r0 != 0) goto L_0x002e
            int[] r0 = r11.g
            int r0 = r0.length
            int r0 = r0 + -1
            goto L_0x002f
        L_0x002e:
            r0 = r5
        L_0x002f:
            int r4 = r4 + 1
            int r5 = r11.f4979h
            r6 = -1
            if (r4 <= r5) goto L_0x003b
            int[] r0 = r11.g
            r0[r1] = r2
            goto L_0x006c
        L_0x003b:
            int[] r5 = r11.g
            r7 = r5[r0]
            if (r7 != 0) goto L_0x0044
            r5[r1] = r2
            goto L_0x006c
        L_0x0044:
            if (r7 >= 0) goto L_0x004b
            r5[r1] = r6
        L_0x0048:
            r1 = r0
            r4 = r2
            goto L_0x0065
        L_0x004b:
            java.lang.Object[] r5 = r11.d
            int r8 = r7 + -1
            r5 = r5[r8]
            int r5 = r11.l(r5)
            int r5 = r5 - r0
            int[] r9 = r11.g
            int r10 = r9.length
            int r10 = r10 + -1
            r5 = r5 & r10
            if (r5 < r4) goto L_0x0065
            r9[r1] = r7
            int[] r4 = r11.f
            r4[r8] = r1
            goto L_0x0048
        L_0x0065:
            int r3 = r3 + r6
            if (r3 >= 0) goto L_0x0024
            int[] r0 = r11.g
            r0[r1] = r6
        L_0x006c:
            int[] r0 = r11.f
            r0[r12] = r6
            int r12 = r11.l
            int r12 = r12 + r6
            r11.l = r12
            int r12 = r11.k
            int r12 = r12 + 1
            r11.k = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: oe.C1217f.o(int):void");
    }

    public final Object put(Object obj, Object obj2) {
        c();
        int a7 = a(obj);
        Object[] objArr = this.e;
        if (objArr == null) {
            int length = this.d.length;
            if (length >= 0) {
                objArr = new Object[length];
                this.e = objArr;
            } else {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
        }
        if (a7 < 0) {
            int i2 = (-a7) - 1;
            Object obj3 = objArr[i2];
            objArr[i2] = obj2;
            return obj3;
        }
        objArr[a7] = obj2;
        return null;
    }

    public final void putAll(Map map) {
        j.e(map, "from");
        c();
        Set<Map.Entry> entrySet = map.entrySet();
        if (!entrySet.isEmpty()) {
            h(entrySet.size());
            for (Map.Entry entry : entrySet) {
                int a7 = a(entry.getKey());
                Object[] objArr = this.e;
                if (objArr == null) {
                    int length = this.d.length;
                    if (length >= 0) {
                        objArr = new Object[length];
                        this.e = objArr;
                    } else {
                        throw new IllegalArgumentException("capacity must be non-negative.");
                    }
                }
                if (a7 >= 0) {
                    objArr[a7] = entry.getValue();
                } else {
                    int i2 = (-a7) - 1;
                    if (!j.a(entry.getValue(), objArr[i2])) {
                        objArr[i2] = entry.getValue();
                    }
                }
            }
        }
    }

    public final Object remove(Object obj) {
        c();
        int j2 = j(obj);
        if (j2 < 0) {
            return null;
        }
        Object[] objArr = this.e;
        j.b(objArr);
        Object obj2 = objArr[j2];
        o(j2);
        return obj2;
    }

    public final int size() {
        return this.l;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder((this.l * 3) + 2);
        sb2.append("{");
        C1215d dVar = new C1215d(this, 0);
        int i2 = 0;
        while (dVar.hasNext()) {
            if (i2 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            int i7 = dVar.e;
            C1217f fVar = dVar.d;
            if (i7 < fVar.f4980i) {
                dVar.e = i7 + 1;
                dVar.f = i7;
                Object obj = fVar.d[i7];
                if (obj == fVar) {
                    sb2.append("(this Map)");
                } else {
                    sb2.append(obj);
                }
                sb2.append('=');
                Object[] objArr = fVar.e;
                j.b(objArr);
                Object obj2 = objArr[dVar.f];
                if (obj2 == fVar) {
                    sb2.append("(this Map)");
                } else {
                    sb2.append(obj2);
                }
                dVar.b();
                i2++;
            } else {
                throw new NoSuchElementException();
            }
        }
        sb2.append("}");
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final Collection values() {
        C1219h hVar = this.n;
        if (hVar != null) {
            return hVar;
        }
        C1219h hVar2 = new C1219h(this);
        this.n = hVar2;
        return hVar2;
    }

    public C1217f(int i2) {
        if (i2 >= 0) {
            Object[] objArr = new Object[i2];
            int[] iArr = new int[i2];
            int highestOneBit = Integer.highestOneBit((i2 < 1 ? 1 : i2) * 3);
            this.d = objArr;
            this.e = null;
            this.f = iArr;
            this.g = new int[highestOneBit];
            this.f4979h = 2;
            this.f4980i = 0;
            this.f4981j = Integer.numberOfLeadingZeros(highestOneBit) + 1;
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }
}
