package com.samsung.android.gallery.app.activity.external.launcher;

import A.a;
import A8.C0545b;
import E3.C0394a;
import E3.b;
import E3.d;
import E3.e;
import E3.f;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CameraQuickViewLauncher extends QuickViewLauncher {
    public CameraQuickViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable) {
        super(blackboard, launchIntent, runnable);
    }

    private static void clearPendingFile(MediaItem mediaItem) {
        mediaItem.clearPending();
    }

    private MediaItem getMediaItem(String str, Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            return this.mItemLoader.loadQuickViewMt2(cursor, str);
        }
        Log.e(this.TAG, "getMediaItem : no data");
        Utils.closeSilently(cursor);
        Runnable runnable = this.mFailCallback;
        if (runnable == null) {
            return null;
        }
        runnable.run();
        return null;
    }

    private String getUriStr() {
        Uri uriData;
        if (isRestartedFromFlipCover()) {
            uriData = getUriFromFlipCoverSavedData();
        } else {
            uriData = this.mLaunchIntent.getUriData();
        }
        return uriData.toString();
    }

    private int getViewIndex() {
        if (isRestartedFromFlipCover()) {
            return getPositionFromFlipCoverSavedData();
        }
        return this.mLaunchIntent.getFromCameraIndex();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchImageQuickView$3(long j2, String str) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).addUri(str));
        MediaItem mediaItem = getMediaItem(str, query);
        if (mediaItem != null) {
            clearPendingFile(mediaItem);
            loadThumbnailSync(mediaItem, (BiConsumer<MediaItem, Bitmap>) null);
            publishCachedCursor(query);
            requestOriginalImage(mediaItem);
            String str2 = this.TAG;
            a.A(new Object[]{MediaItemUtil.getDebugLog(mediaItem), Long.valueOf(j2)}, new StringBuilder("launchImageQuickView "), str2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchVideoQuickView$1(int i2, MediaItem mediaItem, Bitmap bitmap) {
        new VuLauncher(this.mBlackboard).launch(this.mMediaData.getLocationReference(), i2, new MediaItem[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchVideoQuickView$2(long j2, String str, int i2, CountDownLatch countDownLatch) {
        try {
            Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).addUri(str));
            MediaItem mediaItem = getMediaItem(str, query);
            if (mediaItem == null) {
                countDownLatch.countDown();
                return;
            }
            Bitmap decodeUri = BitmapUtils.decodeUri(this.mLaunchIntent.getExtra().getString("currentUri"));
            if (decodeUri != null) {
                this.mBlackboard.publish("data://viewer_first_bitmap", new Object[]{mediaItem, decodeUri});
                new VuLauncher(this.mBlackboard).launch(this.mMediaData.getLocationReference(), i2, new MediaItem[0]);
            } else {
                loadThumbnailSync(mediaItem, new f(this, i2, 0));
            }
            publishCachedCursor(query);
            requestOriginalImage(mediaItem);
            String str2 = this.TAG;
            Log.d(str2, "launchVideoQuickView " + Logger.vt(MediaItemUtil.getDebugLog(mediaItem), Long.valueOf(j2)));
            countDownLatch.countDown();
        } catch (NullPointerException e) {
            String str3 = this.TAG;
            Log.e(str3, "launchVideoQuickView exception" + e);
            countDownLatch.countDown();
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCameraQuickViewItem$4(long j2, MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem.isVideo()) {
            if (this.mMediaData != null) {
                new VuLauncher(this.mBlackboard).launch(this.mMediaData.getLocationReference(), getViewIndex(), new MediaItem[0]);
            }
            this.mBlackboard.publish("data://viewer_first_data", mediaItem);
        }
        requestOriginalImage(mediaItem);
        String str = this.TAG;
        Log.d(str, "loadCameraQuickViewItem {" + mediaItem.getSimpleHashCode() + "} +" + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCameraQuickViewItem$5(long j2) {
        MediaItem loadQuickViewItem = this.mItemLoader.loadQuickViewItem(-1);
        if (loadQuickViewItem == null) {
            Log.e(this.TAG, "loadCameraQuickViewItem : no data");
            Runnable runnable = this.mFailCallback;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        String str = this.TAG;
        Log.p(str, "loadCameraQuickViewItem " + MediaItemUtil.getDebugLog(loadQuickViewItem) + " +" + (System.currentTimeMillis() - j2));
        clearPendingFile(loadQuickViewItem);
        if (!loadQuickViewItem.isVideo()) {
            this.mBlackboard.publish("data://viewer_first_data", loadQuickViewItem);
        }
        loadThumbnailSync(loadQuickViewItem, new d(this, j2, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCameraQuickViewVideoItem$6(long j2, MediaItem mediaItem, Bitmap bitmap) {
        publishVideoQuickViewData(j2, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCameraQuickViewVideoItem$7(long j2, String str) {
        int resumePos = this.mLaunchIntent.getResumePos(str);
        MediaItem loadQuickViewItem = this.mItemLoader.loadQuickViewItem(resumePos);
        if (loadQuickViewItem == null) {
            Log.e(this.TAG, "loadCameraQuickViewVideoItem : no data");
            Runnable runnable = this.mFailCallback;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        String string = this.mLaunchIntent.getExtra().getString("currentUri");
        Bitmap decodeUri = BitmapUtils.decodeUri(string);
        String str2 = this.TAG;
        Log.p(str2, "loadCameraQuickViewVideoItem " + MediaItemUtil.getDebugLog(loadQuickViewItem) + " resumePos=" + resumePos + GlobalPostProcInternalPPInterface.SPLIT_REGEX + string + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.toString(decodeUri) + " +" + (System.currentTimeMillis() - j2));
        clearPendingFile(loadQuickViewItem);
        if (decodeUri != null) {
            this.mBlackboard.publish("data://viewer_first_bitmap", new Object[]{loadQuickViewItem, decodeUri});
            publishVideoQuickViewData(j2, loadQuickViewItem);
            return;
        }
        loadThumbnailSync(loadQuickViewItem, new d(this, j2, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumbnailSync$8(long j2, MediaItem mediaItem, BiConsumer biConsumer, Bitmap bitmap) {
        String str = this.TAG;
        Log.d(str, "loadThumbnailSync " + Logger.v(bitmap) + " +" + (System.currentTimeMillis() - j2));
        this.mBlackboard.publish("data://viewer_first_bitmap", new Object[]{mediaItem, bitmap});
        if (biConsumer != null) {
            biConsumer.accept(mediaItem, bitmap);
        }
    }

    private synchronized void launchImageQuickView(String str, int i2) {
        CameraQuickViewLauncher cameraQuickViewLauncher;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            cameraQuickViewLauncher = this;
            try {
                SimpleThreadPool.getInstance().executeOnPriorThread(new b(cameraQuickViewLauncher, currentTimeMillis, str, 0));
                if (cameraQuickViewLauncher.mMediaData != null) {
                    String str2 = cameraQuickViewLauncher.TAG;
                    Log.d(str2, "launchImageQuickView ViewerData{" + i2 + "}");
                    new VuLauncher(cameraQuickViewLauncher.mBlackboard).launch(cameraQuickViewLauncher.mMediaData.getLocationReference(), i2, new MediaItem[0]);
                }
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cameraQuickViewLauncher = this;
            Throwable th22 = th;
            throw th22;
        }
    }

    private synchronized void launchVideoQuickView(String str, int i2) {
        CameraQuickViewLauncher cameraQuickViewLauncher;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            cameraQuickViewLauncher = this;
            try {
                SimpleThreadPool.getInstance().executeOnPriorThread(new C0394a(cameraQuickViewLauncher, currentTimeMillis, str, i2, countDownLatch));
                countDownLatch.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cameraQuickViewLauncher = this;
            Throwable th22 = th;
            throw th22;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0084, code lost:
        return;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void loadCameraQuickViewItem() {
        /*
            r7 = this;
            java.lang.String r0 = "loadCameraQuickViewItem ViewerData{"
            java.lang.String r1 = "loadCameraQuickViewItem {"
            monitor-enter(r7)
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0081 }
            java.lang.String r4 = r7.getUriStr()     // Catch:{ all -> 0x0081 }
            java.lang.String r5 = r7.TAG     // Catch:{ all -> 0x0081 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r6.<init>(r1)     // Catch:{ all -> 0x0081 }
            r6.append(r4)     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = "}"
            r6.append(r1)     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.support.utils.Log.d(r5, r1)     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.support.utils.SimpleThreadPool r1 = com.samsung.android.gallery.support.utils.SimpleThreadPool.getInstance()     // Catch:{ all -> 0x0081 }
            E3.c r4 = new E3.c     // Catch:{ all -> 0x0081 }
            r5 = 0
            r4.<init>(r7, r2, r5)     // Catch:{ all -> 0x0081 }
            r1.executeOnPriorThread(r4)     // Catch:{ all -> 0x0081 }
            int r1 = r7.getViewIndex()     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = r7.TAG     // Catch:{ all -> 0x0081 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r3.<init>(r0)     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.module.dataset.MediaData r0 = r7.mMediaData     // Catch:{ all -> 0x0081 }
            r4 = 0
            if (r0 == 0) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = r4
        L_0x0043:
            r3.append(r0)     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = ","
            r3.append(r0)     // Catch:{ all -> 0x0081 }
            r3.append(r1)     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = "}"
            r3.append(r0)     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.support.utils.Log.d(r2, r0)     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r7.mLaunchIntent     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = r0.getType()     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = "video"
            boolean r0 = r0.startsWith(r2)     // Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x006a
            monitor-exit(r7)
            return
        L_0x006a:
            com.samsung.android.gallery.module.dataset.MediaData r0 = r7.mMediaData     // Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x0083
            com.samsung.android.gallery.module.viewer.VuLauncher r0 = new com.samsung.android.gallery.module.viewer.VuLauncher     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r7.mBlackboard     // Catch:{ all -> 0x0081 }
            r0.<init>(r2)     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.module.dataset.MediaData r2 = r7.mMediaData     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = r2.getLocationReference()     // Catch:{ all -> 0x0081 }
            com.samsung.android.gallery.module.data.MediaItem[] r3 = new com.samsung.android.gallery.module.data.MediaItem[r4]     // Catch:{ all -> 0x0081 }
            r0.launch(r2, r1, r3)     // Catch:{ all -> 0x0081 }
            goto L_0x0083
        L_0x0081:
            r0 = move-exception
            goto L_0x0085
        L_0x0083:
            monitor-exit(r7)
            return
        L_0x0085:
            monitor-exit(r7)     // Catch:{ all -> 0x0081 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.CameraQuickViewLauncher.loadCameraQuickViewItem():void");
    }

    @Deprecated
    private synchronized boolean loadCameraQuickViewVideoItem() {
        CameraQuickViewLauncher cameraQuickViewLauncher;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String uri = this.mLaunchIntent.getUriData().toString();
            String str = this.TAG;
            Log.d(str, "loadCameraQuickViewVideoItem {" + uri + "}");
            LatchBuilder latchBuilder = new LatchBuilder("camQuickVideo");
            cameraQuickViewLauncher = this;
            try {
                latchBuilder.addWorker(new b(cameraQuickViewLauncher, currentTimeMillis, uri, 1));
                latchBuilder.start();
                return true;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cameraQuickViewLauncher = this;
            Throwable th22 = th;
            throw th22;
        }
    }

    private void loadThumbnailSync(MediaItem mediaItem, BiConsumer<MediaItem, Bitmap> biConsumer) {
        MediaItem mediaItem2 = mediaItem;
        ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem2, ThumbKind.MEDIUM_KIND, new e(this, System.currentTimeMillis(), mediaItem2, (BiConsumer) biConsumer), true);
    }

    private void publishCachedCursor(Cursor cursor) {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null) {
            Log.d(this.TAG, "publishCachedCursor fail");
        } else if (this.mBlackboard.isEmpty(DataKey.DATA_CURSOR(mediaData.getLocationKey()))) {
            this.mBlackboard.publishIfEmpty(DataKey.CACHED_DATA_CURSOR(this.mMediaData.getLocationKey()), new Cursor[]{cursor});
        } else {
            String str = this.TAG;
            Log.i(str, "publishCachedCursor skip" + DataKey.CACHED_DATA_CURSOR(this.mMediaData.getLocationKey()));
        }
    }

    private void publishVideoQuickViewData(long j2, MediaItem mediaItem) {
        if (this.mMediaData != null) {
            new VuLauncher(this.mBlackboard).launch(this.mMediaData.getLocationReference(), this.mLaunchIntent.getFromCameraIndex(), new MediaItem[0]);
        }
        this.mBlackboard.publish("data://viewer_first_data", mediaItem);
        requestOriginalImage(mediaItem);
        String str = this.TAG;
        Log.d(str, "loadCameraQuickViewVideoItem {" + mediaItem.getSimpleHashCode() + "} +" + (System.currentTimeMillis() - j2));
    }

    private void requestOriginalImage(MediaItem mediaItem) {
        this.mBlackboard.publishIfEmpty(CommandKey.DATA_REQUEST_URGENT(MediaItemUtil.getViewerBitmapKey(mediaItem, 0)), mediaItem);
    }

    public void execute() {
        String str;
        String str2;
        if (hasViewBuckets()) {
            int[] array = this.mLaunchIntent.getViewBuckets().stream().mapToInt(new C0545b(7)).toArray();
            this.mAlbumIds = array;
            str = DataKey.getQuickViewDataKey(array);
        } else {
            str = DataKey.getQuickViewDataKeyLegacy();
        }
        if (Features.isEnabled(Features.SUPPORT_PPP_V2) && this.mLaunchIntent.isPostProcessing()) {
            str = ArgumentsUtil.appendArgs(str, "ppp_uri", this.mLaunchIntent.getUriData().toString());
        }
        this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(str);
        boolean startsWith = this.mLaunchIntent.getType().startsWith("video");
        if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            String uriStr = getUriStr();
            int viewIndex = getViewIndex();
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("launchQuickView");
            Integer valueOf = Integer.valueOf(viewIndex);
            if (startsWith) {
                str2 = "v";
            } else {
                str2 = "i";
            }
            sb2.append(Logger.v(uriStr, valueOf, str2));
            Log.d(str3, sb2.toString());
            if (startsWith) {
                launchVideoQuickView(uriStr, viewIndex);
            } else {
                launchImageQuickView(uriStr, viewIndex);
            }
        } else if (startsWith) {
            loadCameraQuickViewVideoItem();
        } else {
            loadCameraQuickViewItem();
        }
    }
}
