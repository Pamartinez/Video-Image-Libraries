package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPetFacesTable extends MpCreatureFacesTable {
    public MpPetFacesTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String getAliasName() {
        return "PF";
    }

    public String getCreatureFaceDataColumnName() {
        return "cluster_data";
    }

    public String getCreatureIdColumnName() {
        return "cluster_info_id";
    }

    public String getTableNameRaw() {
        return "cluster_rect";
    }

    public String tag() {
        return "MpPetFacesTable";
    }

    public void onConstruct() {
    }
}
