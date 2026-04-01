package com.samsung.android.gallery.module.grid;

import N2.j;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Timeline70Impl extends Timeline60Impl {
    public int[] buildMonth() {
        if (GridHelper.NON_REAL_RATIO) {
            return new int[]{2};
        }
        if (!GridHelper.ModelSegment.SEP_LITE) {
            return new int[]{3, 4};
        }
        return new int[]{3};
    }

    public int[] buildSpanInfo() {
        if (GridHelper.ModelSegment.SEP_LITE) {
            return new int[]{17, 1, 1, 2};
        }
        return new int[]{17, 1, 1, 2, 98, 67};
    }

    public int computeGridDepth(Context context) {
        int i2 = -1;
        try {
            TimelineImpl[] timelineImplArr = {new Timeline60Impl(), new Timeline30Impl(), new TimelineImpl()};
            for (int i7 = 0; i7 < 3; i7++) {
                TimelineImpl timelineImpl = timelineImplArr[i7];
                i2 = timelineImpl.getPreferenceGridCount(context);
                Log.d(this.TAG, "computeGridDepth", timelineImpl.getClass().getSimpleName(), Integer.valueOf(i2));
                if (i2 > 0) {
                    break;
                }
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("fail to getPreferenceGridCount : "), this.TAG);
        }
        if (i2 > 0) {
            int[] intArray = context.getResources().getIntArray(getGridArrayResource());
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "GridSpans#recover span count ", " from ");
            o2.append(StringCompat.toString(intArray));
            Log.w(str, o2.toString());
            for (int i8 = 0; i8 < intArray.length; i8++) {
                if (intArray[i8] == i2) {
                    return i8;
                }
            }
        }
        return getDefaultDepth();
    }

    public int getDefaultDepth() {
        if (GridHelper.NON_REAL_RATIO) {
            return 1;
        }
        return 2;
    }

    public int getGridArrayResource() {
        if (GridHelper.ModelSegment.SEP_LITE) {
            return R$array.n_pictures_column_count;
        }
        return R$array.n_timeline_column_count;
    }

    public int getGridDepth() {
        int loadInt = GalleryPreference.getInstance().loadInt(getPreferenceName(), -1);
        if (loadInt >= 0) {
            return loadInt;
        }
        int computeGridDepth = computeGridDepth(AppResources.getAppContext());
        GalleryPreference.getInstance().saveState(getPreferenceName(), computeGridDepth);
        return computeGridDepth;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.TIMELINE_GRID_SIZE_V70;
    }

    public int getSplitArrayResource() {
        if (GridHelper.ModelSegment.SEP_LITE) {
            return R$array.n_pictures_split_column_count;
        }
        return R$array.n_timeline_split_column_count;
    }

    public boolean isMonth(int i2) {
        if (i2 == 3 || i2 == 4) {
            return true;
        }
        return false;
    }

    public boolean isMonthForViewing(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    public boolean isSelectable(int i2) {
        if (i2 < 4) {
            return true;
        }
        return false;
    }

    public boolean isYear(int i2) {
        if (i2 == 5) {
            return true;
        }
        return false;
    }
}
