package com.samsung.android.gallery.compat.qrencoder.common;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Mode {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    
    private final int bits;
    private final int[] characterCountBitsForVersions;

    private Mode(int[] iArr, int i2) {
        this.characterCountBitsForVersions = iArr;
        this.bits = i2;
    }

    public int getBits() {
        return this.bits;
    }

    public int getCharacterCountBits(Version version) {
        char c5;
        int versionNumber = version.getVersionNumber();
        if (versionNumber <= 9) {
            c5 = 0;
        } else if (versionNumber <= 26) {
            c5 = 1;
        } else {
            c5 = 2;
        }
        return this.characterCountBitsForVersions[c5];
    }
}
