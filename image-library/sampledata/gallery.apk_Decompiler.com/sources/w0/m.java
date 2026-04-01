package w0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum m {
    FALSE(20),
    TRUE(21),
    NULL(22),
    UNDEFINED(23),
    RESERVED(0),
    UNALLOCATED(0);
    
    private final int value;

    /* access modifiers changed from: public */
    m(int i2) {
        this.value = i2;
    }

    public final int a() {
        return this.value;
    }
}
