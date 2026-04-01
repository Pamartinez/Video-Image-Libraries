package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i {

    /* renamed from: a  reason: collision with root package name */
    public static final Object[] f4726a = new Object[0];

    public static final Object[] a(Collection collection) {
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArr = new Object[size];
                int i2 = 0;
                while (true) {
                    int i7 = i2 + 1;
                    objArr[i2] = it.next();
                    if (i7 >= objArr.length) {
                        if (!it.hasNext()) {
                            return objArr;
                        }
                        int i8 = ((i7 * 3) + 1) >>> 1;
                        if (i8 <= i7) {
                            i8 = 2147483645;
                            if (i7 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr = Arrays.copyOf(objArr, i8);
                        j.d(objArr, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] copyOf = Arrays.copyOf(objArr, i7);
                        j.d(copyOf, "copyOf(...)");
                        return copyOf;
                    }
                    i2 = i7;
                }
            }
        }
        return f4726a;
    }

    public static final Object[] b(Collection collection, Object[] objArr) {
        Object[] objArr2;
        int size = collection.size();
        int i2 = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArr2 = objArr;
                } else {
                    Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    j.c(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                    objArr2 = (Object[]) newInstance;
                }
                while (true) {
                    int i7 = i2 + 1;
                    objArr2[i2] = it.next();
                    if (i7 >= objArr2.length) {
                        if (!it.hasNext()) {
                            return objArr2;
                        }
                        int i8 = ((i7 * 3) + 1) >>> 1;
                        if (i8 <= i7) {
                            i8 = 2147483645;
                            if (i7 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr2 = Arrays.copyOf(objArr2, i8);
                        j.d(objArr2, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        if (objArr2 == objArr) {
                            objArr[i7] = null;
                            return objArr;
                        }
                        Object[] copyOf = Arrays.copyOf(objArr2, i7);
                        j.d(copyOf, "copyOf(...)");
                        return copyOf;
                    }
                    i2 = i7;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }
}
