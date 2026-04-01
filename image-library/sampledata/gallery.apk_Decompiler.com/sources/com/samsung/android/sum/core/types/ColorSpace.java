package com.samsung.android.sum.core.types;

import android.graphics.Bitmap;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ColorSpace implements NumericEnum {
    NONE(0),
    BT601(1),
    BT709(2),
    BT2020(3),
    SRGB(4),
    DISPLAY_P3(5),
    ADOBE_RGB(6),
    BT601_LR(makeColorSpace(r0, r4)),
    BT601_FR(makeColorSpace(r0, r5)),
    BT709_LR(makeColorSpace(r1, r4)),
    BT709_FR(makeColorSpace(r1, r5)),
    BT2020_LR(makeColorSpace(r2, r4)),
    BT2020_FR(makeColorSpace(r2, r5));
    
    static final int CS_RANGE_SHIFT = 4;
    static final int CS_SET_MASK = 15;
    private static final int CS_SET_MAX = 16;
    private final int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ColorSpaceRange implements NumericEnum {
        NONE(0),
        LIMITED(1),
        FULL(2);
        
        /* access modifiers changed from: private */
        public final int value;

        private ColorSpaceRange(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }

        public String stringfy() {
            return name() + NumericEnum.SEP + this.value;
        }
    }

    private ColorSpace(int i2) {
        this.value = i2;
    }

    private static int makeColorSpace(ColorSpace colorSpace, ColorSpaceRange colorSpaceRange) {
        boolean z;
        if (colorSpace == NONE || colorSpaceRange == ColorSpaceRange.NONE) {
            z = false;
        } else {
            z = true;
        }
        Def.require(z, "color-space is none", new Object[0]);
        return (colorSpace.getValue() & 15) + (colorSpaceRange.getValue() << 4);
    }

    public static ColorSpace of(Bitmap bitmap) {
        android.graphics.ColorSpace colorSpace = bitmap.getColorSpace();
        if (colorSpace == null) {
            return NONE;
        }
        return of(colorSpace);
    }

    public int getStandard() {
        return this.value & 15;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isFullRange() {
        if ((this.value >> 4) == ColorSpaceRange.FULL.value) {
            return true;
        }
        return false;
    }

    public boolean isLimitedRange() {
        if ((this.value >> 4) == ColorSpaceRange.LIMITED.value) {
            return true;
        }
        return false;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }

    public static ColorSpace of(android.graphics.ColorSpace colorSpace) {
        int id = colorSpace.getId();
        if (id == 0) {
            return SRGB;
        }
        if (id == 7) {
            return DISPLAY_P3;
        }
        if (id == 10) {
            return ADOBE_RGB;
        }
        if (id == 4) {
            return BT709_FR;
        }
        if (id != 5) {
            return NONE;
        }
        return BT2020;
    }
}
