package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import M9.o;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CleanOutDelegate {
    public static BaseCleanOutPicturesViewAdapter createCleanOutPicturesViewAdapter(ICleanOutPicturesView iCleanOutPicturesView, String str, View view) {
        if (isCleanOutDuplicatedPictures(str)) {
            return new CleanOutDuplicatedPicturesViewAdapter(iCleanOutPicturesView, str, view);
        }
        return new CleanOutPicturesViewAdapter(iCleanOutPicturesView, str, view);
    }

    public static PicturesViewHolderFactory createViewHolderFactory(Context context, String str) {
        if (isCleanOutDuplicatedPictures(str)) {
            return new CleanOutDuplicatedViewHolderFactory(context);
        }
        return new CleanOutViewHolderFactory(context);
    }

    public static String getDuplicateDividerDescription(ListViewHolder listViewHolder, CharSequence charSequence, int i2, int i7) {
        if (i7 == 1) {
            return AppResources.getQuantityString(R.plurals.speak_item_selected_quantity_string, i2, Integer.valueOf(i2));
        }
        if (i7 != 32768) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        if (listViewHolder.isCheckBoxEnabled()) {
            if (listViewHolder.getCheckbox().isChecked()) {
                sb2.append(AppResources.getString(R.string.speak_checked));
            } else {
                sb2.append(AppResources.getString(R.string.speak_not_checked));
            }
            sb2.append(ArcCommonLog.TAG_COMMA);
        }
        sb2.append(charSequence);
        return sb2.toString();
    }

    public static int[] getGridArray(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.array.clean_duplicate_pictures_column_count;
        } else {
            i2 = R.array.clean_pictures_column_count;
        }
        return resources.getIntArray(i2);
    }

    public static float getItemAspectRatio(Resources resources, int i2) {
        return getRatioDimen(resources, i2);
    }

    public static int getItemBottomPadding(Resources resources, boolean z) {
        if (z) {
            return 0;
        }
        return resources.getDimensionPixelOffset(R.dimen.suggestion_clean_out_pictures_item_bottom_gap);
    }

    public static int getItemLargeSidePadding(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.suggestion_duplicate_pictures_grid_item_large_grid_side_offset;
        } else {
            i2 = R.dimen.suggestion_clean_out_pictures_grid_item_large_grid_side_offset;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    public static float getItemLargeWidthRate(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.suggestion_duplicate_pictures_item_width_large_rate;
        } else {
            i2 = R.dimen.suggestion_clean_out_pictures_item_width_large_rate;
        }
        return getRatioDimen(resources, i2);
    }

    public static int getItemSmallSidePadding(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.suggestion_duplicate_pictures_grid_item_small_grid_side_offset;
        } else {
            i2 = R.dimen.suggestion_clean_out_pictures_grid_item_small_grid_side_offset;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    public static float getItemSmallWidthRate(Resources resources, boolean z) {
        int i2;
        if (z) {
            i2 = R.dimen.suggestion_duplicate_pictures_item_width_small_rate;
        } else {
            i2 = R.dimen.suggestion_clean_out_pictures_item_width_small_rate;
        }
        return getRatioDimen(resources, i2);
    }

    public static float getRatioDimen(Resources resources, int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    public static String getScreenId(ICleanOutPicturesView iCleanOutPicturesView) {
        if (iCleanOutPicturesView.isCleanOutDuplicatedPictures()) {
            if (iCleanOutPicturesView.isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_SUGGEST_DUPLICATE_PICTURES_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_SUGGEST_DUPLICATE_PICTURES_NORMAL.toString();
        } else if (LocationKey.isCleanOutMotionPhoto(iCleanOutPicturesView.getLocationKey())) {
            if (iCleanOutPicturesView.isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_SUGGEST_MOTION_PHOTO_PICTURES_EDIT.toString();
            }
            return AnalyticsScreenId.SCREEN_SUGGEST_MOTION_PHOTO_PICTURES_NORMAL.toString();
        } else if (iCleanOutPicturesView.isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_SUGGEST_CLEAN_OUT_CLUTTER_EDIT.toString();
        } else {
            return AnalyticsScreenId.SCREEN_SUGGEST_CLEAN_OUT_CLUTTER_NORMAL.toString();
        }
    }

    public static boolean isCleanOutDuplicatedPictures(String str) {
        if (LocationKey.isCleanOutDuplicatedPictures(str) || LocationKey.isCleanOutBurstSimilarPhoto(str)) {
            return true;
        }
        return false;
    }

    public static void needFinish(ICleanOutPicturesView iCleanOutPicturesView) {
        if (iCleanOutPicturesView.getMediaData(iCleanOutPicturesView.getLocationKey()).getCount() == 0) {
            if (iCleanOutPicturesView.isCleanOutDuplicatedPictures()) {
                Utils.showToast(AppResources.getAppContext(), (int) R.string.duplicates_deleted);
            }
            ThreadUtil.postOnUiThread(new o(15, iCleanOutPicturesView));
            Log.majorEvent("onDataChangedOnUi : count=0. move back" + Logger.getEncodedString(ThreadUtil.getCallStack()));
        }
    }
}
