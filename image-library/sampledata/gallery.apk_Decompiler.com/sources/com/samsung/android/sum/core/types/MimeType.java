package com.samsung.android.sum.core.types;

import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MimeType implements NumericEnum {
    NONE(0),
    IMAGE_JPEG(1),
    IMAGE_HEIC(2),
    IMAGE_AVIF(3),
    AUDIO_AAC(4),
    VIDEO_AVC(5),
    VIDEO_HEVC(6),
    VIDEO_MP4(7),
    VIDEO_MOV(8);
    
    private final int value;

    /* renamed from: com.samsung.android.sum.core.types.MimeType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$MimeType = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sum.core.types.MimeType[] r0 = com.samsung.android.sum.core.types.MimeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$MimeType = r0
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.IMAGE_AVIF     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.AUDIO_AAC     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.VIDEO_AVC     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.VIDEO_HEVC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.VIDEO_MP4     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MimeType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.sum.core.types.MimeType r1 = com.samsung.android.sum.core.types.MimeType.VIDEO_MOV     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.types.MimeType.AnonymousClass1.<clinit>():void");
        }
    }

    private MimeType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isAudio() {
        return stringfy().startsWith("audio");
    }

    public boolean isImage() {
        return stringfy().startsWith("image");
    }

    public boolean isVideo() {
        return stringfy().startsWith("video");
    }

    public String stringfy() {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$MimeType[ordinal()]) {
            case 1:
                return "image/jpeg";
            case 2:
                return "image/heic";
            case 3:
                return "image/avif";
            case 4:
                return Encode.CodecsMime.AUDIO_CODEC_AAC;
            case 5:
                return Encode.CodecsMime.VIDEO_CODEC_H264;
            case 6:
                return "video/hevc";
            case 7:
                return Encode.ContentType.VIDEO_MP4;
            case 8:
                return "video/quicktime";
            default:
                return "n/a";
        }
    }
}
