package vf;

import Df.C0736b;
import Ne.l;
import Qe.C;
import Te.z;
import java.util.ArrayList;
import java.util.List;
import ne.C1194l;
import ne.C1202t;
import o1.C0246a;

/* renamed from: vf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1328h {
    public static C1322b a(List list, C c5, l lVar) {
        ArrayList arrayList = new ArrayList();
        for (Object b : C1194l.k1(list)) {
            C1327g b5 = b((z) null, b);
            if (b5 != null) {
                arrayList.add(b5);
            }
        }
        if (c5 != null) {
            return new C1346z(arrayList, c5.f().q(lVar));
        }
        return new C1322b(arrayList, new C0736b(26, lVar));
    }

    public static C1327g b(z zVar, Object obj) {
        if (obj instanceof Byte) {
            return new C1324d(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new C1344x(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new C1331k(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new C1342v(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new C1327g((Character) obj);
        }
        if (obj instanceof Float) {
            return new C1323c(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new C1323c(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new C1323c((Object) (Boolean) obj);
        }
        if (obj instanceof String) {
            return new C1327g((String) obj);
        }
        boolean z = obj instanceof byte[];
        List list = C1202t.d;
        int i2 = 0;
        if (z) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            ArrayList arrayList = list;
            if (length != 0) {
                if (length != 1) {
                    ArrayList arrayList2 = new ArrayList(bArr.length);
                    int length2 = bArr.length;
                    while (i2 < length2) {
                        arrayList2.add(Byte.valueOf(bArr[i2]));
                        i2++;
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = C0246a.e0(Byte.valueOf(bArr[0]));
                }
            }
            return a(arrayList, zVar, l.BYTE);
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int length3 = sArr.length;
            ArrayList arrayList3 = list;
            if (length3 != 0) {
                if (length3 != 1) {
                    ArrayList arrayList4 = new ArrayList(sArr.length);
                    int length4 = sArr.length;
                    while (i2 < length4) {
                        arrayList4.add(Short.valueOf(sArr[i2]));
                        i2++;
                    }
                    arrayList3 = arrayList4;
                } else {
                    arrayList3 = C0246a.e0(Short.valueOf(sArr[0]));
                }
            }
            return a(arrayList3, zVar, l.SHORT);
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            int length5 = iArr.length;
            ArrayList arrayList5 = list;
            if (length5 != 0) {
                if (length5 != 1) {
                    ArrayList arrayList6 = new ArrayList(iArr.length);
                    int length6 = iArr.length;
                    while (i2 < length6) {
                        arrayList6.add(Integer.valueOf(iArr[i2]));
                        i2++;
                    }
                    arrayList5 = arrayList6;
                } else {
                    arrayList5 = C0246a.e0(Integer.valueOf(iArr[0]));
                }
            }
            return a(arrayList5, zVar, l.INT);
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            int length7 = jArr.length;
            ArrayList arrayList7 = list;
            if (length7 != 0) {
                if (length7 != 1) {
                    ArrayList arrayList8 = new ArrayList(jArr.length);
                    int length8 = jArr.length;
                    while (i2 < length8) {
                        arrayList8.add(Long.valueOf(jArr[i2]));
                        i2++;
                    }
                    arrayList7 = arrayList8;
                } else {
                    arrayList7 = C0246a.e0(Long.valueOf(jArr[0]));
                }
            }
            return a(arrayList7, zVar, l.LONG);
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            int length9 = cArr.length;
            ArrayList arrayList9 = list;
            if (length9 != 0) {
                if (length9 != 1) {
                    ArrayList arrayList10 = new ArrayList(cArr.length);
                    int length10 = cArr.length;
                    while (i2 < length10) {
                        arrayList10.add(Character.valueOf(cArr[i2]));
                        i2++;
                    }
                    arrayList9 = arrayList10;
                } else {
                    arrayList9 = C0246a.e0(Character.valueOf(cArr[0]));
                }
            }
            return a(arrayList9, zVar, l.CHAR);
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length11 = fArr.length;
            ArrayList arrayList11 = list;
            if (length11 != 0) {
                if (length11 != 1) {
                    ArrayList arrayList12 = new ArrayList(fArr.length);
                    int length12 = fArr.length;
                    while (i2 < length12) {
                        arrayList12.add(Float.valueOf(fArr[i2]));
                        i2++;
                    }
                    arrayList11 = arrayList12;
                } else {
                    arrayList11 = C0246a.e0(Float.valueOf(fArr[0]));
                }
            }
            return a(arrayList11, zVar, l.FLOAT);
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length13 = dArr.length;
            ArrayList arrayList13 = list;
            if (length13 != 0) {
                if (length13 != 1) {
                    ArrayList arrayList14 = new ArrayList(dArr.length);
                    int length14 = dArr.length;
                    while (i2 < length14) {
                        arrayList14.add(Double.valueOf(dArr[i2]));
                        i2++;
                    }
                    arrayList13 = arrayList14;
                } else {
                    arrayList13 = C0246a.e0(Double.valueOf(dArr[0]));
                }
            }
            return a(arrayList13, zVar, l.DOUBLE);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length15 = zArr.length;
            ArrayList arrayList15 = list;
            if (length15 != 0) {
                if (length15 != 1) {
                    ArrayList arrayList16 = new ArrayList(zArr.length);
                    int length16 = zArr.length;
                    while (i2 < length16) {
                        arrayList16.add(Boolean.valueOf(zArr[i2]));
                        i2++;
                    }
                    arrayList15 = arrayList16;
                } else {
                    arrayList15 = C0246a.e0(Boolean.valueOf(zArr[0]));
                }
            }
            return a(arrayList15, zVar, l.BOOLEAN);
        } else if (obj == null) {
            return new C1327g((Object) null);
        } else {
            return null;
        }
    }
}
