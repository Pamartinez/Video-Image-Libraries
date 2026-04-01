package qf;

/* renamed from: qf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1240g implements Comparable {
    public final String d;
    public final boolean e;

    public C1240g(String str, boolean z) {
        if (str != null) {
            this.d = str;
            this.e = z;
            return;
        }
        a(0);
        throw null;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[0] = "name";
        }
        if (i2 == 1) {
            objArr[1] = "asString";
        } else if (i2 == 2) {
            objArr[1] = "getIdentifier";
        } else if (i2 == 3 || i2 == 4) {
            objArr[1] = "asStringStripSpecialMarkers";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
        }
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                objArr[2] = "identifier";
                break;
            case 6:
                objArr[2] = "isValidIdentifier";
                break;
            case 7:
                objArr[2] = "identifierIfValid";
                break;
            case 8:
                objArr[2] = "special";
                break;
            case 9:
                objArr[2] = "guessByFirstCharacter";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static C1240g d(String str) {
        if (str == null) {
            a(9);
            throw null;
        } else if (str.startsWith("<")) {
            return g(str);
        } else {
            return e(str);
        }
    }

    public static C1240g e(String str) {
        if (str != null) {
            return new C1240g(str, false);
        }
        a(5);
        throw null;
    }

    public static boolean f(String str) {
        if (str == null) {
            a(6);
            throw null;
        } else if (str.isEmpty() || str.startsWith("<")) {
            return false;
        } else {
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (charAt == '.' || charAt == '/' || charAt == '\\') {
                    return false;
                }
            }
            return true;
        }
    }

    public static C1240g g(String str) {
        if (str == null) {
            a(8);
            throw null;
        } else if (str.startsWith("<")) {
            return new C1240g(str, true);
        } else {
            throw new IllegalArgumentException("special name must start with '<': ".concat(str));
        }
    }

    public final String b() {
        String str = this.d;
        if (str != null) {
            return str;
        }
        a(1);
        throw null;
    }

    public final String c() {
        if (!this.e) {
            String b = b();
            if (b != null) {
                return b;
            }
            a(2);
            throw null;
        }
        throw new IllegalStateException("not identifier: " + this);
    }

    public final int compareTo(Object obj) {
        return this.d.compareTo(((C1240g) obj).d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1240g)) {
            return false;
        }
        C1240g gVar = (C1240g) obj;
        if (this.e == gVar.e && this.d.equals(gVar.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.d.hashCode() * 31) + (this.e ? 1 : 0);
    }

    public final String toString() {
        return this.d;
    }
}
