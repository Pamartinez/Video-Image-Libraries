package Nd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum c {
    DELETE_APP_DATA("com.sec.android.diagmonagent.sa.terms.DELETE_APP_DATA"),
    DELETE_SENSITIVE_APP_DATA("com.sec.android.diagmonagent.sa.terms.DELETE_SENSITIVE_APP_DATA"),
    SEND_PREVIOUS_REGISTRATION_INFO("None");
    
    private final String action;

    /* access modifiers changed from: public */
    c(String str) {
        this.action = str;
    }

    public final String a() {
        return this.action;
    }
}
