package com.samsung.android.gallery.module.data;

import A.a;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.GroupMediaImpl;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GroupItemLoader {
    public static ArrayList<MediaItem> getGroupShotList(MediaItem mediaItem) {
        Cursor groupShotCursor;
        try {
            groupShotCursor = new GroupMediaImpl(DbCompatGroup.getGroupShotQueryParams(mediaItem)).getGroupShotCursor();
            if (groupShotCursor != null) {
                if (groupShotCursor.getCount() > 0 && groupShotCursor.moveToFirst()) {
                    ArrayList<MediaItem> arrayList = new ArrayList<>();
                    do {
                        arrayList.add(MediaItemLoader.load(groupShotCursor));
                    } while (groupShotCursor.moveToNext());
                    groupShotCursor.close();
                    return arrayList;
                }
            }
            if (groupShotCursor != null) {
                groupShotCursor.close();
            }
        } catch (Exception e) {
            Log.e((CharSequence) "GroupItemLoader", "getGroupShotList failed e", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return new ArrayList<>();
        throw th;
    }

    public static boolean hasShareGroupMedia(MediaItem[] mediaItemArr) {
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isSingleTakenShot() || mediaItem.isSimilarShot()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<MediaItem> loadShareGroupItems(MediaItem[] mediaItemArr) {
        Cursor cursor;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        int i2 = 0;
        int i7 = 0;
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isSimilarShot()) {
                i2++;
                cursor = DbCompat.query(DbKey.FILES_SIMILAR, new a(mediaItem, 1));
            } else if (mediaItem.isSingleTakenShot()) {
                i7++;
                cursor = DbCompat.query(DbKey.FILES_SINGLETAKE, new a(mediaItem, 2));
            } else {
                arrayList.add(mediaItem);
            }
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(MediaItemBuilder.create(cursor));
                }
                cursor.close();
            }
        }
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadShareGroupItems"), "GroupItemLoader");
        return arrayList;
    }

    public static ArrayList<MediaItem> loadShareGroupItemsForTimeline(MediaItem[] mediaItemArr) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isSimilarShot()) {
                arrayList2.add(mediaItem);
            } else if (mediaItem.isSingleTakenShot()) {
                arrayList3.add(mediaItem);
            } else {
                arrayList.add(mediaItem);
            }
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList2.stream().map(new j(4)).forEach(new c(stringJoiner, 0));
        Cursor query = DbCompat.query(DbKey.FILES_SIMILAR_ALL, new c(stringJoiner, 1));
        if (query != null) {
            while (query.moveToNext()) {
                arrayList.add(MediaItemBuilder.create(query));
            }
            query.close();
        }
        StringJoiner stringJoiner2 = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList3.stream().map(new j(4)).forEach(new c(stringJoiner2, 2));
        Cursor query2 = DbCompat.query(DbKey.FILES_SINGLETAKE_ALL, new c(stringJoiner2, 3));
        if (query2 != null) {
            while (query2.moveToNext()) {
                arrayList.add(MediaItemBuilder.create(query2));
            }
            query2.close();
        }
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), Integer.valueOf(arrayList2.size()), Integer.valueOf(arrayList3.size()), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadShareGroupItemsForTimeline"), "GroupItemLoader");
        return arrayList;
    }

    public static long queryTotalSize(String str, boolean z) {
        QueryParams groupTypes = new QueryParams(DbKey.ALL_PICTURES).setGroupTypes(GroupType.BURST, GroupType.SINGLE_TAKEN);
        if (z) {
            groupTypes.addGroupType(GroupType.SIMILAR);
        }
        groupTypes.setFileIds(str);
        groupTypes.setGroupSizeSum();
        Cursor query = DbCompat.query(groupTypes);
        long j2 = 0;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    long j3 = 0;
                    do {
                        long j8 = SuggestData.of(MediaItemLoader.load(query)).cleanOutTotalSize;
                        if (j8 > 0) {
                            j3 += j8;
                        } else {
                            throw new IllegalStateException();
                        }
                    } while (query.moveToNext());
                    j2 = j3;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        Log.d("GroupItemLoader", "queryTotalSize=" + j2);
        return j2;
        throw th;
    }
}
