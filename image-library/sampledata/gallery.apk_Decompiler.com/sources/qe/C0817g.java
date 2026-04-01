package Qe;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* renamed from: Qe.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0817g {
    CLASS("class"),
    INTERFACE("interface"),
    ENUM_CLASS("enum class"),
    ENUM_ENTRY((String) null),
    ANNOTATION_CLASS("annotation class"),
    OBJECT("object");
    
    private final String codeRepresentation;

    static {
        C0817g[] gVarArr;
        $ENTRIES = c.t(gVarArr);
    }

    /* access modifiers changed from: public */
    C0817g(String str) {
        this.codeRepresentation = str;
    }

    public final boolean a() {
        if (this == OBJECT || this == ENUM_ENTRY) {
            return true;
        }
        return false;
    }
}
