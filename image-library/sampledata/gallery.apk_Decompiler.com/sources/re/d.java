package Re;

import B1.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum d {
    FIELD((String) null),
    FILE((String) null),
    PROPERTY((String) null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER((String) null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");
    
    private final String renderName;

    static {
        d[] dVarArr;
        $ENTRIES = c.t(dVarArr);
    }

    /* access modifiers changed from: public */
    d(String str) {
        this.renderName = str == null ? a.U(name()) : str;
    }

    public final String a() {
        return this.renderName;
    }
}
