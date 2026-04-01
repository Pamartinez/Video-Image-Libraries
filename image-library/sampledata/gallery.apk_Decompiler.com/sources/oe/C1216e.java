package oe;

import Be.a;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.j;

/* renamed from: oe.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1216e implements Map.Entry, a {
    public final C1217f d;
    public final int e;
    public final int f;

    public C1216e(C1217f fVar, int i2) {
        j.e(fVar, "map");
        this.d = fVar;
        this.e = i2;
        this.f = fVar.k;
    }

    public final void a() {
        if (this.d.k != this.f) {
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!j.a(entry.getKey(), getKey()) || !j.a(entry.getValue(), getValue())) {
            return false;
        }
        return true;
    }

    public final Object getKey() {
        a();
        return this.d.d[this.e];
    }

    public final Object getValue() {
        a();
        Object[] objArr = this.d.e;
        j.b(objArr);
        return objArr[this.e];
    }

    public final int hashCode() {
        int i2;
        Object key = getKey();
        int i7 = 0;
        if (key != null) {
            i2 = key.hashCode();
        } else {
            i2 = 0;
        }
        Object value = getValue();
        if (value != null) {
            i7 = value.hashCode();
        }
        return i2 ^ i7;
    }

    public final Object setValue(Object obj) {
        a();
        C1217f fVar = this.d;
        fVar.c();
        Object[] objArr = fVar.e;
        if (objArr == null) {
            int length = fVar.d.length;
            if (length >= 0) {
                objArr = new Object[length];
                fVar.e = objArr;
            } else {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
        }
        int i2 = this.e;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getKey());
        sb2.append('=');
        sb2.append(getValue());
        return sb2.toString();
    }
}
