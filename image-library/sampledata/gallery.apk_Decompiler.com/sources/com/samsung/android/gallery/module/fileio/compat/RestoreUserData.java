package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.scsp.media.api.d;
import g6.f;
import g9.C0692a;
import g9.b;
import g9.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RestoreUserData {
    BulkOperation captured = new CapturedBulkOperation();
    BulkOperation favorite = new FavoriteBulkOperation();
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    int retryCount;
    BulkOperation userTag = new TagBulkOperation();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class BulkOperation {
        final ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        final HashMap<String, Object> source = new HashMap<>();
        final HashMap<String, Object> target = new HashMap<>();
        Uri uri;

        public boolean applyBatch() {
            if (size() == 0 || MediaStoreApi.applyBatch(this.uri, this.operations)) {
                return true;
            }
            return false;
        }

        public abstract void buildOperations();

        public HashMap<String, Object> removeIf(Predicate<String> predicate) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Iterator it = new ArrayList(this.target.keySet()).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (predicate.test(str)) {
                    hashMap.put(str, this.target.remove(str));
                }
            }
            return hashMap;
        }

        public int size() {
            return this.operations.size();
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(this.source.size());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.target.size());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0086a.l(sb2, size(), "]");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CapturedBulkOperation extends BulkOperation {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$buildOperations$0(String str, Object obj) {
            String[] strArr = (String[]) obj;
            ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(this.uri).withSelection("_data=?", new String[]{str}).withValue("captured_url", strArr[0]).withValue("captured_app", strArr[1]);
            if (Features.isEnabled(Features.SUPPORT_GO_TO_CAPTURED_PATH)) {
                withValue.withValue("captured_original_path", strArr[2]);
            }
            this.operations.add(withValue.build());
        }

        public void buildOperations() {
            this.uri = SecMediaStoreApi.FILES_URI;
            this.target.forEach(new h(this, 0));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FavoriteBulkOperation extends BulkOperation {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$buildOperations$0(String str, Object obj) {
            this.operations.add(ContentProviderOperation.newUpdate(this.uri).withValue("is_favorite", "1").withSelection("_data=?", new String[]{str}).build());
        }

        public void buildOperations() {
            this.uri = MediaStoreApi.FILES_URI;
            this.target.forEach(new h(this, 1));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TagBulkOperation extends BulkOperation {
        final HashMap<String, String> tagMap = new HashMap<>();

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$applyBatch$1(ArrayList arrayList, String str, Long l) {
            String remove = this.tagMap.remove(str);
            if (remove != null) {
                for (String buildContentValues : remove.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                    arrayList.add(TagEditApi.buildContentValues(l.longValue(), buildContentValues));
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$buildOperations$0(String str, Object obj) {
            this.tagMap.put(str, (String) obj);
        }

        private HashMap<String, Long> loadDataMap(Collection<String> collection) {
            HashMap<String, Long> hashMap = new HashMap<>();
            if (collection.size() > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                String next = collection.iterator().next();
                String volumeName = FileUtils.getVolumeName(next);
                int bucketId = FileUtils.getBucketId(FileUtils.getParent(next));
                List list = (List) collection.stream().map(new d(23)).collect(Collectors.toList());
                int size = list.size();
                String[] strArr = {"_id", "_data"};
                int i2 = 0;
                while (i2 < size) {
                    int i7 = i2 + StatusCodes.INPUT_MISSING;
                    SecMediaStoreApi.load(volumeName, strArr, bucketId, list.subList(i2, Math.min(size, i7)), new f(1, hashMap));
                    i2 = i7;
                }
                a.A(new Object[]{volumeName, "in=" + list.size(), "out=" + hashMap.size(), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadDataMap"), "FileOpData#Restore");
            }
            return hashMap;
        }

        public boolean applyBatch() {
            int i2;
            if (size() <= 0) {
                return true;
            }
            long currentTimeMillis = System.currentTimeMillis();
            int size = this.tagMap.size();
            HashMap<String, Long> loadDataMap = loadDataMap(this.tagMap.keySet());
            if (loadDataMap.size() <= 0) {
                return true;
            }
            ArrayList arrayList = new ArrayList();
            loadDataMap.forEach(new i(this, arrayList));
            if (arrayList.size() > 0) {
                i2 = new TagEditApi().bulkInsertMyTags((ContentValues[]) arrayList.toArray(new ContentValues[0]));
            } else {
                i2 = 0;
            }
            StringBuilder sb2 = new StringBuilder("applyBatch#tag");
            sb2.append(Logger.vt(C0086a.i(size, "total="), "remained=" + this.tagMap.size(), j.g(new StringBuilder("in="), arrayList), C0086a.i(i2, "out="), Long.valueOf(currentTimeMillis)));
            Log.d("FileOpData#Restore", sb2.toString());
            if (arrayList.size() == i2) {
                return true;
            }
            return false;
        }

        public void buildOperations() {
            this.target.forEach(new h(this, 2));
        }

        public int size() {
            return this.tagMap.size();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$init$0(FileOpJob fileOpJob) {
        if (fileOpJob.isCopy() || fileOpJob.isMove() || fileOpJob.source.isDrm()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(ArrayList arrayList, FileOpJob fileOpJob) {
        if (fileOpJob.source.isFavourite()) {
            this.favorite.source.put(fileOpJob.source.getPath(), "1");
        }
        DetailsData of2 = DetailsData.of(fileOpJob.source);
        if (!(of2.capturedUrl == null && of2.capturedPath == null)) {
            this.captured.source.put(fileOpJob.source.getPath(), new String[]{of2.capturedUrl, of2.capturedApp, of2.capturedPath});
        }
        arrayList.add(fileOpJob.source);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(String str, String str2) {
        this.userTag.source.put(str, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadUserTag$11(BiConsumer biConsumer, Boolean bool, List list) {
        if (list != null && !list.isEmpty()) {
            String str = (String) list.stream().map(new d(21)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            if (bool.booleanValue()) {
                PrivateDatabase.getInstance().loadUserTags(str, biConsumer);
            } else {
                loadUserTag(str, (BiConsumer<String, String>) biConsumer);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$prepare$3(FileOpJob fileOpJob) {
        if (fileOpJob.isCopy() || fileOpJob.isMove() || fileOpJob.source.isDrm()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$4(FileOpJob fileOpJob, Object obj) {
        this.favorite.target.put(fileOpJob.target, obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$5(FileOpJob fileOpJob, Object obj) {
        this.captured.target.put(fileOpJob.target, obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$6(FileOpJob fileOpJob, Object obj) {
        this.userTag.target.put(fileOpJob.target, obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$7(boolean z, boolean z3, boolean z7, FileOpJob fileOpJob) {
        String path = fileOpJob.source.getPath();
        if (z) {
            Optional.ofNullable(this.favorite.source.get(path)).ifPresent(new C0692a(this, fileOpJob, 1));
        }
        if (z3) {
            Optional.ofNullable(this.captured.source.get(path)).ifPresent(new C0692a(this, fileOpJob, 2));
        }
        if (z7) {
            Optional.ofNullable(this.userTag.source.get(path)).ifPresent(new C0692a(this, fileOpJob, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$tryRestore$9() {
        SimpleThreadPool.getInstance().execute(new b(this, 1));
    }

    private void loadUserTag(List<FileItemInterface> list, BiConsumer<String, String> biConsumer) {
        ((Map) list.stream().collect(Collectors.groupingBy(new d(22)))).forEach(new A9.a(21, this, biConsumer));
    }

    private void tryRestore() {
        if (this.favorite.size() + this.captured.size() + this.userTag.size() > 0) {
            ThreadUtil.postOnBgThreadDelayed(new b(this, 0), 1000);
        }
    }

    public void executeRestore() {
        TimeTickLog timeTickLog = new TimeTickLog();
        this.favorite.applyBatch();
        timeTickLog.tick();
        this.captured.applyBatch();
        timeTickLog.tick();
        this.userTag.applyBatch();
        timeTickLog.tick();
        Log.d("FileOpData#Restore", "executeRestore#" + this.retryCount + " " + this + Logger.vt(timeTickLog));
        int i2 = this.retryCount + 1;
        this.retryCount = i2;
        if (i2 < 10) {
            tryRestore();
        }
    }

    public void init(ArrayList<FileOpJob> arrayList) {
        if (!this.initialized.getAndSet(true)) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList2 = new ArrayList();
            arrayList.stream().filter(new com.samsung.android.gallery.module.dynamicview.a(26)).forEach(new e(20, this, arrayList2));
            if (!arrayList2.isEmpty()) {
                loadUserTag((List<FileItemInterface>) arrayList2, (BiConsumer<String, String>) new f(1, this));
            }
            StringBuilder sb2 = new StringBuilder("init ");
            sb2.append(this);
            sb2.append(" +");
            a.x(sb2, currentTimeMillis, "FileOpData#Restore");
        }
    }

    public void prepare(ArrayList<FileOpJob> arrayList) {
        long currentTimeMillis = System.currentTimeMillis();
        arrayList.stream().filter(new com.samsung.android.gallery.module.dynamicview.a(27)).forEach(new c(this, !this.favorite.source.isEmpty(), !this.captured.source.isEmpty(), !this.userTag.source.isEmpty()));
        StringBuilder sb2 = new StringBuilder("prepare ");
        sb2.append(this);
        sb2.append(" +");
        a.x(sb2, currentTimeMillis, "FileOpData#Restore");
    }

    public void restore() {
        long currentTimeMillis = System.currentTimeMillis();
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            PrivateDatabase.getInstance().saveUserTags(this.userTag.removeIf(new com.samsung.android.gallery.module.dynamicview.a(28)));
        }
        this.favorite.buildOperations();
        this.captured.buildOperations();
        this.userTag.buildOperations();
        StringBuilder sb2 = new StringBuilder("restore ");
        sb2.append(this);
        sb2.append(" +");
        a.x(sb2, currentTimeMillis, "FileOpData#Restore");
        tryRestore();
    }

    public String toString() {
        return "{fav=" + this.favorite + ",cap=" + this.captured + ",tag=" + this.userTag + "}";
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadUserTag(java.lang.String r6, java.util.function.BiConsumer<java.lang.String, java.lang.String> r7) {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r5 = "SELECT A._id, A._data, group_concat(U.tag) as user_tag FROM files as A INNER JOIN usertag as U ON A._id=U.sec_media_id WHERE A._id in ("
            java.lang.String r2 = ") GROUP BY A._id"
            java.lang.String r5 = i.C0212a.m(r5, r6, r2)
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r6 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor
            r6.<init>()
            java.lang.String r2 = "queryUserTag"
            android.database.Cursor r5 = r6.rawQuery((java.lang.String) r5, (java.lang.String) r2)
            if (r5 == 0) goto L_0x0041
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0037 }
            if (r6 == 0) goto L_0x0041
            int r6 = r5.getCount()     // Catch:{ all -> 0x0037 }
        L_0x0023:
            r3 = 1
            java.lang.String r3 = r5.getString(r3)     // Catch:{ all -> 0x0037 }
            r4 = 2
            java.lang.String r4 = r5.getString(r4)     // Catch:{ all -> 0x0037 }
            r7.accept(r3, r4)     // Catch:{ all -> 0x0037 }
            boolean r3 = r5.moveToNext()     // Catch:{ all -> 0x0037 }
            if (r3 != 0) goto L_0x0023
            goto L_0x0042
        L_0x0037:
            r6 = move-exception
            r5.close()     // Catch:{ all -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r5 = move-exception
            r6.addSuppressed(r5)
        L_0x0040:
            throw r6
        L_0x0041:
            r6 = 0
        L_0x0042:
            if (r5 == 0) goto L_0x0047
            r5.close()
        L_0x0047:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r2)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Long r7 = java.lang.Long.valueOf(r0)
            java.lang.String r0 = "FileOpData#Restore"
            java.lang.Object[] r6 = new java.lang.Object[]{r0, r6, r7}
            A.a.A(r6, r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.compat.RestoreUserData.loadUserTag(java.lang.String, java.util.function.BiConsumer):void");
    }
}
