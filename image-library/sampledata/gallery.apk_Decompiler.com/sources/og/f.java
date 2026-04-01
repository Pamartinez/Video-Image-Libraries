package og;

import com.samsung.android.sdk.cover.ScoverState;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends AbstractList implements RandomAccess {
    public final c[] d;
    public final int[] e;

    public f(c[] cVarArr, int[] iArr) {
        this.d = cVarArr;
        this.e = iArr;
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [og.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v10, types: [og.a, java.lang.Object] */
    public static void i(long j2, a aVar, int i2, ArrayList arrayList, int i7, int i8, ArrayList arrayList2) {
        int i10;
        int i11;
        int i12;
        ArrayList arrayList3;
        int i13;
        long j3;
        a aVar2 = aVar;
        int i14 = i2;
        ArrayList arrayList4 = arrayList;
        int i15 = i7;
        int i16 = i8;
        ArrayList arrayList5 = arrayList2;
        if (i15 < i16) {
            int i17 = i15;
            while (i17 < i16) {
                if (((c) arrayList4.get(i17)).g() >= i14) {
                    i17++;
                } else {
                    throw new AssertionError();
                }
            }
            c cVar = (c) arrayList.get(i7);
            c cVar2 = (c) arrayList4.get(i16 - 1);
            if (i14 == cVar.g()) {
                int intValue = ((Integer) arrayList5.get(i15)).intValue();
                int i18 = i15 + 1;
                c cVar3 = (c) arrayList4.get(i18);
                i10 = i18;
                i11 = intValue;
                cVar = cVar3;
            } else {
                i10 = i15;
                i11 = -1;
            }
            if (cVar.b(i14) != cVar2.b(i14)) {
                int i19 = 1;
                for (int i20 = i10 + 1; i20 < i16; i20++) {
                    if (((c) arrayList4.get(i20 - 1)).b(i14) != ((c) arrayList4.get(i20)).b(i14)) {
                        i19++;
                    }
                }
                long j8 = j2 + ((long) ((int) (aVar2.e / 4))) + 2 + ((long) (i19 * 2));
                aVar2.n(i19);
                aVar2.n(i11);
                for (int i21 = i10; i21 < i16; i21++) {
                    byte b = ((c) arrayList4.get(i21)).b(i14);
                    if (i21 == i10 || b != ((c) arrayList4.get(i21 - 1)).b(i14)) {
                        aVar2.n(b & 255);
                    }
                }
                ? obj = new Object();
                int i22 = i10;
                while (i22 < i16) {
                    byte b5 = ((c) arrayList4.get(i22)).b(i14);
                    int i23 = i22 + 1;
                    int i24 = i23;
                    while (true) {
                        if (i24 >= i16) {
                            i24 = i16;
                            break;
                        } else if (b5 != ((c) arrayList4.get(i24)).b(i14)) {
                            break;
                        } else {
                            i24++;
                        }
                    }
                    if (i23 == i24 && i14 + 1 == ((c) arrayList4.get(i22)).g()) {
                        aVar2.n(((Integer) arrayList5.get(i22)).intValue());
                        arrayList3 = arrayList5;
                        j3 = j8;
                        i13 = i24;
                    } else {
                        aVar2.n((int) ((((long) ((int) (obj.e / 4))) + j8) * -1));
                        arrayList3 = arrayList5;
                        j3 = j8;
                        i13 = i24;
                        ArrayList arrayList6 = arrayList;
                        i(j3, obj, i14 + 1, arrayList6, i22, i13, arrayList3);
                        arrayList4 = arrayList6;
                    }
                    j8 = j3;
                    i22 = i13;
                    arrayList5 = arrayList3;
                }
                aVar2.l(obj, obj.e);
                return;
            }
            ArrayList arrayList7 = arrayList5;
            int min = Math.min(cVar.g(), cVar2.g());
            int i25 = 0;
            int i26 = i14;
            while (i26 < min && cVar.b(i26) == cVar2.b(i26)) {
                i25++;
                i26++;
            }
            long j10 = j2 + ((long) ((int) (aVar2.e / 4))) + 2 + ((long) i25) + 1;
            aVar2.n(-i25);
            aVar2.n(i11);
            int i27 = i14;
            while (true) {
                i12 = i14 + i25;
                if (i27 >= i12) {
                    break;
                }
                aVar2.n(cVar.b(i27) & 255);
                i27++;
            }
            if (i10 + 1 != i16) {
                ? obj2 = new Object();
                aVar2.n((int) ((((long) ((int) (obj2.e / 4))) + j10) * -1));
                i(j10, obj2, i12, arrayList4, i10, i16, arrayList7);
                aVar2.l(obj2, obj2.e);
            } else if (i12 == ((c) arrayList4.get(i10)).g()) {
                aVar2.n(((Integer) arrayList7.get(i10)).intValue());
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [og.a, java.lang.Object] */
    public static f p(c... cVarArr) {
        int i2;
        int i7 = 0;
        if (cVarArr.length == 0) {
            return new f(new c[0], new int[]{0, -1});
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(cVarArr));
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            arrayList2.add(-1);
        }
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            arrayList2.set(Collections.binarySearch(arrayList, cVarArr[i10]), Integer.valueOf(i10));
        }
        if (((c) arrayList.get(0)).g() != 0) {
            int i11 = 0;
            while (i11 < arrayList.size()) {
                c cVar = (c) arrayList.get(i11);
                int i12 = i11 + 1;
                int i13 = i12;
                while (i13 < arrayList.size()) {
                    c cVar2 = (c) arrayList.get(i13);
                    cVar2.getClass();
                    if (!cVar2.f(cVar, cVar.g())) {
                        continue;
                        break;
                    } else if (cVar2.g() == cVar.g()) {
                        throw new IllegalArgumentException("duplicate option: " + cVar2);
                    } else if (((Integer) arrayList2.get(i13)).intValue() > ((Integer) arrayList2.get(i11)).intValue()) {
                        arrayList.remove(i13);
                        arrayList2.remove(i13);
                    } else {
                        i13++;
                    }
                }
                i11 = i12;
            }
            ? obj = new Object();
            i(0, obj, 0, arrayList, 0, arrayList.size(), arrayList2);
            int i14 = (int) (obj.e / 4);
            int[] iArr = new int[i14];
            while (i7 < i14) {
                long j2 = obj.e;
                if (j2 >= 4) {
                    j jVar = obj.d;
                    int i15 = jVar.b;
                    int i16 = jVar.f5017c;
                    if (i16 - i15 < 4) {
                        i2 = ((obj.c() & ScoverState.TYPE_NFC_SMART_COVER) << 24) | ((obj.c() & ScoverState.TYPE_NFC_SMART_COVER) << 16) | ((obj.c() & ScoverState.TYPE_NFC_SMART_COVER) << 8) | (obj.c() & ScoverState.TYPE_NFC_SMART_COVER);
                    } else {
                        byte[] bArr = jVar.f5016a;
                        int i17 = ((bArr[i15 + 1] & ScoverState.TYPE_NFC_SMART_COVER) << 16) | ((bArr[i15] & ScoverState.TYPE_NFC_SMART_COVER) << 24);
                        int i18 = i15 + 3;
                        int i19 = i15 + 4;
                        int i20 = (bArr[i18] & ScoverState.TYPE_NFC_SMART_COVER) | i17 | ((bArr[i15 + 2] & ScoverState.TYPE_NFC_SMART_COVER) << 8);
                        obj.e = j2 - 4;
                        if (i19 == i16) {
                            obj.d = jVar.a();
                            k.H(jVar);
                        } else {
                            jVar.b = i19;
                        }
                        i2 = i20;
                    }
                    iArr[i7] = i2;
                    i7++;
                } else {
                    throw new IllegalStateException("size < 4: " + obj.e);
                }
            }
            if (obj.e == 0) {
                return new f((c[]) cVarArr.clone(), iArr);
            }
            throw new AssertionError();
        }
        throw new IllegalArgumentException("the empty byte string is not a supported option");
    }

    public final Object get(int i2) {
        return this.d[i2];
    }

    public final int size() {
        return this.d.length;
    }
}
