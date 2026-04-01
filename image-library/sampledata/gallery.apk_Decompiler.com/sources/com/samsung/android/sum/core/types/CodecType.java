package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CodecType implements NumericEnum {
    NONE(0),
    HEIF(1),
    JPEG_SQ(2),
    JPEG_QURAM(3);
    
    private final int value;

    /* renamed from: com.samsung.android.sum.core.types.CodecType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$MimeType = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.sum.core.types.MimeType[] r0 = com.samsung.android.sum.core.types.MimeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$MimeType = r0
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.types.CodecType.AnonymousClass1.<clinit>():void");
        }
    }

    private CodecType(int i2) {
        this.value = i2;
    }

    public static CodecType fromMimeType(MimeType mimeType) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$MimeType[mimeType.ordinal()];
        if (i2 == 1) {
            return HEIF;
        }
        if (i2 != 2) {
            return NONE;
        }
        return JPEG_SQ;
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
