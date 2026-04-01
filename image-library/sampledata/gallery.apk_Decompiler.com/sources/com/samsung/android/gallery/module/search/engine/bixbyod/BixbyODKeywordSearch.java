package com.samsung.android.gallery.module.search.engine.bixbyod;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.ExtraResults;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyODKeywordSearch {
    private boolean isSupported() {
        if (!Features.isEnabled(Features.SUPPORT_SEARCH_KEYWORD_FROM_BIXBY_OD) || !BixbyOnDeviceNer.support(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    public Cursor query(IntelligentSearch intelligentSearch, SearchFilter searchFilter, ExtraResults extraResults) {
        if (!isSupported()) {
            return null;
        }
        BixbyNerObject.unmarshal(BixbyOnDeviceNer.load(searchFilter.getRawKeyword()));
        Log.se("BixbyODKeywordSearch", "nerObject is null");
        return null;
    }
}
