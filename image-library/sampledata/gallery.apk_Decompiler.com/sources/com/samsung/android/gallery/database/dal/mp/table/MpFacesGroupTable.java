package com.samsung.android.gallery.database.dal.mp.table;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpFacesGroupTable extends DbTable {
    public MpFacesGroupTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void filterGroupType(int i2) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("FG.group_type = " + i2);
    }

    public void filterHidden(int i2, String str) {
        String str2 = "";
        if (i2 == 0) {
            StringBuilder sb2 = new StringBuilder("FG.unique_days >= 5");
            if (!TextUtils.isEmpty(str)) {
                str2 = C0212a.l(" or ", str);
            }
            sb2.append(str2);
            C0212a.w("((FG.essential_group = 0 and (", sb2.toString(), ")) or FG.essential_group > 0)", this.mQueryBuilder);
        } else if (i2 == 1) {
            StringBuilder sb3 = new StringBuilder("FG.unique_days < 5");
            if (!TextUtils.isEmpty(str)) {
                str2 = C0212a.l(" and ", str);
            }
            sb3.append(str2);
            C0212a.w("((FG.essential_group = 0 and (", sb3.toString(), ")) or FG.essential_group < 0)", this.mQueryBuilder);
        }
    }

    public String getTableNameRaw() {
        return "faces_group";
    }

    public void setDefaultCondition() {
        this.mQueryBuilder.andCondition("FG.group_merge_history is null");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("FG.unique_days", "__facesUniqueDays");
        this.mQueryBuilder.addProjection("FG.essential_group", "__facesEssentialGroup");
        this.mQueryBuilder.addProjection("FG.group_uuid", "__facesGroupId");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "FG");
    }

    public String tag() {
        return getClass().getSimpleName();
    }

    public void onConstruct() {
    }

    public void setDefaultOrder() {
    }
}
