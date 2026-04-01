package me;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements Serializable {
    public final Object d;
    public final Object e;

    public i(Object obj, Object obj2) {
        this.d = obj;
        this.e = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (j.a(this.d, iVar.d) && j.a(this.e, iVar.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        Object obj = this.d;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        int i8 = i2 * 31;
        Object obj2 = this.e;
        if (obj2 != null) {
            i7 = obj2.hashCode();
        }
        return i8 + i7;
    }

    public final String toString() {
        return "(" + this.d + ArcCommonLog.TAG_COMMA + this.e + ')';
    }
}
