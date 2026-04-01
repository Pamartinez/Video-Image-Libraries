package com.samsung.android.sum.core.utils.dump;

import com.samsung.android.sum.core.types.MediaType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DumperFactory {

    /* renamed from: com.samsung.android.sum.core.utils.dump.DumperFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.sum.core.types.MediaType[] r0 = com.samsung.android.sum.core.types.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$MediaType = r0
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.AUDIO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.IMAGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$MediaType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.META     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.utils.dump.DumperFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public static BaseDumper create(MediaType mediaType, DumpConfig dumpConfig) {
        if (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$MediaType[mediaType.ordinal()] == 1) {
            return new AudioDumper(dumpConfig);
        }
        throw new UnsupportedOperationException("not supported yet");
    }
}
