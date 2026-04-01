package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.compat.FileOpError;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileOperationMsgMgr {

    /* renamed from: com.samsung.android.gallery.module.service.message.FileOperationMsgMgr$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult;
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError;

        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|56) */
        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|56) */
        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|56) */
        /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0091 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x009b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00a5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00af */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00b9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00c3 */
        static {
            /*
                com.samsung.android.gallery.module.fileio.compat.FileOpError[] r0 = com.samsung.android.gallery.module.fileio.compat.FileOpError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError = r0
                r1 = 1
                com.samsung.android.gallery.module.fileio.compat.FileOpError r2 = com.samsung.android.gallery.module.fileio.compat.FileOpError.CloudPartialFailed     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r3 = com.samsung.android.gallery.module.fileio.compat.FileOpError.LocalPartialFailed     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r4 = com.samsung.android.gallery.module.fileio.compat.FileOpError.None     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r5 = com.samsung.android.gallery.module.fileio.compat.FileOpError.OperationCanceled     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r6 = com.samsung.android.gallery.module.fileio.compat.FileOpError.StorageNotEnough     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r7 = com.samsung.android.gallery.module.fileio.compat.FileOpError.CloudFailed     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r8 = com.samsung.android.gallery.module.fileio.compat.FileOpError.CloudQuotaFailed     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r9 = com.samsung.android.gallery.module.fileio.compat.FileOpError.CloudToSdcardUnsupported     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                r8 = 9
                int[] r9 = $SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError     // Catch:{ NoSuchFieldError -> 0x006c }
                com.samsung.android.gallery.module.fileio.compat.FileOpError r10 = com.samsung.android.gallery.module.fileio.compat.FileOpError.LocalFailed     // Catch:{ NoSuchFieldError -> 0x006c }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult[] r9 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.values()
                int r9 = r9.length
                int[] r9 = new int[r9]
                $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult = r9
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r10 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_CANCEL     // Catch:{ NoSuchFieldError -> 0x007d }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r9[r10] = r1     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r1 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x0087 }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r9 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_CLOUD_PARTIAL_OK     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r1[r9] = r0     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x0091 }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_CLOUD_OK     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x009b }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_LOCAL_OK_SYNC_OFF     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x00a5 }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_LOCAL_OK     // Catch:{ NoSuchFieldError -> 0x00a5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a5 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00a5 }
            L_0x00a5:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x00af }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_NOT_ENOUGH_STORAGE     // Catch:{ NoSuchFieldError -> 0x00af }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00af }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00af }
            L_0x00af:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x00b9 }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_CLOUD_FAIL     // Catch:{ NoSuchFieldError -> 0x00b9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b9 }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00b9 }
            L_0x00b9:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x00c3 }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_CLOUD_QUOTA_FAIL     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult     // Catch:{ NoSuchFieldError -> 0x00cd }
                com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r1 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_FAIL_SYNC_OFF     // Catch:{ NoSuchFieldError -> 0x00cd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cd }
                r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x00cd }
            L_0x00cd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.message.FileOperationMsgMgr.AnonymousClass1.<clinit>():void");
        }
    }

    private static String getAlbumName(String str) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || (!str.startsWith("/data/sec/gallery/") && !str.startsWith("/data/sec_pass/"))) {
            return AlbumTitleHelper.getAlbumTitle(FileUtils.getBucketId(str), FileUtils.getNameFromPath(str));
        }
        return AppResources.getString(R$string.hidden_storage);
    }

    private static String getCancelMessage(Context context) {
        return context.getString(R$string.transfer_canceled);
    }

    private static String getCloudFailMessage(Context context) {
        return context.getString(R$string.a_network_or_server_error_has_occurred);
    }

    private static String getCloudSuccessMessage(Context context, FileOperationMsgParams fileOperationMsgParams) {
        int i2;
        int i7;
        int i8;
        int i10;
        String albumName = getAlbumName(fileOperationMsgParams.getAlbumPath());
        if (fileOperationMsgParams.getSuccessCount() == 1 && fileOperationMsgParams.getFailCount() == 1) {
            if (fileOperationMsgParams.isCopy()) {
                i10 = R$string.copy_to_sync_off_one_local_t_one_cloud_f;
            } else {
                i10 = R$string.move_to_sync_off_one_local_t_one_cloud_f;
            }
            return context.getString(i10, new Object[]{albumName});
        } else if (fileOperationMsgParams.getSuccessCount() == 1 && fileOperationMsgParams.getFailCount() > 1) {
            if (fileOperationMsgParams.isCopy()) {
                i8 = R$string.copy_to_sync_off_one_local_t_cloud_f;
            } else {
                i8 = R$string.move_to_sync_off_one_local_t_cloud_f;
            }
            return context.getString(i8, new Object[]{Integer.valueOf(fileOperationMsgParams.getFailCount()), albumName});
        } else if (fileOperationMsgParams.getSuccessCount() <= 1 || fileOperationMsgParams.getFailCount() != 1) {
            if (fileOperationMsgParams.isCopy()) {
                i2 = R$string.copy_to_sync_off_local_t_cloud_f;
            } else {
                i2 = R$string.move_to_sync_off_local_t_cloud_f;
            }
            return context.getString(i2, new Object[]{Integer.valueOf(fileOperationMsgParams.getSuccessCount()), Integer.valueOf(fileOperationMsgParams.getFailCount()), albumName});
        } else {
            if (fileOperationMsgParams.isCopy()) {
                i7 = R$string.copy_to_sync_off_local_t_one_cloud_f;
            } else {
                i7 = R$string.move_to_sync_off_local_t_one_cloud_f;
            }
            return context.getString(i7, new Object[]{Integer.valueOf(fileOperationMsgParams.getSuccessCount()), albumName});
        }
    }

    private static String getDefaultFailMessage(Context context, boolean z) {
        int i2;
        if (z) {
            i2 = R$string.unable_to_copy;
        } else {
            i2 = R$string.unable_to_move;
        }
        return context.getString(i2);
    }

    private static String getDefaultSuccessMessage(Context context, boolean z) {
        int i2;
        if (z) {
            i2 = R$string.copy_completed;
        } else {
            i2 = R$string.move_completed;
        }
        return context.getString(i2);
    }

    public static String getDialogTitle(Context context, String str, boolean z, boolean z3, FileOperationMsgParams fileOperationMsgParams) {
        int i2;
        String privateAlbumDialogTitle = getPrivateAlbumDialogTitle(context, str, fileOperationMsgParams);
        if (!TextUtils.isEmpty(privateAlbumDialogTitle)) {
            return privateAlbumDialogTitle;
        }
        if (z3) {
            return context.getString(R$string.rename_album);
        }
        if (z) {
            i2 = R$string.copying_items_to;
        } else {
            i2 = R$string.moving_items_to;
        }
        return context.getString(i2, new Object[]{getAlbumName(str)});
    }

    public static String getNotEnoughStorageMessage(Context context, FileOperationMsgParams fileOperationMsgParams) {
        int i2;
        int i7;
        int i8;
        int totalCount = fileOperationMsgParams.getTotalCount();
        if (fileOperationMsgParams.getImageCount() == 1 && totalCount == 1) {
            if (fileOperationMsgParams.isCopy()) {
                i8 = R$string.free_up_some_more_storage_space_to_copy_this_video;
            } else {
                i8 = R$string.free_up_some_more_storage_space_to_move_this_image;
            }
            return context.getString(i8);
        } else if (fileOperationMsgParams.getVideoCount() == 1 && totalCount == 1) {
            if (fileOperationMsgParams.isCopy()) {
                i7 = R$string.free_up_some_more_storage_space_to_copy_this_video;
            } else {
                i7 = R$string.free_up_some_more_storage_space_to_move_this_video;
            }
            return context.getString(i7);
        } else {
            if (fileOperationMsgParams.isCopy()) {
                i2 = R$string.free_up_some_more_storage_space_to_copy_these_items;
            } else {
                i2 = R$string.free_up_some_more_storage_space_to_move_these_items;
            }
            return context.getString(i2);
        }
    }

    public static String getNotificationMessage(Context context, FileOperationMsgParams fileOperationMsgParams) {
        int i2;
        if (fileOperationMsgParams.isRename()) {
            if (fileOperationMsgParams.getTotalCount() == 1) {
                i2 = R$string.renaming_file;
            } else {
                i2 = R$string.renaming_files;
            }
            return context.getString(i2);
        } else if (fileOperationMsgParams.isCopy()) {
            if (fileOperationMsgParams.getTotalCount() == 1) {
                return context.getString(R$string.copying_one_item);
            }
            return context.getString(R$string.copying_n_items, new Object[]{Integer.valueOf(fileOperationMsgParams.getOperateCount()), Integer.valueOf(fileOperationMsgParams.getTotalCount())});
        } else if (!fileOperationMsgParams.isMove()) {
            return null;
        } else {
            if (fileOperationMsgParams.getTotalCount() == 1) {
                return context.getString(R$string.moving_one_item);
            }
            return context.getString(R$string.moving_n_items, new Object[]{Integer.valueOf(fileOperationMsgParams.getOperateCount()), Integer.valueOf(fileOperationMsgParams.getTotalCount())});
        }
    }

    private static String getPrivateAlbumDialogTitle(Context context, String str, FileOperationMsgParams fileOperationMsgParams) {
        int i2;
        if (fileOperationMsgParams == null || !PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            return null;
        }
        if (!str.startsWith("/data/sec/gallery/") && !str.startsWith("/data/sec_pass/")) {
            return null;
        }
        if (fileOperationMsgParams.getImageCount() == 1 && fileOperationMsgParams.getTotalCount() == 1) {
            i2 = R$string.move_image_to_private_album;
        } else if (fileOperationMsgParams.getVideoCount() == 1 && fileOperationMsgParams.getTotalCount() == 1) {
            i2 = R$string.move_video_to_private_album;
        } else if (fileOperationMsgParams.getVideoCount() == 0) {
            i2 = R$string.move_images_to_private_album;
        } else if (fileOperationMsgParams.getImageCount() == 0) {
            i2 = R$string.move_videos_to_private_album;
        } else {
            i2 = R$string.move_image_video_to_private_album;
        }
        return context.getString(i2);
    }

    private static String getQuotaFailMessage(Context context, int i2) {
        String cloudBrand = StringResources.getCloudBrand();
        if (i2 == 1) {
            return context.getString(R$string.cant_copy_item_cloud_storage_full, new Object[]{cloudBrand});
        }
        return context.getString(R$string.cant_copy_items_cloud_storage_full, new Object[]{cloudBrand});
    }

    public static String getResultMessage(Context context, FileOpResult fileOpResult, FileOperationMsgParams fileOperationMsgParams) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$fileio$abstraction$FileOpResult[fileOpResult.ordinal()]) {
            case 1:
                return getCancelMessage(context);
            case 2:
            case 3:
            case 4:
                if (fileOperationMsgParams.getFailCount() > 0) {
                    return getCloudSuccessMessage(context, fileOperationMsgParams);
                }
                break;
            case 5:
                break;
            case 6:
                return getStorageFailMessage(context, fileOperationMsgParams.getAlbumPath());
            case 7:
                return getCloudFailMessage(context);
            case 8:
                return getQuotaFailMessage(context, fileOperationMsgParams.getTotalCount());
            case 9:
                return getSyncOffFailMessage(context, fileOperationMsgParams);
            default:
                return getDefaultFailMessage(context, fileOperationMsgParams.isCopy());
        }
        return getDefaultSuccessMessage(context, fileOperationMsgParams.isCopy());
    }

    public static String getServiceName(Context context, boolean z, boolean z3) {
        int i2;
        if (z3) {
            i2 = R$string.rename;
        } else if (z) {
            i2 = R$string.copy;
        } else {
            i2 = R$string.move;
        }
        return context.getString(i2);
    }

    private static String getStorageFailMessage(Context context, String str) {
        int i2;
        if (FileUtils.isInRemovableStorage(str)) {
            i2 = R$string.not_enough_space_on_sd_card;
        } else {
            i2 = R$string.operation_failed_low_storage;
        }
        return context.getString(i2);
    }

    private static String getSyncOffFailMessage(Context context, FileOperationMsgParams fileOperationMsgParams) {
        return context.getResources().getQuantityString(R$plurals.move_to_sync_off_album_cloud_file, fileOperationMsgParams.getFailCount(), new Object[]{Integer.valueOf(fileOperationMsgParams.getFailCount()), StringResources.getCloudBrand(), getAlbumName(fileOperationMsgParams.getAlbumPath())});
    }

    public static String getResultMessage(Context context, FileOpError fileOpError, FileOperationMsgParams fileOperationMsgParams) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$fileio$compat$FileOpError[fileOpError.ordinal()]) {
            case 1:
                if (fileOperationMsgParams.getFailCount() > 0) {
                    return getCloudSuccessMessage(context, fileOperationMsgParams);
                }
                break;
            case 2:
            case 3:
                break;
            case 4:
                return getCancelMessage(context);
            case 5:
                return getStorageFailMessage(context, fileOperationMsgParams.getAlbumPath());
            case 6:
                return getCloudFailMessage(context);
            case 7:
                return getQuotaFailMessage(context, fileOperationMsgParams.getTotalCount());
            case 8:
                return getSyncOffFailMessage(context, fileOperationMsgParams);
            default:
                return getDefaultFailMessage(context, fileOperationMsgParams.isCopy());
        }
        if (fileOperationMsgParams.getFailCount() > 0) {
            return getDefaultFailMessage(context, fileOperationMsgParams.isCopy());
        }
        return getDefaultSuccessMessage(context, fileOperationMsgParams.isCopy());
    }
}
