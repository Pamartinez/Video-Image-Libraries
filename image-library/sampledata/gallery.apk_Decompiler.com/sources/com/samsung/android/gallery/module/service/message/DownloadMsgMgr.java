package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DownloadMsgMgr {

    /* renamed from: com.samsung.android.gallery.module.service.message.DownloadMsgMgr$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType[] r0 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType = r0
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.GDPR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Server     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Network     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Storage     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.NotStart     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.module.cloud.sdk.CloudErrorType r1 = com.samsung.android.gallery.module.cloud.sdk.CloudErrorType.Migrating     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.message.DownloadMsgMgr.AnonymousClass1.<clinit>():void");
        }
    }

    private static String getDownloadFailMessageByGDPR(Context context, int i2, int i7) {
        int i8;
        int i10 = i2 + i7;
        if (i10 == i2) {
            if (i10 == 1) {
                i8 = R$string.can_not_download_image_gdpr;
            } else {
                i8 = R$string.can_not_download_images_gdpr;
            }
        } else if (i10 != i7) {
            i8 = R$string.can_not_download_items_gdpr;
        } else if (i10 == 1) {
            i8 = R$string.can_not_download_video_gdpr;
        } else {
            i8 = R$string.can_not_download_videos_gdpr;
        }
        return context.getString(i8, new Object[]{StringResources.getCloudBrand()});
    }

    private static String getDownloadFailMessageByNetworkError(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_image_network_error);
            }
            return context.getString(R$string.could_not_download_images_network_error);
        } else if (i8 != i7) {
            return context.getString(R$string.could_not_download_items_network_error);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_video_network_error);
            }
            return context.getString(R$string.could_not_download_videos_network_error);
        }
    }

    private static String getDownloadFailMessageByServerError(Context context) {
        return context.getString(R$string.could_not_connect_to_cloud, new Object[]{StringResources.getCloudBrand()});
    }

    public static String getDownloadFailMessageByStorageFullError(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_image_storage_full);
            }
            return context.getString(R$string.could_not_download_images_storage_full);
        } else if (i8 != i7) {
            return context.getString(R$string.could_not_download_items_storage_full);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_video_storage_full);
            }
            return context.getString(R$string.could_not_download_videos_storage_full);
        }
    }

    private static String getDownloadFailMessageByUnknownError(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_image);
            }
            return context.getString(R$string.could_not_download_images);
        } else if (i8 != i7) {
            return context.getString(R$string.could_not_download_items);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.could_not_download_video);
            }
            return context.getString(R$string.could_not_download_videos);
        }
    }

    public static String[] getDownloadFailNotificationMessage(Context context, int i2, int i7) {
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        int i8 = i2 + i7;
        if (i8 == 1) {
            z = true;
        } else {
            z = false;
        }
        String[] strArr = new String[2];
        if (i8 == i2) {
            if (z) {
                str3 = context.getString(R$string.could_not_download_image_notification);
            } else {
                str3 = context.getString(R$string.could_not_download_images_notification);
            }
            strArr[0] = str3;
            if (z) {
                str4 = context.getString(R$string.something_went_wrong_while_downloading_image);
            } else {
                str4 = context.getString(R$string.something_went_wrong_while_downloading_images);
            }
            strArr[1] = str4;
            return strArr;
        } else if (i8 == i7) {
            if (z) {
                str = context.getString(R$string.could_not_download_video_notification);
            } else {
                str = context.getString(R$string.could_not_download_videos_notification);
            }
            strArr[0] = str;
            if (z) {
                str2 = context.getString(R$string.something_went_wrong_while_downloading_video);
            } else {
                str2 = context.getString(R$string.something_went_wrong_while_downloading_videos);
            }
            strArr[1] = str2;
            return strArr;
        } else {
            strArr[0] = context.getString(R$string.could_not_download_items_notification);
            strArr[1] = context.getString(R$string.something_went_wrong_while_downloading_items);
            return strArr;
        }
    }

    public static String getDownloadFailToastMessage(Context context, CloudErrorType cloudErrorType, int i2, int i7) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$cloud$sdk$CloudErrorType[cloudErrorType.ordinal()]) {
            case 1:
                return getDownloadFailMessageByGDPR(context, i2, i7);
            case 2:
                return getDownloadFailMessageByServerError(context);
            case 3:
                return getDownloadFailMessageByNetworkError(context, i2, i7);
            case 4:
                return getDownloadFailMessageByStorageFullError(context, i2, i7);
            case 5:
                return getDownloadNotStartedToastMessage(context);
            case 6:
                return getDownloadOneDriveMigratingToastMessage(context);
            default:
                return getDownloadFailMessageByUnknownError(context, i2, i7);
        }
    }

    public static String getDownloadNotStartedToastMessage(Context context) {
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            return context.getString(R$string.all_items_already_on_tablet);
        }
        return context.getString(R$string.all_items_already_on_phone);
    }

    private static String getDownloadOneDriveMigratingToastMessage(Context context) {
        return context.getString(R$string.could_not_download_onedrive_migrating);
    }

    public static String getDownloadProgressNotificationMessage(Context context, int i2, int i7, int i8) {
        if (i2 != 0) {
            if (i2 != 1) {
                return context.getString(R$string.downloading_pd_of_pd_items, new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)});
            }
            if (i8 == 1) {
                return context.getString(R$string.downloading_one_video);
            }
            return context.getString(R$string.downloading_pd_of_pd_videos, new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)});
        } else if (i8 == 1) {
            return context.getString(R$string.downloading_one_image);
        } else {
            return context.getString(R$string.downloading_pd_of_pd_images, new Object[]{Integer.valueOf(i7), Integer.valueOf(i8)});
        }
    }

    public static String getDownloadStartNotificationMessage(Context context) {
        return context.getString(R$string.waiting_for_connection);
    }

    public static String getDownloadStartToastMessage(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.downloading_image);
            }
            return context.getString(R$string.downloading_images);
        } else if (i8 != i7) {
            return context.getString(R$string.downloading_items);
        } else {
            if (i8 == 1) {
                return context.getString(R$string.downloading_video);
            }
            return context.getString(R$string.downloading_videos);
        }
    }

    public static String getDownloadSuccessToastMessage(Context context, int i2, int i7) {
        int i8 = i2 + i7;
        if (i8 == i2) {
            if (i8 == 1) {
                return context.getString(R$string.image_downloaded);
            }
            return context.getResources().getQuantityString(R$plurals.images_downloaded, i8, new Object[]{Integer.valueOf(i8)});
        } else if (i8 != i7) {
            return context.getResources().getQuantityString(R$plurals.items_downloaded, i8, new Object[]{Integer.valueOf(i8)});
        } else {
            if (i8 == 1) {
                return context.getString(R$string.video_downloaded);
            }
            return context.getResources().getQuantityString(R$plurals.videos_downloaded, i8, new Object[]{Integer.valueOf(i8)});
        }
    }
}
