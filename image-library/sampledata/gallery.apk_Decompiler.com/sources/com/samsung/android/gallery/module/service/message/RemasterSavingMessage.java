package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.BucketUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemasterSavingMessage {
    public static String getCopyResultText(Context context, int i2, String str) {
        if (hasPortraitEffect(i2)) {
            return context.getString(R$string.toast_saved_as_copy_still_image_background_effect);
        }
        if (isMotionPhoto(i2)) {
            return context.getString(R$string.toast_saved_as_copy_still_image_motion_photo);
        }
        if (str == null || !ShotModeType.is3dCapture(str)) {
            return "";
        }
        return context.getString(R$string.toast_saved_as_copy_still_image_3d_capture);
    }

    public static int getErrorTextId() {
        return R$string.unable_to_save;
    }

    public static int getFailTextId() {
        return R$string.image_save_fail;
    }

    public static String getResultText(Context context, int i2, String str) {
        if (hasPortraitEffect(i2)) {
            return context.getString(R$string.toast_saved_as_still_image_background_effect);
        }
        if (isMotionPhoto(i2)) {
            return context.getString(R$string.toast_saved_as_still_image_motion_photo);
        }
        if (str == null || !ShotModeType.is3dCapture(str)) {
            return "";
        }
        return context.getString(R$string.toast_saved_as_still_image_3d_capture);
    }

    public static String getResultTextFromSuggestion(Context context, String str, int i2) {
        String translatedDirectory = BucketUtils.getTranslatedDirectory(str);
        ShotMode bySefValue = ShotModeList.getInstance().getBySefValue(i2);
        if (bySefValue == null) {
            return context.getString(R$string.toast_image_saved_in, new Object[]{translatedDirectory});
        }
        return context.getString(R$string.toast_saved_as_still_image_in, new Object[]{context.getString(bySefValue.titleRes), translatedDirectory});
    }

    private static boolean hasPortraitEffect(int i2) {
        if (ShotModeType.isLiveFocus(i2) || i2 == 3552) {
            return true;
        }
        return false;
    }

    private static boolean isMotionPhoto(int i2) {
        if (2608 == i2) {
            return true;
        }
        return false;
    }
}
