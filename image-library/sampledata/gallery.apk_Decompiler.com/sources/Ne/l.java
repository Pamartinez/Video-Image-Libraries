package Ne;

import L1.d;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Set;
import me.f;
import me.h;
import ne.C1192j;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum l {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");
    
    public static final k Companion = null;
    public static final Set<l> NUMBER_TYPES = null;
    private final f arrayTypeFqName$delegate;
    private final C1240g arrayTypeName;
    private final f typeFqName$delegate;
    private final C1240g typeName;

    /* JADX WARNING: type inference failed for: r0v3, types: [Ne.k, java.lang.Object] */
    static {
        l lVar;
        l lVar2;
        l lVar3;
        l lVar4;
        l lVar5;
        l lVar6;
        l lVar7;
        l[] lVarArr;
        $ENTRIES = c.t(lVarArr);
        Companion = new Object();
        l lVar8 = lVar7;
        l lVar9 = lVar4;
        l lVar10 = lVar;
        NUMBER_TYPES = C1192j.z0(new l[]{lVar10, lVar2, lVar3, lVar9, lVar5, lVar6, lVar8});
    }

    /* access modifiers changed from: public */
    l(String str) {
        this.typeName = C1240g.e(str);
        this.arrayTypeName = C1240g.e(str.concat("Array"));
        h hVar = h.PUBLICATION;
        this.typeFqName$delegate = d.p(hVar, new j(this, 0));
        this.arrayTypeFqName$delegate = d.p(hVar, new j(this, 1));
    }

    public static C1236c a(l lVar) {
        return q.l.c(lVar.typeName);
    }

    public static C1236c b(l lVar) {
        return q.l.c(lVar.arrayTypeName);
    }

    public final C1236c c() {
        return (C1236c) this.arrayTypeFqName$delegate.getValue();
    }

    public final C1240g d() {
        return this.arrayTypeName;
    }

    public final C1236c e() {
        return (C1236c) this.typeFqName$delegate.getValue();
    }

    public final C1240g f() {
        return this.typeName;
    }
}
