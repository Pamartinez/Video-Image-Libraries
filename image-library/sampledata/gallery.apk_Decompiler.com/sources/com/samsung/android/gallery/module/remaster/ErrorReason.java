package com.samsung.android.gallery.module.remaster;

import android.content.Context;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ErrorReason {
    MANY_FRAMES,
    LOW_QUALITY,
    UNKNOWN;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.remaster.ErrorReason$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends ErrorReason {
        public /* synthetic */ AnonymousClass1() {
            this("CANCEL", 0);
        }

        public String getErrorReasonText(MediaItem mediaItem) {
            return AppResources.getString(RemasterHelper.getErrorReasonText(mediaItem));
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }

        public void showToastForErrorReason(Context context, MediaItem mediaItem) {
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.remaster.ErrorReason$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends ErrorReason {
        public /* synthetic */ AnonymousClass2() {
            this("MANY_PIXELS", 1);
        }

        public void showToastForErrorReason(Context context, MediaItem mediaItem) {
            Utils.showToast(context, R$string.remaster_ondemand_gif_error_pixel);
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.remaster.ErrorReason$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends ErrorReason {
        public /* synthetic */ AnonymousClass3() {
            this("ERROR_DECODING", 4);
        }

        public void showToastForErrorReason(Context context, MediaItem mediaItem) {
            Utils.showToast(context, R$string.remaster_ondemand_gif_error_decoding);
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.remaster.ErrorReason$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends ErrorReason {
        public /* synthetic */ AnonymousClass4() {
            this("ERROR_NO_REMASTERING_NEED", 5);
        }

        public String getErrorReasonText(MediaItem mediaItem) {
            return "";
        }

        public void showToastForErrorReason(Context context, MediaItem mediaItem) {
            Utils.showToast(context, R$string.remaster_ondemand_no_remastering_need);
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    private boolean isEnhancedType(int i2) {
        if (i2 == 19 || i2 == 9 || i2 == 17 || i2 == 15) {
            return true;
        }
        return false;
    }

    public String getErrorReasonText(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isGif()) {
            return "";
        }
        return AppResources.getString(R$string.remaster_gif_ondemand_error);
    }

    public void showToastForErrorReason(Context context, MediaItem mediaItem) {
        int i2 = R$string.remaster_ondemand_error_toast;
        if (mediaItem != null && isEnhancedType((int) MediaItemSuggest.getRevitalizedType(mediaItem))) {
            i2 = R$string.unknown_error_description;
        }
        Utils.showToast(context, i2);
    }
}
