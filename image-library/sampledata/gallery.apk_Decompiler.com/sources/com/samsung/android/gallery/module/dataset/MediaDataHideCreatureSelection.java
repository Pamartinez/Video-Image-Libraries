package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataHideCreatureSelection extends MediaDataSearchCategory {
    public MediaDataHideCreatureSelection(Blackboard blackboard, String str) {
        super(blackboard, str, true);
    }

    public SearchSorter getSearchSorter() {
        return new SearchSorter("location://search/fileList/Category/PeopleAndPets");
    }
}
