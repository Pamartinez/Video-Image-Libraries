package com.samsung.android.gallery.database.dal.util;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class QueryUtils {
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCount(com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor r1, com.samsung.android.gallery.database.dal.abstraction.query.Query r2) {
        /*
            java.lang.String r0 = "getMediaCount"
            android.database.Cursor r1 = r1.getCursor(r2, r0)
            if (r1 == 0) goto L_0x0029
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0029
        L_0x000e:
            java.lang.String r2 = "__count"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x001f }
            int r2 = r1.getInt(r2)     // Catch:{ all -> 0x001f }
            boolean r0 = r1.moveToNext()     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x000e
            goto L_0x002a
        L_0x001f:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0028:
            throw r2
        L_0x0029:
            r2 = 0
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.util.QueryUtils.getCount(com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor, com.samsung.android.gallery.database.dal.abstraction.query.Query):int");
    }

    private static String getGroupConcatIdAndDateTime() {
        return "group_concat(distinct(A._id)||':'||IFNULL(A.datetime, A.datetaken))";
    }

    private static QueryBuilder getInBuilder(Query query, SecLocationView secLocationView, boolean z) {
        QueryBuilder queryBuilder = query.getQueryBuilder();
        String innerJoin = queryBuilder.getInnerJoin();
        if (innerJoin == null || !innerJoin.contains("location as L")) {
            queryBuilder.addLeftOuterJoin(secLocationView.getTableNameRaw() + " as L", secLocationView.getFilesJoinCondition());
        }
        queryBuilder.replaceProjectionByAlias("coalesce(L.locality,L.sub_admin_area,L.admin_area,L.country_name)  ", "location");
        queryBuilder.clearOrderBy();
        queryBuilder.addOrderBy("count(location) DESC, location");
        queryBuilder.addGroupBy("location");
        if (z) {
            queryBuilder.addProjection(getGroupConcatIdAndDateTime(), "__clusterIdsAndDateTime");
        }
        return queryBuilder;
    }

    public static int[] getMediaCount(RawQueryExecutor rawQueryExecutor, Query query) {
        int i2;
        int i7;
        int[] iArr = {0, 0};
        Cursor cursor = rawQueryExecutor.getCursor(query, "getMediaCount");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("__mediaType");
                    int columnIndex2 = cursor.getColumnIndex("__count");
                    do {
                        if (columnIndex < 0) {
                            i2 = -1;
                        } else {
                            i2 = cursor.getInt(columnIndex);
                        }
                        if (columnIndex2 < 0) {
                            i7 = 0;
                        } else {
                            i7 = cursor.getInt(columnIndex2);
                        }
                        if (i2 == MediaType.Image.toInt()) {
                            iArr[0] = i7;
                        } else if (i2 == MediaType.Video.toInt()) {
                            iArr[1] = i7;
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return iArr;
        throw th;
    }

    public static Query updateQueryForAllDates(Query query) {
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(query.getQueryBuilder().build());
        addFromSelect.addProjection("__day");
        addFromSelect.addProjection("count(*)", "__count");
        addFromSelect.addProjection("group_concat(_id)", "__fileIds");
        addFromSelect.groupBy("__day");
        addFromSelect.addOrderBy("__dateTaken desc");
        return new Query(addFromSelect);
    }

    public static Query updateQueryForMultipleLocations(Query query, SecLocationView secLocationView) {
        return updateQueryForMultipleLocations(query, "__dateTaken desc", secLocationView);
    }

    public static Query updateQueryForMultipleLocations(Query query, SecLocationView secLocationView, boolean z) {
        return updateQueryForMultipleLocations(query, "__dateTaken desc", secLocationView, z);
    }

    public static Query updateQueryForMultipleLocations(Query query, String str, SecLocationView secLocationView) {
        return updateQueryForMultipleLocations(query, str, secLocationView, false);
    }

    public static Query updateQueryForMultipleLocations(Query query, String str, SecLocationView secLocationView, boolean z) {
        if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            secLocationView.addFileJoingCondition("L.tag_type=2");
        }
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(getInBuilder(query, secLocationView, z).build());
        addFromSelect.addProjection("sum(__count)", "__count");
        addFromSelect.addProjection("group_concat(distinct location)", "__Address");
        addFromSelect.addProjection("group_concat(__latitude)", "__latitudeList");
        addFromSelect.addProjection("group_concat(__longitude)", "__longitudeList");
        addFromSelect.addProjection("__absID,__day,__dateTaken");
        if (z) {
            addFromSelect.addProjection("group_concat(__clusterIdsAndDateTime)", "__clusterIdsDateTimeConcat");
        }
        addFromSelect.groupBy("__day");
        addFromSelect.addOrderBy(str);
        return new Query(addFromSelect);
    }
}
