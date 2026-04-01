package qf;

/* renamed from: qf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1236c {

    /* renamed from: c  reason: collision with root package name */
    public static final C1236c f5035c = new C1236c("");

    /* renamed from: a  reason: collision with root package name */
    public final C1238e f5036a;
    public transient C1236c b;

    public C1236c(String str) {
        if (str != null) {
            this.f5036a = new C1238e(str, this);
        } else {
            a(1);
            throw null;
        }
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
            case 9:
            case 10:
            case 11:
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
            case 9:
            case 10:
            case 11:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "fqName";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            case 8:
                objArr[0] = "name";
                break;
            case 12:
                objArr[0] = "segment";
                break;
            case 13:
                objArr[0] = "other";
                break;
            case 14:
                objArr[0] = "shortName";
                break;
            default:
                objArr[0] = "names";
                break;
        }
        switch (i2) {
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
                objArr[1] = "toUnsafe";
                break;
            case 6:
            case 7:
                objArr[1] = "parent";
                break;
            case 9:
                objArr[1] = "shortName";
                break;
            case 10:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 11:
                objArr[1] = "pathSegments";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
        }
        switch (i2) {
            case 1:
            case 2:
            case 3:
                objArr[2] = "<init>";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                break;
            case 8:
                objArr[2] = "child";
                break;
            case 12:
            case 13:
                objArr[2] = "startsWith";
                break;
            case 14:
                objArr[2] = "topLevel";
                break;
            default:
                objArr[2] = "fromSegments";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public static C1236c j(C1240g gVar) {
        if (gVar != null) {
            return new C1236c(new C1238e(gVar.b(), f5035c.i(), gVar));
        }
        a(14);
        throw null;
    }

    public final String b() {
        String str = this.f5036a.f5037a;
        if (str != null) {
            return str;
        }
        C1238e.a(4);
        throw null;
    }

    public final C1236c c(C1240g gVar) {
        if (gVar != null) {
            return new C1236c(this.f5036a.b(gVar), this);
        }
        a(8);
        throw null;
    }

    public final boolean d() {
        return this.f5036a.f5037a.isEmpty();
    }

    public final C1236c e() {
        C1236c cVar = this.b;
        if (cVar != null) {
            return cVar;
        }
        if (!d()) {
            C1238e eVar = this.f5036a;
            C1238e eVar2 = eVar.f5038c;
            if (eVar2 == null) {
                if (!eVar.f5037a.isEmpty()) {
                    eVar.c();
                    eVar2 = eVar.f5038c;
                    if (eVar2 == null) {
                        C1238e.a(8);
                        throw null;
                    }
                } else {
                    throw new IllegalStateException("root");
                }
            }
            C1236c cVar2 = new C1236c(eVar2);
            this.b = cVar2;
            return cVar2;
        }
        throw new IllegalStateException("root");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C1236c) && this.f5036a.equals(((C1236c) obj).f5036a)) {
            return true;
        }
        return false;
    }

    public final C1240g f() {
        C1240g f = this.f5036a.f();
        if (f != null) {
            return f;
        }
        a(9);
        throw null;
    }

    public final C1240g g() {
        C1238e eVar = this.f5036a;
        if (eVar.f5037a.isEmpty()) {
            C1240g gVar = C1238e.e;
            if (gVar != null) {
                return gVar;
            }
            C1238e.a(12);
            throw null;
        }
        C1240g f = eVar.f();
        if (f != null) {
            return f;
        }
        C1238e.a(13);
        throw null;
    }

    public final boolean h(C1240g gVar) {
        if (gVar != null) {
            String str = this.f5036a.f5037a;
            if (!str.isEmpty()) {
                int indexOf = str.indexOf(46);
                if (indexOf == -1) {
                    indexOf = str.length();
                }
                String b5 = gVar.b();
                if (indexOf != b5.length() || !str.regionMatches(0, b5, 0, indexOf)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        a(12);
        throw null;
    }

    public final int hashCode() {
        return this.f5036a.f5037a.hashCode();
    }

    public final C1238e i() {
        C1238e eVar = this.f5036a;
        if (eVar != null) {
            return eVar;
        }
        a(5);
        throw null;
    }

    public final String toString() {
        return this.f5036a.toString();
    }

    public C1236c(C1238e eVar) {
        this.f5036a = eVar;
    }

    public C1236c(C1238e eVar, C1236c cVar) {
        this.f5036a = eVar;
        this.b = cVar;
    }
}
