package com.samsung.android.gallery.module.publisher;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.SuggestedLocalApi;
import com.samsung.android.gallery.database.dal.mp.helper.CleanOutBurstSimilarApi;
import com.samsung.android.gallery.database.dal.mp.helper.HighlightApi;
import com.samsung.android.gallery.database.dal.mp.helper.MotionPhotoClipApi;
import com.samsung.android.gallery.database.dal.mp.helper.RevitalizedApi;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MtpMediaItemLoader;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ListDataPublisher extends CursorPublisher {
    private Context mAppContext;
    boolean mDevelopDelay;
    private final Runnable[] mReloadFullData = new Runnable[3];
    private final AtomicBoolean mThumbnailLoadDone = new AtomicBoolean(false);

    public ListDataPublisher(Blackboard blackboard) {
        super(blackboard);
        boolean z = DeviceConfig.UNIT_TEST;
        this.mDevelopDelay = false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishMtpData$8(Cursor[] cursorArr, Context context) {
        cursorArr[0] = MtpMediaItemLoader.getMtpDeviceCursor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishMtpData$9(Cursor[] cursorArr, long j2) {
        if (cursorArr[0] != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "publishMtpData : " + getCursorInfo(cursorArr[0], j2));
            publishCursorArray("location://mtp", new Cursor[]{cursorArr[0]});
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPrivateAlbumData$13(Cursor[] cursorArr) {
        cursorArr[3] = PrivateDatabase.getInstance().queryRealRatio();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPrivateAlbumData$14(Cursor[] cursorArr) {
        cursorArr[1] = PrivateDatabase.getInstance().queryDays();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPrivateAlbumData$15(Cursor[] cursorArr) {
        cursorArr[0] = PrivateDatabase.getInstance().queryData();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishPrivateAlbumData$16(Cursor[] cursorArr, long j2, String str) {
        a.y(new StringBuilder("publishPrivateAlbumData "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPrivateTrashData$17(Cursor[] cursorArr) {
        cursorArr[3] = PrivateDatabase.getInstance().queryTrashRealRatio();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishPrivateTrashData$18(Cursor[] cursorArr) {
        cursorArr[0] = PrivateDatabase.getInstance().queryTrashData();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishPrivateTrashData$19(Cursor[] cursorArr, long j2, String str) {
        a.y(new StringBuilder("publishPrivateTrashData "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishTimelineData$3(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishTimelineData$4(QueryParams queryParams, Cursor[] cursorArr, long j2) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishTimelineData(T) " + queryParams + " " + getCursorListInfo(cursorArr, j2));
        this.mBlackboard.erase(DataKey.CACHED_DATA_CURSOR("location://timeline"));
        publishCursorArray("location://timeline", cursorArr);
        dumpDataCount(PreferenceName.LAST_FILE_COUNT, getCursorCount(cursorArr[0]));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishTimelineData$5(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishTimelineData$7(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[5] = loadFileIdCursor(queryParams, DbKey.TIMELINE_FILE_IDS_ORDERED);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishTimelineFakeData$0(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams.setLimit(0, 3000));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishTimelineFakeData$1(Cursor[] cursorArr, Bundle bundle) {
        String dataStamp = YearQueryCache.getInstance().getDataStamp();
        if (dataStamp == null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "YearDataStamp = " + dataStamp);
            cursorArr[1] = DbCompat.query(createTimelineQueryParams(bundle).setDbKey(DbKey.TIMELINE_DAY));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishTimelineFakeData$2(QueryParams queryParams, Cursor[] cursorArr, long j2) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("publishTimelineData(fake) ");
        sb2.append(queryParams);
        sb2.append(" ");
        a.y(sb2, getCursorListInfo(cursorArr, j2), stringCompat);
        publishTimelineFakeCursor(cursorArr, 3000, j2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishTrashData$10(Cursor[] cursorArr, ITrashProvider iTrashProvider, boolean z) {
        cursorArr[5] = iTrashProvider.loadTrashIdsCursor(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishTrashData$11(Cursor[] cursorArr, ITrashProvider iTrashProvider, boolean z) {
        cursorArr[3] = iTrashProvider.loadTrashRealRatioCursor(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishTrashData$12(Cursor[] cursorArr, long j2) {
        a.y(new StringBuilder("publishTrashData : "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://trash", cursorArr);
    }

    /* access modifiers changed from: private */
    public void onContext(Object obj, Bundle bundle) {
        this.mAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public void onThumbnailLoadDone(Object obj, Bundle bundle) {
        boolean z;
        boolean z3;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onThumbnailLoadDone {");
        if (this.mReloadFullData[0] != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mReloadFullData[1] != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        sb2.append(z3);
        sb2.append("}");
        Log.p(stringCompat, sb2.toString());
        this.mThumbnailLoadDone.set(true);
        this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://timeline/fake"));
        this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://albums/cache"));
        for (Runnable runnable : this.mReloadFullData) {
            if (runnable != null) {
                ThreadUtil.removeCallbackOnBgThread(runnable);
                runnable.run();
            }
        }
    }

    /* access modifiers changed from: private */
    public void publishAllPictures(Object obj, Bundle bundle) {
        int i2;
        Cursor query;
        String DATA_REQUEST_TO_LOCATION = CommandKey.DATA_REQUEST_TO_LOCATION(BundleWrapper.getKey(bundle));
        try {
            Trace.beginSection("publishAllPictures");
            long currentTimeMillis = System.currentTimeMillis();
            QueryParams createTimelineQueryParams = createTimelineQueryParams(bundle);
            long j2 = BundleWrapper.getLong(bundle, "date_time", -1);
            if (j2 != -1) {
                long[] jArr = {new TimeUtil(j2).startOfMonthsAgo(1), new TimeUtil(j2).startOfMonthsAgo(-1)};
                createTimelineQueryParams.setCreationTimeLimit(jArr[0], jArr[1]);
            }
            Cursor[] cursorArr = new Cursor[6];
            Cursor loadFileIdCursor = loadFileIdCursor(createTimelineQueryParams, DbKey.ALL_PICTURES_ID);
            cursorArr[5] = loadFileIdCursor;
            Log.p(this.TAG, "publishAllPictures(D) " + createTimelineQueryParams + " " + getCursorListInfo(cursorArr, currentTimeMillis));
            publishCursorArray(DATA_REQUEST_TO_LOCATION, cursorArr);
            MediaItem mediaItem = null;
            String string = BundleWrapper.getString(bundle, "publish_on_ready", (String) null);
            if (!TextUtils.isEmpty(string)) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (loadFileIdCursor instanceof CollectionCursor) {
                    long j3 = BundleWrapper.getLong(bundle, "file_id", 0);
                    if (j3 > 0) {
                        i2 = Math.max(((CollectionCursor) loadFileIdCursor).indexOf(Long.valueOf(j3)), 0);
                        query = DbCompat.query(createTimelineQueryParams.clone().setDbKey(DbKey.ALL_PICTURES).setFileId(j3));
                        if (query != null) {
                            if (query.moveToFirst()) {
                                mediaItem = MediaItemLoader.load(query);
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                        String appendArgs = ArgumentsUtil.appendArgs(string, Message.KEY_POSITION, String.valueOf(i2));
                        Log.d(this.TAG, "publishAllPictures(D)#moveTo" + Logger.vt(appendArgs, Long.valueOf(currentTimeMillis2)));
                        new VuLauncher(this.mBlackboard).launch(appendArgs, 0, mediaItem);
                    }
                }
                i2 = 0;
                String appendArgs2 = ArgumentsUtil.appendArgs(string, Message.KEY_POSITION, String.valueOf(i2));
                Log.d(this.TAG, "publishAllPictures(D)#moveTo" + Logger.vt(appendArgs2, Long.valueOf(currentTimeMillis2)));
                new VuLauncher(this.mBlackboard).launch(appendArgs2, 0, mediaItem);
            }
            Trace.endSection();
            return;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public void publishCleanOutBurstSimilarPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cleanOutBurstSimilarCursor = new CleanOutBurstSimilarApi().getCleanOutBurstSimilarCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishCleanOutBurstSimilarPicturesData : " + getCursorInfo(cleanOutBurstSimilarCursor, currentTimeMillis));
        publishCursorArray("location://cleanOut/burstSimilar/fileList", new Cursor[]{cleanOutBurstSimilarCursor});
    }

    /* access modifiers changed from: private */
    public void publishCleanOutPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cleanOutSuggestedCursor = DbCompat.suggestedApi().getCleanOutSuggestedCursor(Integer.parseInt(bundle.getString("delete_type", "-1")));
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishCleanPicturesData : " + getCursorInfo(cleanOutSuggestedCursor, currentTimeMillis));
        publishCursorArray("location://cleanOut/fileList", new Cursor[]{cleanOutSuggestedCursor});
    }

    /* access modifiers changed from: private */
    public void publishDuplicatedPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cleanOutDuplicatedCursor = DbCompat.suggestedApi().getCleanOutDuplicatedCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishDuplicatedPicturesData : " + getCursorInfo(cleanOutDuplicatedCursor, currentTimeMillis));
        publishCursorArray("location://cleanOut/duplicated/fileList", new Cursor[]{cleanOutDuplicatedCursor});
    }

    /* access modifiers changed from: private */
    public void publishHighlightPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor highlightCursor = new HighlightApi().getHighlightCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishHighlightPicturesData : " + getCursorInfo(highlightCursor, currentTimeMillis));
        publishCursorArray("location://highlight/fileList", new Cursor[]{highlightCursor});
    }

    /* access modifiers changed from: private */
    public void publishMotionPhotoClipPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor motionPhotoClipCursor = new MotionPhotoClipApi().getMotionPhotoClipCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishMotionPhotoClipPicturesData : " + getCursorInfo(motionPhotoClipCursor, currentTimeMillis));
        publishCursorArray("location://cleanOut/motionPhotoClip/fileList", new Cursor[]{motionPhotoClipCursor});
    }

    /* access modifiers changed from: private */
    public void publishMtpData(Object obj, Bundle bundle) {
        Context context = this.mAppContext;
        if (context != null) {
            Cursor[] cursorArr = new Cursor[1];
            new LatchBuilder("publishMtpData").addWorker(new r(2, cursorArr, context)).setPostExecutor((Runnable) new B(this, cursorArr, System.currentTimeMillis(), 1)).start();
        }
    }

    /* access modifiers changed from: private */
    public void publishMtpFileData(Object obj, Bundle bundle) {
        Context context = this.mAppContext;
        if (context != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Cursor mtpDeviceItemCursor = MtpMediaItemLoader.getMtpDeviceItemCursor(context, this.mBlackboard, MtpClient.getInstance(context).getDeviceNameFromPath(bundle.getString("__absPath")));
            if (mtpDeviceItemCursor != null) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "publishMtpFileData : " + getCursorInfo(mtpDeviceItemCursor, currentTimeMillis));
                publishCursorArray("location://mtp/fileList", new Cursor[]{mtpDeviceItemCursor});
            }
        }
    }

    /* access modifiers changed from: private */
    public void publishPortraitPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor portraitCursor = DbCompat.suggestedApi().getPortraitCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishPortraitPicturesData : " + getCursorInfo(portraitCursor, currentTimeMillis));
        publishCursorArray("location://portrait/fileList", new Cursor[]{portraitCursor});
    }

    /* access modifiers changed from: private */
    public void publishQuickViewFileData(Object obj, Bundle bundle) {
        Trace.beginSection("publishQuickViewFileData");
        long currentTimeMillis = System.currentTimeMillis();
        QueryParams createQueryParams = createQueryParams(bundle);
        if (this.mDevelopDelay && RandomNumber.nextBoolean()) {
            Log.i(this.TAG, "[Develop only] Give 1sec delay to test quickView data loading delay case");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.mDevelopDelay = false;
        Cursor[] cursorArr = new Cursor[1];
        if (bundle.get("id") == null && bundle.get("ids") == null) {
            cursorArr[0] = DbCompat.query(createQueryParams.setDbKey(DbKey.TIMELINE).addDataStamp());
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("publishQuickViewFileData(T) ");
            sb2.append(createQueryParams);
            sb2.append(" ");
            a.y(sb2, getCursorListInfo(cursorArr, currentTimeMillis), stringCompat);
        } else {
            cursorArr[0] = DbCompat.query(createQueryParams.setDbKey(DbKey.ALBUM_FILES).addDataStamp());
            StringCompat stringCompat2 = this.TAG;
            StringBuilder sb3 = new StringBuilder("publishQuickViewFileData(A) ");
            sb3.append(createQueryParams);
            sb3.append(" ");
            a.y(sb3, getCursorListInfo(cursorArr, currentTimeMillis), stringCompat2);
        }
        publishCursorArray("location://quickView/fileList", cursorArr);
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishRevitalizedPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor revitalizedCursor = new RevitalizedApi().getRevitalizedCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishRevitalizedPicturesData : " + getCursorInfo(revitalizedCursor, currentTimeMillis));
        publishCursorArray("location://revitalized/fileList", new Cursor[]{revitalizedCursor});
    }

    /* access modifiers changed from: private */
    public void publishSuggestionsData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor loadSuggestedInfo = new SuggestedLocalApi().loadSuggestedInfo();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishSuggestionsData : " + getCursorInfo(loadSuggestedInfo, currentTimeMillis));
        publishCursorArray("location://suggestions", new Cursor[]{loadSuggestedInfo});
    }

    /* access modifiers changed from: private */
    public void publishTimelineData(Object obj, Bundle bundle) {
        Bundle bundle2 = bundle;
        if (!this.mBlackboard.isEmpty(CommandKey.DATA_REQUEST("location://timeline/fake"))) {
            Log.w(this.TAG, "publishTimelineData postponed while fake loading");
            return;
        }
        try {
            Trace.beginSection("publishTimelineData");
            long currentTimeMillis = System.currentTimeMillis();
            this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(currentTimeMillis));
            boolean z = BundleWrapper.getBoolean(bundle2, "disableTimeline", false);
            QueryParams createTimelineQueryParams = createTimelineQueryParams(bundle2);
            QueryParams dbKey = createTimelineQueryParams.setDbKey(DbKey.TIMELINE);
            if (z) {
                Cursor[] cursorArr = {DbCompat.query(dbKey)};
                StringCompat stringCompat = this.TAG;
                Log.p(stringCompat, "publishTimelineData(D) " + dbKey + " " + getCursorListInfo(cursorArr, currentTimeMillis));
                publishCursorArray("location://timeline", cursorArr);
            } else {
                Cursor[] cursorArr2 = {null, null, null, null, null, null};
                QueryParams dbKey2 = createTimelineQueryParams.clone().setDbKey(DbKey.TIMELINE_DAY);
                QueryParams dbKey3 = createTimelineQueryParams.clone().setDbKey(DbKey.TIMELINE_REALRATIO);
                QueryParams queryParams = dbKey;
                LatchBuilder postExecutor = new LatchBuilder("publishTimelineData(T)").addWorker(new C0625e(cursorArr2, 6, dbKey2)).setPostExecutor((Runnable) new C0644y(this, queryParams, cursorArr2, currentTimeMillis, 0));
                if (CursorPublisher.SUPPORT_REAL_RATIO) {
                    postExecutor.addWorker(new C0625e(cursorArr2, 7, dbKey3));
                }
                postExecutor.setCurrent(new C0636p(this, cursorArr2, queryParams, 1));
                postExecutor.start();
            }
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public void publishTimelineFakeData(Object obj, Bundle bundle) {
        Trace.beginSection("publishTimelineFakeData");
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(currentTimeMillis));
        QueryParams dbKey = createTimelineQueryParams(bundle).setDbKey(DbKey.TIMELINE);
        if (GridHelper.isTimelineYear()) {
            Cursor[] cursorArr = new Cursor[2];
            new LatchBuilder("publishTimelineData(fake)").setCurrent(new C0625e(cursorArr, 8, dbKey)).addWorker(new C0636p(this, cursorArr, bundle, 2)).setPostExecutor((Runnable) new C0644y(this, dbKey, cursorArr, currentTimeMillis, 1)).start();
        } else {
            Cursor[] cursorArr2 = {DbCompat.query(dbKey.setLimit(0, 3000))};
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("publishTimelineData(fake) ");
            sb2.append(dbKey);
            sb2.append(" ");
            a.y(sb2, getCursorListInfo(cursorArr2, currentTimeMillis), stringCompat);
            publishTimelineFakeCursor(cursorArr2, 3000, currentTimeMillis);
        }
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void publishTrashData(Object obj, Bundle bundle) {
        boolean z;
        Context context = this.mAppContext;
        if (context != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!SamsungCloudCompat.isSyncOn(context) || Features.isEnabled(Features.IS_UPSM)) {
                z = true;
            } else {
                z = false;
            }
            ITrashProvider instance = TrashProviderFactory.getInstance(context);
            Cursor[] cursorArr = {null, null, null, null, null, null};
            new LatchBuilder("publishTrashData").setCurrent(new A(cursorArr, instance, z, 0)).addWorker(new A(cursorArr, instance, z, 1)).setPostExecutor((Runnable) new B(this, cursorArr, currentTimeMillis, 0)).start();
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("data://app_context", new C0643x(this, 0)));
        arrayList.add(new SubscriberInfo("lifecycle://on_thumbnail_load_done", new C0643x(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://quickView/fileList"), new C0643x(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://timeline"), new C0643x(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://allPictures"), new C0643x(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://timeline/fake"), new C0643x(this, 5)).setHighPriority());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://mtp"), new C0643x(this, 6)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://mtp/fileList"), new C0643x(this, 7)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://trash"), new C0643x(this, 8)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://suggestions"), new C0643x(this, 9)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://cleanOut/fileList"), new C0643x(this, 10)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://cleanOut/motionPhotoClip/fileList"), new C0643x(this, 11)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://cleanOut/duplicated/fileList"), new C0643x(this, 12)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://cleanOut/burstSimilar/fileList"), new C0643x(this, 13)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://revitalized/fileList"), new C0643x(this, 14)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://highlight/fileList"), new C0643x(this, 15)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://portrait/fileList"), new C0643x(this, 16)));
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://albums/private/fileList"), new C0643x(this, 17)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://private/trash"), new C0643x(this, 18)));
        }
    }

    public synchronized void loadTimelineFullData() {
        try {
            this.mReloadFullData[0] = null;
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://timeline/fake"));
            String str = "location://timeline";
            if (!PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
                str = PickerUtil.appendPickerArgs(this.mBlackboard, str);
            }
            this.mBlackboard.publish(CommandKey.DATA_REQUEST(str), (Object) null);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void publishPrivateAlbumData(Object obj, Bundle bundle) {
        if (this.mAppContext != null) {
            Cursor[] cursorArr = {null, null, null, null, null, null};
            new LatchBuilder("publishPrivateAlbumData").addWorker(new C0628h(cursorArr, 3)).addWorker(new C0628h(cursorArr, 4)).setCurrent(new C0628h(cursorArr, 5)).setPostExecutor((Runnable) new C0645z(this, cursorArr, System.currentTimeMillis(), getLocationKeyFromReq(bundle), 1)).start();
        }
    }

    public void publishPrivateTrashData(Object obj, Bundle bundle) {
        if (this.mAppContext != null) {
            Cursor[] cursorArr = {null, null, null, null, null, null};
            new LatchBuilder("publishPrivateTrashData").addWorker(new C0628h(cursorArr, 1)).setCurrent(new C0628h(cursorArr, 2)).setPostExecutor((Runnable) new C0645z(this, cursorArr, System.currentTimeMillis(), getLocationKeyFromReq(bundle), 0)).start();
        }
    }

    public void publishTimelineFakeCursor(Cursor[] cursorArr, int i2, long j2) {
        this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://timeline/fake"));
        Cursor cursor = cursorArr[0];
        if (cursor == null) {
            Log.e(this.TAG, "publishTimelineData(fake) : query failed. request full loading");
            Runnable[] runnableArr = this.mReloadFullData;
            C0639t tVar = new C0639t(this, 1);
            runnableArr[0] = tVar;
            ThreadUtil.postOnBgThreadDelayed(tVar, 400);
        } else if (cursor.getCount() == i2) {
            publishCachedCursorArray("location://timeline", cursorArr);
            if (this.mThumbnailLoadDone.get()) {
                loadTimelineFullData();
                return;
            }
            Runnable[] runnableArr2 = this.mReloadFullData;
            C0639t tVar2 = new C0639t(this, 1);
            runnableArr2[0] = tVar2;
            ThreadUtil.postOnBgThreadDelayed(tVar2, 400);
        } else {
            publishCursorArray("location://timeline", cursorArr);
            dumpDataCount(PreferenceName.LAST_FILE_COUNT, getCursorCount(cursorArr[0]));
        }
    }
}
