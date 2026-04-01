package com.samsung.android.gallery.database.dal.mp.table;

import X3.C0413a;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecSearchableView;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpTagView extends SecSearchableView {
    private static final String LOCATION_TAG_TYPE_NOT_IN_CONDITION;

    static {
        String str;
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            str = " not in (7,11)";
        } else {
            str = " not in (2,7,11)";
        }
        LOCATION_TAG_TYPE_NOT_IN_CONDITION = str;
    }

    public MpTagView(QueryParams queryParams) {
        super(queryParams);
    }

    private void appendEnglishKeyword(StringBuilder sb2, String str, boolean z) {
        Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).filter(new e(10)).forEach(new C0413a(this, sb2, z, 4));
    }

    private boolean enabledLocation() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$appendEnglishKeyword$0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$appendEnglishKeyword$1(StringBuilder sb2, boolean z, String str) {
        sb2.append(likeWildTagData(str, z));
        sb2.append(" or ");
    }

    private String likeWildTagData(String str, boolean z) {
        StringJoiner stringJoiner = new StringJoiner(" ", "(", ")");
        stringJoiner.add(likeWild(getColumnNameTagData(), str));
        if (!z) {
            stringJoiner.add("AND").add(getColumnNameTagType()).add(LOCATION_TAG_TYPE_NOT_IN_CONDITION);
        }
        return stringJoiner.toString();
    }

    public void addOrderByCount() {
        this.mQueryBuilder.addOrderBy("__count DESC");
    }

    public void addOrderByDate() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC, A._id DESC");
    }

    public void addProjectionForCount() {
        this.mQueryBuilder.addProjection("count(distinct A._id)", "__count");
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterBurstShotBestImage(boolean z) {
        this.mFilesTable.filterGroupMediaBest(true);
        if (z) {
            this.mFilesTable.addGroupMediaCountProjection(this.mQueryBuilder);
        }
    }

    public void filterExistTagData() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameTagData() + " is not null");
    }

    public void filterFileIds(String str) {
        this.mFilesTable.filterIds(str);
    }

    public void filterLocation() {
        String str;
        filterExistTagData();
        String str2 = " in (2,7,11)";
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getColumnNameTagType());
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
                str2 = " in (7,11)";
            }
            sb2.append(str2);
            queryBuilder.andCondition(sb2.toString());
        } else {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition(getColumnNameTagType() + str2);
        }
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        if (enabledLocation()) {
            str = "1";
        } else {
            str = "0";
        }
        queryBuilder3.andCondition(str);
    }

    public void filterRemainScenes() {
        filterSceneOrSubScene();
        filterRemainScenesNewCategory();
    }

    public void filterRemainScenesNewCategory() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(columnToLowerCase(getColumnNameTagData()) + " not in " + getCategoryName(CategoryType.VISUAL_SEARCH_ALL_OTHER_CATEGORY));
    }

    public void filterSceneOrSubScene() {
        filterExistTagData();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameTagType() + " in (4,5,104,105)");
    }

    public void filterTagData(String str) {
        String escapeString = QueryBuilder.escapeString(str);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getColumnNameTagData() + " COLLATE NOCASE in ('" + escapeString + "')");
    }

    public String getCategoryName(String[] strArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, " (", ") ");
        for (String str : strArr) {
            stringJoiner.add("'" + str.toLowerCase() + "'");
        }
        return stringJoiner.toString();
    }

    public String getColumnNameSceneRegionRatio() {
        return "SC.scene_region_ratio";
    }

    public String getColumnNameTagData() {
        return "T.tag_data";
    }

    public String getColumnNameTagType() {
        return "T.tag_type";
    }

    public void groupTagData() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy("LOWER(" + getColumnNameTagData() + ")");
    }

    public String hashTagSearch(String str) {
        StringJoiner stringJoiner = new StringJoiner(" ", "(", ")");
        stringJoiner.add(getColumnNameTagData() + " = '" + str + "'").add("COLLATE").add("NOCASE");
        stringJoiner.add("AND").add(getColumnNameTagType()).add(" in (0)");
        return stringJoiner.toString();
    }

    public void havingLatest() {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.having("max(A." + this.mFilesTable.getColumnDateTaken() + ")");
    }

    public void includeFileIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.firstOrCondition("A._id IN (" + str + ")");
    }

    public void orderLatestTag() {
        this.mQueryBuilder.addOrderBy("T.rowid DESC");
    }

    public void resetProjectionForCursorCount(boolean z) {
        super.resetProjectionForCursorCount(z);
        this.mQueryBuilder.removeJoin("scene");
        this.mQueryBuilder.removeJoin("location");
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.replaceProjectionByAlias("CASE WHEN " + getColumnNameSceneRegionRatio() + " is null THEN A.smartcrop_rect_ratio ELSE " + getColumnNameSceneRegionRatio() + " END", "__sceneRegion");
        this.mQueryBuilder.addProjection(getColumnNameTagData(), "__subCategory");
        this.mQueryBuilder.addProjection(getColumnNameTagType(), "__tagType");
        this.mQueryBuilder.addProjection("TL.country_code", "__countryCode");
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        this.mQueryBuilder.addLeftOuterJoin("tag_view as T", "A._id=T.sec_media_id");
        this.mQueryBuilder.addLeftOuterJoin("scene as SC", "A._id=SC.sec_media_id  and T.tag_data=SC.scene_name");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addLeftOuterJoin("location as TL", "A.latitude=TL.latitude AND   A.longitude=TL.longitude  AND TL.locale='" + Locale.getDefault() + "'");
    }

    public String stringSearch(SearchFilter searchFilter) {
        StringBuilder sb2 = new StringBuilder();
        String rawKeyword = searchFilter.getRawKeyword();
        String engKeyword = searchFilter.getEngKeyword();
        String originEngKeyword = searchFilter.getOriginEngKeyword();
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
        boolean isCountryKeyword = searchFilter.isCountryKeyword(rawKeyword);
        sb2.append(likeWildTagData(rawKeyword, isEnabled));
        sb2.append(" or ");
        if (!TextUtils.isEmpty(engKeyword)) {
            appendEnglishKeyword(sb2, engKeyword, isEnabled);
        }
        if (!TextUtils.equals(originEngKeyword, engKeyword) && !TextUtils.isEmpty(originEngKeyword)) {
            appendEnglishKeyword(sb2, originEngKeyword, isEnabled);
        }
        sb2.append(likeWild("A._display_name", rawKeyword));
        sb2.append(" or ");
        sb2.append(likeWild("A.bucket_display_name", rawKeyword));
        if (enabledLocation()) {
            sb2.append(" or ");
            sb2.append(likeWild("A.addr", rawKeyword));
        }
        if (isCountryKeyword) {
            sb2.append(" or ");
            sb2.append("TL.country_code = '");
            sb2.append(searchFilter.getCountryCode());
            sb2.append("'");
        }
        return sb2.toString();
    }

    public String tag() {
        return "MpTagView";
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
