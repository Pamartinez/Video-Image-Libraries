package Jf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum b {
    ERROR_CLASS("<Error class: %s>"),
    ERROR_FUNCTION("<Error function>"),
    ERROR_SCOPE("<Error scope>"),
    ERROR_MODULE("<Error module>"),
    ERROR_PROPERTY("<Error property>"),
    ERROR_TYPE("[Error type: %s]"),
    PARENT_OF_ERROR_SCOPE("<Fake parent for error lexical scope>");
    
    private final String debugText;

    static {
        b[] bVarArr;
        $ENTRIES = c.t(bVarArr);
    }

    /* access modifiers changed from: public */
    b(String str) {
        this.debugText = str;
    }

    public final String a() {
        return this.debugText;
    }
}
