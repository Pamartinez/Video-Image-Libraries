package qf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.jvm.internal.j;

/* renamed from: qf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1238e {
    public static final C1240g e = C1240g.g("<root>");
    public static final Pattern f = Pattern.compile("\\.");
    public static final C1237d g = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final String f5037a;
    public transient C1236c b;

    /* renamed from: c  reason: collision with root package name */
    public transient C1238e f5038c;
    public transient C1240g d;

    public C1238e(String str, C1236c cVar) {
        if (str != null) {
            this.f5037a = str;
            this.b = cVar;
            return;
        }
        a(0);
        throw null;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        if (i2 != 1) {
            switch (i2) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 18:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                    break;
                case 9:
                    objArr[0] = "name";
                    break;
                case 15:
                    objArr[0] = "segment";
                    break;
                case 16:
                    objArr[0] = "other";
                    break;
                case 17:
                    objArr[0] = "shortName";
                    break;
                default:
                    objArr[0] = "fqName";
                    break;
            }
        } else {
            objArr[0] = "safe";
        }
        switch (i2) {
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
            case 6:
                objArr[1] = "toSafe";
                break;
            case 7:
            case 8:
                objArr[1] = "parent";
                break;
            case 10:
            case 11:
                objArr[1] = "shortName";
                break;
            case 12:
            case 13:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 14:
                objArr[1] = "pathSegments";
                break;
            case 18:
                objArr[1] = "toString";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                break;
        }
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                break;
            case 9:
                objArr[2] = "child";
                break;
            case 15:
            case 16:
                objArr[2] = "startsWith";
                break;
            case 17:
                objArr[2] = "topLevel";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final C1238e b(C1240g gVar) {
        String str;
        if (gVar != null) {
            String str2 = this.f5037a;
            if (str2.isEmpty()) {
                str = gVar.b();
            } else {
                str = str2 + "." + gVar.b();
            }
            return new C1238e(str, this, gVar);
        }
        a(9);
        throw null;
    }

    public final void c() {
        String str = this.f5037a;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            this.d = C1240g.d(str.substring(lastIndexOf + 1));
            this.f5038c = new C1238e(str.substring(0, lastIndexOf));
            return;
        }
        this.d = C1240g.d(str);
        this.f5038c = C1236c.f5035c.i();
    }

    public final boolean d() {
        if (this.b != null) {
            return true;
        }
        String str = this.f5037a;
        if (str == null) {
            a(4);
            throw null;
        } else if (str.indexOf(60) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public final List e() {
        List list;
        String str = this.f5037a;
        if (str.isEmpty()) {
            list = Collections.EMPTY_LIST;
        } else {
            String[] split = f.split(str);
            j.e(split, "<this>");
            j.e(g, "transform");
            ArrayList arrayList = new ArrayList(split.length);
            for (String d2 : split) {
                arrayList.add(C1240g.d(d2));
            }
            list = arrayList;
        }
        if (list != null) {
            return list;
        }
        a(14);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C1238e) && this.f5037a.equals(((C1238e) obj).f5037a)) {
            return true;
        }
        return false;
    }

    public final C1240g f() {
        C1240g gVar = this.d;
        if (gVar != null) {
            if (gVar != null) {
                return gVar;
            }
            a(10);
            throw null;
        } else if (!this.f5037a.isEmpty()) {
            c();
            C1240g gVar2 = this.d;
            if (gVar2 != null) {
                return gVar2;
            }
            a(11);
            throw null;
        } else {
            throw new IllegalStateException("root");
        }
    }

    public final C1236c g() {
        C1236c cVar = this.b;
        if (cVar != null) {
            return cVar;
        }
        C1236c cVar2 = new C1236c(this);
        this.b = cVar2;
        return cVar2;
    }

    public final int hashCode() {
        return this.f5037a.hashCode();
    }

    public final String toString() {
        String str = this.f5037a;
        if (str.isEmpty()) {
            str = e.b();
        }
        if (str != null) {
            return str;
        }
        a(18);
        throw null;
    }

    public C1238e(String str) {
        if (str != null) {
            this.f5037a = str;
        } else {
            a(2);
            throw null;
        }
    }

    public C1238e(String str, C1238e eVar, C1240g gVar) {
        if (str != null) {
            this.f5037a = str;
            this.f5038c = eVar;
            this.d = gVar;
            return;
        }
        a(3);
        throw null;
    }
}
