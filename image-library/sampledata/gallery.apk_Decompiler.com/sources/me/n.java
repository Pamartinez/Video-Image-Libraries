package me;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements Serializable {
    public final Object d;
    public final Object e;
    public final Object f;

    public n(Object obj, Object obj2, Object obj3) {
        this.d = obj;
        this.e = obj2;
        this.f = obj3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (j.a(this.d, nVar.d) && j.a(this.e, nVar.e) && j.a(this.f, nVar.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7;
        int i8 = 0;
        Object obj = this.d;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        int i10 = i2 * 31;
        Object obj2 = this.e;
        if (obj2 == null) {
            i7 = 0;
        } else {
            i7 = obj2.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        Object obj3 = this.f;
        if (obj3 != null) {
            i8 = obj3.hashCode();
        }
        return i11 + i8;
    }

    public final String toString() {
        return "(" + this.d + ArcCommonLog.TAG_COMMA + this.e + ArcCommonLog.TAG_COMMA + this.f + ')';
    }
}
