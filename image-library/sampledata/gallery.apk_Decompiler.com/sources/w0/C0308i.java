package w0;

/* renamed from: w0.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0308i {
    INVALID(-1),
    UNSIGNED_INTEGER(0),
    NEGATIVE_INTEGER(1),
    BYTE_STRING(2),
    UNICODE_STRING(3),
    ARRAY(4),
    MAP(5),
    TAG(6),
    SPECIAL(7);
    
    private final int value;

    /* access modifiers changed from: public */
    C0308i(int i2) {
        this.value = i2;
    }
}
