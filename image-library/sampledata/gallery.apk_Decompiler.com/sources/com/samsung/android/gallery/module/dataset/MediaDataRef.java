package com.samsung.android.gallery.module.dataset;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.StaleDataException;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.CameraItemLoader;
import com.samsung.android.gallery.module.data.DataStamp;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaDataRef extends Subscriber implements MediaData {
    protected static final boolean PRIVATE_LOG = Logger.isAllowPrivateLog();
    private final Object THREAD_POOL_LOCK = new Object();
    private boolean mCheckPppCursorExpire = false;
    protected int mDataCount = -1;
    private volatile int mDataVersion = 0;
    protected int mFakeLoadingCount;
    protected boolean mForceSwap;
    private GridHelper mGridHelper;
    DataStamp mLastDataStamp;
    protected final AtomicLong mLastPppUpdateTime = new AtomicLong(0);
    protected final ArrayList<MediaData.OnDataChangeListener> mListener = new ArrayList<>();
    protected final String mLocationKey;
    protected String mLocationReference;
    protected Handler mMainHandler = ThreadUtil.createMainThreadHandler();
    protected ContentObserver mPppObserver;
    protected final PppUpdater mPppUpdater = new PppUpdater();
    protected MediaItem mPreloadedPppItem;
    protected final AtomicInteger mRefCount = new AtomicInteger(0);
    protected volatile ThreadPool mThreadPool;

    public MediaDataRef(Blackboard blackboard, String str) {
        super(blackboard);
        this.mLocationKey = ArgumentsUtil.removeArgs(str);
        this.mLocationReference = str;
        if (PreferenceFeatures.OneUi30.YEAR_CLUSTERING) {
            this.mFakeLoadingCount = ArgumentsUtil.getArgValue(str, "fakeLoadingCount", 0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unregisterPppObserver$0(Context context) {
        context.getContentResolver().unregisterContentObserver(this.mPppObserver);
    }

    /* access modifiers changed from: private */
    public void onPppCompleted(Object obj, Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            MediaItem mediaItem = (MediaItem) obj;
            if (isDataAvailable()) {
                this.mLastPppUpdateTime.set(BundleWrapper.getLong(bundle, "date_time", System.currentTimeMillis()));
                try {
                    onPppUpdate(mediaItem);
                } catch (Error | Exception e) {
                    if (!isDataAvailable() || this.mRefCount.get() <= 0) {
                        StringCompat stringCompat = this.TAG;
                        Log.w(stringCompat, "exception after destroy : " + e.getMessage());
                        return;
                    }
                    throw e;
                }
            } else {
                this.mPreloadedPppItem = mediaItem;
            }
        }
    }

    private void onPppUpdate(MediaItem mediaItem) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            this.mPppUpdater.onUpdatePppMediaItem(this, mediaItem, this.mBlackboard);
        }
    }

    private boolean updateDataStampIfDiff(DataStamp dataStamp) {
        DataStamp dataStamp2 = this.mLastDataStamp;
        if (dataStamp2 != null && dataStamp2.equals(dataStamp)) {
            return false;
        }
        this.mLastDataStamp = dataStamp;
        return true;
    }

    private boolean updateDataStampIfPpp(DataStamp dataStamp) {
        DataStamp dataStamp2 = this.mLastDataStamp;
        if (dataStamp2 == null || !dataStamp2.isPppChanged(dataStamp)) {
            return false;
        }
        this.mLastDataStamp = dataStamp;
        return true;
    }

    public void close() {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("close {");
        sb2.append(this.mLocationKey);
        sb2.append(',');
        sb2.append(this.mRefCount.get() - 1);
        sb2.append('}');
        Log.d(stringCompat, sb2.toString());
        if (this.mRefCount.decrementAndGet() == 0) {
            onDestroy();
        }
    }

    public final void closeCursors(Cursor[] cursorArr) {
        if (cursorArr != null) {
            for (Cursor cursor : cursorArr) {
                if (cursor != null && !cursor.isClosed()) {
                    Utils.closeSilently(cursor);
                }
            }
        }
    }

    public int getCount() {
        return Math.max(this.mDataCount, 0);
    }

    public final int getCursorCount(Cursor cursor) {
        if (cursor == null) {
            return 0;
        }
        try {
            return cursor.getCount();
        } catch (StaleDataException unused) {
            return 0;
        }
    }

    public int getDataVersion() {
        return this.mDataVersion;
    }

    public GridHelper getGridHelper() {
        if (this.mGridHelper == null) {
            this.mGridHelper = GridHelper.getInstance(getLocationKey());
        }
        return this.mGridHelper;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public String getLocationReference() {
        return this.mLocationReference;
    }

    public int getRealCount() {
        return Math.max(this.mDataCount, 0);
    }

    public int getRefCount() {
        return this.mRefCount.get();
    }

    public ThreadPool getThreadPool() {
        if (this.mThreadPool == null) {
            synchronized (this.THREAD_POOL_LOCK) {
                try {
                    if (this.mThreadPool == null) {
                        this.mThreadPool = ThreadPool.createPrivateInstance("md-" + hashCode());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mThreadPool;
    }

    public boolean hasSameData(EventMessage eventMessage) {
        String str;
        Bundle data = eventMessage.getData();
        if (data == null) {
            str = null;
        } else {
            str = data.getString("data_stamp");
        }
        if (str == null || updateDataStampIfDiff(new DataStamp(str))) {
            return false;
        }
        return true;
    }

    public final int increaseDataVersion() {
        int i2 = this.mDataVersion;
        this.mDataVersion = i2 + 1;
        return i2;
    }

    public boolean isCursorExpired(Cursor[] cursorArr) {
        if (!Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            return false;
        }
        long j2 = this.mLastPppUpdateTime.get();
        if (j2 == 0) {
            return false;
        }
        long queryTime = CursorHelper.getQueryTime(cursorArr);
        if (queryTime >= j2) {
            return false;
        }
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "ppp cursor expired" + Logger.v(Long.valueOf(queryTime), Long.valueOf(j2)));
        return true;
    }

    public boolean isDataAvailable() {
        if (this.mDataCount >= 0) {
            return true;
        }
        return false;
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public final boolean isInvalidCursors(Cursor[] cursorArr) {
        if (cursorArr == null || cursorArr.length == 0) {
            Log.e(this.TAG, "cursor array length = 0");
            return true;
        }
        int length = cursorArr.length;
        int i2 = 0;
        while (i2 < length) {
            Cursor cursor = cursorArr[i2];
            if (cursor == null || !cursor.isClosed()) {
                i2++;
            } else {
                Log.e(this.TAG, "cursor closed : " + cursor);
                return true;
            }
        }
        if (!Features.isEnabled(Features.SUPPORT_PPP_V3) || !this.mCheckPppCursorExpire || !isDataAvailable() || !isCursorExpired(cursorArr)) {
            return false;
        }
        closeCursors(cursorArr);
        requestData(this.mLocationReference);
        return true;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 101) {
            return true;
        }
        return false;
    }

    public boolean isStory() {
        String str = this.mLocationKey;
        if (str == null || !str.startsWith("location://story/albums")) {
            return false;
        }
        return true;
    }

    public boolean isStoryChanged(Uri uri) {
        if (uri == null || !"content://com.samsung.cmh/story".equals(uri.toString())) {
            return false;
        }
        return true;
    }

    public final void notifyChanged() {
        if (Features.isEnabled(Features.SUPPORT_PPP_V3) && this.mPreloadedPppItem != null) {
            Log.d(this.TAG, "pppUpdate with preloaded item");
            onPppUpdate(this.mPreloadedPppItem);
            this.mPreloadedPppItem = null;
        }
        increaseDataVersion();
        notifyChangedPrimitive();
    }

    public final void notifyChangedPrimitive() {
        synchronized (this.mListener) {
            try {
                Iterator it = new ArrayList(this.mListener).iterator();
                while (it.hasNext()) {
                    ((MediaData.OnDataChangeListener) it.next()).onDataChanged();
                }
                this.mListener.removeIf(new C0598e(5));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void notifyDataRangeChanged(int i2, int i7) {
        increaseDataVersion();
        synchronized (this.mListener) {
            try {
                Iterator it = new ArrayList(this.mListener).iterator();
                while (it.hasNext()) {
                    ((MediaData.OnDataChangeListener) it.next()).onDataRangeChanged(i2, i7);
                }
                this.mListener.removeIf(new C0598e(5));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void notifyDataRangeInserted(int i2, int i7) {
        increaseDataVersion();
        if (i7 != 0) {
            synchronized (this.mListener) {
                try {
                    Iterator it = new ArrayList(this.mListener).iterator();
                    while (it.hasNext()) {
                        ((MediaData.OnDataChangeListener) it.next()).onDataRangeInserted(i2, i7);
                    }
                    this.mListener.removeIf(new C0598e(5));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void onCreate() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onCreate " + this);
        super.onCreate();
        getThreadPool();
    }

    public void onDataChanged(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        if (eventMessage != null && isListeningEvent(eventMessage)) {
            if (isFilteredEvent(eventMessage)) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "onDataChanged(filtered) " + eventMessage);
            } else if (!SdkConfig.atLeast(SdkConfig.SEM.T) || !hasSameData(eventMessage)) {
                StringCompat stringCompat2 = this.TAG;
                Log.d(stringCompat2, "onDataChanged(executed) " + eventMessage + ArcCommonLog.TAG_COMMA + this.mLastDataStamp);
                requestData(this.mLocationReference, eventMessage);
                if (!this.mForceSwap) {
                    boolean z = true;
                    if (eventMessage.arg1 != 1) {
                        z = false;
                    }
                    this.mForceSwap = z;
                }
            } else {
                StringCompat stringCompat3 = this.TAG;
                Log.d(stringCompat3, "onDataChanged(duplicated) " + this.mLastDataStamp);
            }
        }
    }

    public void onDataChangedGmp(Object obj, Bundle bundle) {
        Log.d(this.TAG, "onDataChangedGmp(executed) ");
        requestData(this.mLocationReference, (EventMessage) null);
        this.mForceSwap = true;
    }

    public void onDataDirty(Object obj, Bundle bundle) {
        Log.i(this.TAG, "onDataDirty : set force swap");
        this.mForceSwap = true;
    }

    public void onDestroy() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDestroy " + this);
        super.onDestroy();
        this.mBlackboard.erase(DataKey.DATA_CURSOR(this.mLocationKey));
        synchronized (this.mListener) {
            this.mListener.clear();
        }
        this.mDataCount = -1;
        this.mDataVersion = 0;
        this.mLastDataStamp = null;
        synchronized (this.THREAD_POOL_LOCK) {
            try {
                if (this.mThreadPool != null) {
                    this.mThreadPool.shutDown();
                    this.mThreadPool = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public MediaData open(String str, boolean z) {
        if (!z) {
            setLocationKey(str);
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "open {" + this.mLocationKey + ',' + (this.mRefCount.get() + 1) + '}');
        if (this.mRefCount.getAndIncrement() == 0) {
            onCreate();
            requestIfRequired(this.mBlackboard, this.mLocationReference);
        }
        return this;
    }

    public void preloadPppItem(String str, Consumer<MediaItem> consumer) {
        String str2;
        Cursor query;
        Throwable th;
        MediaItem load;
        Context appContext = AppResources.getAppContext();
        if (appContext != null && str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Uri parse = Uri.parse(MediaUri.getCameraPppUri());
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            if (MediaUri.isSecMediaUri(str)) {
                str2 = "sec_media_id=";
            } else {
                str2 = "media_id=";
            }
            try {
                query = appContext.getContentResolver().query(parse, new String[]{"*"}, C0212a.l(str2, lastPathSegment), (String[]) null, (String) null, (CancellationSignal) null);
                if (query != null) {
                    if (query.getCount() > 0 && query.moveToFirst() && (load = CameraItemLoader.load(query)) != null && !load.isPostProcessing()) {
                        StringCompat stringCompat = this.TAG;
                        Log.d(stringCompat, "ppp preload " + load + " +" + (System.currentTimeMillis() - currentTimeMillis));
                        if (MediaItemUtil.verifyPppCompletion(load)) {
                            consumer.accept(load);
                        } else {
                            StringCompat stringCompat2 = this.TAG;
                            Log.w(stringCompat2, "ppp update ignore wrong data " + load);
                        }
                        query.close();
                        return;
                    }
                }
                if (query == null) {
                    return;
                }
                query.close();
                return;
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "ppp preload failed ", (Throwable) e);
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            return;
        }
        throw th;
    }

    public void reInit(String str) {
        new InternalException("reInit not supported").post();
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "reInit not supported " + str);
    }

    public abstract MediaItem read(int i2);

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void register(com.samsung.android.gallery.module.dataset.MediaData.OnDataChangeListener r3) {
        /*
            r2 = this;
            java.util.ArrayList<com.samsung.android.gallery.module.dataset.MediaData$OnDataChangeListener> r0 = r2.mListener
            monitor-enter(r0)
            java.util.ArrayList<com.samsung.android.gallery.module.dataset.MediaData$OnDataChangeListener> r1 = r2.mListener     // Catch:{ all -> 0x001c }
            boolean r1 = r1.contains(r3)     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x0024
            boolean r1 = r2.isDataAvailable()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001e
            r3.onDataChanged()     // Catch:{ all -> 0x001c }
            boolean r1 = r3.isInstant()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001e
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r2 = move-exception
            goto L_0x0026
        L_0x001e:
            java.util.ArrayList<com.samsung.android.gallery.module.dataset.MediaData$OnDataChangeListener> r2 = r2.mListener     // Catch:{ all -> 0x001c }
            r1 = 0
            r2.add(r1, r3)     // Catch:{ all -> 0x001c }
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return
        L_0x0026:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataRef.register(com.samsung.android.gallery.module.dataset.MediaData$OnDataChangeListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = com.samsung.android.gallery.support.utils.AppResources.getAppContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerPppObserver(final java.util.function.Consumer<com.samsung.android.gallery.module.data.MediaItem> r4) {
        /*
            r3 = this;
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_PPP_V2
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 == 0) goto L_0x0033
            android.content.Context r0 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            if (r0 != 0) goto L_0x000f
            goto L_0x0033
        L_0x000f:
            com.samsung.android.gallery.module.dataset.MediaDataRef$1 r1 = new com.samsung.android.gallery.module.dataset.MediaDataRef$1
            android.os.Handler r2 = com.samsung.android.gallery.support.utils.ThreadUtil.getBackgroundThreadHandler()
            r1.<init>(r2, r0, r4)
            r3.mPppObserver = r1
            com.samsung.android.gallery.support.utils.StringCompat r4 = r3.TAG
            java.lang.String r1 = "ppp register camera observer"
            com.samsung.android.gallery.support.utils.Log.d(r4, r1)
            android.content.ContentResolver r4 = r0.getContentResolver()
            java.lang.String r0 = com.samsung.android.gallery.support.providers.MediaUri.getCameraPppUri()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            r1 = 1
            android.database.ContentObserver r3 = r3.mPppObserver
            r4.registerContentObserver(r0, r1, r3)
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataRef.registerPppObserver(java.util.function.Consumer):void");
    }

    public void reopen(String str) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "reopen {" + str + "}");
        setLocationKey(str);
        this.mForceSwap = true;
        requestData(str);
    }

    public abstract void requestData(String str);

    public void requestData(String str, EventMessage eventMessage) {
        requestData(str);
    }

    public void requestIfRequired(Blackboard blackboard, String str) {
        BlackboardUtils.publishDataRequest(blackboard, str);
    }

    public void runOnUiThread(Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    public void setLocationKey(String str) {
        String str2 = this.mLocationReference;
        this.mLocationReference = str;
        if (str2 == null || !str2.equals(str)) {
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "setLocationKey {" + str + "}");
        }
    }

    public void subscribePppCompleted(ArrayList<SubscriberInfo> arrayList) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            this.mCheckPppCursorExpire = true;
            arrayList.add(new SubscriberInfo("data://pppCompleted", new C0594c(this, 3)).setWorkingCurrent().setTriggerPreloadedData());
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(hashTag());
        sb2.append("{");
        sb2.append(this.mLocationKey);
        sb2.append(',');
        sb2.append(this.mRefCount);
        sb2.append(',');
        sb2.append(this.mDataVersion);
        sb2.append(',');
        sb2.append(this.mDataCount);
        sb2.append(',');
        return C0086a.n(sb2, this.mForceSwap, '}');
    }

    public void unregister(MediaData.OnDataChangeListener onDataChangeListener) {
        synchronized (this.mListener) {
            this.mListener.remove(onDataChangeListener);
        }
    }

    public void unregisterPppObserver() {
        if (Features.isEnabled(Features.SUPPORT_PPP_V2) && this.mPppObserver != null) {
            Optional.ofNullable(AppResources.getAppContext()).ifPresent(new O(2, this));
            this.mPppObserver = null;
        }
    }

    public void updateDataStampByPpp(MediaItem mediaItem) {
        if (mediaItem != null) {
            String dataStamp = mediaItem.getDataStamp();
            if (TextUtils.isEmpty(dataStamp)) {
                return;
            }
            if (updateDataStampIfPpp(new DataStamp(dataStamp))) {
                StringCompat stringCompat = this.TAG;
                Log.i(stringCompat, "updateDataStampByPpp : " + dataStamp);
                return;
            }
            StringCompat stringCompat2 = this.TAG;
            Log.i(stringCompat2, "updateDataStampByPpp fail : " + this.mLastDataStamp, dataStamp);
        }
    }

    public boolean useGmpOnly() {
        if (!PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || !this.mLocationKey.startsWith("location://search/fileList/Category/Location")) {
            return false;
        }
        return true;
    }

    public void runOnUiThread(Runnable runnable, long j2) {
        this.mMainHandler.postDelayed(runnable, j2);
    }

    public final void notifyDataRangeChanged(int i2, int i7, Object obj) {
        increaseDataVersion();
        synchronized (this.mListener) {
            try {
                Iterator it = new ArrayList(this.mListener).iterator();
                while (it.hasNext()) {
                    ((MediaData.OnDataChangeListener) it.next()).onDataRangeChanged(i2, i7, obj);
                }
                this.mListener.removeIf(new C0598e(5));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
