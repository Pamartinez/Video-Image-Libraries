package com.samsung.android.gallery.module.knox;

import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.knox.KnoxOperationTask;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class KnoxOperatorString {

    /* renamed from: com.samsung.android.gallery.module.knox.KnoxOperatorString$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType[] r0 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType = r0
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_CLOUD_CONTENTS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_EMPTY_DATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_WRONG_CONTAINER_ID     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_EXCEEDED_RANGE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.knox.KnoxOperatorString.AnonymousClass1.<clinit>():void");
        }
    }

    private static int getCannotMoveToResId(int i2, int i7) {
        if (i2 == 1 && i7 == 0) {
            return R$string.cannot_move_to_empty_album_personal;
        }
        if (i2 > 1 && i7 == 0) {
            return R$string.cannot_move_to_empty_albums_personal;
        }
        if (i2 == 0 && i7 == 1) {
            return R$string.cannot_move_to_s_empty_group_personal;
        }
        if (i2 != 0 || i7 <= 1) {
            return R$string.cannot_move_to_s_empty_albums_groups_personal;
        }
        return R$string.cannot_move_to_s_empty_groups_personal;
    }

    public static String getMessage(KnoxOperationTask.MessageType messageType, KnoxUtil.MoveType moveType, int i2, int i7) {
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType[messageType.ordinal()];
        if (i8 == 1) {
            return AppResources.getQuantityString(R$plurals.move_to_sync_off_album_cloud_file, i2, Integer.valueOf(i2), StringResources.getCloudBrand(), KnoxUtil.getInstance().getKnoxContainerName(moveType));
        }
        if (i8 == 2 || i8 == 3) {
            return AppResources.getString(R$string.unable_to_move);
        }
        if (i8 != 4) {
            return "";
        }
        return AppResources.getString(R$string.move_to_knox_max_number_exceeded, Integer.valueOf(i7));
    }

    private static int getMoveToResId(int i2, int i7) {
        if (i2 == 1 && i7 == 0) {
            return R$string.cannot_move_to_s_empty_album;
        }
        if (i2 > 1 && i7 == 0) {
            return R$string.cannot_move_to_s_empty_albums;
        }
        if (i2 == 0 && i7 == 1) {
            return R$string.cannot_move_to_s_empty_group;
        }
        if (i2 != 0 || i7 <= 1) {
            return R$string.cannot_move_to_s_empty_albums_groups;
        }
        return R$string.cannot_move_to_s_empty_groups;
    }

    public static String getNoItemMessage(KnoxUtil.MoveType moveType, int i2, int i7) {
        String knoxContainerName = KnoxUtil.getInstance().getKnoxContainerName(moveType);
        if (moveType == KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER || moveType == KnoxUtil.MoveType.MOVE_TO_KNOX || (moveType == KnoxUtil.MoveType.REMOVE_FROM_KNOX && knoxContainerName != null)) {
            return SeApiCompat.naturalizeText(AppResources.getString(getMoveToResId(i2, i7), knoxContainerName));
        }
        if (moveType == KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER) {
            return AppResources.getString(getRemoveFromSecureFolderResId(i2, i7), knoxContainerName);
        }
        return AppResources.getString(getCannotMoveToResId(i2, i7));
    }

    private static int getRemoveFromSecureFolderResId(int i2, int i7) {
        if (i2 == 1 && i7 == 0) {
            return R$string.cannot_move_out_s_empty_album;
        }
        if (i2 > 1 && i7 == 0) {
            return R$string.cannot_move_out_s_empty_albums;
        }
        if (i2 == 0 && i7 == 1) {
            return R$string.cannot_move_out_s_empty_group;
        }
        if (i2 != 0 || i7 <= 1) {
            return R$string.cannot_move_out_s_empty_albums_groups;
        }
        return R$string.cannot_move_out_s_empty_groups;
    }
}
