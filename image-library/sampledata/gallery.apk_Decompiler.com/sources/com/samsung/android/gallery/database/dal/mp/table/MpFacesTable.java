package com.samsung.android.gallery.database.dal.mp.table;

import N2.j;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpFacesTable extends MpCreatureFacesTable {
    public MpFacesTable(QueryParams queryParams) {
        super(queryParams);
    }

    public void addRecommendationIdProjection() {
        this.mQueryBuilder.addProjection("CASE  WHEN (F.recommended_id in (0,1) AND F.person_id = 1 AND F.group_id > 0) THEN (F.group_id + 100000) WHEN (F.recommended_id in (0,1) AND F.person_id > 1 AND F.group_id > 0) THEN F.person_id ELSE F.recommended_id END", "__creatureFaceRecommendedID");
    }

    public String getAliasName() {
        return "F";
    }

    public String getCreatureFaceDataColumnName() {
        return "face_data";
    }

    public String getCreatureIdColumnName() {
        return "person_id";
    }

    public String getRawQueryMediaIdForExpression(String str) {
        StringBuilder k = j.k("select sec_media_id from faces AS F where F.", str, " >= 0.8 AND ");
        k.append("(" + str + " = MAX(IFNULL(F.expression_like, 0), IFNULL(F.expression_neutral, 0), IFNULL(F.expression_surprise, 0), IFNULL(F.expression_dislike, 0)))");
        return k.toString();
    }

    public String getTableNameRaw() {
        return "faces";
    }

    public void resetProjectionForFaceCluster() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("group_concat(DISTINCT F.group_id)", "__creatureFaceGroupIDs");
        this.mQueryBuilder.addProjection("group_concat(DISTINCT F.group_facedata_values)", "__groupFacedataValues");
        this.mQueryBuilder.addProjection("group_concat(DISTINCT F.excluded_facegroup_suggestion)", "__excludedFacegroupSuggestion");
    }

    public void setDefaultCondition() {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            this.mQueryBuilder.andCondition("( ((F.recommended_id > 1 and F.recommended_id != 100000) or F.recommended_id in (0,1)) and F.group_id > 0 )");
        } else {
            this.mQueryBuilder.andCondition("F.group_id > 0");
        }
    }

    public String tag() {
        return "MpFacesTable";
    }

    public void onConstruct() {
    }
}
