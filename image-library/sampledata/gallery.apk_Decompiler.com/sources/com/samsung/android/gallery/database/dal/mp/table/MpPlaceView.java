package com.samsung.android.gallery.database.dal.mp.table;

import N2.j;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpPlaceView extends BaseView {
    public MpPlaceView(QueryParams queryParams) {
        super(queryParams);
    }

    private boolean enabledLocation() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    private String getDateOrdered() {
        if (SortByType.getSortBy(this.mParams.getSortBy()) == 20) {
            return IParameterKey.DATE_MODIFIED;
        }
        return super.getColumnDateTaken();
    }

    private String getDateOrderedColumn() {
        if (SortByType.getSortBy(this.mParams.getSortBy()) == 20) {
            return "__dateModified";
        }
        return "__dateTaken";
    }

    public void addOrderByDate() {
        String str;
        if (SortByType.getOrderBy(this.mParams.getSortBy()) == 1) {
            str = "ASC";
        } else {
            str = "DESC";
        }
        QueryBuilder queryBuilder = this.mQueryBuilder;
        StringBuilder sb2 = new StringBuilder("A.");
        C0086a.z(sb2, getDateOrdered(), " ", str, ", A._id ");
        sb2.append(str);
        queryBuilder.addOrderBy(sb2.toString());
    }

    public Query buildSelectQuery() {
        this.mQueryBuilder.andCondition(this.mFilesTable.getWhere());
        return super.buildSelectQuery();
    }

    public SecFilesTable createFilesTable() {
        return new MpFilesTable(this.mParams);
    }

    public void filterAlbumIDWithFavorites(int i2) {
        C0212a.w("A._id in (", C0086a.i(i2, "select _id from files where is_favorite=1 or bucket_id="), ")", this.mQueryBuilder);
    }

    public void filterAlbumIDs(ArrayList<Integer> arrayList) {
        C0212a.w("A._id in (", "select _id from files where bucket_id in " + CursorHelper.joinIds(arrayList), ")", this.mQueryBuilder);
    }

    public void filterBurstShotBestImage(boolean z) {
        this.mFilesTable.filterGroupMediaBest(true);
        if (z) {
            this.mFilesTable.addGroupMediaCountProjection(this.mQueryBuilder);
        }
    }

    public void filterDate(long j2, long j3) {
        this.mQueryBuilder.addProjection(getDateOrdered(), getDateOrderedColumn());
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition(getDateOrdered() + ">=" + j2 + " AND " + getDateOrdered() + "<=" + j3);
    }

    public void filterFileIds(String str) {
        C0212a.w("A._id IN (", str, ")", this.mQueryBuilder);
    }

    public void filterGpsRange(String str) {
        if (str != null) {
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("A.latitude is not null AND A.latitude >= " + split[0] + " AND A.latitude <=" + split[1] + " AND A.longitude is not null AND A.longitude >= " + split[2] + " AND A.longitude <=" + split[3]);
        }
    }

    public void filterLocation(String str) {
        String str2;
        String escapeString = QueryBuilder.escapeString(str);
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            if (TextUtils.isEmpty(escapeString)) {
                if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
                    this.mQueryBuilder.andCondition("(L.locality is not null or P.poi_name is not null)");
                } else {
                    this.mQueryBuilder.andCondition("(L.locality is not null)");
                }
                QueryBuilder queryBuilder = this.mQueryBuilder;
                queryBuilder.andCondition("(L.locale='" + Locale.getDefault() + "')");
            } else if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
                this.mQueryBuilder.andCondition(j.d("(L.locality in ('", escapeString, "') or P.poi_name in ('", escapeString, "'))"));
            } else {
                C0212a.w("(L.locality in ('", escapeString, "'))", this.mQueryBuilder);
            }
        } else if (TextUtils.isEmpty(escapeString)) {
            this.mQueryBuilder.andCondition("(L.locality is not null or P.poi_name is not null)");
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition("(L.locale='" + Locale.getDefault() + "')");
        } else {
            this.mQueryBuilder.andCondition(j.d("(L.locality in ('", escapeString, "') or P.poi_name in ('", escapeString, "'))"));
        }
        QueryBuilder queryBuilder3 = this.mQueryBuilder;
        if (enabledLocation()) {
            str2 = "1";
        } else {
            str2 = "0";
        }
        queryBuilder3.andCondition(str2);
    }

    public void includeFileIds(String str) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.firstOrCondition("A._id IN (" + str + ")");
    }

    public void onConstruct() {
        super.onConstruct();
        this.mFilesTable.filterGalleryMedia(this.mParams.getMediaTypeFilter());
    }

    public void replaceSubCategoryProjection(String str) {
        this.mQueryBuilder.replaceProjectionByAliasWithValue(QueryBuilder.escapeString(str), "__subCategory");
    }

    public void resetProjectionForGps() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        this.mQueryBuilder.addProjection("A.latitude", "__latitude");
        this.mQueryBuilder.addProjection("A.longitude", "__longitude");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + getDateOrdered(), getDateOrderedColumn());
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(this.mFilesTable.getTableName());
        this.mQueryBuilder.addLeftOuterJoin("location as L", "A.latitude=L.latitude AND A.longitude=L.longitude");
        if (this.USE_GMP) {
            this.mQueryBuilder.addLeftOuterJoin("poicache as P", "A.latitude=P.latitude AND A.longitude=P.longitude");
        } else {
            this.mQueryBuilder.addLeftOuterJoin("poi as P", "A.latitude=P.latitude AND A.longitude=P.longitude");
        }
    }

    public String tag() {
        return "MpPlaceView";
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
