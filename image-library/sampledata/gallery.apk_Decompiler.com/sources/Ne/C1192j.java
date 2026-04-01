package ne;

import A.a;
import Ae.b;
import Qe.B;
import Sf.e;
import Sf.k;
import Sf.o;
import Sf.r;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import me.p;
import me.t;
import me.w;
import o1.C0246a;

/* renamed from: ne.j  reason: case insensitive filesystem */
public abstract class C1192j extends c {
    public static ArrayList A0(Object[] objArr, Object[] objArr2) {
        j.e(objArr, "<this>");
        j.e(objArr2, "other");
        int min = Math.min(objArr.length, objArr2.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(new i(objArr[i2], objArr2[i2]));
        }
        return arrayList;
    }

    public static Iterable Z(Object[] objArr) {
        j.e(objArr, "<this>");
        if (objArr.length == 0) {
            return C1202t.d;
        }
        return new r(2, objArr);
    }

    public static List a0(Object[] objArr) {
        j.e(objArr, "<this>");
        List asList = Arrays.asList(objArr);
        j.d(asList, "asList(...)");
        return asList;
    }

    public static k b0(Object[] objArr) {
        if (objArr.length == 0) {
            return e.f3730a;
        }
        return new o(3, objArr);
    }

    public static boolean c0(int i2, int[] iArr) {
        j.e(iArr, "<this>");
        int length = iArr.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            } else if (i2 == iArr[i7]) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 >= 0) {
            return true;
        }
        return false;
    }

    public static boolean d0(Object[] objArr, Object obj) {
        j.e(objArr, "<this>");
        if (q0(objArr, obj) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean e0(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr.length == objArr2.length) {
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Object[] objArr3 = objArr[i2];
                Object[] objArr4 = objArr2[i2];
                if (objArr3 != objArr4) {
                    if (!(objArr3 == null || objArr4 == null)) {
                        if (!(objArr3 instanceof Object[]) || !(objArr4 instanceof Object[])) {
                            if (!(objArr3 instanceof byte[]) || !(objArr4 instanceof byte[])) {
                                if (!(objArr3 instanceof short[]) || !(objArr4 instanceof short[])) {
                                    if (!(objArr3 instanceof int[]) || !(objArr4 instanceof int[])) {
                                        if (!(objArr3 instanceof long[]) || !(objArr4 instanceof long[])) {
                                            if (!(objArr3 instanceof float[]) || !(objArr4 instanceof float[])) {
                                                if (!(objArr3 instanceof double[]) || !(objArr4 instanceof double[])) {
                                                    if (!(objArr3 instanceof char[]) || !(objArr4 instanceof char[])) {
                                                        if (!(objArr3 instanceof boolean[]) || !(objArr4 instanceof boolean[])) {
                                                            if (!(objArr3 instanceof p) || !(objArr4 instanceof p)) {
                                                                if (!(objArr3 instanceof w) || !(objArr4 instanceof w)) {
                                                                    if (!(objArr3 instanceof me.r) || !(objArr4 instanceof me.r)) {
                                                                        if (!(objArr3 instanceof t) || !(objArr4 instanceof t)) {
                                                                            if (!objArr3.equals(objArr4)) {
                                                                            }
                                                                        } else if (!Arrays.equals(((t) objArr3).d, ((t) objArr4).d)) {
                                                                        }
                                                                    } else if (!Arrays.equals(((me.r) objArr3).d, ((me.r) objArr4).d)) {
                                                                    }
                                                                } else if (!Arrays.equals(((w) objArr3).d, ((w) objArr4).d)) {
                                                                }
                                                            } else if (!Arrays.equals(((p) objArr3).d, ((p) objArr4).d)) {
                                                            }
                                                        } else if (!Arrays.equals((boolean[]) objArr3, (boolean[]) objArr4)) {
                                                        }
                                                    } else if (!Arrays.equals((char[]) objArr3, (char[]) objArr4)) {
                                                    }
                                                } else if (!Arrays.equals((double[]) objArr3, (double[]) objArr4)) {
                                                }
                                            } else if (!Arrays.equals((float[]) objArr3, (float[]) objArr4)) {
                                            }
                                        } else if (!Arrays.equals((long[]) objArr3, (long[]) objArr4)) {
                                        }
                                    } else if (!Arrays.equals((int[]) objArr3, (int[]) objArr4)) {
                                    }
                                } else if (!Arrays.equals((short[]) objArr3, (short[]) objArr4)) {
                                }
                            } else if (!Arrays.equals((byte[]) objArr3, (byte[]) objArr4)) {
                            }
                        } else if (!e0(objArr3, objArr4)) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void f0(int i2, int i7, int i8, int[] iArr, int[] iArr2) {
        j.e(iArr, "<this>");
        j.e(iArr2, "destination");
        System.arraycopy(iArr, i7, iArr2, i2, i8 - i7);
    }

    public static void g0(int i2, int i7, int i8, Object[] objArr, Object[] objArr2) {
        j.e(objArr, "<this>");
        j.e(objArr2, "destination");
        System.arraycopy(objArr, i7, objArr2, i2, i8 - i7);
    }

    public static /* synthetic */ void h0(int i2, int i7, int i8, Object[] objArr, Object[] objArr2) {
        if ((i8 & 4) != 0) {
            i2 = 0;
        }
        if ((i8 & 8) != 0) {
            i7 = objArr.length;
        }
        g0(0, i2, i7, objArr, objArr2);
    }

    public static Object[] i0(Object[] objArr, int i2, int i7) {
        j.e(objArr, "<this>");
        int length = objArr.length;
        if (i7 <= length) {
            Object[] copyOfRange = Arrays.copyOfRange(objArr, i2, i7);
            j.d(copyOfRange, "copyOfRange(...)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException(a.d(i7, length, "toIndex (", ") is greater than size (", ")."));
    }

    public static List j0(int i2, Object[] objArr) {
        if (i2 >= 0) {
            int length = objArr.length - i2;
            if (length < 0) {
                length = 0;
            }
            if (length < 0) {
                throw new IllegalArgumentException(C0212a.j(length, "Requested element count ", " is less than zero.").toString());
            } else if (length == 0) {
                return C1202t.d;
            } else {
                int length2 = objArr.length;
                if (length >= length2) {
                    return x0(objArr);
                }
                if (length == 1) {
                    return C0246a.e0(objArr[length2 - 1]);
                }
                ArrayList arrayList = new ArrayList(length);
                for (int i7 = length2 - length; i7 < length2; i7++) {
                    arrayList.add(objArr[i7]);
                }
                return arrayList;
            }
        } else {
            throw new IllegalArgumentException(C0212a.j(i2, "Requested element count ", " is less than zero.").toString());
        }
    }

    public static final void k0(Object[] objArr, B b, int i2, int i7) {
        j.e(objArr, "<this>");
        Arrays.fill(objArr, i2, i7, b);
    }

    public static ArrayList l0(Object[] objArr) {
        j.e(objArr, "<this>");
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object m0(Object[] objArr) {
        j.e(objArr, "<this>");
        if (objArr.length != 0) {
            return objArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static Object n0(Object[] objArr) {
        j.e(objArr, "<this>");
        if (objArr.length == 0) {
            return null;
        }
        return objArr[0];
    }

    public static Integer o0(int i2, int[] iArr) {
        j.e(iArr, "<this>");
        if (i2 < 0 || i2 >= iArr.length) {
            return null;
        }
        return Integer.valueOf(iArr[i2]);
    }

    public static Object p0(int i2, Object[] objArr) {
        j.e(objArr, "<this>");
        if (i2 < 0 || i2 >= objArr.length) {
            return null;
        }
        return objArr[i2];
    }

    public static int q0(Object[] objArr, Object obj) {
        j.e(objArr, "<this>");
        int i2 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i2 < length) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i2 < length2) {
            if (obj.equals(objArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static final void r0(Object[] objArr, StringBuilder sb2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, b bVar) {
        j.e(objArr, "<this>");
        sb2.append(charSequence2);
        int i2 = 0;
        for (Object obj : objArr) {
            i2++;
            if (i2 > 1) {
                sb2.append(charSequence);
            }
            Gd.a.b(sb2, obj, bVar);
        }
        sb2.append(charSequence3);
    }

    public static String s0(Object[] objArr, String str, String str2, String str3, b bVar, int i2) {
        String str4;
        String str5;
        if ((i2 & 1) != 0) {
            str = ArcCommonLog.TAG_COMMA;
        }
        String str6 = str;
        if ((i2 & 2) != 0) {
            str4 = "";
        } else {
            str4 = str2;
        }
        if ((i2 & 4) != 0) {
            str5 = "";
        } else {
            str5 = str3;
        }
        if ((i2 & 32) != 0) {
            bVar = null;
        }
        j.e(objArr, "<this>");
        StringBuilder sb2 = new StringBuilder();
        r0(objArr, sb2, str6, str4, str5, "...", bVar);
        return sb2.toString();
    }

    public static Object t0(Object[] objArr) {
        j.e(objArr, "<this>");
        if (objArr.length != 0) {
            return objArr[objArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static Object[] u0(Collection collection, Object[] objArr) {
        j.e(collection, "elements");
        int length = objArr.length;
        Object[] copyOf = Arrays.copyOf(objArr, collection.size() + length);
        for (Object obj : collection) {
            copyOf[length] = obj;
            length++;
        }
        j.b(copyOf);
        return copyOf;
    }

    public static Object v0(Object[] objArr) {
        j.e(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (length == 1) {
            return objArr[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    public static final void w0(Object[] objArr, LinkedHashSet linkedHashSet) {
        j.e(objArr, "<this>");
        for (Object add : objArr) {
            linkedHashSet.add(add);
        }
    }

    public static List x0(Object[] objArr) {
        j.e(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            return C1202t.d;
        }
        if (length != 1) {
            return new ArrayList(new C1190h(objArr, false));
        }
        return C0246a.e0(objArr[0]);
    }

    public static Set y0(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return v.d;
        }
        if (length == 1) {
            return B1.a.S(Integer.valueOf(iArr[0]));
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(iArr.length));
        for (int valueOf : iArr) {
            linkedHashSet.add(Integer.valueOf(valueOf));
        }
        return linkedHashSet;
    }

    public static Set z0(Object[] objArr) {
        j.e(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            return v.d;
        }
        if (length == 1) {
            return B1.a.S(objArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(objArr.length));
        w0(objArr, linkedHashSet);
        return linkedHashSet;
    }
}
