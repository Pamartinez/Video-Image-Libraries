package com.samsung.android.gallery.module.bgm.bgmdata;

import A.a;
import Da.f;
import Fa.F;
import G8.C0572a;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmData {
    private static final Uri CONTENT_URI = Uri.parse("content://com.sec.android.app.ve.vebgm.bgmhandler/Bgm/*");
    private static final BgmData sInstance = new BgmData();
    private ConcurrentHashMap<String, BgmItem> mBgmItemMap = new ConcurrentHashMap<>();
    protected Handler mHandler;
    private final ArrayList<BgmDataListener> mListeners = new ArrayList<>();
    protected final ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z, Uri uri) {
            Handler handler = BgmData.this.mHandler;
            if (handler != null && !handler.hasMessages(100)) {
                Log.d("BgmData", "onChange", Boolean.valueOf(z), uri);
                Handler handler2 = BgmData.this.mHandler;
                handler2.sendMessageDelayed(handler2.obtainMessage(100), 250);
            }
        }
    };
    private final AtomicInteger mRefCount = new AtomicInteger();

    private void destroy() {
        unregisterContentObserver();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.getLooper().quitSafely();
            this.mHandler = null;
        }
        Log.d("BgmData", "destroy");
    }

    public static BgmData getInstance() {
        return sInstance;
    }

    private void init() {
        this.mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("BgmData")) {
            public void handleMessage(Message message) {
                if (message.what == 100) {
                    BgmData.this.loadData();
                }
            }
        };
        registerContentObserver();
        Log.d("BgmData", "init");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$0(ConcurrentHashMap concurrentHashMap) {
        Log.d("BgmData", "swap", Integer.valueOf(getCount()), Integer.valueOf(concurrentHashMap.size()));
        this.mBgmItemMap = concurrentHashMap;
        notifyDataChange();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0068 A[SYNTHETIC, Splitter:B:15:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadData() {
        /*
            r7 = this;
            java.lang.String r0 = "BgmData"
            long r1 = java.lang.System.currentTimeMillis()
            android.content.Context r3 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ Exception -> 0x006c }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x006c }
            android.net.Uri r4 = CONTENT_URI     // Catch:{ Exception -> 0x006c }
            r5 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r5, r5)     // Catch:{ Exception -> 0x006c }
            if (r3 == 0) goto L_0x0061
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x005f }
            if (r4 == 0) goto L_0x0061
            java.util.concurrent.ConcurrentHashMap r4 = new java.util.concurrent.ConcurrentHashMap     // Catch:{ all -> 0x005f }
            r4.<init>()     // Catch:{ all -> 0x005f }
        L_0x0022:
            com.samsung.android.gallery.module.bgm.bgmdata.BgmItem r5 = com.samsung.android.gallery.module.bgm.bgmdata.BgmItem.build(r3)     // Catch:{ all -> 0x005f }
            java.lang.String r6 = r5.getName()     // Catch:{ all -> 0x005f }
            r4.put(r6, r5)     // Catch:{ all -> 0x005f }
            boolean r5 = r3.moveToNext()     // Catch:{ all -> 0x005f }
            if (r5 != 0) goto L_0x0022
            r7.swap(r4)     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r7.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r5 = "loadData"
            r7.append(r5)     // Catch:{ all -> 0x005f }
            int r4 = r4.size()     // Catch:{ all -> 0x005f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x005f }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x005f }
            java.lang.Object[] r1 = new java.lang.Object[]{r4, r1}     // Catch:{ all -> 0x005f }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r1)     // Catch:{ all -> 0x005f }
            r7.append(r1)     // Catch:{ all -> 0x005f }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x005f }
            com.samsung.android.gallery.support.utils.Log.d(r0, r7)     // Catch:{ all -> 0x005f }
            goto L_0x0066
        L_0x005f:
            r7 = move-exception
            goto L_0x006f
        L_0x0061:
            java.lang.String r7 = "loadData failed null cursor"
            com.samsung.android.gallery.support.utils.Log.e(r0, r7)     // Catch:{ all -> 0x005f }
        L_0x0066:
            if (r3 == 0) goto L_0x006e
            r3.close()     // Catch:{ Exception -> 0x006c }
            return
        L_0x006c:
            r7 = move-exception
            goto L_0x007a
        L_0x006e:
            return
        L_0x006f:
            if (r3 == 0) goto L_0x0079
            r3.close()     // Catch:{ all -> 0x0075 }
            goto L_0x0079
        L_0x0075:
            r1 = move-exception
            r7.addSuppressed(r1)     // Catch:{ Exception -> 0x006c }
        L_0x0079:
            throw r7     // Catch:{ Exception -> 0x006c }
        L_0x007a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "loadData failed. e="
            r1.<init>(r2)
            A.a.s(r7, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.bgm.bgmdata.BgmData.loadData():void");
    }

    /* access modifiers changed from: private */
    public void notifyDataChange() {
        synchronized (this.mListeners) {
            this.mListeners.forEach(new F(17));
        }
    }

    private void registerContentObserver() {
        try {
            AppResources.getAppContext().getContentResolver().registerContentObserver(CONTENT_URI, false, this.mObserver);
        } catch (Exception e) {
            a.s(e, new StringBuilder("registerContentObserver failed e="), "BgmData");
        }
    }

    private void swap(ConcurrentHashMap<String, BgmItem> concurrentHashMap) {
        ThreadUtil.runOnUiThread(new f(29, this, concurrentHashMap));
    }

    private void unregisterContentObserver() {
        try {
            AppResources.getAppContext().getContentResolver().unregisterContentObserver(this.mObserver);
        } catch (Exception e) {
            a.s(e, new StringBuilder("unregisterContentObserver failed e="), "BgmData");
        }
    }

    public void close(BgmDataListener bgmDataListener) {
        synchronized (this.mListeners) {
            try {
                if (this.mListeners.contains(bgmDataListener)) {
                    this.mListeners.remove(bgmDataListener);
                    if (this.mRefCount.decrementAndGet() == 0) {
                        destroy();
                    }
                    Log.d("BgmData", "reference count", Integer.valueOf(this.mRefCount.get()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ConcurrentHashMap<String, BgmItem> getBgmItemMap() {
        return new ConcurrentHashMap<>(this.mBgmItemMap);
    }

    public int getCount() {
        return this.mBgmItemMap.size();
    }

    public BgmData open(BgmDataListener bgmDataListener) {
        synchronized (this.mListeners) {
            try {
                if (!this.mListeners.contains(bgmDataListener)) {
                    this.mListeners.add(bgmDataListener);
                    if (this.mRefCount.incrementAndGet() == 1) {
                        init();
                        Handler handler = this.mHandler;
                        if (handler != null) {
                            handler.post(new C0572a(this, 0));
                        }
                    }
                }
                if (!this.mBgmItemMap.isEmpty()) {
                    ThreadUtil.postOnUiThread(new C0572a(this, 1));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this;
    }

    public void reloadData(long j2) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessageDelayed(handler.obtainMessage(100), j2);
        }
    }
}
