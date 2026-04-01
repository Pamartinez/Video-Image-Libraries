package com.samsung.android.gallery.compat.qrencoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final ErrorCorrectionLevel[] FOR_BITS = null;
    private final int bits;

    static {
        ErrorCorrectionLevel errorCorrectionLevel;
        ErrorCorrectionLevel errorCorrectionLevel2;
        ErrorCorrectionLevel errorCorrectionLevel3;
        ErrorCorrectionLevel errorCorrectionLevel4;
        FOR_BITS = new ErrorCorrectionLevel[]{errorCorrectionLevel2, errorCorrectionLevel, errorCorrectionLevel4, errorCorrectionLevel3};
    }

    private ErrorCorrectionLevel(int i2) {
        this.bits = i2;
    }

    public int getBits() {
        return this.bits;
    }
}
