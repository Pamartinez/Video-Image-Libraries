package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RestoreMsgMgr {

    /* renamed from: com.samsung.android.gallery.module.service.message.RestoreMsgMgr$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType[] r0 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType = r0
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.MHS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Storage     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.GDPR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Server     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.message.RestoreMsgMgr.AnonymousClass1.<clinit>():void");
        }
    }

    private static String getCommonFailMessage(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.restore_image_fail_try_again, new Object[]{Integer.valueOf(i8)});
            }
            return context.getString(R$string.restore_images_fail_try_again, new Object[]{Integer.valueOf(i8)});
        } else if (i8 != i7) {
            return context.getString(R$string.restore_items_fail_try_again, new Object[]{Integer.valueOf(i8)});
        } else {
            if (i8 == 1) {
                return context.getString(R$string.restore_video_fail_try_again, new Object[]{Integer.valueOf(i8)});
            }
            return context.getString(R$string.restore_videos_fail_try_again, new Object[]{Integer.valueOf(i8)});
        }
    }

    public static String getFailMessage(Context context, CloudErrorType cloudErrorType, boolean z, int i2, int i7) {
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType[cloudErrorType.ordinal()];
        if (i8 == 1) {
            return context.getString(R$string.can_not_restore_items_while_mobile_hotspot);
        }
        if (i8 == 2) {
            return getLowStorageMessage(context, i2, i7);
        }
        if (i8 == 3) {
            return getGDPRMessage(context, z, i2, i7);
        }
        if (i8 != 4) {
            return getCommonFailMessage(context, i2, i7);
        }
        return getServerMessage(context, i2, i7);
    }

    private static String getGDPRMessage(Context context, boolean z, int i2, int i7) {
        String cloudBrand = StringResources.getCloudBrand();
        int i8 = i2 + i7;
        if (i2 == i8) {
            if (z) {
                return context.getResources().getQuantityString(R$plurals.images_restored_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
            }
            return context.getResources().getQuantityString(R$plurals.can_not_restore_images_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
        } else if (i7 == i8) {
            if (z) {
                return context.getResources().getQuantityString(R$plurals.videos_restored_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
            }
            return context.getResources().getQuantityString(R$plurals.can_not_restore_videos_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
        } else if (z) {
            return context.getResources().getQuantityString(R$plurals.items_restored_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
        } else {
            return context.getResources().getQuantityString(R$plurals.can_not_restore_items_gdpr, i8, new Object[]{Integer.valueOf(i8), cloudBrand, cloudBrand});
        }
    }

    private static String getLowStorageMessage(Context context, int i2, int i7) {
        String cloudBrand = StringResources.getCloudBrand();
        int i8 = i2 + i7;
        if (i8 == i2) {
            return context.getResources().getQuantityString(R$plurals.clear_cloud_space_for_image, i8, new Object[]{Integer.valueOf(i8), cloudBrand});
        }
        if (i8 == i7) {
            return context.getResources().getQuantityString(R$plurals.clear_cloud_space_for_video, i8, new Object[]{Integer.valueOf(i8), cloudBrand});
        }
        return context.getResources().getQuantityString(R$plurals.clear_cloud_space_for_item, i8, new Object[]{Integer.valueOf(i8), cloudBrand});
    }

    private static String getServerMessage(Context context, int i2, int i7) {
        String cloudBrand = StringResources.getCloudBrand();
        int i8 = i2 + i7;
        if (i8 == i7) {
            return context.getResources().getQuantityString(R$plurals.cloud_not_restore_video_server, i8, new Object[]{Integer.valueOf(i8), cloudBrand});
        }
        if (i8 != i2) {
            return context.getString(R$string.cloud_not_restore_items_server, new Object[]{Integer.valueOf(i8), cloudBrand});
        }
        if (i8 == 1) {
            return context.getString(R$string.cloud_not_restore_image_server, new Object[]{Integer.valueOf(i8), cloudBrand});
        }
        return context.getString(R$string.cloud_not_restore_images_server, new Object[]{Integer.valueOf(i8), cloudBrand});
    }

    public static String getSuccessMessage(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.image_restored);
            }
            return context.getString(R$string.images_restored);
        } else if (i8 != i7) {
            return context.getString(R$string.items_restored);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.video_restored);
            }
            return context.getString(R$string.videos_restored);
        }
    }
}
