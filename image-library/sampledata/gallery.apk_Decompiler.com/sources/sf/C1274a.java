package sf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* renamed from: sf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1274a {
    NO_ARGUMENTS(3),
    UNLESS_EMPTY(2),
    ALWAYS_PARENTHESIZED(true, true);
    
    private final boolean includeAnnotationArguments;
    private final boolean includeEmptyAnnotationArguments;

    static {
        C1274a[] aVarArr;
        $ENTRIES = c.t(aVarArr);
    }

    public final boolean a() {
        return this.includeAnnotationArguments;
    }

    public final boolean b() {
        return this.includeEmptyAnnotationArguments;
    }

    /* access modifiers changed from: public */
    C1274a(boolean z, boolean z3) {
        this.includeAnnotationArguments = z;
        this.includeEmptyAnnotationArguments = z3;
    }
}
