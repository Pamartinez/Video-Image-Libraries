package com.samsung.android.sum.core.types;

import T8.C0578a;
import android.os.Build;
import com.samsung.android.gallery.module.dynamicview.a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SocVendor {
    NONE,
    QCOM,
    SLSI,
    MTK;
    
    private static volatile SocVendor sValue;

    /* renamed from: com.samsung.android.sum.core.types.SocVendor$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$SocVendor = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sum.core.types.SocVendor[] r0 = com.samsung.android.sum.core.types.SocVendor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$SocVendor = r0
                com.samsung.android.sum.core.types.SocVendor r1 = com.samsung.android.sum.core.types.SocVendor.QCOM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$SocVendor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.SocVendor r1 = com.samsung.android.sum.core.types.SocVendor.MTK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$SocVendor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.SocVendor r1 = com.samsung.android.sum.core.types.SocVendor.SLSI     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.types.SocVendor.AnonymousClass1.<clinit>():void");
        }
    }

    public static SocVendor[] all() {
        return (SocVendor[]) Arrays.stream(values()).filter(new a(13)).toArray(new C0578a(11));
    }

    public static SocVendor currentSoc() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31 && sValue == null) {
            synchronized (SocVendor.class) {
                try {
                    if (sValue == null) {
                        String lowerCase = Build.SOC_MANUFACTURER.toLowerCase();
                        if (Arrays.asList(new String[]{"qti", "qcom", "qtı"}).contains(lowerCase)) {
                            sValue = QCOM;
                        } else if (Arrays.asList(new String[]{"samsung", "slsi"}).contains(lowerCase)) {
                            sValue = SLSI;
                        } else if (Arrays.asList(new String[]{"mediatek", "mtk"}).contains(lowerCase)) {
                            sValue = MTK;
                        } else {
                            throw new UnsupportedOperationException("not supported: " + lowerCase);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (i2 < 31) {
            sValue = NONE;
        }
        return sValue;
    }

    public static int getCameraColorSpace() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$SocVendor[currentSoc().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return 146931712;
        }
        if (i2 == 3) {
            return 281083904;
        }
        throw new UnsupportedOperationException("not supported vendor: " + currentSoc());
    }

    public static int getHwDecoderColorFormat() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$SocVendor[currentSoc().ordinal()];
        if (i2 == 1) {
            return 34;
        }
        if (i2 == 2) {
            return 35;
        }
        if (i2 != 3) {
            return 0;
        }
        return 34;
    }

    public static int getHwEncoderColorFormat() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$SocVendor[currentSoc().ordinal()];
        if (i2 == 1) {
            return 34;
        }
        if (i2 == 2) {
            return 17;
        }
        if (i2 != 3) {
            return 0;
        }
        return 34;
    }
}
