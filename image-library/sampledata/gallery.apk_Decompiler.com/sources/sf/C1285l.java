package sf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.Set;
import ne.C1192j;
import ne.C1194l;

/* renamed from: sf.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1285l {
    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true),
    CONST(true),
    LATEINIT(true),
    FUN(true),
    VALUE(true);
    
    public static final Set<C1285l> ALL = null;
    public static final Set<C1285l> ALL_EXCEPT_ANNOTATIONS = null;
    public static final C1284k Companion = null;
    private final boolean includeByDefault;

    /* JADX WARNING: type inference failed for: r0v5, types: [sf.k, java.lang.Object] */
    static {
        C1285l[] lVarArr;
        $ENTRIES = c.t(lVarArr);
        Companion = new Object();
        C1285l[] values = values();
        ArrayList arrayList = new ArrayList();
        for (C1285l lVar : values) {
            if (lVar.includeByDefault) {
                arrayList.add(lVar);
            }
        }
        ALL_EXCEPT_ANNOTATIONS = C1194l.p1(arrayList);
        ALL = C1192j.z0(values());
    }

    /* access modifiers changed from: public */
    C1285l(boolean z) {
        this.includeByDefault = z;
    }
}
