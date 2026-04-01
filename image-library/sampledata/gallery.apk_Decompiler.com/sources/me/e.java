package me;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements Comparable {

    /* renamed from: h  reason: collision with root package name */
    public static final e f4914h = new e(2, 1, 20);
    public final int d;
    public final int e;
    public final int f;
    public final int g;

    public e(int i2, int i7, int i8) {
        this.d = i2;
        this.e = i7;
        this.f = i8;
        if (i2 < 0 || i2 >= 256 || i7 < 0 || i7 >= 256 || i8 < 0 || i8 >= 256) {
            throw new IllegalArgumentException(("Version components are out of range: " + i2 + '.' + i7 + '.' + i8).toString());
        }
        this.g = (i2 << 16) + (i7 << 8) + i8;
    }

    public final int compareTo(Object obj) {
        e eVar = (e) obj;
        j.e(eVar, "other");
        return this.g - eVar.g;
    }

    public final boolean equals(Object obj) {
        e eVar;
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            eVar = (e) obj;
        } else {
            eVar = null;
        }
        if (eVar != null && this.g == eVar.g) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.g;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.d);
        sb2.append('.');
        sb2.append(this.e);
        sb2.append('.');
        sb2.append(this.f);
        return sb2.toString();
    }
}
