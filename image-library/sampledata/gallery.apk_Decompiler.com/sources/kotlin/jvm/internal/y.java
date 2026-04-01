package kotlin.jvm.internal;

import Ae.b;
import Ae.e;
import Ae.f;
import Be.a;
import Be.c;
import Be.d;
import Ke.C0785e;
import i.C0212a;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class y {
    public static Map a(Object obj) {
        if (!(obj instanceof a) || (obj instanceof c)) {
            try {
                return (Map) obj;
            } catch (ClassCastException e) {
                j.i(e, y.class.getName());
                throw e;
            }
        } else {
            e(obj, "kotlin.collections.MutableMap");
            throw null;
        }
    }

    public static Set b(Object obj) {
        if (!(obj instanceof a) || (obj instanceof d)) {
            try {
                return (Set) obj;
            } catch (ClassCastException e) {
                j.i(e, y.class.getName());
                throw e;
            }
        } else {
            e(obj, "kotlin.collections.MutableSet");
            throw null;
        }
    }

    public static void c(int i2, Object obj) {
        if (obj != null && !d(i2, obj)) {
            e(obj, "kotlin.jvm.functions.Function" + i2);
            throw null;
        }
    }

    public static boolean d(int i2, Object obj) {
        int i7;
        if (obj instanceof me.c) {
            if (obj instanceof f) {
                i7 = ((f) obj).getArity();
            } else if (obj instanceof Ae.a) {
                i7 = 0;
            } else if (obj instanceof b) {
                i7 = 1;
            } else if (obj instanceof Ae.c) {
                i7 = 2;
            } else if (obj instanceof Ae.d) {
                i7 = 3;
            } else if (obj instanceof e) {
                i7 = 4;
            } else {
                boolean z = obj instanceof C0785e;
                if (z) {
                    i7 = 5;
                } else if (obj instanceof f) {
                    i7 = 6;
                } else if (z) {
                    i7 = 7;
                } else if (z) {
                    i7 = 8;
                } else if (z) {
                    i7 = 9;
                } else if (z) {
                    i7 = 10;
                } else if (z) {
                    i7 = 11;
                } else if (z) {
                    i7 = 12;
                } else if (z) {
                    i7 = 13;
                } else if (z) {
                    i7 = 14;
                } else if (z) {
                    i7 = 15;
                } else if (z) {
                    i7 = 16;
                } else if (z) {
                    i7 = 17;
                } else if (z) {
                    i7 = 18;
                } else if (z) {
                    i7 = 19;
                } else if (z) {
                    i7 = 20;
                } else if (z) {
                    i7 = 21;
                } else if (z) {
                    i7 = 22;
                } else {
                    i7 = -1;
                }
            }
            if (i7 == i2) {
                return true;
            }
        }
        return false;
    }

    public static void e(Object obj, String str) {
        String str2;
        if (obj == null) {
            str2 = "null";
        } else {
            str2 = obj.getClass().getName();
        }
        ClassCastException classCastException = new ClassCastException(C0212a.B(str2, " cannot be cast to ", str));
        j.i(classCastException, y.class.getName());
        throw classCastException;
    }
}
