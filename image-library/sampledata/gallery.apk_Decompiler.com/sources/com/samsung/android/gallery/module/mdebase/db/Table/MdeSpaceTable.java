package com.samsung.android.gallery.module.mdebase.db.Table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeSpaceTable extends DbTable {
    public MdeSpaceTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String getTableNameRaw() {
        return "space";
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "");
    }

    public String tag() {
        return "MdeSpaceTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }

    public void setDefaultProjection() {
    }
}
