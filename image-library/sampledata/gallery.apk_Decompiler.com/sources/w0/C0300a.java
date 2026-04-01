package w0;

/* renamed from: w0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0300a {
    DIRECT(0),
    ONE_BYTE(24),
    TWO_BYTES(25),
    FOUR_BYTES(26),
    EIGHT_BYTES(27),
    RESERVED(28),
    INDEFINITE(31);
    
    private final int value;

    /* access modifiers changed from: public */
    C0300a(int i2) {
        this.value = i2;
    }
}
