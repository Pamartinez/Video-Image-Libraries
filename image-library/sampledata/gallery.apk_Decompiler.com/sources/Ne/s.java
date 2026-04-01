package Ne;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import og.k;
import qf.C1235b;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum s {
    UBYTEARRAY(k.y("kotlin/UByteArray", false)),
    USHORTARRAY(k.y("kotlin/UShortArray", false)),
    UINTARRAY(k.y("kotlin/UIntArray", false)),
    ULONGARRAY(k.y("kotlin/ULongArray", false));
    
    private final C1235b classId;
    private final C1240g typeName;

    static {
        s[] sVarArr;
        $ENTRIES = c.t(sVarArr);
    }

    /* access modifiers changed from: public */
    s(C1235b bVar) {
        this.classId = bVar;
        this.typeName = bVar.f();
    }

    public final C1240g a() {
        return this.typeName;
    }
}
