package com.samsung.android.gallery.database.dal.abstraction.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseView extends DbTable {
    protected SecFilesTable mFilesTable;

    public BaseView(QueryParams queryParams) {
        super(queryParams);
    }

    public abstract SecFilesTable createFilesTable();

    public String getColumnDateTaken() {
        return this.mFilesTable.getColumnDateTaken();
    }

    public void onConstruct() {
        this.mFilesTable = createFilesTable();
    }

    public String tag() {
        return "BaseView";
    }
}
