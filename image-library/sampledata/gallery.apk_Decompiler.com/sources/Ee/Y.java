package ee;

import E2.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Y {
    OK(0),
    CANCELLED(1),
    UNKNOWN(2),
    INVALID_ARGUMENT(3),
    DEADLINE_EXCEEDED(4),
    NOT_FOUND(5),
    ALREADY_EXISTS(6),
    PERMISSION_DENIED(7),
    RESOURCE_EXHAUSTED(8),
    FAILED_PRECONDITION(9),
    ABORTED(10),
    OUT_OF_RANGE(11),
    UNIMPLEMENTED(12),
    INTERNAL(13),
    UNAVAILABLE(14),
    DATA_LOSS(15),
    UNAUTHENTICATED(16);
    
    private final int value;
    private final byte[] valueAscii;

    /* access modifiers changed from: public */
    Y(int i2) {
        this.value = i2;
        this.valueAscii = Integer.toString(i2).getBytes(g.f166a);
    }

    public static byte[] a(Y y) {
        return y.valueAscii;
    }

    public final a0 b() {
        return (a0) a0.d.get(this.value);
    }

    public final int c() {
        return this.value;
    }
}
