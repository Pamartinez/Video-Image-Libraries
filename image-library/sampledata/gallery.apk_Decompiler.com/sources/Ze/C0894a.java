package Ze;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* renamed from: Ze.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0894a {
    METHOD_RETURN_TYPE("METHOD"),
    VALUE_PARAMETER("PARAMETER"),
    FIELD("FIELD"),
    TYPE_USE("TYPE_USE"),
    TYPE_PARAMETER_BOUNDS("TYPE_USE"),
    TYPE_PARAMETER("TYPE_PARAMETER");
    
    private final String javaTarget;

    static {
        C0894a[] aVarArr;
        $ENTRIES = c.t(aVarArr);
    }

    /* access modifiers changed from: public */
    C0894a(String str) {
        this.javaTarget = str;
    }

    public final String a() {
        return this.javaTarget;
    }
}
