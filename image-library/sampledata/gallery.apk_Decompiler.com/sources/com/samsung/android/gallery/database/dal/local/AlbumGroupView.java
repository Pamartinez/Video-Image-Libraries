package com.samsung.android.gallery.database.dal.local;

import A8.C0545b;
import E5.b;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import f4.a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumGroupView {
    static final String[] PROJECTION = {"_id", "bucket_id", "bucket_display_name", "parent", "parent_name", "descendant", "bucket_id_list", "priority", BundleKey.MODIFIED_TIME};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Group {
        /* access modifiers changed from: package-private */
        public String albumIds;
        String bucketDisplayName;
        int bucketId;
        String bucketIds;
        ArrayList<Group> child = new ArrayList<>();
        String descendantIds;
        String groupIds;
        long id;
        long lastModifiedTime;
        long modifiedTime;
        int parent;
        String parentName;
        int priority;
        int type;

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$descendantOf$2(Group group) {
            if (group.type == 2) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$descendantOf$4(String str) {
            return !TextUtils.isEmpty(str);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$descendantOf$5(Group group) {
            if (group.type == 1) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$descendantOf$7(String str) {
            return !TextUtils.isEmpty(str);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$listOf$1(String str) {
            return !TextUtils.isEmpty(str);
        }

        public Object[] buildContentValues() {
            return new Object[]{Long.valueOf(this.id), Integer.valueOf(this.bucketId), this.bucketDisplayName, Integer.valueOf(this.parent), this.parentName, descendantOf(), listOf(), Integer.valueOf(this.priority), Long.valueOf(lastModified())};
        }

        public String descendantOf() {
            if (this.descendantIds == null) {
                ArrayList arrayList = new ArrayList();
                String str = this.albumIds;
                if (str != null) {
                    arrayList.add(str);
                }
                this.child.stream().filter(new a(2)).map(new f(0)).filter(new a(3)).forEach(new a(arrayList, 17));
                this.child.stream().filter(new a(4)).map(new f(1)).forEach(new a(arrayList, 17));
                this.descendantIds = (String) arrayList.stream().filter(new a(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            }
            return this.descendantIds;
        }

        public long lastModified() {
            if (this.lastModifiedTime == 0) {
                long j2 = this.modifiedTime;
                Iterator<Group> it = this.child.iterator();
                while (it.hasNext()) {
                    j2 = Math.max(j2, it.next().lastModified());
                }
                this.lastModifiedTime = j2;
            }
            return this.lastModifiedTime;
        }

        public String listOf() {
            if (this.bucketIds == null) {
                ArrayList arrayList = new ArrayList();
                String str = this.albumIds;
                if (str != null) {
                    arrayList.add(str);
                }
                this.child.forEach(new b(2, arrayList));
                this.bucketIds = (String) arrayList.stream().filter(new a(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            }
            return this.bucketIds;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("{");
            sb2.append(this.id);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.bucketId);
            sb2.append(",'");
            sb2.append(this.bucketDisplayName);
            sb2.append("',");
            sb2.append(lastModified());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.parent);
            sb2.append(",'");
            sb2.append(this.parentName);
            sb2.append("',");
            sb2.append(this.priority);
            sb2.append(",[");
            sb2.append(descendantOf());
            sb2.append("],[");
            return C0212a.p(sb2, listOf(), "]}");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SqliteArg {
        static final Pattern pattern = Pattern.compile("(\\w*)\\s*([><!]?=|(?i)in|(?i)not in|(?i)not|(?i)is)\\s*([\\w?,()'\"]*)");
        String condition;
        String key;
        String raw;
        String value;

        public SqliteArg(Matcher matcher) {
            this.raw = matcher.group(0);
            this.key = matcher.group(1);
            this.condition = matcher.group(2);
            this.value = matcher.group(3);
        }

        public static ArrayList<SqliteArg> build(String str, String[] strArr) {
            ArrayList<SqliteArg> arrayList = new ArrayList<>();
            if (!TextUtils.isEmpty(str)) {
                Matcher matcher = pattern.matcher(str);
                int i2 = 0;
                while (matcher.find()) {
                    SqliteArg sqliteArg = new SqliteArg(matcher);
                    if ("?".equals(sqliteArg.value)) {
                        sqliteArg.value = strArr[i2];
                        i2++;
                    }
                    arrayList.add(sqliteArg);
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$0(HashSet hashSet, Long l) {
            if (hashSet.contains(l)) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$1(HashSet hashSet, Long l) {
            if (!hashSet.contains(l)) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$2(long j2, Long l) {
            if (l.longValue() >= j2) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$3(long j2, Long l) {
            if (l.longValue() <= j2) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$4(long j2, Long l) {
            if (l.longValue() > j2) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$5(long j2, Long l) {
            if (l.longValue() < j2) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$6(long j2, Long l) {
            if (l.longValue() == j2) {
                return 1;
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$getLongComparator$7(long j2, Long l) {
            if (l.longValue() != j2) {
                return 1;
            }
            return -1;
        }

        public Comparable<Long> getLongComparator() {
            if ("in".equalsIgnoreCase(this.condition)) {
                return new h((HashSet) Arrays.stream(this.value.replaceAll("[()]", "").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).mapToLong(new b(7)).boxed().collect(Collectors.toCollection(new ld.b(7))), 0);
            }
            if ("not in".equalsIgnoreCase(this.condition)) {
                return new h((HashSet) Arrays.stream(this.value.replaceAll("[()]", "").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).mapToLong(new b(7)).boxed().collect(Collectors.toCollection(new ld.b(7))), 1);
            }
            long parseLong = Long.parseLong(this.value);
            if (">=".equals(this.condition)) {
                return new g(parseLong, 2);
            }
            if ("<=".equals(this.condition)) {
                return new g(parseLong, 3);
            }
            if (">".equals(this.condition)) {
                return new g(parseLong, 4);
            }
            if ("<".equals(this.condition)) {
                return new g(parseLong, 5);
            }
            if ("=".equals(this.condition)) {
                return new g(parseLong, 0);
            }
            if ("!=".equals(this.condition)) {
                return new g(parseLong, 1);
            }
            throw new IllegalArgumentException("not supported " + this.condition);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Arg{");
            sb2.append(this.key);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.condition);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0212a.p(sb2, this.value, "}");
        }
    }

    private String getFilterVolumeName() {
        return C0212a.m("(__absPath is not null AND (__volumeName in (", FileUtils.getMountedVolumeNames(), "))) OR (__absPath is null)");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$query$0(Group group) {
        return !TextUtils.isEmpty(group.groupIds);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$query$1(Group group, int i2) {
        if (i2 != group.parent) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$query$4(HashMap hashMap, Group group) {
        String[] split = group.groupIds.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (split.length > 0) {
            Arrays.stream(split).mapToInt(new C0545b(3)).filter(new d(group)).forEach(new e(hashMap, group));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$query$6(Comparable comparable, Group group) {
        if (comparable.compareTo(Long.valueOf(group.lastModified())) <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$query$7(Comparable comparable, Group group) {
        if (comparable.compareTo(Long.valueOf((long) group.bucketId)) <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$query$8(Comparable comparable, Group group) {
        if (comparable.compareTo(Long.valueOf((long) group.priority)) <= 0) {
            return true;
        }
        return false;
    }

    public Cursor query(Context context, String str, String[] strArr) {
        Throwable th;
        String str2;
        ArrayList arrayList;
        HashMap hashMap;
        String str3;
        String p6 = C0212a.p(new StringBuilder("select _id, __bucketID as bucket_id, __Title as bucket_display_name, __albumLevel as priority, folder_id as parent, folder_name as parent_name, __albumType as type, (select group_concat(__bucketID) from mxalbum where folder_id = F.__bucketID and __albumType in (0,5) AND ("), getFilterVolumeName(), ")) as album_list, (select group_concat(__bucketID) from mxalbum where folder_id = F.__bucketID and __albumType in (1,2)) as group_list, (select MAX(__dateModified) * 1000 from mxalbum where folder_id = F.__bucketID or __bucketID = F.__bucketID) as modified_time from mxalbum as F where F.__albumType in (1,2)");
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        Cursor rawQuery = LocalDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery(p6, (String[]) null);
        try {
            if (rawQuery.moveToFirst()) {
                int columnIndex = rawQuery.getColumnIndex("_id");
                int columnIndex2 = rawQuery.getColumnIndex("bucket_id");
                int columnIndex3 = rawQuery.getColumnIndex("type");
                int columnIndex4 = rawQuery.getColumnIndex("bucket_display_name");
                int columnIndex5 = rawQuery.getColumnIndex("priority");
                int columnIndex6 = rawQuery.getColumnIndex("parent");
                int columnIndex7 = rawQuery.getColumnIndex("parent_name");
                int columnIndex8 = rawQuery.getColumnIndex("album_list");
                int columnIndex9 = rawQuery.getColumnIndex("group_list");
                int columnIndex10 = rawQuery.getColumnIndex(BundleKey.MODIFIED_TIME);
                str2 = "priority";
                while (true) {
                    Group group = new Group();
                    HashMap hashMap3 = hashMap2;
                    ArrayList arrayList3 = arrayList2;
                    group.id = rawQuery.getLong(columnIndex);
                    group.bucketId = rawQuery.getInt(columnIndex2);
                    group.type = rawQuery.getInt(columnIndex3);
                    group.bucketDisplayName = rawQuery.getString(columnIndex4);
                    group.priority = rawQuery.getInt(columnIndex5);
                    group.parent = rawQuery.getInt(columnIndex6);
                    group.parentName = rawQuery.getString(columnIndex7);
                    group.albumIds = rawQuery.getString(columnIndex8);
                    group.groupIds = rawQuery.getString(columnIndex9);
                    group.modifiedTime = rawQuery.getLong(columnIndex10);
                    if (group.type == 1) {
                        arrayList = arrayList3;
                        arrayList.add(group);
                    } else {
                        arrayList = arrayList3;
                    }
                    int i2 = columnIndex;
                    hashMap = hashMap3;
                    hashMap.put(Integer.valueOf(group.bucketId), group);
                    if (!rawQuery.moveToNext()) {
                        break;
                    }
                    arrayList2 = arrayList;
                    hashMap2 = hashMap;
                    columnIndex = i2;
                }
            } else {
                hashMap = hashMap2;
                arrayList = arrayList2;
                str2 = "priority";
            }
            rawQuery.close();
            arrayList.stream().filter(new a(0)).forEach(new b(0, hashMap));
            try {
                if (!TextUtils.isEmpty(str)) {
                    Iterator<SqliteArg> it = SqliteArg.build(str, strArr).iterator();
                    while (it.hasNext()) {
                        SqliteArg next = it.next();
                        if (BundleKey.MODIFIED_TIME.equals(next.key)) {
                            arrayList.removeIf(new c(next.getLongComparator(), 0));
                        } else if ("bucket_id".equals(next.key)) {
                            arrayList.removeIf(new c(next.getLongComparator(), 1));
                        } else {
                            str3 = str2;
                            if (str3.equals(next.key)) {
                                arrayList.removeIf(new c(next.getLongComparator(), 2));
                            }
                            str2 = str3;
                        }
                        str3 = str2;
                        str2 = str3;
                    }
                }
                MatrixCursor matrixCursor = new MatrixCursor(PROJECTION);
                arrayList.forEach(new b(1, matrixCursor));
                Bundle bundle = new Bundle();
                bundle.putLong("version", 1);
                matrixCursor.setExtras(bundle);
                return matrixCursor;
            } catch (StackOverflowError e) {
                Log.e("AlbumGroupView", "stackOverFlow occurred" + e.getMessage());
                return new MatrixCursor(PROJECTION);
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
