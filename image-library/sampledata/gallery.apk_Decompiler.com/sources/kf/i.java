package Kf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum i {
    IN("in"),
    OUT("out"),
    INV("");
    
    private final String presentation;

    static {
        i[] iVarArr;
        $ENTRIES = c.t(iVarArr);
    }

    /* access modifiers changed from: public */
    i(String str) {
        this.presentation = str;
    }

    public final String toString() {
        return this.presentation;
    }
}
