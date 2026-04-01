package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpLocationView extends SecLocationView {
    public MpLocationView(QueryParams queryParams) {
        super(queryParams);
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterBurstShotBestImage() {
        if (this.IS_GTE_Q) {
            this.mFilesTable.filterGroupMediaBest(false);
        } else {
            super.filterBurstShotBestImage();
        }
    }

    public String getTagTypeColumnName() {
        if (!this.IS_GTE_Q) {
            return "2";
        }
        if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            return "L.tag_type";
        }
        return "2";
    }

    public String tag() {
        return "MpLocationView";
    }
}
