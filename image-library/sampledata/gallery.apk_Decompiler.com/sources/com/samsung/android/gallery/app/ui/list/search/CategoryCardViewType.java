package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryCardViewType {
    public static int getTypeFromLocationKey(String str) {
        if ("location://search/fileList/Category/MyTag".equals(str)) {
            return 2;
        }
        if (LocationKey.isSearchCategoryCreatureMatch(str)) {
            return 3;
        }
        if ("location://search/fileList/Category/Activity".equals(str)) {
            return 7;
        }
        if ("location://search/fileList/Category/MyQuery".equals(str)) {
            return 8;
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && "location://search/fileList/Category/ScreenShot".equals(str)) {
            return 9;
        }
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB && "location://search/fileList/Category/Stories".equals(str)) {
            return 10;
        }
        if (!PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            return 1;
        }
        if ("location://search/fileList/Category/Location".equals(str)) {
            return 4;
        }
        if ("location://search/fileList/Category/ShotMode".equals(str)) {
            return 5;
        }
        if ("location://search/fileList/Category/Scene".equals(str)) {
            return 6;
        }
        return 1;
    }

    public static boolean isLocationType(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }
}
