package yf;

import qf.C1235b;
import qf.C1236c;

/* renamed from: yf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1358b {

    /* renamed from: a  reason: collision with root package name */
    public final String f5168a;

    public C1358b(String str) {
        if (str != null) {
            this.f5168a = str;
        } else {
            a(7);
            throw null;
        }
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (!(i2 == 3 || i2 == 5)) {
            switch (i2) {
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i2 == 3 || i2 == 5)) {
            switch (i2) {
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    i7 = 3;
                    break;
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 2:
                objArr[0] = "classId";
                break;
            case 3:
            case 5:
            case 8:
            case 9:
            case 10:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                break;
            case 4:
            case 6:
                objArr[0] = "fqName";
                break;
            default:
                objArr[0] = "internalName";
                break;
        }
        if (i2 == 3) {
            objArr[1] = "internalNameByClassId";
        } else if (i2 != 5) {
            switch (i2) {
                case 8:
                    objArr[1] = "getFqNameForClassNameWithoutDollars";
                    break;
                case 9:
                    objArr[1] = "getPackageFqName";
                    break;
                case 10:
                    objArr[1] = "getInternalName";
                    break;
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                    break;
            }
        } else {
            objArr[1] = "byFqNameWithoutInnerClasses";
        }
        switch (i2) {
            case 1:
                objArr[2] = "byClassId";
                break;
            case 2:
                objArr[2] = "internalNameByClassId";
                break;
            case 3:
            case 5:
            case 8:
            case 9:
            case 10:
                break;
            case 4:
            case 6:
                objArr[2] = "byFqNameWithoutInnerClasses";
                break;
            case 7:
                objArr[2] = "<init>";
                break;
            default:
                objArr[2] = "byInternalName";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i2 == 3 || i2 == 5)) {
            switch (i2) {
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public static C1358b b(C1236c cVar) {
        if (cVar != null) {
            return new C1358b(cVar.b().replace('.', '/'));
        }
        a(4);
        throw null;
    }

    public static C1358b c(String str) {
        if (str != null) {
            return new C1358b(str);
        }
        a(0);
        throw null;
    }

    public static String e(C1235b bVar) {
        C1236c cVar = bVar.f5033a;
        String replace = bVar.b.b().replace('.', '$');
        if (!cVar.d()) {
            replace = cVar.b().replace('.', '/') + "/" + replace;
        }
        if (replace != null) {
            return replace;
        }
        a(3);
        throw null;
    }

    public final String d() {
        String str = this.f5168a;
        if (str != null) {
            return str;
        }
        a(10);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1358b.class != obj.getClass()) {
            return false;
        }
        return this.f5168a.equals(((C1358b) obj).f5168a);
    }

    public final int hashCode() {
        return this.f5168a.hashCode();
    }

    public final String toString() {
        return this.f5168a;
    }
}
