package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AddTagMsgMgr {

    /* renamed from: com.samsung.android.gallery.module.service.message.AddTagMsgMgr$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.module.service.message.AddTagMsgMgr$ErrorType[] r0 = com.samsung.android.gallery.module.service.message.AddTagMsgMgr.ErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType = r0
                com.samsung.android.gallery.module.service.message.AddTagMsgMgr$ErrorType r1 = com.samsung.android.gallery.module.service.message.AddTagMsgMgr.ErrorType.LIMITED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.service.message.AddTagMsgMgr$ErrorType r1 = com.samsung.android.gallery.module.service.message.AddTagMsgMgr.ErrorType.PRESENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.service.message.AddTagMsgMgr$ErrorType r1 = com.samsung.android.gallery.module.service.message.AddTagMsgMgr.ErrorType.OVER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.service.message.AddTagMsgMgr$ErrorType r1 = com.samsung.android.gallery.module.service.message.AddTagMsgMgr.ErrorType.BROKEN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.message.AddTagMsgMgr.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ErrorType {
        NONE,
        LIMITED,
        PRESENT,
        OVER,
        BROKEN
    }

    private static String getFailedByBrokenToastMessage(Context context, int i2, int i7, int i8) {
        if (i2 == 1) {
            if (i7 == 1) {
                return context.getString(R$string.cannot_tag_one_broken_image);
            }
            return context.getString(R$string.cannot_tag_one_broken_video);
        } else if (i7 == i2) {
            return context.getString(R$string.cannot_tag_broken_images, new Object[]{Integer.valueOf(i2)});
        } else {
            if (i8 == i2) {
                return context.getString(R$string.cannot_tag_broken_videos, new Object[]{Integer.valueOf(i2)});
            }
            return context.getString(R$string.cannot_tag_broken_items, new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String getFailedToastMessage(Context context, ErrorType errorType, int i2, int i7) {
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$service$message$AddTagMsgMgr$ErrorType[errorType.ordinal()];
        if (i8 == 1) {
            return context.getString(R$string.you_cant_add_more_than_limits_tags, new Object[]{30});
        }
        if (i8 == 2) {
            return context.getString(R$string.tag_already_added);
        }
        if (i8 == 3) {
            return context.getResources().getQuantityString(R$plurals.more_than_limit, 50, new Object[]{50});
        }
        if (i8 != 4) {
            return null;
        }
        return getFailedByBrokenToastMessage(context, i2 + i7, i2, i7);
    }

    public static String getSucceedToastMessage(Context context, int i2, int i7, int i8) {
        if (i2 == 1) {
            if (i7 == 1) {
                return context.getString(R$string.tag_added_one_image);
            }
            return context.getString(R$string.tag_added_one_video);
        } else if (i7 == i2) {
            return context.getString(R$string.tag_added_images_count, new Object[]{Integer.valueOf(i2)});
        } else {
            if (i8 == i2) {
                return context.getString(R$string.tag_added_videos_count, new Object[]{Integer.valueOf(i2)});
            }
            return context.getString(R$string.tag_added_items_count, new Object[]{Integer.valueOf(i2)});
        }
    }
}
