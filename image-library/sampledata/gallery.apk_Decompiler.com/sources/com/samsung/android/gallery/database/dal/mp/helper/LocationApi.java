package com.samsung.android.gallery.database.dal.mp.helper;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.impl.ImplHelper;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpPlaceView;
import com.samsung.android.gallery.database.dal.mp.table.MpPoiTable;
import com.samsung.android.gallery.database.dal.mp.table.MpPoiView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationApi extends BaseImpl {
    public LocationApi() {
        super(new QueryParams());
    }

    private MpPlaceView createPlaceView(String str) {
        MpPlaceView mpPlaceView = new MpPlaceView(this.mParams);
        mpPlaceView.filterLocation(str);
        mpPlaceView.filterBurstShotBestImage(true);
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            mpPlaceView.filterFileIds(this.mParams.getFileIds());
        }
        mpPlaceView.groupByFileId();
        mpPlaceView.replaceSubCategoryProjection(str);
        if (!TextUtils.isEmpty(this.mParams.getIncludeFileIds())) {
            mpPlaceView.includeFileIds(this.mParams.getIncludeFileIds());
        }
        if (this.mParams.getAlbumIdCount() > 0) {
            if (this.mParams.isIncludeFavorite()) {
                mpPlaceView.filterAlbumIDWithFavorites(this.mParams.getAlbumIdList().get(0).intValue());
                return mpPlaceView;
            }
            mpPlaceView.filterAlbumIDs(this.mParams.getAlbumIdList());
        }
        return mpPlaceView;
    }

    private boolean exposePoi() {
        return false;
    }

    private Query getLocationQuery(long j2) {
        return getLocationQuery(j2, (String) null);
    }

    private SecFilesTable getMapTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterByMap();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByMap();
        mpFilesTable.filterGalleryMedia();
        mpFilesTable.filterGroupMediaBest(true);
        return mpFilesTable;
    }

    public ContentValues createContentValues(double d, double d2, String str, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("latitude", Double.valueOf(d));
        contentValues.put("longitude", Double.valueOf(d2));
        if (this.IS_LT_Q) {
            if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll) || TextUtils.isEmpty(str)) {
                contentValues.putNull("addr");
            } else {
                contentValues.put("addr", str);
            }
            if (j2 > 0) {
                contentValues.put(IParameterKey.SIZE, Long.valueOf(j2));
            }
        }
        return contentValues;
    }

    public String getFileIdColumnName() {
        return "_id";
    }

    public Cursor getGpsFileCursor() {
        SecFilesTable mapTable = getMapTable();
        mapTable.addOrderByDate();
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            mapTable.filterIds(this.mParams.getFileIds());
        }
        return getCursor(mapTable.buildSelectQuery(), "GpsFile");
    }

    public Cursor getGpsFileGpsCursor() {
        SecFilesTable mapTable = getMapTable();
        mapTable.clearProjection();
        mapTable.addOrderByDate();
        mapTable.addProjection("A._id", "__absID");
        mapTable.addProjection("A.latitude", "__latitude");
        mapTable.addProjection("A.longitude", "__longitude");
        mapTable.addProjection("A." + getDateTakenColumnName(), "__dateTaken");
        if (!TextUtils.isEmpty(this.mParams.getFileIds())) {
            mapTable.filterIds(this.mParams.getFileIds());
        }
        return getCursor(mapTable.buildSelectQuery(), "GpsFileGps");
    }

    public Cursor getLatestLocationData(long j2) {
        SecFilesTable mapTable = getMapTable();
        if (j2 > 0) {
            QueryBuilder queryBuilder = mapTable.getQueryBuilder();
            queryBuilder.andCondition(mapTable.getColumnDateTaken() + ">=" + j2);
        }
        mapTable.getQueryBuilder().andCondition("A.addr not null AND A.addr not like 'null|%'");
        mapTable.setIndex("location_datetime_idx");
        mapTable.limit(1);
        return getCursor(mapTable.buildSelectQuery(), "getLatestLocationInfo");
    }

    public Bundle getLatestLocationInfo(long j2) {
        long j3;
        Cursor cursor;
        Throwable th;
        long j8 = j2;
        long currentTimeMillis = System.currentTimeMillis();
        SecFilesTable mapTable = getMapTable();
        mapTable.clearProjection();
        mapTable.addProjection("A._id", "__absID");
        mapTable.addProjection(mapTable.getColumnDateTaken(), "__dateTaken");
        mapTable.addProjection("A.latitude", "__latitude");
        mapTable.addProjection("A.longitude", "__longitude");
        mapTable.addProjection("A._size", "__size");
        mapTable.addProjection("A.date_modified", "__dateModified");
        mapTable.addProjection("A._data", "__absPath");
        if (j8 > 0) {
            QueryBuilder queryBuilder = mapTable.getQueryBuilder();
            StringBuilder sb2 = new StringBuilder();
            j3 = currentTimeMillis;
            sb2.append(mapTable.getColumnDateTaken());
            sb2.append(">=");
            sb2.append(j8);
            queryBuilder.andCondition(sb2.toString());
        } else {
            j3 = currentTimeMillis;
        }
        mapTable.getQueryBuilder().andCondition("A.addr not null AND A.addr not like 'null|%'");
        mapTable.setIndex("location_datetime_idx");
        mapTable.limit(1);
        try {
            cursor = getCursor(mapTable.buildSelectQuery(), "getLatestLocationInfo");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble("__latitude", cursor.getDouble(cursor.getColumnIndex("__latitude")));
                    bundle.putDouble("__longitude", cursor.getDouble(cursor.getColumnIndex("__longitude")));
                    long j10 = cursor.getLong(cursor.getColumnIndex("__absID"));
                    long j11 = cursor.getLong(cursor.getColumnIndex("__dateTaken"));
                    bundle.putLong("__absID", j10);
                    bundle.putLong("__dateTaken", j11);
                    bundle.putLong("__dateModified", cursor.getLong(cursor.getColumnIndex("__dateModified")));
                    bundle.putLong("__size", cursor.getLong(cursor.getColumnIndex("__size")));
                    bundle.putString("__absPath", cursor.getString(cursor.getColumnIndex("__absPath")));
                    String str = this.TAG;
                    Log.d(str, "getLatestLocationInfo" + Logger.vt(Long.valueOf(j10), Long.valueOf(j11), Long.valueOf(j8), Long.valueOf(j3)) + "");
                    cursor.close();
                    return bundle;
                }
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatestLocationInfo failed. e="), this.TAG);
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public Cursor getLocationClusterCursor() {
        return getCursor(getLocationQuery(-1, this.mParams.getNames()), "Location");
    }

    public Cursor getLocationCursor() {
        return getCursor(getLocationQuery(-1), "Location");
    }

    public Cursor getLocationCursorByFileId(long j2) {
        MpLocationView mpLocationView = new MpLocationView(this.mParams);
        mpLocationView.filterFileId(j2);
        return getCursor(mpLocationView.buildSelectQuery(), "getLocationCursorByFileId");
    }

    public Cursor getLocationFileCursor(String str) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.addOrderByDate();
        Query buildSelectQuery = createPlaceView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Location : " + str);
    }

    public Cursor getLocationFileDateCursor(String str) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        createPlaceView.distinctIdCountForTimelineDateData();
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(createPlaceView.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "Location date : " + str);
    }

    public Cursor getLocationFileGpsCursor(String str) {
        return getLocationFileGpsCursor(str, -1, -1);
    }

    public Cursor getLocationFileIdsCursor(String str) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.addOrderByDate();
        createPlaceView.resetProjectionForID();
        Query buildSelectQuery = createPlaceView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Location : " + str);
    }

    public Cursor getLocationFileRealRatioCursor(String str) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.addOrderByDate();
        return ImplHelper.getRealRatioMergedCursor((BaseView) createPlaceView, this.mQueryExecutor);
    }

    public Cursor getPoiTableCursor(double d, double d2) {
        MpPoiTable mpPoiTable = new MpPoiTable(this.mParams);
        mpPoiTable.filterLocation(d, d2);
        return getCursor(mpPoiTable.buildSelectQuery(), "LOCATION_POI_VIEW");
    }

    public String tag() {
        return "LocationApi";
    }

    public int updateLocation(Uri uri, long j2, double d, double d2) {
        return updateLocation(uri, j2, d, d2, (String) null, 0);
    }

    public LocationApi(QueryParams queryParams) {
        super(queryParams);
    }

    private Query getLocationQuery(long j2, String str) {
        MpLocationView mpLocationView = new MpLocationView(this.mParams);
        mpLocationView.groupByLocality();
        int i2 = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
        if (i2 != 0) {
            mpLocationView.filterFileId(j2);
        }
        mpLocationView.filterBurstShotBestImage();
        Features features = Features.IS_UPSM;
        mpLocationView.filterLocalOnly(Features.isEnabled(features));
        mpLocationView.getQueryBuilder().replaceProjectionByAliasWithValue("Location", "__mainCategory");
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster) && !TextUtils.isEmpty(str)) {
            mpLocationView.filterClusterNames(str);
        }
        mpLocationView.getQueryBuilder().addProjection("count(A._id)", "__count");
        Query buildSelectQuery = mpLocationView.buildSelectQuery();
        if (exposePoi()) {
            MpPoiView mpPoiView = new MpPoiView(this.mParams);
            mpPoiView.groupByPoi();
            if (i2 != 0) {
                mpPoiView.filterFileId(j2);
            }
            mpPoiView.filterBurstShotBestImage();
            mpPoiView.filterLocalOnly(Features.isEnabled(features));
            mpPoiView.getQueryBuilder().replaceProjectionByAliasWithValue("Location", "__mainCategory");
            mpPoiView.getQueryBuilder().addProjection("count(A._id)", "__count");
            buildSelectQuery.unionAll(mpPoiView.buildSelectQuery());
        }
        buildSelectQuery.getQueryBuilder().addOrderBy("__count DESC");
        return buildSelectQuery;
    }

    public Cursor getLocationFileGpsCursor(String str, long j2, long j3) {
        return getLocationFileGpsCursor(str, j2, j3, (String) null);
    }

    public int updateLocation(Uri uri, long j2, double d, double d2, String str, long j3) {
        return this.mQueryExecutor.getContentResolver().update(uri, createContentValues(d, d2, str, j3), getFileIdColumnName() + "=" + j2, (String[]) null);
    }

    public Cursor getLocationFileGpsCursor(String str, long j2, long j3, String str2) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.resetProjectionForGps();
        createPlaceView.addOrderByDate();
        if (j2 > 0 && j3 > 0 && j2 != j3) {
            createPlaceView.filterDate(Math.min(j2, j3), Math.max(j2, j3));
        }
        if (!TextUtils.isEmpty(str2)) {
            createPlaceView.filterGpsRange(str2);
        }
        Query buildSelectQuery = createPlaceView.buildSelectQuery();
        return getCursor(buildSelectQuery, "LocationGps : " + str);
    }

    public Cursor getLocationFileCursor(String str, long j2, long j3, String str2) {
        MpPlaceView createPlaceView = createPlaceView(str);
        createPlaceView.addOrderByDate();
        if (j2 > 0 && j3 > 0 && j2 != j3) {
            createPlaceView.filterDate(Math.min(j2, j3), Math.max(j2, j3));
        }
        if (!TextUtils.isEmpty(str2)) {
            createPlaceView.filterGpsRange(str2);
        }
        Query buildSelectQuery = createPlaceView.buildSelectQuery();
        return getCursor(buildSelectQuery, "Location : " + str);
    }
}
