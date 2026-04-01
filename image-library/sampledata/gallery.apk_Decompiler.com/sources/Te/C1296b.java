package te;

import java.io.Serializable;
import kotlin.jvm.internal.j;
import ne.C1184b;
import ne.C1187e;
import ne.C1192j;

/* renamed from: te.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1296b extends C1187e implements C1295a, Serializable {
    public final Enum[] d;

    public C1296b(Enum[] enumArr) {
        j.e(enumArr, "entries");
        this.d = enumArr;
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum enumR = (Enum) obj;
        if (((Enum) C1192j.p0(enumR.ordinal(), this.d)) == enumR) {
            return true;
        }
        return false;
    }

    public final Object get(int i2) {
        C1184b bVar = C1187e.Companion;
        Enum[] enumArr = this.d;
        int length = enumArr.length;
        bVar.getClass();
        C1184b.a(i2, length);
        return enumArr[i2];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum enumR = (Enum) obj;
        int ordinal = enumR.ordinal();
        if (((Enum) C1192j.p0(ordinal, this.d)) == enumR) {
            return ordinal;
        }
        return -1;
    }

    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        return indexOf((Enum) obj);
    }

    public final int p() {
        return this.d.length;
    }
}
