package mg;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum u {
    OBJ('{', '}'),
    LIST('[', ']'),
    MAP('{', '}'),
    POLY_OBJ('[', ']');
    
    public final char begin;
    public final char end;

    static {
        u[] uVarArr;
        $ENTRIES = c.t(uVarArr);
    }

    /* access modifiers changed from: public */
    u(char c5, char c6) {
        this.begin = c5;
        this.end = c6;
    }
}
