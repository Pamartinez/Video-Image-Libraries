package com.samsung.android.sum.core.types;

import com.samsung.android.sum.core.buffer.SharedBufferManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ColorFormat implements NumericEnum {
    NONE(0),
    OPAQUE(1),
    GRAY(2),
    NV12(3),
    NV21(4),
    YUV420(5),
    P010(6),
    P010_ZIPPED(7),
    RGB(8),
    RGBA(9),
    ARGB(10),
    BGR(11),
    BGRA(12),
    ABGR(13),
    RGBA_1010102(14),
    VENDOR_IMPLEMENTED(100);
    
    private final int value;

    /* renamed from: com.samsung.android.sum.core.types.ColorFormat$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$ColorFormat = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sum.core.types.ColorFormat[] r0 = com.samsung.android.sum.core.types.ColorFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$ColorFormat = r0
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.GRAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV12     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.NV21     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.YUV420     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.VENDOR_IMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGB     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.BGR     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x006c }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGBA     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.ARGB     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.BGRA     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.RGBA_1010102     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x009c }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.P010     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.P010_ZIPPED     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$ColorFormat     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.samsung.android.sum.core.types.ColorFormat r1 = com.samsung.android.sum.core.types.ColorFormat.ABGR     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.types.ColorFormat.AnonymousClass1.<clinit>():void");
        }
    }

    private ColorFormat(int i2) {
        this.value = i2;
    }

    public static ColorFormat from(int i2) {
        return (ColorFormat) NumericEnum.fromValue(ColorFormat.class, i2);
    }

    public static ColorFormat fromHalFormat(int i2) {
        return SharedBufferManager.colorFormatFromHalFormat(i2);
    }

    public float bytePerPixel() {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[ordinal()]) {
            case 1:
            case 2:
                return 1.0f;
            case 3:
            case 4:
            case 5:
            case 6:
                return 1.5f;
            case 7:
            case 8:
                return 3.0f;
            case 9:
            case 10:
            case 11:
            case 12:
                return 4.0f;
            default:
                throw new UnsupportedOperationException("not support");
        }
    }

    public int getChannels() {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[ordinal()]) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 13:
            case 14:
                return 3;
            case 9:
            case 10:
            case 11:
            case 12:
            case 15:
                return 4;
            default:
                return 0;
        }
    }

    public ColorFormat getOpaque() {
        if (this == RGBA || this == ARGB || this == RGBA_1010102) {
            return RGB;
        }
        if (this == BGRA || this == ABGR) {
            return BGR;
        }
        return this;
    }

    public int getValue() {
        return this.value;
    }

    public boolean hasAlpha() {
        if (hasFrontAlpha() || this == RGBA || this == BGRA || this == RGBA_1010102) {
            return true;
        }
        return false;
    }

    public boolean hasFrontAlpha() {
        if (this == ARGB || this == ABGR) {
            return true;
        }
        return false;
    }

    public boolean isPlanar() {
        return isYuv();
    }

    public boolean isYuv() {
        if (this == NV12 || this == NV21 || this == YUV420 || this == P010 || this == P010_ZIPPED || this == VENDOR_IMPLEMENTED) {
            return true;
        }
        return false;
    }

    public int numberOfChromaChannels() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[ordinal()];
        if (i2 == 3 || i2 == 4 || i2 == 6) {
            return 2;
        }
        return 1;
    }

    public int numberOfPlanes() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$ColorFormat[ordinal()];
        if (i2 == 3 || i2 == 4) {
            return 2;
        }
        if (i2 != 5) {
            if (i2 == 6) {
                return 2;
            }
            if (i2 == 13 || i2 == 14) {
                return 3;
            }
            return 1;
        }
        return 3;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }

    public int toHalFormat() {
        return SharedBufferManager.colorFormatToHalFormat(this);
    }

    public static float bytePerPixel(ColorFormat colorFormat) {
        return colorFormat.bytePerPixel();
    }
}
