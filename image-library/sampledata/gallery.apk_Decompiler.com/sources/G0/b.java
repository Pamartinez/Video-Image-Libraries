package G0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum b {
    JSON(".json"),
    ZIP(".zip"),
    GZIP(".gz");
    
    public final String extension;

    /* access modifiers changed from: public */
    b(String str) {
        this.extension = str;
    }

    public final String toString() {
        return this.extension;
    }
}
