package Qf;

import c0.C0086a;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends AbstractList implements RandomAccess {
    public int d;
    public Object e;

    public static /* synthetic */ void i(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6 || i2 == 7) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6 || i2 == 7) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = "a";
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i2 == 2 || i2 == 3) {
            objArr[1] = "iterator";
        } else if (i2 == 5 || i2 == 6 || i2 == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
        }
        switch (i2) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6 || i2 == 7) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final boolean add(Object obj) {
        int i2 = this.d;
        if (i2 == 0) {
            this.e = obj;
        } else if (i2 == 1) {
            this.e = new Object[]{this.e, obj};
        } else {
            Object[] objArr = (Object[]) this.e;
            int length = objArr.length;
            if (i2 >= length) {
                int i7 = ((length * 3) / 2) + 1;
                int i8 = i2 + 1;
                if (i7 < i8) {
                    i7 = i8;
                }
                Object[] objArr2 = new Object[i7];
                this.e = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
                objArr = objArr2;
            }
            objArr[this.d] = obj;
        }
        this.d++;
        this.modCount++;
        return true;
    }

    public final void clear() {
        this.e = null;
        this.d = 0;
        this.modCount++;
    }

    public final Object get(int i2) {
        int i7;
        if (i2 < 0 || i2 >= (i7 = this.d)) {
            StringBuilder o2 = C0086a.o(i2, "Index: ", ", Size: ");
            o2.append(this.d);
            throw new IndexOutOfBoundsException(o2.toString());
        } else if (i7 == 1) {
            return this.e;
        } else {
            return ((Object[]) this.e)[i2];
        }
    }

    public final Iterator iterator() {
        int i2 = this.d;
        if (i2 == 0) {
            return d.d;
        }
        if (i2 == 1) {
            return new e(this);
        }
        Iterator it = super.iterator();
        if (it != null) {
            return it;
        }
        i(3);
        throw null;
    }

    public final Object remove(int i2) {
        int i7;
        Object obj;
        if (i2 < 0 || i2 >= (i7 = this.d)) {
            StringBuilder o2 = C0086a.o(i2, "Index: ", ", Size: ");
            o2.append(this.d);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        if (i7 == 1) {
            obj = this.e;
            this.e = null;
        } else {
            Object[] objArr = (Object[]) this.e;
            Object obj2 = objArr[i2];
            if (i7 == 2) {
                this.e = objArr[1 - i2];
            } else {
                int i8 = (i7 - i2) - 1;
                if (i8 > 0) {
                    System.arraycopy(objArr, i2 + 1, objArr, i2, i8);
                }
                objArr[this.d - 1] = null;
            }
            obj = obj2;
        }
        this.d--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i2, Object obj) {
        int i7;
        if (i2 < 0 || i2 >= (i7 = this.d)) {
            StringBuilder o2 = C0086a.o(i2, "Index: ", ", Size: ");
            o2.append(this.d);
            throw new IndexOutOfBoundsException(o2.toString());
        } else if (i7 == 1) {
            Object obj2 = this.e;
            this.e = obj;
            return obj2;
        } else {
            Object[] objArr = (Object[]) this.e;
            Object obj3 = objArr[i2];
            objArr[i2] = obj;
            return obj3;
        }
    }

    public final int size() {
        return this.d;
    }

    public final void sort(Comparator comparator) {
        int i2 = this.d;
        if (i2 >= 2) {
            Arrays.sort((Object[]) this.e, 0, i2, comparator);
        }
    }

    public final Object[] toArray(Object[] objArr) {
        if (objArr != null) {
            int length = objArr.length;
            int i2 = this.d;
            if (i2 == 1) {
                if (length != 0) {
                    objArr[0] = this.e;
                } else {
                    Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), 1);
                    objArr2[0] = this.e;
                    return objArr2;
                }
            } else if (length < i2) {
                Object[] copyOf = Arrays.copyOf((Object[]) this.e, i2, objArr.getClass());
                if (copyOf != null) {
                    return copyOf;
                }
                i(6);
                throw null;
            } else if (i2 != 0) {
                System.arraycopy(this.e, 0, objArr, 0, i2);
            }
            int i7 = this.d;
            if (length > i7) {
                objArr[i7] = null;
            }
            return objArr;
        }
        i(4);
        throw null;
    }

    public final void add(int i2, Object obj) {
        int i7;
        if (i2 < 0 || i2 > (i7 = this.d)) {
            StringBuilder o2 = C0086a.o(i2, "Index: ", ", Size: ");
            o2.append(this.d);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        if (i7 == 0) {
            this.e = obj;
        } else if (i7 == 1 && i2 == 0) {
            this.e = new Object[]{obj, this.e};
        } else {
            Object[] objArr = new Object[(i7 + 1)];
            if (i7 == 1) {
                objArr[0] = this.e;
            } else {
                Object[] objArr2 = (Object[]) this.e;
                System.arraycopy(objArr2, 0, objArr, 0, i2);
                System.arraycopy(objArr2, i2, objArr, i2 + 1, this.d - i2);
            }
            objArr[i2] = obj;
            this.e = objArr;
        }
        this.d++;
        this.modCount++;
    }
}
