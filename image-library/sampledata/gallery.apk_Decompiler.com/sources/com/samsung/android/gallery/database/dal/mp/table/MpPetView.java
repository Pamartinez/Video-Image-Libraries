package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPetView extends MpCreatureView {
    public MpPetView(QueryParams queryParams) {
        super(queryParams);
    }

    public MpCreatureFacesTable createCreatureFacesTable() {
        return new MpPetFacesTable(this.mParams);
    }

    public MpCreatureTagTable createMpCreatureTagTable() {
        return new MpPetTagTable(this.mParams);
    }

    public String getCreatureType() {
        return MpCreatureView.CreatureType.PET.ordinal() + "";
    }

    public String getEssentialGroupExtraOrCondition(int i2) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mCreatureTagTable.getAliasName());
        if (i2 == 0) {
            str = ".name is not null";
        } else {
            str = ".name is null";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public String getMainCategory() {
        return "Pet";
    }

    public void setDefaultCondition() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("IFNULL(" + this.mCreatureFacesTable.getAliasName() + ".cluster_type, 0) = 1");
    }

    public String tag() {
        return "MpPetView";
    }
}
