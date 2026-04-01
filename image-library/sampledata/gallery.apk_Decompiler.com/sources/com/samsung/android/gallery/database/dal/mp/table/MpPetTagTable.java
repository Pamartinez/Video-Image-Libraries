package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPetTagTable extends MpCreatureTagTable {
    public MpPetTagTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String getAliasName() {
        return "PT";
    }

    public String getTableNameRaw() {
        return "cluster_info";
    }

    public String tag() {
        return "MpPetTagTable";
    }

    public void onConstruct() {
    }
}
