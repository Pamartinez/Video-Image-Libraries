package Ke;

import Ae.b;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.lang.reflect.Type;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class W implements Type {
    public final Type[] d;
    public final int e;

    public W(Type[] typeArr) {
        j.e(typeArr, "types");
        this.d = typeArr;
        this.e = Arrays.hashCode(typeArr);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof W)) {
            return false;
        }
        if (Arrays.equals(this.d, ((W) obj).d)) {
            return true;
        }
        return false;
    }

    public final String getTypeName() {
        return C1192j.s0(this.d, ArcCommonLog.TAG_COMMA, "[", "]", (b) null, 56);
    }

    public final int hashCode() {
        return this.e;
    }

    public final String toString() {
        return getTypeName();
    }
}
