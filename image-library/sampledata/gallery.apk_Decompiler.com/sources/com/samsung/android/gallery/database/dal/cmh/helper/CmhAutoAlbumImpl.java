package com.samsung.android.gallery.database.dal.cmh.helper;

import A.a;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.AutoAlbumApi;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.database.dal.cmh.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.cmh.table.AutoAlbumPicturesView;
import com.samsung.android.gallery.database.dal.cmh.table.AutoAlbumView;
import com.samsung.android.gallery.database.dal.cmh.table.CmhLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhAutoAlbumImpl extends BaseImpl implements AutoAlbumApi {
    private static final Uri CMH_AUTO_ALBUM_RULE_TABLE_URI = Uri.parse("content://com.samsung.cmh/auto_album_rule");
    private static final Uri CMH_AUTO_ALBUM_TABLE_URI = Uri.parse("content://com.samsung.cmh/auto_album");

    public CmhAutoAlbumImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void updateAutoAlbumTable(long j2, ContentValues contentValues) {
        getContentResolver().update(CMH_AUTO_ALBUM_TABLE_URI, contentValues, a.f("album_id = ", j2), (String[]) null);
    }

    public void addAutoAlbumContents(String str, List<Long> list) {
        for (Long longValue : list) {
            addAutoAlbumContents(str, longValue.longValue());
        }
    }

    public void addAutoAlbumRules(List<Pair<Long, String>> list, long j2, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Pair next : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("rule_type", Integer.valueOf(i2));
            contentValues.put("recommended_id", (Long) next.first);
            if (!TextUtils.isEmpty((CharSequence) next.second)) {
                contentValues.put("private_data", (String) next.second);
            }
            contentValues.put("album_id", Long.valueOf(j2));
            contentValues.put("data_provider", 0);
            arrayList.add(ContentProviderOperation.newInsert(CMH_AUTO_ALBUM_RULE_TABLE_URI).withValues(contentValues).build());
        }
        try {
            getContentResolver().applyBatch(CMH_AUTO_ALBUM_RULE_TABLE_URI.getAuthority(), arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFamilyAlbumContents(String str, long j2) {
        ContentValues c5 = C0086a.c("sec_media_id", str);
        c5.put("album_id", Long.valueOf(j2));
        c5.put("sa_type", "1");
        updateAutoAlbumTable(j2, c5);
    }

    public void changeAutoUpdatingProperty(int i2, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("update_status", Integer.valueOf(i2));
        updateAutoAlbumTable(j2, contentValues);
    }

    public Uri createAutoAlbum(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", str);
        contentValues.put("sec_media_id", str2);
        contentValues.put("oneui_version", Integer.valueOf(Build.VERSION.SEM_PLATFORM_INT));
        contentValues.put("update_status", 0);
        return getContentResolver().insert(CMH_AUTO_ALBUM_TABLE_URI, contentValues);
    }

    public Uri createFamilyAutoAlbum(String str, String str2) {
        ContentValues c5 = C0086a.c("title", str);
        c5.put("oneui_version", Integer.valueOf(Build.VERSION.SEM_PLATFORM_INT));
        c5.put("update_status", 0);
        c5.put("sa_type", 1);
        c5.put("shared_group_id", str2);
        return getContentResolver().insert(CMH_AUTO_ALBUM_TABLE_URI, c5);
    }

    public int deleteAutoAlbum(String str) {
        return getContentResolver().delete(CMH_AUTO_ALBUM_TABLE_URI, C0212a.m("album_id IN (", str, ")"), (String[]) null);
    }

    public void deleteFamilyAutoAlbum() {
        try {
            Cursor rawQuery = new CmhQueryExecutor().rawQuery("delete from auto_album where shared_group_id is not null", "deleteFamilyAutoAlbum");
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("deleteFamilyAutoAlbum failed. e="), this.TAG);
        }
    }

    public int getAlbumCount(int i2, MediaType mediaType) {
        AutoAlbumPicturesView autoAlbumPicturesView = new AutoAlbumPicturesView(this.mParams);
        autoAlbumPicturesView.filterAlbumId(i2);
        autoAlbumPicturesView.filterMediaType(String.valueOf(mediaType.toInt()));
        autoAlbumPicturesView.resetProjectionForCursorCount(false);
        Query buildSelectQuery = autoAlbumPicturesView.buildSelectQuery();
        Cursor cursor = getCursor(buildSelectQuery, "getAutoAlbumCount#" + mediaType + "(" + i2 + ")");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i7 = cursor.getInt(0);
                    cursor.close();
                    return i7;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return 0;
        throw th;
    }

    public Cursor getAutoAlbumCursor() {
        try {
            AutoAlbumView autoAlbumView = new AutoAlbumView(this.mParams);
            autoAlbumView.addAutoAlbumCoverData();
            autoAlbumView.groupByAlbum();
            return getCursor(autoAlbumView.buildSelectQuery(), "getAutoAlbumCursor");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getAutoAlbumCursor failed. e="), this.TAG);
            return null;
        }
    }

    public Cursor getAutoAlbumIds(String str, boolean z) {
        String str2;
        try {
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addTable("auto_album_rule");
            queryBuilder.addProjection("distinct(album_id)");
            StringBuilder sb2 = new StringBuilder("recommended_id in (");
            sb2.append(str);
            sb2.append(") AND rule_type = ");
            if (z) {
                str2 = "1";
            } else {
                str2 = "2";
            }
            sb2.append(str2);
            queryBuilder.andCondition(sb2.toString());
            return getCursor(new Query(queryBuilder), "getAutoAlbumIds");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getAutoAlbumIds failed. e="), this.TAG);
            return null;
        }
    }

    public Cursor getAutoAlbumPicturesCursor(int i2, int i7) {
        try {
            AutoAlbumPicturesView autoAlbumPicturesView = new AutoAlbumPicturesView(this.mParams);
            autoAlbumPicturesView.filterAlbumId(i2);
            autoAlbumPicturesView.setSortBy(i7);
            return getCursor(autoAlbumPicturesView.buildSelectQuery(), "getAutoAlbumPicturesCursor");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getAutoAlbumPicturesCursor failed. e="), this.TAG);
            return null;
        }
    }

    public Cursor getAutoAlbumPicturesDateCursor(int i2, int i7) {
        AutoAlbumPicturesView autoAlbumPicturesView = new AutoAlbumPicturesView(this.mParams);
        autoAlbumPicturesView.filterAlbumId(i2);
        autoAlbumPicturesView.modifyForTimelineDateData(DateType.DAY, IParameterKey.DATE_TAKEN, i7);
        return getCursor(QueryUtils.updateQueryForMultipleLocations(autoAlbumPicturesView.buildSelectQuery(), autoAlbumPicturesView.getSortByForDateCursor(i7), (SecLocationView) new CmhLocationView(this.mParams)), a.d(i2, i7, "AutoAlbum(", GlobalPostProcInternalPPInterface.SPLIT_REGEX, ")"));
    }

    public Cursor getAutoUpdateStateCursor(int i2) {
        QueryBuilder g = C0212a.g("auto_album");
        g.andCondition("album_id=" + i2);
        g.addProjection("update_status");
        return getCursor(new Query(g), "getAutoUpdateStateCursor");
    }

    public Cursor getCreatureRuleCursor(long j2) {
        return getContentResolver().query(CMH_AUTO_ALBUM_RULE_TABLE_URI, new String[]{"rule_type", "recommended_id"}, a.f("album_id=", j2), (String[]) null, (String) null);
    }

    public Cursor getFamilyAutoAlbumIdCursor(String str) {
        try {
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addTable("auto_album");
            queryBuilder.andCondition("sa_type=1");
            queryBuilder.andCondition("shared_group_id like '" + str + "'");
            queryBuilder.addProjection("album_id");
            return getCursor(new Query(queryBuilder), "getFamilyAutoAlbumIdCursor");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getFamilyAutoAlbumIdCursor failed. e="), this.TAG);
            return null;
        }
    }

    public Cursor getSuggestedContentsRuleCursor(long j2) {
        try {
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addTable("auto_album");
            queryBuilder.andCondition("album_id = " + j2);
            queryBuilder.addProjection("user_update_type");
            return getCursor(new Query(queryBuilder), "getSuggestedContentsRuleCursor");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getSuggestedContentsRuleCursor failed. e="), this.TAG);
            return null;
        }
    }

    public boolean isAutoAlbumAlreadyExist(String str) {
        Throwable th;
        Cursor cursor = this.mQueryExecutor.getCursor(CMH_AUTO_ALBUM_TABLE_URI, new String[]{"count(*)"}, C0212a.m("title='", str, "' COLLATE NOCASE"), (String[]) null, (String) null, (String) null);
        boolean z = false;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    if (cursor.getInt(0) > 0) {
                        z = true;
                    }
                    cursor.close();
                    return z;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return false;
        throw th;
    }

    public void removeAutoAlbumContent(long j2, long j3) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = CMH_AUTO_ALBUM_TABLE_URI;
        StringBuilder j8 = j.j(j3, "album_id = ", " AND sec_media_id = ");
        j8.append(j2);
        contentResolver.delete(uri, j8.toString(), (String[]) null);
    }

    public void removeAutoAlbumContents(ArrayList<Long> arrayList, long j2) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ContentProviderOperation.newDelete(CMH_AUTO_ALBUM_TABLE_URI).withSelection("album_id = ? AND sec_media_id = ?", new String[]{Long.toString(j2), Long.toString(it.next().longValue())}).build());
        }
        getContentResolver().applyBatch(CMH_AUTO_ALBUM_TABLE_URI.getAuthority(), arrayList2);
    }

    public void removeAutoAlbumRules(List<Long> list, long j2, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Long l : list) {
            ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(CMH_AUTO_ALBUM_RULE_TABLE_URI);
            arrayList.add(newDelete.withSelection("album_id = " + j2 + " AND recommended_id = " + l + " AND rule_type = " + i2, (String[]) null).build());
        }
        try {
            getContentResolver().applyBatch(CMH_AUTO_ALBUM_RULE_TABLE_URI.getAuthority(), arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameAutoAlbum(String str, long j2) {
        updateAutoAlbumTable(j2, C0086a.c("title", str));
    }

    public String tag() {
        return "AutoAlbumApi";
    }

    public void updateSuggestedContentsRule(int i2, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_update_type", Integer.valueOf(i2));
        updateAutoAlbumTable(j2, contentValues);
    }

    public void addAutoAlbumContents(String str, long j2) {
        updateAutoAlbumTable(j2, C0086a.c("sec_media_id", str));
    }

    public int[] getAlbumCount(int i2) {
        return new int[]{getAlbumCount(i2, MediaType.Image), getAlbumCount(i2, MediaType.Video)};
    }

    public Cursor getAutoAlbumPicturesCursor(Collection<Integer> collection, int i2) {
        try {
            AutoAlbumPicturesView autoAlbumPicturesView = new AutoAlbumPicturesView(this.mParams);
            autoAlbumPicturesView.filterAlbumId(collection);
            if (i2 != 0) {
                autoAlbumPicturesView.setSortBy(i2);
            }
            return getCursor(autoAlbumPicturesView.buildSelectQuery(), "getAutoAlbumPicturesCursor");
        } catch (Exception e) {
            j.s(e, new StringBuilder("getAutoAlbumPicturesCursor failed. e="), this.TAG);
            return null;
        }
    }
}
