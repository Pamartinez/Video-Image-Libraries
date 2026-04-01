package com.samsung.android.gallery.database.dal.abstraction.table;

import N2.j;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import i4.C0468a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DbTable {
    public static final int TIMEZONE_OFFSET_SECOND = (TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000);
    public static int UNLOADED = -99;
    protected final boolean IS_GTE_Q;
    protected final boolean IS_GTE_R;
    protected final boolean IS_GTE_S;
    protected final boolean IS_GTE_T;
    protected final boolean IS_LT_Q;
    protected final String TAG = tag();
    protected final boolean USE_GMP;
    protected QueryParams mParams;
    protected final QueryBuilder mQueryBuilder;

    public DbTable(QueryParams queryParams) {
        boolean z;
        boolean z3;
        boolean z7;
        this.mParams = queryParams;
        this.mQueryBuilder = new QueryBuilder();
        int osVersion = this.mParams.getOsVersion();
        boolean z9 = false;
        if (osVersion >= 33) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_T = z;
        if (osVersion >= 31) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.IS_GTE_S = z3;
        if (osVersion >= 30) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.IS_GTE_R = z7;
        z9 = osVersion >= 29 ? true : z9;
        this.IS_GTE_Q = z9;
        this.IS_LT_Q = !z9;
        this.USE_GMP = QueryParams.TargetDbTypes.GMP.equals(this.mParams.mTargetDb);
        onConstruct();
        setDefaultTable();
        setDefaultProjection();
        setDefaultCondition();
        if (this.mParams.useDefaultOrder) {
            setDefaultOrder();
        }
        updateByQueryParams();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getProjection$0(ArrayList arrayList) {
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void addOrderByIds(String str, List<String> list) {
        String m = C0212a.m("CASE ", str, " ");
        int i2 = 0;
        for (String k : list) {
            StringBuilder k10 = j.k(" WHEN ", k, " THEN ");
            k10.append(i2);
            m = m.concat(k10.toString());
            i2++;
        }
        this.mQueryBuilder.addOrderBy(m.concat(" END"));
    }

    public void addProjection(String str, String str2) {
        this.mQueryBuilder.addProjection(str, str2);
    }

    public void addProjectionForCursorCount(boolean z) {
        this.mQueryBuilder.addProjection(z ? "count(*)" : "count(distinct A._id)", "__count");
    }

    public Query buildSelectQuery() {
        return new Query(this.mQueryBuilder);
    }

    public void clearOrderBy() {
        this.mQueryBuilder.clearOrderBy();
    }

    public void clearProjection() {
        this.mQueryBuilder.clearProjection();
    }

    public void clearSelection() {
        this.mQueryBuilder.clearSelection();
    }

    public String columnToLowerCase(String str) {
        return C0212a.m("LOWER(", str, ")");
    }

    public String convertStringKeysToArray(Iterable<String> iterable) {
        StringJoiner stringJoiner = new StringJoiner(ArcCommonLog.TAG_COMMA, "(", ")");
        for (String str : iterable) {
            stringJoiner.add("'" + str + "'");
        }
        return stringJoiner.toString();
    }

    public String convertToArray(Iterable<Integer> iterable) {
        StringJoiner stringJoiner = new StringJoiner(ArcCommonLog.TAG_COMMA, "(", ")");
        for (Integer valueOf : iterable) {
            stringJoiner.add(String.valueOf(valueOf));
        }
        return stringJoiner.toString();
    }

    public void distinctIdCountForTimelineDateData() {
        this.mQueryBuilder.replaceProjectionByAlias("count(DISTINCT A._id)", "__count");
    }

    public void excludeFileIds(String str) {
        C0212a.w("A._id not in (", str, ")", this.mQueryBuilder);
    }

    public void filterBucketIds(Collection<Integer> collection, boolean z) {
        String str;
        if (!collection.isEmpty()) {
            if (z) {
                str = " in ";
            } else {
                str = " not in ";
            }
            this.mQueryBuilder.andCondition("(bucket_id".concat(str) + CursorHelper.joinIds(collection) + ")");
        }
    }

    public void filterCloudOnly(boolean z) {
        if (z) {
            this.mQueryBuilder.andCondition("(A.is_cloud == 2 and A.cloud_thumb_path not null)");
        }
    }

    public void filterExceptCloudVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mQueryBuilder.andCondition("A._id not in (select _id from files where is_cloud=2 and media_type is 3)");
        } else if (MediaType.Video.toInt() == UnsafeCast.toInt(str)) {
            this.mQueryBuilder.andCondition("A.is_cloud in (1,3)");
        }
    }

    public void filterLocalOnly(boolean z) {
        if (z) {
            this.mQueryBuilder.andCondition("(A.is_cloud != 2)");
        }
    }

    public void filterMediaType(String str) {
        if (str != null) {
            C0212a.w("(A.media_type = ", str, ")", this.mQueryBuilder);
        }
    }

    public void filterMimeType(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            for (String str : strArr) {
                stringJoiner.add("'" + str + "'");
            }
            this.mQueryBuilder.andCondition("A.mime_type in (" + stringJoiner.toString() + ")");
        }
    }

    public void filterNoLocation() {
        this.mQueryBuilder.andCondition("(A.latitude is null or A.longitude is null)");
    }

    public void filterNullDate() {
        if (this.IS_GTE_Q) {
            this.mQueryBuilder.andCondition("(A.datetime is null or A.datetime = 0)");
        } else {
            this.mQueryBuilder.andCondition("(A.datetaken is null or A.datetaken = 0)");
        }
    }

    public void filterNullFileSize() {
        this.mQueryBuilder.andCondition("(A._size is null or A._size = 0)");
    }

    public void filterNullMediaSize() {
        this.mQueryBuilder.andCondition("(A.width is null or A.width = 0 or A.height is null or A.height = 0)");
    }

    public void filterStorageType() {
        if (this.mParams.isShowCloudOnly()) {
            filterCloudOnly(true);
        } else {
            filterLocalOnly(this.mParams.isShowLocalOnly());
        }
    }

    public void filterWrongDate() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.IS_GTE_Q) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("(A.datetime = A.date_modified*1000 or A.datetime > " + currentTimeMillis + ")");
            return;
        }
        QueryBuilder queryBuilder2 = this.mQueryBuilder;
        queryBuilder2.andCondition("(A.datetaken = A.date_modified*1000 or A.datetaken > " + currentTimeMillis + ")");
    }

    public void filterWrongFileTime() {
        this.mQueryBuilder.andCondition("(A.date_modified > 253402300799)");
    }

    public String getAliasColumnName(String str) {
        return this.mQueryBuilder.getAliasColumnName(str);
    }

    public String[] getProjection() {
        return (String[]) Optional.ofNullable(getProjectionArray()).map(new C0468a(26)).orElse((Object) null);
    }

    public ArrayList<String> getProjectionArray() {
        return this.mQueryBuilder.getProjectionArray();
    }

    public QueryBuilder getQueryBuilder() {
        return this.mQueryBuilder;
    }

    public String getTableName() {
        return this.mQueryBuilder.getTableName();
    }

    public abstract String getTableNameRaw();

    public String getWhere() {
        return this.mQueryBuilder.getWhere();
    }

    public void groupByFileId() {
        this.mQueryBuilder.groupBy("A._id");
    }

    public void groupByMediaType() {
        this.mQueryBuilder.addProjection("A.media_type", "__mediaType");
        this.mQueryBuilder.groupBy("A.media_type");
    }

    public String likeWhereArgs(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        this.mQueryBuilder.is(str2);
        return str + " like ? ";
    }

    public String likeWild(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        return str + " like '%" + str2 + "%'";
    }

    public void limit(int i2) {
        this.mQueryBuilder.limit(Integer.toString(i2));
    }

    public void modifyForAllFilesData(DateType dateType, String str) {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection(String.format(C0086a.l(j.k("strftime(%s, (A.", str, " / 1000)+"), TIMEZONE_OFFSET_SECOND, ", 'unixepoch')"), new Object[]{dateType.getDateFormat()}), "__day");
        this.mQueryBuilder.addProjection("A._id", "_id");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("A." + str, "__dateTaken");
    }

    public void modifyForTimelineDateData(DateType dateType, String str) {
        modifyForTimelineDateData(dateType, str, 10);
    }

    public abstract void onConstruct();

    public void resetProjectionForCursorCount(boolean z) {
        String str;
        if (z) {
            str = "*";
        } else {
            str = "distinct A._id";
        }
        this.mQueryBuilder.clearProjection();
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.addProjection("count(" + str + ")", "__count");
        this.mQueryBuilder.clearOrderBy();
    }

    public void resetProjectionForID() {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
    }

    public abstract void setDefaultCondition();

    public abstract void setDefaultOrder();

    public abstract void setDefaultProjection();

    public abstract void setDefaultTable();

    public void setWhere(String str) {
        this.mQueryBuilder.setWhere(new StringBuilder(str));
    }

    public String tag() {
        return "DbTable";
    }

    public void addProjectionForCursorCount(int i2) {
        this.mQueryBuilder.addProjection("count(distinct A._id)", "__count");
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.andCondition("(A.media_type = " + i2 + ")");
    }

    public void limit(int i2, int i7) {
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.limit(i2 + ArcCommonLog.TAG_COMMA + i7);
    }

    public void modifyForTimelineDateData(DateType dateType, String str, int i2) {
        this.mQueryBuilder.clearProjection();
        this.mQueryBuilder.addProjection("A._id", "__absID");
        if (i2 == 20) {
            this.mQueryBuilder.addProjection(String.format(C0086a.l(new StringBuilder("strftime(%s, (A.date_modified)+"), TIMEZONE_OFFSET_SECOND, ", 'unixepoch')"), new Object[]{dateType.getDateFormat()}), "__day");
            this.mQueryBuilder.addProjection("(A.date_modified*1000)", "__dateTaken");
        } else if (i2 == 70) {
            this.mQueryBuilder.addProjection(String.format(C0086a.l(new StringBuilder("strftime(%s, (A.recent_primary/1000000)+"), TIMEZONE_OFFSET_SECOND, ", 'unixepoch')"), new Object[]{dateType.getDateFormat()}), "__day");
            this.mQueryBuilder.addProjection("(A.recent_primary/1000)", "__dateTaken");
        }
        if (i2 == 80) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            String dateFormat = dateType.getDateFormat();
            queryBuilder.addProjection("strftime(" + dateFormat + ", ifnull(A.localdate,0)/1000, 'unixepoch')", "__day");
            this.mQueryBuilder.addProjection("ifnull(A.localdate,0)", "__dateTaken");
        } else {
            this.mQueryBuilder.addProjection(String.format(C0086a.l(j.k("strftime(%s, (A.", str, " / 1000)+"), TIMEZONE_OFFSET_SECOND, ", 'unixepoch')"), new Object[]{dateType.getDateFormat()}), "__day");
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.addProjection("A." + str, "__dateTaken");
        }
        this.mQueryBuilder.addProjection("count(A._id)", "__count");
        this.mQueryBuilder.addProjection("A.addr", "__Address");
        this.mQueryBuilder.addProjection("A.latitude", "__latitude");
        this.mQueryBuilder.addProjection("A.longitude", "__longitude");
        this.mQueryBuilder.addGroupBy("__day");
    }

    public void updateByQueryParams() {
    }
}
