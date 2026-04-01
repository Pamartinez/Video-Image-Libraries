package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.content.res.Resources;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RevitalizedDelegate {
    public static boolean compareResolution(MediaItem mediaItem, BitmapOptions bitmapOptions) {
        int widthInDB = mediaItem.getWidthInDB();
        int heightInDB = mediaItem.getHeightInDB();
        int i2 = bitmapOptions.outWidth;
        int i7 = bitmapOptions.outHeight;
        if (widthInDB == i2 && heightInDB == i7) {
            return true;
        }
        return false;
    }

    private static String getBodyText(int i2) {
        switch (i2) {
            case 1:
                return AppResources.getString(R.string.increase_resolution);
            case 2:
            case 3:
            case 4:
                return AppResources.getString(R.string.improve_brightness_color_and_sharpness);
            case 5:
                return AppResources.getString(R.string.remaster_result_deblur);
            case 6:
                return AppResources.getString(R.string.remaster_result_denoise);
            case 8:
                return AppResources.getString(R.string.sharpen_blurry_face);
            case 9:
                return AppResources.getString(R.string.remove_moire_patter_from_screen);
            default:
                return null;
        }
    }

    public static String getResolutionDescription(MediaItem mediaItem, BitmapOptions bitmapOptions, int i2) {
        int widthInDB = mediaItem.getWidthInDB();
        int heightInDB = mediaItem.getHeightInDB();
        int i7 = bitmapOptions.outWidth;
        int i8 = bitmapOptions.outHeight;
        String string = AppResources.getString(R.string.original_resolution, Integer.valueOf(widthInDB), Integer.valueOf(heightInDB));
        String string2 = AppResources.getString(R.string.new_resolution, Integer.valueOf(i7), Integer.valueOf(i8));
        if (hasEnoughSpace(i2)) {
            return C0212a.B(string2, "  |  ", string);
        }
        return C0212a.B(string2, "\n", string);
    }

    public static float getResolutionDescriptionSize(int i2) {
        int i7;
        Resources resources = AppResources.getAppContext().getResources();
        if (i2 == 5) {
            i7 = R.dimen.large_grid_revitalized_card_view_body_text_size;
        } else {
            i7 = R.dimen.small_grid_revitalized_card_view_body_text_size;
        }
        return (float) resources.getDimensionPixelOffset(i7);
    }

    public static int getRevitalizedSubTitleMaxLine(int i2) {
        if (hasEnoughSpace(i2)) {
            return 1;
        }
        return 2;
    }

    public static int getRevitalizedTitleMaxLine(int i2, boolean z) {
        if (!z && !hasEnoughSpace(i2)) {
            return 3;
        }
        return 2;
    }

    public static String getRevitalizedTypeDescription(long j2) {
        String bodyText = getBodyText((int) j2);
        if (!TextUtils.isEmpty(bodyText)) {
            return bodyText;
        }
        List<Integer> decodeEnhances = VslMesDetectorCompat.decodeEnhances(j2, true);
        if (RemasterType.isUpscaleDeblur(decodeEnhances)) {
            return AppResources.getString(R.string.increase_resolution_and_reduce_blurring);
        }
        if (RemasterType.isLowLightDeblur(decodeEnhances)) {
            return AppResources.getString(R.string.improve_brightness_and_reduce_blurring);
        }
        if (RemasterType.isDenoiseDeblur(decodeEnhances)) {
            return AppResources.getString(R.string.remaster_result_deblur);
        }
        if (RemasterType.isUpscaleOld(decodeEnhances)) {
            return AppResources.getString(R.string.sharpen_blurry_face);
        }
        if (RemasterType.isGIFs(decodeEnhances)) {
            return AppResources.getString(R.string.remaster_gifs);
        }
        if (RemasterType.isShadowAndReflection(decodeEnhances)) {
            return AppResources.getString(R.string.remove_shadows_and_reflections);
        }
        return AppResources.getString(R.string.improve_brightness_color_and_sharpness);
    }

    public static int[] getTitleLayoutParams(int i2) {
        int i7;
        int i8;
        int i10;
        Resources resources = AppResources.getAppContext().getResources();
        if (i2 == 5) {
            i7 = resources.getDimensionPixelOffset(R.dimen.large_grid_revitalized_card_button_layout_height);
            i8 = resources.getDimensionPixelOffset(R.dimen.large_grid_revitalized_card_button_layout_margin_end);
            i10 = resources.getDimensionPixelOffset(R.dimen.large_grid_revitalized_card_button_layout_margin_top);
        } else {
            i7 = resources.getDimensionPixelOffset(R.dimen.small_grid_revitalized_card_button_layout_height);
            i8 = resources.getDimensionPixelOffset(R.dimen.small_grid_revitalized_card_button_layout_margin_end);
            i10 = resources.getDimensionPixelOffset(R.dimen.small_grid_revitalized_card_button_layout_margin_top);
        }
        return new int[]{i7, i10, i8};
    }

    public static boolean hasEnoughSpace(int i2) {
        if (i2 == 5) {
            return true;
        }
        return false;
    }

    public static boolean isResolutionChangedType(long j2) {
        List<Integer> decodeEnhances = VslMesDetectorCompat.decodeEnhances(j2, true);
        if (j2 == 1 || j2 == 8 || RemasterType.isUpscaleDeblur(decodeEnhances)) {
            return true;
        }
        return false;
    }
}
