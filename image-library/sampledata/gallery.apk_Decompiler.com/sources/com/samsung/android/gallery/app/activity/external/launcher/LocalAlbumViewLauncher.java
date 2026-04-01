package com.samsung.android.gallery.app.activity.external.launcher;

import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalAlbumViewLauncher extends QuickViewLauncher {
    public LocalAlbumViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable) {
        super(blackboard, launchIntent, runnable);
    }

    private String getLocalAlbumDataKey(int[] iArr, boolean z, boolean z3, String str) {
        String str2;
        UriBuilder uriBuilder = new UriBuilder("location://albums/fileList");
        if (str != null) {
            uriBuilder.appendArg("ids", str);
        } else {
            uriBuilder.appendArg("id", iArr[0]);
        }
        UriBuilder appendArg = uriBuilder.appendArg("disableTimeline", true).appendArg("disableRealRatio", true).appendArg("with_group", false).appendArg("withEmpty", true).appendArg("showHidden", true).appendArg("filterLocalOnly", z);
        if (z3) {
            str2 = MediaFilterType.IMAGE_ONLY.toString();
        } else {
            str2 = null;
        }
        return appendArg.appendArg("filterMediaType", str2).appendArg("quick_view", true).build();
    }

    private boolean hasAbsolutePath() {
        return !TextUtils.isEmpty(this.mLaunchIntent.getAbsolutePath());
    }

    private boolean hasBucketId() {
        if (this.mLaunchIntent.getAlbumBucketId() != 0) {
            return true;
        }
        return false;
    }

    private boolean isLocalAlbumData() {
        Uri uriData = this.mLaunchIntent.getUriData();
        if (uriData == null) {
            return false;
        }
        if ((hasAbsolutePath() || hasBucketId()) && MediaUri.getInstance().matches(uriData.toString())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadLocalAlbumItem$0(Uri uri, MediaItem[] mediaItemArr, AtomicInteger atomicInteger, CountDownLatch countDownLatch) {
        boolean z = !this.mLaunchIntent.needToCheckCloudContentIncluded();
        boolean isLocalAlbumData = isLocalAlbumData();
        boolean isImageOnly = this.mLaunchIntent.isImageOnly();
        try {
            MediaItem loadAlbumItem = this.mItemLoader.loadAlbumItem(isLocalAlbumData, uri);
            int i2 = 0;
            mediaItemArr[0] = loadAlbumItem;
            Log.d(this.TAG, "loadLocalAlbumItem : " + MediaItemUtil.getDebugLog(loadAlbumItem));
            if (loadAlbumItem == null) {
                Runnable runnable = this.mFailCallback;
                if (runnable != null) {
                    runnable.run();
                }
                countDownLatch.countDown();
                return;
            }
            this.mBlackboard.publish("data://viewer_first_data", loadAlbumItem);
            loadThumbnailSync(loadAlbumItem);
            if (this.mAlbumIds != null) {
                int positionByAlbum = new MpHelper(new QueryParams(z, isLocalAlbumData, isImageOnly)).getPositionByAlbum(this.mAlbumIds, loadAlbumItem.getMediaId(), loadAlbumItem.getFileId(), loadAlbumItem.getDateTaken());
                if (positionByAlbum < 0) {
                    Log.e((CharSequence) this.TAG, "loadLocalAlbumItem failed to find", Long.valueOf(loadAlbumItem.getMediaId()), Long.valueOf(loadAlbumItem.getFileId()), Long.valueOf(loadAlbumItem.getDateTaken()));
                } else {
                    i2 = positionByAlbum;
                }
                atomicInteger.set(i2);
            }
            countDownLatch.countDown();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "loadLocalAlbumItem failed", (Throwable) e);
            Runnable runnable2 = this.mFailCallback;
            if (runnable2 != null) {
                runnable2.run();
            }
            countDownLatch.countDown();
        } catch (Throwable th) {
            Throwable th2 = th;
            countDownLatch.countDown();
            throw th2;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0070 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void loadLocalAlbumItem(java.lang.String r13) {
        /*
            r12 = this;
            java.lang.String r0 = "loadLocalAlbumItem ViewerData{"
            java.lang.String r1 = "loadLocalAlbumItem {"
            monitor-enter(r12)
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0134 }
            boolean r4 = r12.isRestartedFromFlipCover()     // Catch:{ all -> 0x0134 }
            if (r4 == 0) goto L_0x001a
            android.net.Uri r4 = r12.getUriFromFlipCoverSavedData()     // Catch:{ all -> 0x0015 }
        L_0x0013:
            r7 = r4
            goto L_0x0021
        L_0x0015:
            r0 = move-exception
            r13 = r0
            r6 = r12
            goto L_0x0138
        L_0x001a:
            com.samsung.android.gallery.module.abstraction.LaunchIntent r4 = r12.mLaunchIntent     // Catch:{ all -> 0x0134 }
            android.net.Uri r4 = r4.getUriData()     // Catch:{ all -> 0x0134 }
            goto L_0x0013
        L_0x0021:
            if (r7 != 0) goto L_0x0033
            java.lang.String r13 = r12.TAG     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = "loadLocalAlbumItem : no uri"
            com.samsung.android.gallery.support.utils.Log.e(r13, r0)     // Catch:{ all -> 0x0015 }
            java.lang.Runnable r13 = r12.mFailCallback     // Catch:{ all -> 0x0015 }
            if (r13 == 0) goto L_0x0031
            r13.run()     // Catch:{ all -> 0x0015 }
        L_0x0031:
            monitor-exit(r12)
            return
        L_0x0033:
            java.lang.String r4 = r12.TAG     // Catch:{ all -> 0x0134 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0134 }
            r5.<init>(r1)     // Catch:{ all -> 0x0134 }
            r5.append(r7)     // Catch:{ all -> 0x0134 }
            java.lang.String r1 = "}"
            r5.append(r1)     // Catch:{ all -> 0x0134 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x0134 }
            com.samsung.android.gallery.support.utils.Log.d(r4, r1)     // Catch:{ all -> 0x0134 }
            java.util.concurrent.CountDownLatch r10 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0134 }
            r1 = 1
            r10.<init>(r1)     // Catch:{ all -> 0x0134 }
            com.samsung.android.gallery.module.data.MediaItem[] r8 = new com.samsung.android.gallery.module.data.MediaItem[r1]     // Catch:{ all -> 0x0134 }
            java.util.concurrent.atomic.AtomicInteger r9 = new java.util.concurrent.atomic.AtomicInteger     // Catch:{ all -> 0x0134 }
            r9.<init>()     // Catch:{ all -> 0x0134 }
            com.samsung.android.gallery.support.utils.SimpleThreadPool r4 = com.samsung.android.gallery.support.utils.SimpleThreadPool.getInstance()     // Catch:{ all -> 0x0134 }
            B5.c r5 = new B5.c     // Catch:{ all -> 0x0134 }
            r11 = 2
            r6 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x006c }
            r4.execute(r5)     // Catch:{ all -> 0x006c }
            java.util.concurrent.TimeUnit r12 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0070 }
            r4 = 2
            r10.await(r4, r12)     // Catch:{ InterruptedException -> 0x0070 }
            goto L_0x0077
        L_0x006c:
            r0 = move-exception
        L_0x006d:
            r13 = r0
            goto L_0x0138
        L_0x0070:
            java.lang.String r12 = r6.TAG     // Catch:{ all -> 0x006c }
            java.lang.String r4 = "loadLocalAlbumItem time-out"
            com.samsung.android.gallery.support.utils.Log.e(r12, r4)     // Catch:{ all -> 0x006c }
        L_0x0077:
            java.lang.String r12 = r6.TAG     // Catch:{ all -> 0x006c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006c }
            r4.<init>(r0)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.dataset.MediaData r0 = r6.mMediaData     // Catch:{ all -> 0x006c }
            r5 = 0
            if (r0 == 0) goto L_0x0085
            r0 = r1
            goto L_0x0086
        L_0x0085:
            r0 = r5
        L_0x0086:
            r4.append(r0)     // Catch:{ all -> 0x006c }
            java.lang.String r0 = ","
            r4.append(r0)     // Catch:{ all -> 0x006c }
            r0 = r8[r5]     // Catch:{ all -> 0x006c }
            if (r0 == 0) goto L_0x0094
            r0 = r1
            goto L_0x0095
        L_0x0094:
            r0 = r5
        L_0x0095:
            r4.append(r0)     // Catch:{ all -> 0x006c }
            java.lang.String r0 = ","
            r4.append(r0)     // Catch:{ all -> 0x006c }
            int r0 = r9.get()     // Catch:{ all -> 0x006c }
            r4.append(r0)     // Catch:{ all -> 0x006c }
            java.lang.String r0 = "} +"
            r4.append(r0)     // Catch:{ all -> 0x006c }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x006c }
            long r10 = r10 - r2
            r4.append(r10)     // Catch:{ all -> 0x006c }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.support.utils.Log.d(r12, r0)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.abstraction.LaunchIntent r12 = r6.mLaunchIntent     // Catch:{ all -> 0x006c }
            boolean r12 = r12.needToCheckCloudContentIncluded()     // Catch:{ all -> 0x006c }
            r12 = r12 ^ r1
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r6.mLaunchIntent     // Catch:{ all -> 0x006c }
            boolean r0 = r0.isImageOnly()     // Catch:{ all -> 0x006c }
            int[] r1 = r6.mAlbumIds     // Catch:{ all -> 0x006c }
            java.lang.String r12 = r6.getLocalAlbumDataKey(r1, r12, r0, r13)     // Catch:{ all -> 0x006c }
            int r13 = r9.get()     // Catch:{ all -> 0x006c }
            r0 = 2048(0x800, float:2.87E-42)
            if (r13 <= r0) goto L_0x00d7
            r13 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x00dd
        L_0x00d7:
            int r13 = r9.get()     // Catch:{ all -> 0x006c }
            int r13 = r13 + 30
        L_0x00dd:
            java.lang.String r0 = "fakeLoadingCount"
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ all -> 0x006c }
            java.lang.String r12 = com.samsung.android.gallery.support.utils.ArgumentsUtil.appendArgs(r12, r0, r13)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.support.blackboard.Blackboard r13 = r6.mBlackboard     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.dataset.MediaDataFactory r13 = com.samsung.android.gallery.module.dataset.MediaDataFactory.getInstance(r13)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.dataset.MediaData r12 = r13.open(r12)     // Catch:{ all -> 0x006c }
            r6.mMediaData = r12     // Catch:{ all -> 0x006c }
            r12 = r8[r5]     // Catch:{ all -> 0x006c }
            if (r12 == 0) goto L_0x0110
            com.samsung.android.gallery.database.dbtype.StorageType r12 = r12.getStorageType()     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.database.dbtype.StorageType r13 = com.samsung.android.gallery.database.dbtype.StorageType.UriItem     // Catch:{ all -> 0x006c }
            if (r12 != r13) goto L_0x0110
            com.samsung.android.gallery.module.viewer.VuLauncher r12 = new com.samsung.android.gallery.module.viewer.VuLauncher     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.support.blackboard.Blackboard r13 = r6.mBlackboard     // Catch:{ all -> 0x006c }
            r12.<init>(r13)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.viewer.VuLauncher r12 = r12.publishData()     // Catch:{ all -> 0x006c }
            java.lang.String r13 = "location://quickView"
            r12.launch(r13, r5, r8)     // Catch:{ all -> 0x006c }
            goto L_0x0132
        L_0x0110:
            com.samsung.android.gallery.module.dataset.MediaData r12 = r6.mMediaData     // Catch:{ all -> 0x006c }
            if (r12 == 0) goto L_0x0132
            r12 = r8[r5]     // Catch:{ all -> 0x006c }
            if (r12 == 0) goto L_0x0132
            com.samsung.android.gallery.module.viewer.VuLauncher r12 = new com.samsung.android.gallery.module.viewer.VuLauncher     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.support.blackboard.Blackboard r13 = r6.mBlackboard     // Catch:{ all -> 0x006c }
            r12.<init>(r13)     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.dataset.MediaData r13 = r6.mMediaData     // Catch:{ all -> 0x006c }
            java.lang.String r13 = r13.getLocationReference()     // Catch:{ all -> 0x006c }
            int r0 = r9.get()     // Catch:{ all -> 0x006c }
            r1 = r8[r5]     // Catch:{ all -> 0x006c }
            com.samsung.android.gallery.module.data.MediaItem[] r1 = new com.samsung.android.gallery.module.data.MediaItem[]{r1}     // Catch:{ all -> 0x006c }
            r12.launch(r13, r0, r1)     // Catch:{ all -> 0x006c }
        L_0x0132:
            monitor-exit(r6)
            return
        L_0x0134:
            r0 = move-exception
            r6 = r12
            goto L_0x006d
        L_0x0138:
            monitor-exit(r6)     // Catch:{ all -> 0x006c }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.LocalAlbumViewLauncher.loadLocalAlbumItem(java.lang.String):void");
    }

    private String loadMultipleBucket() {
        String albumBucketIds = this.mLaunchIntent.getAlbumBucketIds();
        int[] intArray = StringCompat.toIntArray(albumBucketIds, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (BucketUtils.containsRecent(intArray)) {
            intArray = new int[]{BucketUtils.VirtualBucketHolder.recent};
        }
        this.mAlbumIds = intArray;
        Log.d(this.TAG, "loadLocalAlbums", C0212a.m("[", albumBucketIds, "]"));
        return albumBucketIds;
    }

    private void loadSingleBucket() {
        int albumBucketId = this.mLaunchIntent.getAlbumBucketId();
        if (albumBucketId == 0) {
            albumBucketId = FileUtils.getBucketId(FileUtils.getCanonicalPath(FileUtils.getDirectoryFromPath(this.mLaunchIntent.getAbsolutePath(), false)));
        }
        this.mAlbumIds = new int[]{albumBucketId};
        String str = this.TAG;
        Log.d(str, "loadLocalAlbums {" + albumBucketId + "}");
    }

    public void execute() {
        String str;
        if (isLocalAlbumData()) {
            loadSingleBucket();
        } else if (!TextUtils.isEmpty(this.mLaunchIntent.getAlbumBucketIds())) {
            str = loadMultipleBucket();
            loadLocalAlbumItem(str);
        }
        str = null;
        loadLocalAlbumItem(str);
    }
}
