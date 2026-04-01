package Od;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum d {
    ONE_DEPTH("\u0002", "\u0003"),
    TWO_DEPTH("\u0004", "\u0005"),
    THREE_DEPTH("\u0006", "\u0007");
    
    private final String collDlm;
    private final String keyValueDlm;

    /* access modifiers changed from: public */
    d(String str, String str2) {
        this.collDlm = str;
        this.keyValueDlm = str2;
    }

    public final String a() {
        return this.collDlm;
    }

    public final String b() {
        return this.keyValueDlm;
    }
}
