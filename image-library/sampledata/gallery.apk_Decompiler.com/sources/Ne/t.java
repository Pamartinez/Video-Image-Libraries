package Ne;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import og.k;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum t {
    UBYTE(k.y("kotlin/UByte", false)),
    USHORT(k.y("kotlin/UShort", false)),
    UINT(k.y("kotlin/UInt", false)),
    ULONG(k.y("kotlin/ULong", false));
    
    private final C1235b arrayClassId;
    private final C1235b classId;
    private final C1240g typeName;

    static {
        t[] tVarArr;
        $ENTRIES = c.t(tVarArr);
    }

    /* access modifiers changed from: public */
    t(C1235b bVar) {
        this.classId = bVar;
        C1240g f = bVar.f();
        this.typeName = f;
        C1236c cVar = bVar.f5033a;
        this.arrayClassId = new C1235b(cVar, C1240g.e(f.b() + "Array"));
    }

    public final C1235b a() {
        return this.arrayClassId;
    }

    public final C1235b b() {
        return this.classId;
    }

    public final C1240g c() {
        return this.typeName;
    }
}
