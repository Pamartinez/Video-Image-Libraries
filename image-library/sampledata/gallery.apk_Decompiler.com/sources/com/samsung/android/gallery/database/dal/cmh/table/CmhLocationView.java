package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhLocationView extends SecLocationView {
    public CmhLocationView(QueryParams queryParams) {
        super(queryParams);
    }

    public SecFilesTable createFilesTable() {
        if (this.USE_GMP) {
            return new MpFilesTable(this.mParams);
        }
        return new CmhFilesTable(this.mParams);
    }

    public String getTagTypeColumnName() {
        return "2";
    }

    public String tag() {
        return "CmhLocationView";
    }
}
