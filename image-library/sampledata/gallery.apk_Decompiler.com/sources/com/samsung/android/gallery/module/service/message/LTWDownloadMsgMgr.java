package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.microsoft.YourPhone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LTWDownloadMsgMgr extends DownloadMsgMgr {

    /* renamed from: com.samsung.android.gallery.module.service.message.LTWDownloadMsgMgr$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$microsoft$YourPhone$ErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.module.microsoft.YourPhone$ErrorType[] r0 = com.samsung.android.gallery.module.microsoft.YourPhone.ErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$microsoft$YourPhone$ErrorType = r0
                com.samsung.android.gallery.module.microsoft.YourPhone$ErrorType r1 = com.samsung.android.gallery.module.microsoft.YourPhone.ErrorType.STORAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$microsoft$YourPhone$ErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.microsoft.YourPhone$ErrorType r1 = com.samsung.android.gallery.module.microsoft.YourPhone.ErrorType.ETC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.message.LTWDownloadMsgMgr.AnonymousClass1.<clinit>():void");
        }
    }

    private static String getDownloadFailMessageByCommon(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.failed_download_image);
            }
            return context.getString(R$string.failed_download_images);
        } else if (i8 != i7) {
            return context.getString(R$string.failed_download_items);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.failed_download_video);
            }
            return context.getString(R$string.failed_download_videos);
        }
    }

    public static String getDownloadFailToastMessage(Context context, YourPhone.ErrorType errorType, int i2, int i7) {
        if (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$microsoft$YourPhone$ErrorType[errorType.ordinal()] != 1) {
            return getDownloadFailMessageByCommon(context, i2, i7);
        }
        return DownloadMsgMgr.getDownloadFailMessageByStorageFullError(context, i2, i7);
    }

    public static String getDownloadSuccessToastMessage(Context context, int i2, int i7) {
        return context.getString(R$string.download_completed);
    }
}
