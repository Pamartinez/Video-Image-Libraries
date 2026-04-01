package com.samsung.android.gallery.database.dal.mp.table;

import N2.j;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpCreatureView;
import com.samsung.android.gallery.database.dbtype.FacePriorityScore;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPeopleView extends MpCreatureView {
    private static final boolean SUPPORT_PEOPLE_FACE_SCORE = Features.isEnabled(Features.SUPPORT_PEOPLE_FACE_SCORE);

    public MpPeopleView(QueryParams queryParams) {
        super(queryParams);
    }

    public MpCreatureFacesTable createCreatureFacesTable() {
        return new MpFacesTable(this.mParams);
    }

    public MpCreatureTagTable createMpCreatureTagTable() {
        return new MpPersonTable(this.mParams);
    }

    public void filterRelationship(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(this.mCreatureTagTable.getAliasName() + ".relationship= \"" + str + "\"");
    }

    public void filterTaggedNameFromUser() {
        super.filterTaggedNameFromUser();
        this.mQueryBuilder.andCondition("__creatureContactRawId = 0");
    }

    public String getCreatureFaceScoreProjection() {
        if (MpCreatureView.USE_CREATURE_FACE_SCORE_ONLY) {
            return super.getCreatureFaceScoreProjection();
        }
        StringBuilder sb2 = new StringBuilder();
        String str = "A." + this.mFilesTable.getColumnDateTaken();
        String aliasName = this.mCreatureFacesTable.getAliasName();
        SqliteCaseBuilder sqliteCaseBuilder = new SqliteCaseBuilder();
        boolean z = SUPPORT_PEOPLE_FACE_SCORE;
        if (z) {
            sqliteCaseBuilder.whenThen(aliasName + ".title_info >= 900000000", 0);
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(aliasName);
        sb3.append(".pos_left < 0 or ");
        sb3.append(aliasName);
        sb3.append(".pos_right < 0 or ");
        sb3.append(aliasName);
        sqliteCaseBuilder.whenThen(j.f(sb3, ".pos_top < 0 or ", aliasName, ".pos_bottom < 0"), -100);
        sqliteCaseBuilder.whenThen("(strftime('%s','now','localtime')*1000 - " + str + ") < 86400000", FacePriorityScore.P_1);
        sqliteCaseBuilder.whenThen("(strftime('%s','now','localtime')*1000 - " + str + ") < 94608000000", FacePriorityScore.P_2);
        sqliteCaseBuilder.elseThen(0);
        sb2.append(sqliteCaseBuilder.build());
        if (z) {
            sb2.append("\n + IFNULL(F.title_info, 0)");
        } else {
            sb2.append("\n + ");
            SqliteCaseBuilder sqliteCaseBuilder2 = new SqliteCaseBuilder();
            sqliteCaseBuilder2.whenThen("A.sef_file_type IN ('2304', '2320', '2416', '2417', '2432', '3008')", 4);
            sqliteCaseBuilder2.elseThen(0);
            sb2.append(sqliteCaseBuilder2.build());
            sb2.append("\n + ");
            SqliteCaseBuilder sqliteCaseBuilder3 = new SqliteCaseBuilder();
            sqliteCaseBuilder3.whenThen("(F.pos_right - F.pos_left >= 360) and (F.pos_bottom - F.pos_top >= 360)", 2);
            sqliteCaseBuilder3.elseThen(0);
            sb2.append(sqliteCaseBuilder3.build());
            sb2.append("\n + ");
            SqliteCaseBuilder sqliteCaseBuilder4 = new SqliteCaseBuilder();
            sqliteCaseBuilder4.whenThen("abs(F.pos_left + F.pos_right - A.width) <= (A.width * 0.7) and abs(F.pos_top + F.pos_bottom - A.height) <= (A.height * 0.7)", 1);
            sqliteCaseBuilder4.elseThen(0);
            sb2.append(sqliteCaseBuilder4.build());
        }
        return sb2.toString();
    }

    public String getCreatureType() {
        return MpCreatureView.CreatureType.PEOPLE.ordinal() + "";
    }

    public String getEssentialGroupExtraOrCondition(int i2) {
        String str;
        String str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mCreatureTagTable.getAliasName());
        if (i2 == 0) {
            str = ".name is not null or ";
        } else {
            str = ".name is null and ";
        }
        sb2.append(str);
        sb2.append(this.mCreatureTagTable.getAliasName());
        if (i2 == 0) {
            str2 = ".relationship is not null";
        } else {
            str2 = ".relationship is null";
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public String getMainCategory() {
        return "People";
    }

    public void setDefaultCondition() {
        if (!this.IS_GTE_T) {
            this.mFilesTable.filterImage();
        }
    }

    public String tag() {
        return "MpPeopleView";
    }
}
