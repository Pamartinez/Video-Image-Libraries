package com.samsung.android.gallery.module.mdebase.db;

import A.a;
import N2.j;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.mdebase.constants.MdeConstants;
import com.samsung.android.gallery.module.mdebase.db.Table.MdeGallerySettingTable;
import com.samsung.android.gallery.module.mdebase.db.Table.MdeSharedItemTable;
import com.samsung.android.gallery.module.mdebase.db.Table.MdeSpaceTable;
import com.samsung.android.gallery.support.utils.CursorCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.datasync.GallerySetting;
import com.samsung.android.sdk.mobileservice.social.datasync.provider.DataSyncContract;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupMemberContract;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.share.provider.SharedItemContract;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeDatabase {
    private static final Uri GROUP_CONTENT_URI = GroupContract.Group.CONTENT_URI.buildUpon().appendPath("group").build();
    private static final Uri GROUP_MEMBERS_CONTENT_URI = GroupMemberContract.GroupMember.CONTENT_URI.buildUpon().appendPath("group").build();
    private static final Uri SPACE_CONTENT_URI = SpaceContract.Space.CONTENT_URI.buildUpon().appendPath("space").build();
    private static final Uri SPACE_ITEM_CONTENT_URI = SharedItemContract.Item.CONTENT_URI.buildUpon().appendPath("space").build();
    private final MdeGallerySettingTable mMdeGallerySettingTable = new MdeGallerySettingTable(new QueryParams());
    private final MdeSharedItemTable mMdeSharedItemTable = new MdeSharedItemTable(new QueryParams());
    private final MdeSpaceTable mMdeSpaceTable = new MdeSpaceTable(new QueryParams());
    protected final QueryExecutor mQueryExecutor = new QueryExecutor();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RawQueryUriHolder {
        static final Uri group = Uri.parse("content://com.samsung.android.mobileservice.social.group/raw_query");
        static final Uri space = Uri.parse("content://com.samsung.android.mobileservice.social.share/raw_query");
    }

    private String getOrderBy() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_SHARING_SORT_BY) {
            return "S.contents_update_time DESC";
        }
        int sharingOrder = getSharingOrder();
        if (sharingOrder == 21) {
            return "S.contents_update_time ASC";
        }
        if (sharingOrder == 51) {
            return "S.media_count ASC";
        }
        if (sharingOrder != 52) {
            return "S.contents_update_time DESC";
        }
        return "S.media_count DESC";
    }

    private Cursor getSharedItemCountCursor(String str, boolean z) {
        String str2;
        if (z) {
            str2 = "(mime_type like 'image/%')";
        } else {
            str2 = "(mime_type like 'video/%')";
        }
        return getCursor(SPACE_ITEM_CONTENT_URI.buildUpon().appendPath(str).build(), new String[]{"count(*)"}, str2, (String[]) null, (String) null, "getSharedItemCountCursor");
    }

    private int getSharingOrder() {
        int i2;
        if (Features.isEnabled(Features.SUPPORT_SORT_SHARINGS)) {
            i2 = getSharingOrderFromDataSync();
        } else {
            i2 = -1;
        }
        if (i2 > 0) {
            return i2;
        }
        return SortByType.getSharingOrder();
    }

    private int getSharingOrderFromDataSync() {
        Exception exc;
        Throwable th;
        int i2 = -1;
        try {
            Cursor cursor = getCursor(DataSyncContract.GallerySettingData.CONTENT_URI.buildUpon().appendPath(GallerySetting.SYNC_SERVICE_NAME).build(), this.mMdeGallerySettingTable.getProjection(), (String) null, (String[]) null, (String) null, "getSharingOrderFromDataSync");
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        i2 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSyncContract.GallerySettingData.SORT_TYPE)));
                        GalleryPreference.getInstance().saveState(PreferenceName.SORT_BY_SHARING, i2);
                    }
                } catch (Exception e) {
                    exc = e;
                    i2 = -1;
                    a.s(exc, new StringBuilder("getSharingOrderFromDataSync failed. e="), "MdeDatabase");
                    return i2;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            if (cursor == null) {
                return i2;
            }
            cursor.close();
            return i2;
            throw th;
        } catch (Exception e7) {
            exc = e7;
            a.s(exc, new StringBuilder("getSharingOrderFromDataSync failed. e="), "MdeDatabase");
            return i2;
        }
    }

    private String getSortOrder(String str) {
        if (!Features.isEnabled(Features.SUPPORT_SHARED_SORT)) {
            return "modifiedTime DESC";
        }
        int loadSharedSortBy = GalleryPreference.getInstance().loadSharedSortBy(str, 20);
        StringBuilder sb2 = new StringBuilder();
        if (SortByType.getSortBy(loadSharedSortBy) != 10) {
            sb2.append("modifiedTime");
        } else {
            sb2.append("date_taken");
        }
        if (SortByType.getOrderBy(loadSharedSortBy) != 1) {
            sb2.append(" DESC");
        } else {
            sb2.append(" ASC");
        }
        return sb2.toString();
    }

    private Cursor getSpacesCursor(boolean z, boolean z3) {
        String str;
        String str2;
        String str3;
        String str4 = "";
        if (z) {
            str = "my_usage > 0";
        } else {
            str = str4;
        }
        if (z3) {
            str2 = str4;
        } else {
            str2 = " and S.groupId not like 'SAFM%'";
        }
        String concat = str.concat(str2);
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            str3 = buildSpacesQuery(concat, "case when S.groupId like 'SAFM%' then 0 else 1 end, " + getOrderBy());
        } else {
            str3 = buildSpacesQuery(concat, getOrderBy());
        }
        if (z) {
            str4 = "(Usage)";
        }
        return queryShareRaw(str3, "getSpacesCursor".concat(str4));
    }

    private String getTrashCondition() {
        return "is_trashed=1 AND expiry_at > " + System.currentTimeMillis();
    }

    private Cursor queryGroupRaw(String str, String str2) {
        return this.mQueryExecutor.getCursor(RawQueryUriHolder.group, (String[]) null, str, (String[]) null, (String) null, str2);
    }

    private Cursor queryShareRaw(String str, String str2) {
        return this.mQueryExecutor.getCursor(RawQueryUriHolder.space, (String[]) null, str, (String[]) null, (String) null, str2);
    }

    public String buildSpacesQuery(String str, String str2) {
        String str3;
        StringBuilder sb2 = new StringBuilder("select S._id, S.spaceId, S.title, S.memo, S.owner, S.is_owned_by_me, S.groupId, S.unread_count, S.weblink_url, S.weblink_expired_time, S.my_usage, S.media_count, S.meta_data, S.thumbnail_local_path, ifnull(CASE WHEN S.cover_id IS NULL THEN null ELSE (select thumbnail_local_path || '|' || mime_type || '|' || itemId || '|' || meta_data from item where itemId = S.cover_id) END, (select thumbnail_local_path || '|' || mime_type || '|' || itemId || '|' || meta_data from item  where spaceId = S.spaceId ORDER BY modifiedTime DESC limit 1)) AS _cover_item, S.cover_id, S.cover_rect from (select *,CASE WHEN coalesce(meta_data, '') = '' THEN null ELSE SUBSTR(meta_data, 14, INSTR(meta_data, ';c') - 14) END AS cover_id,SUBSTR(meta_data, INSTR(meta_data, 'o;') + 2) AS cover_rect from space) S");
        String str4 = "";
        if (TextUtils.isEmpty(str)) {
            str3 = str4;
        } else {
            str3 = C0212a.l(" where ", str);
        }
        sb2.append(str3);
        if (!TextUtils.isEmpty(str2)) {
            str4 = C0212a.l(" order by ", str2);
        }
        sb2.append(str4);
        return sb2.toString();
    }

    public Cursor getCursor(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        return this.mQueryExecutor.getCursor(uri, strArr, str, strArr2, str2, str3);
    }

    public int[] getFamilySharedTrashCount() {
        long currentTimeMillis = System.currentTimeMillis();
        int[] iArr = {0, 0};
        Cursor queryShareRaw = queryShareRaw(C0212a.m("select M.media_type, count(M.media_type) from (", "select (case when mime_type like 'image/%' then 1 when mime_type like 'video/%' then 3 else 0 end) as media_type from gallery_item where " + getTrashCondition(), ") as M group by M.media_type"), "getFamilySharedTrashCount");
        if (queryShareRaw != null) {
            try {
                if (queryShareRaw.moveToFirst()) {
                    do {
                        int i2 = queryShareRaw.getInt(0);
                        if (i2 == 1) {
                            iArr[0] = queryShareRaw.getInt(1);
                        } else if (i2 == 3) {
                            iArr[1] = queryShareRaw.getInt(1);
                        }
                    } while (queryShareRaw.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (queryShareRaw != null) {
            queryShareRaw.close();
        }
        a.A(new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Long.valueOf(currentTimeMillis)}, new StringBuilder("getFamilySharedTrashCount"), "MdeDatabase");
        return iArr;
        throw th;
    }

    public Cursor getFamilySharedTrashCursor() {
        ArrayList<String> projectionArray = this.mMdeSharedItemTable.getProjectionArray();
        projectionArray.add(BundleKey.EXPIRY_AT);
        return queryShareRaw(C0212a.p(j.k("select ", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, projectionArray), " from gallery_item where "), getTrashCondition(), " order by expiry_at DESC"), "getFamilySharedTrashCursor");
    }

    public Cursor getFamilySharedTrashStorage() {
        return queryShareRaw("select sum(size) from gallery_item where " + getTrashCondition(), "getFamilySharedTrashStorage");
    }

    public String getGroupIdInSpace(String str) {
        Cursor queryShareRaw = queryShareRaw(C0212a.m("select groupId from space where spaceId is '", str, "'"), "getGroupIdInSpace");
        if (queryShareRaw != null) {
            try {
                if (queryShareRaw.moveToFirst()) {
                    String string = queryShareRaw.getString(0);
                    queryShareRaw.close();
                    return string;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (queryShareRaw == null) {
            return null;
        }
        queryShareRaw.close();
        return null;
        throw th;
    }

    public Pair<Long, Long> getPeriodInSpace(String str) {
        Cursor queryShareRaw = queryShareRaw(C0212a.m("select min(date_taken), max(date_taken) from gallery_item where spaceId is '", str, "'"), "getPeriodInSpace");
        if (queryShareRaw != null) {
            try {
                if (queryShareRaw.moveToFirst()) {
                    Pair<Long, Long> pair = new Pair<>(Long.valueOf(queryShareRaw.getLong(0)), Long.valueOf(queryShareRaw.getLong(1)));
                    queryShareRaw.close();
                    return pair;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (queryShareRaw != null) {
            queryShareRaw.close();
        }
        return new Pair<>(0L, 0L);
        throw th;
    }

    public List<String> getRemainedItemInstantMetaDataList() {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        Cursor queryShareRaw = queryShareRaw("select instant_meta_data from gallery_item where instant_meta_data is not null", "getRemainedItemInstantMetaDataList");
        if (queryShareRaw != null) {
            try {
                if (queryShareRaw.moveToFirst()) {
                    do {
                        arrayList.add(queryShareRaw.getString(0));
                    } while (queryShareRaw.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (queryShareRaw != null) {
            queryShareRaw.close();
        }
        a.A(new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("getRemainedItemInstantMetaDataList"), "MdeDatabase");
        return arrayList;
        throw th;
    }

    public Cursor getSharedGroupCursor() {
        if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY)) {
            return queryGroupRaw("select * from gallery_group_member_view group by groupId", "getSharedGroupCursor");
        }
        return getCursor(GroupContract.Group.CONTENT_URI.buildUpon().appendPath(GroupContract.Group.PATTERN_WITH_DUMMY_FAMILY).build(), (String[]) null, (String) null, (String[]) null, (String) null, "getSharedGroupCursor(legacy)");
    }

    public Cursor getSharedGroupMembersCursor(String str) {
        return getCursor(GROUP_MEMBERS_CONTENT_URI.buildUpon().appendPath(str).build(), (String[]) null, "status = ?", new String[]{String.valueOf(2)}, (String) null, "getSharedGroupMembersCursor");
    }

    public int[] getSharedItemCount(String str) {
        Cursor sharedItemCountCursor;
        Cursor sharedItemCountCursor2;
        int[] iArr = {0, 0};
        long currentTimeMillis = System.currentTimeMillis();
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            Cursor queryShareRaw = queryShareRaw(C0212a.m("select M.media_type, count(M.media_type) from (", C0212a.m("select (case when mime_type like 'image/%' then 1 when mime_type like 'video/%' then 3 else 0 end) as media_type from item where spaceId is '", str, "'"), ") as M group by M.media_type"), "getSharedItemCount");
            if (queryShareRaw != null) {
                try {
                    if (queryShareRaw.moveToFirst()) {
                        do {
                            int i2 = queryShareRaw.getInt(0);
                            if (i2 == 1) {
                                iArr[0] = queryShareRaw.getInt(1);
                            } else if (i2 == 3) {
                                iArr[1] = queryShareRaw.getInt(1);
                            }
                        } while (queryShareRaw.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (queryShareRaw != null) {
                queryShareRaw.close();
            }
        } else {
            try {
                sharedItemCountCursor2 = getSharedItemCountCursor(str, true);
                if (sharedItemCountCursor2 != null) {
                    if (sharedItemCountCursor2.moveToFirst()) {
                        iArr[0] = sharedItemCountCursor2.getInt(0);
                    }
                }
                if (sharedItemCountCursor2 != null) {
                    sharedItemCountCursor2.close();
                }
            } catch (Exception e) {
                Log.she("MdeDatabase", "getSharedItemCount#image failed e=" + e.getMessage());
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            try {
                sharedItemCountCursor = getSharedItemCountCursor(str, false);
                if (sharedItemCountCursor != null) {
                    if (sharedItemCountCursor.moveToFirst()) {
                        iArr[1] = sharedItemCountCursor.getInt(0);
                    }
                }
                if (sharedItemCountCursor != null) {
                    sharedItemCountCursor.close();
                }
            } catch (Exception e7) {
                Log.she("MdeDatabase", "getSharedItemCount#video failed e=" + e7.getMessage());
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        }
        a.A(new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Long.valueOf(currentTimeMillis)}, new StringBuilder("getSharedItemCount"), "MdeDatabase");
        return iArr;
        throw th;
        throw th;
        throw th;
    }

    public Cursor getSharedItemCursor(String str, String str2) {
        return getCursor(SPACE_ITEM_CONTENT_URI.buildUpon().appendPath(str).build(), (String[]) this.mMdeSharedItemTable.getProjectionArray().toArray(new String[0]), str2, (String[]) null, getSortOrder(str), "getSharedItemCursor");
    }

    public Cursor getSharedItemCursorFromStorageUsage(String str, String str2) {
        return getCursor(SPACE_ITEM_CONTENT_URI.buildUpon().appendPath(str).build(), (String[]) this.mMdeSharedItemTable.getProjectionArray().toArray(new String[0]), str2, (String[]) null, getSortOrder(str), "getSharedItemCursorFromStorageUsage");
    }

    public Cursor getSharedItemMimeTypeCursor(String str) {
        return getCursor(SPACE_ITEM_CONTENT_URI.buildUpon().appendPath(String.valueOf(str)).build(), new String[]{"mime_type"}, (String) null, (String[]) null, (String) null, "getSharedItemMimeTypeCursor");
    }

    public List<Pair<String, String>> getSharedTrashSpaces() {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        Cursor queryShareRaw = queryShareRaw("select S.spaceId, S.groupId from space as S where spaceId in (select spaceId from gallery_item where is_trashed=1 group by spaceId)", "getSharedTrashSpaces2");
        if (queryShareRaw != null) {
            try {
                if (queryShareRaw.moveToFirst()) {
                    do {
                        arrayList.add(new Pair(queryShareRaw.getString(0), queryShareRaw.getString(1)));
                    } while (queryShareRaw.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (queryShareRaw != null) {
            queryShareRaw.close();
        }
        a.A(new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("getSharedTrashSpaces"), "MdeDatabase");
        return arrayList;
        throw th;
    }

    public Cursor getSharingFamilyStorageUsageCursor() {
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            return queryShareRaw(buildSpacesQuery("S.groupId like 'SAFM%'", (String) null), "getSharingFamilyStorageUsageCursor");
        }
        try {
            return getCursor(SPACE_CONTENT_URI, this.mMdeSpaceTable.getProjection(), "groupId like 'SAFM%'", (String[]) null, "contents_update_time DESC", "getSharingFamilyStorageUsageCursor");
        } catch (Exception e) {
            a.s(e, new StringBuilder("getSharingFamilyStorageUsageCursor failed. e="), "MdeDatabase");
            return CursorCompat.EMPTY_CURSOR;
        }
    }

    public Cursor getSharingInvitationListCursor() {
        return getCursor(MdeConstants.INVITATION_CONTENT_URI.buildUpon().appendPath("group").build(), (String[]) null, "featureId = ?", new String[]{"32"}, "requestedTime DESC", "getSharedInvitationListCursor");
    }

    public Cursor getSharingStorageUsageCursor() {
        String str;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM);
        boolean z = !isEnabled;
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            return getSpacesCursor(true, z);
        }
        if (!isEnabled) {
            str = "";
        } else {
            str = " AND groupId not like 'SAFM%'";
        }
        try {
            return getCursor(SPACE_CONTENT_URI, this.mMdeSpaceTable.getProjection(), "my_usage > 0".concat(str), (String[]) null, "contents_update_time DESC", "getSharingStorageUsageCursor");
        } catch (Exception e) {
            a.s(e, new StringBuilder("getSharingStorageUsageCursor failed. e="), "MdeDatabase");
            return CursorCompat.EMPTY_CURSOR;
        }
    }

    public Cursor getSharingsCursor() {
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            return getSpacesCursor(false, true);
        }
        return getCursor(SPACE_CONTENT_URI, this.mMdeSpaceTable.getProjection(), (String) null, (String[]) null, "contents_update_time DESC", "getSharingsCursor");
    }

    public Cursor getSpaceCoverItemCursor(String str, String str2) {
        MdeDatabase mdeDatabase;
        Uri build = SPACE_ITEM_CONTENT_URI.buildUpon().appendPath(str).build();
        if (!TextUtils.isEmpty(str2)) {
            mdeDatabase = this;
            Cursor cursor = mdeDatabase.getCursor(build, this.mMdeSharedItemTable.getSpaceCoverProjectionArray(), "itemId=?", new String[]{str2}, (String) null, "getSpaceCoverItemCursor");
            if (cursor != null && cursor.getCount() > 0) {
                return cursor;
            }
            Utils.closeSilently(cursor);
        } else {
            mdeDatabase = this;
        }
        return mdeDatabase.getCursor(build, mdeDatabase.mMdeSharedItemTable.getSpaceCoverProjectionArray(), (String) null, (String[]) null, "modifiedTime DESC limit 1", "getFirstSharedItemCursor");
    }

    public String getSpaceIdByGroupId(String str) {
        Throwable th;
        Throwable th2;
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            Cursor queryShareRaw = queryShareRaw(C0212a.m("select spaceId from space where groupId is '", str, "'"), "getSpaceIdByGroupId");
            if (queryShareRaw != null) {
                try {
                    if (queryShareRaw.moveToFirst()) {
                        String string = queryShareRaw.getString(0);
                        queryShareRaw.close();
                        return string;
                    }
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            if (queryShareRaw != null) {
                queryShareRaw.close();
            }
            return null;
        }
        Cursor cursor = getCursor(SPACE_CONTENT_URI, new String[]{"spaceId"}, "groupId=?", new String[]{str}, (String) null, "getSpaceIdByGroupId(legacy)");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String string2 = cursor.getString(0);
                    cursor.close();
                    return string2;
                }
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
        throw th2;
        throw th;
    }

    public String getUserId(String str) {
        String str2 = null;
        if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY)) {
            Cursor queryGroupRaw = queryGroupRaw(C0212a.m("select id from sems_member where optionalId is '", str, "'"), "getUserId");
            if (queryGroupRaw != null) {
                try {
                    if (queryGroupRaw.moveToFirst()) {
                        str2 = queryGroupRaw.getString(0);
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (queryGroupRaw != null) {
                queryGroupRaw.close();
            }
        }
        return str2;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        if (r10.getCount() > 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        if (r10.getCount() > 0) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasFamilySharedAlbum() {
        /*
            r10 = this;
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_SHARED_RAW_QUERY
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "select groupId from space where groupId like 'SAFM%'"
            java.lang.String r3 = "hasFamilySharedAlbum"
            android.database.Cursor r10 = r10.queryShareRaw(r0, r3)
            if (r10 == 0) goto L_0x0028
            int r0 = r10.getCount()     // Catch:{ all -> 0x001c }
            if (r0 <= 0) goto L_0x0028
            goto L_0x0029
        L_0x001c:
            r0 = move-exception
            r1 = r0
            r10.close()     // Catch:{ all -> 0x0022 }
            goto L_0x0027
        L_0x0022:
            r0 = move-exception
            r10 = r0
            r1.addSuppressed(r10)
        L_0x0027:
            throw r1
        L_0x0028:
            r1 = r2
        L_0x0029:
            if (r10 == 0) goto L_0x002e
            r10.close()
        L_0x002e:
            return r1
        L_0x002f:
            java.lang.String r0 = "groupId"
            java.lang.String[] r5 = new java.lang.String[]{r0}
            android.net.Uri r4 = SPACE_CONTENT_URI     // Catch:{ Exception -> 0x0057 }
            java.lang.String r6 = "groupId like 'SAFM%'"
            java.lang.String r9 = "hasFamilySharedAlbum(legacy)"
            r7 = 0
            r8 = 0
            r3 = r10
            android.database.Cursor r10 = r3.getCursor(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0057 }
            if (r10 == 0) goto L_0x005a
            int r0 = r10.getCount()     // Catch:{ all -> 0x004b }
            if (r0 <= 0) goto L_0x005a
            goto L_0x005b
        L_0x004b:
            r0 = move-exception
            r1 = r0
            r10.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0056
        L_0x0051:
            r0 = move-exception
            r10 = r0
            r1.addSuppressed(r10)     // Catch:{ Exception -> 0x0057 }
        L_0x0056:
            throw r1     // Catch:{ Exception -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            r10 = r0
            goto L_0x0061
        L_0x005a:
            r1 = r2
        L_0x005b:
            if (r10 == 0) goto L_0x0060
            r10.close()     // Catch:{ Exception -> 0x0057 }
        L_0x0060:
            return r1
        L_0x0061:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "hasFamilySharedAlbum failed. e="
            r0.<init>(r1)
            java.lang.String r1 = "MdeDatabase"
            A.a.s(r10, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mdebase.db.MdeDatabase.hasFamilySharedAlbum():boolean");
    }

    public boolean isGroupAvailable(String str) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY)) {
            Cursor queryGroupRaw = queryGroupRaw(C0212a.m("select _id from sems_group where groupId is '", str, "'"), "isGroupAvailable");
            if (queryGroupRaw != null) {
                try {
                    if (queryGroupRaw.getCount() > 0) {
                        z = true;
                    }
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            if (queryGroupRaw != null) {
                queryGroupRaw.close();
            }
            return z;
        }
        Cursor cursor = getCursor(GROUP_CONTENT_URI.buildUpon().appendPath(str).build(), (String[]) null, (String) null, (String[]) null, (String) null, "isGroupAvailable(legacy)");
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    z = true;
                }
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return z;
        throw th;
        throw th2;
    }

    public boolean isSpaceAvailable(String str) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            Cursor queryShareRaw = queryShareRaw(C0212a.m("select _id from space where spaceId is '", str, "'"), "isSpaceAvailable");
            if (queryShareRaw != null) {
                try {
                    if (queryShareRaw.getCount() > 0) {
                        z = true;
                    }
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            if (queryShareRaw != null) {
                queryShareRaw.close();
            }
            return z;
        }
        Cursor cursor = getCursor(SPACE_CONTENT_URI.buildUpon().appendPath(str).build(), (String[]) null, (String) null, (String[]) null, (String) null, "isSpaceAvailable(legacy)");
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    z = true;
                }
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return z;
        throw th;
        throw th2;
    }
}
