package Ze;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum G {
    NULL("NULL"),
    INDEX("INDEX"),
    FALSE("FALSE"),
    MAP_GET_OR_DEFAULT("MAP_GET_OR_DEFAULT");
    
    private final Object defaultValue;

    static {
        G[] gArr;
        $ENTRIES = c.t(gArr);
    }

    /* access modifiers changed from: public */
    G(String str) {
        this.defaultValue = r2;
    }
}
