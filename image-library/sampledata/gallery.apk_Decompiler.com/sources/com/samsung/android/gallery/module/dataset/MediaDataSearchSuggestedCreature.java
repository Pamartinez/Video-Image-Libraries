package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchSuggestedCreature extends MediaDataSearchCategory {
    public MediaDataSearchSuggestedCreature(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str, z);
    }

    public SearchSorter getSearchSorter() {
        return new SearchCreatureSorter(this.mLocationKey);
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mSorter != null) {
            String argValue = ArgumentsUtil.getArgValue(this.mLocationReference, "people_ids", "");
            if (!TextUtils.isEmpty(argValue)) {
                this.mSorter.setPriorityValues(Arrays.stream(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).distinct().map(new K(16)).collect(Collectors.toList()));
            }
        }
        super.swap(cursorArr);
    }
}
