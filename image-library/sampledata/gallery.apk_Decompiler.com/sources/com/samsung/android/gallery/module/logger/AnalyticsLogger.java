package com.samsung.android.gallery.module.logger;

import D0.e;
import Dd.C0733d;
import Dd.C0734e;
import Ed.b;
import Gd.a;
import N2.j;
import Od.d;
import a.C0068a;
import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Pair;
import c0.C0086a;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import o1.C0246a;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnalyticsLogger {
    public static final boolean USER_RELEASE = (!DeviceConfig.DEBUG_BINARY);
    private static final AnalyticsLogger sInstance = new AnalyticsLogger();
    private final int MSG_FLUSH_LOG = 0;
    private final AtomicInteger mAnalyticsCount = new AtomicInteger(0);
    private final Handler mLogHandler = new Handler(ThreadUtil.getBackgroundThreadLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                AnalyticsLogger.this.flushLog();
            }
        }

        public boolean sendMessageAtTime(Message message, long j2) {
            if (Logger.isAllowDebug()) {
                ThreadUtil.saveCallStackForDebug(Thread.currentThread());
            }
            return super.sendMessageAtTime(message, j2);
        }
    };
    private final ConcurrentLinkedQueue<LogItem> mLogQueue = new ConcurrentLinkedQueue<>();
    private LogThread mLogThread;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BackgroundEventListHolder {
        static final HashSet<String> set = new HashSet<String>() {
            {
                add(AnalyticsEventId.EVENT_CHANNEL_NO_OF_STORIES.toString());
                add(AnalyticsEventId.EVENT_STORIES_NOTI_COUNT.toString());
                add(AnalyticsEventId.EVENT_ALBUM_COUNT.toString());
            }
        };

        public static boolean contains(String str) {
            return set.contains(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LogItem {
        final Map<String, String> mEventDetail;
        String mEventId;
        Long mEventValue;
        String mKey;
        String mScreenId;
        final Map<String, String[]> mScreenLabelDetail;
        String mValue;

        public /* synthetic */ LogItem(int i2) {
            this();
        }

        private Map<String, String> buildEvent() {
            HashMap hashMap = new HashMap();
            if (!AnalyticsScreenId.SCREEN_UNKNOWN.toString().equals(this.mScreenId)) {
                String str = this.mScreenId;
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put("pn", str);
                }
            }
            String str2 = this.mEventId;
            if (TextUtils.isEmpty(str2)) {
                C0246a.m0("Failure to build Log : Event id cannot be null");
            }
            hashMap.put("en", str2);
            Long l = this.mEventValue;
            if (l != null) {
                hashMap.put("ev", String.valueOf(l.longValue()));
            }
            if (!this.mEventDetail.isEmpty()) {
                hashMap.put("cd", C0246a.f0(a.f(this.mEventDetail), d.TWO_DEPTH));
            }
            if (!this.mScreenLabelDetail.isEmpty()) {
                Map<String, String[]> map = this.mScreenLabelDetail;
                HashMap hashMap2 = new HashMap();
                StringBuilder sb2 = new StringBuilder();
                for (Map.Entry next : map.entrySet()) {
                    sb2.setLength(0);
                    for (String str3 : (String[]) next.getValue()) {
                        if (sb2.length() != 0) {
                            sb2.append(d.THREE_DEPTH.a());
                        }
                        sb2.append(str3);
                    }
                    hashMap2.put((String) next.getKey(), sb2.toString());
                }
                hashMap.put("pd", C0246a.f0(hashMap2, d.TWO_DEPTH));
            }
            if (BackgroundEventListHolder.contains(this.mEventId)) {
                hashMap.put("et", String.valueOf(1));
            }
            if (!hashMap.containsKey("en")) {
                C0246a.m0("Failure to build Log : Event name cannot be null");
                return null;
            }
            hashMap.put("t", "ev");
            hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
            return new HashMap(hashMap);
        }

        private Map<String, String> buildScreen() {
            HashMap hashMap = new HashMap();
            String str = this.mScreenId;
            if (TextUtils.isEmpty(str)) {
                C0246a.m0("Failure to set Screen View : Screen name cannot be null.");
            } else {
                hashMap.put("pn", str);
            }
            if (TextUtils.isEmpty((CharSequence) hashMap.get("pn"))) {
                C0246a.m0("Failure to build Log : Screen name cannot be null");
                return null;
            }
            hashMap.put("t", "pv");
            hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
            return new HashMap(hashMap);
        }

        private Map<String, String> buildSetting() {
            HashMap hashMap = new HashMap();
            String str = this.mKey;
            String str2 = this.mValue;
            if (TextUtils.isEmpty(str)) {
                C0246a.m0("Failure to build logs [setting] : Key cannot be null.");
            } else if (str.equalsIgnoreCase("t")) {
                C0246a.m0("Failure to build logs [setting] : 't' is reserved word, choose another word.");
            } else {
                hashMap.put(str, str2);
            }
            HashMap hashMap2 = new HashMap();
            if (!hashMap.isEmpty()) {
                hashMap2.put("sti", C0246a.f0(a.f(hashMap), d.TWO_DEPTH));
                hashMap2.put("ts", String.valueOf(System.currentTimeMillis()));
                hashMap2.put("t", "st");
            }
            return hashMap2;
        }

        public static LogItem obtain(String str) {
            LogItem logItem = LogItemPool.get();
            logItem.mScreenId = str;
            return logItem;
        }

        public static LogItem obtainSetting(String str, String str2) {
            LogItem logItem = LogItemPool.get();
            logItem.mKey = str;
            logItem.mValue = str2;
            return logItem;
        }

        public Map<String, String> build() {
            if (this.mKey != null) {
                return buildSetting();
            }
            if (this.mEventId != null) {
                return buildEvent();
            }
            return buildScreen();
        }

        public void recycle() {
            this.mScreenId = null;
            this.mEventId = null;
            this.mEventDetail.clear();
            this.mEventValue = null;
            this.mKey = null;
            this.mValue = null;
            this.mScreenLabelDetail.clear();
            LogItemPool.put(this);
        }

        public String toString() {
            if (this.mKey != null) {
                StringBuilder sb2 = new StringBuilder("LogItem{K=");
                sb2.append(this.mKey);
                sb2.append(", V=");
                return C0212a.p(sb2, this.mValue, "}");
            }
            String str = "LogItem{S=" + this.mScreenId + ", E=" + this.mEventId + ", D=" + this.mEventDetail + ", V=" + this.mEventValue;
            if (!this.mScreenLabelDetail.isEmpty()) {
                for (String str2 : this.mScreenLabelDetail.keySet()) {
                    StringBuilder t = C0212a.t(str, ", SLD=");
                    t.append(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, new CharSequence[]{str2}));
                    str = t.toString();
                }
            }
            return C0212a.A(str, "}");
        }

        private LogItem() {
            this.mEventDetail = new HashMap();
            this.mScreenLabelDetail = new HashMap();
        }

        public static LogItem obtain(String str, String str2) {
            assertWrongEventId(str2);
            LogItem logItem = LogItemPool.get();
            logItem.mScreenId = str;
            logItem.mEventId = str2;
            return logItem;
        }

        public static LogItem obtain(String str, String str2, String str3) {
            assertWrongEventId(str2);
            LogItem logItem = LogItemPool.get();
            logItem.mScreenId = str;
            logItem.mEventId = str2;
            logItem.mEventDetail.put("det", str3);
            return logItem;
        }

        public static LogItem obtain(String str, String str2, long j2) {
            assertWrongEventId(str2);
            LogItem logItem = LogItemPool.get();
            logItem.mScreenId = str;
            logItem.mEventId = str2;
            logItem.mEventValue = Long.valueOf(j2);
            return logItem;
        }

        public static LogItem obtain(String str, String str2, long j2, String str3) {
            assertWrongEventId(str2);
            LogItem logItem = LogItemPool.get();
            logItem.mScreenId = str;
            logItem.mEventId = str2;
            logItem.mEventValue = Long.valueOf(j2);
            logItem.mEventDetail.put("det", str3);
            return logItem;
        }

        private static void assertWrongEventId(String str) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LogItemPool {
        private static final ConcurrentLinkedQueue<LogItem> sPool = new ConcurrentLinkedQueue<>();

        public static LogItem get() {
            LogItem poll = sPool.poll();
            if (poll != null) {
                return poll;
            }
            return new LogItem(0);
        }

        public static void put(LogItem logItem) {
            sPool.offer(logItem);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LogThread extends Thread {
        private final Object LOCK = new Object();
        private boolean mJobAvailable;
        private final Runnable mRunnable;
        private boolean mThreadStopped;

        public LogThread(Runnable runnable) {
            super("AnalyticsLogger");
            this.mRunnable = runnable;
        }

        public void resumeJob() {
            this.mJobAvailable = true;
            synchronized (this.LOCK) {
                this.LOCK.notify();
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:4|5|(2:7|8)|10|11) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r2 = this;
            L_0x0000:
                boolean r0 = r2.mThreadStopped
                if (r0 != 0) goto L_0x001f
                java.lang.Object r0 = r2.LOCK
                monitor-enter(r0)
                boolean r1 = r2.mJobAvailable     // Catch:{ all -> 0x0011 }
                if (r1 != 0) goto L_0x0013
                java.lang.Object r1 = r2.LOCK     // Catch:{ InterruptedException -> 0x0013 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0013 }
                goto L_0x0013
            L_0x0011:
                r2 = move-exception
                goto L_0x001d
            L_0x0013:
                monitor-exit(r0)     // Catch:{ all -> 0x0011 }
                java.lang.Runnable r0 = r2.mRunnable
                r0.run()
                r0 = 0
                r2.mJobAvailable = r0
                goto L_0x0000
            L_0x001d:
                monitor-exit(r0)     // Catch:{ all -> 0x0011 }
                throw r2
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.logger.AnalyticsLogger.LogThread.run():void");
        }

        public void stopThread() {
            this.mJobAvailable = false;
            this.mThreadStopped = true;
            interrupt();
        }
    }

    private AnalyticsLogger() {
    }

    private void enqueueLog(LogItem logItem) {
        this.mLogQueue.offer(logItem);
        if (!this.mLogHandler.hasMessages(0)) {
            Handler handler = this.mLogHandler;
            handler.sendMessageDelayed(handler.obtainMessage(0), 600);
        }
    }

    /* access modifiers changed from: private */
    public void flushLog() {
        LogThread logThread = this.mLogThread;
        if (logThread != null) {
            logThread.resumeJob();
        }
    }

    public static AnalyticsLogger getInstance() {
        return sInstance;
    }

    private void registerSettingsPref() {
        C0733d dVar = new C0733d();
        String name = GalleryPreference.getInstanceAnalytics().getName();
        SamsungAnalyticsPrefs.SETTING_PREF_NAME.forEach(new t9.a(dVar, name, 0));
        SamsungAnalyticsPrefs.STATUS_KEYS.forEach(new t9.a(dVar, name, 1));
        PreferenceAnalytics.listOf().forEach(new t9.a(dVar, name, 2));
        SasCount.keys().forEach(new t9.a(dVar, name, 3));
        SamsungAnalyticsPrefs.MISC_KEYS.forEach(new t9.a(dVar, GalleryPreference.getInstance().getName(), 4));
        HashMap hashMap = dVar.f3333a;
        C0068a.e(hashMap.toString());
        HashMap hashMap2 = new HashMap(hashMap);
        C0734e a7 = C0734e.a();
        a7.getClass();
        try {
            b bVar = a7.d;
            bVar.getClass();
            Trace.beginAsyncSection("Tracker registerSettingPref SingleThreadExecutor", 499562429);
            i e = i.e();
            e eVar = new e((Object) bVar, (Object) hashMap2, false, 4);
            e.getClass();
            i.d(eVar);
            Trace.endAsyncSection("Tracker registerSettingPref SingleThreadExecutor", 499562429);
        } catch (NullPointerException e7) {
            C0068a.f(C0734e.class, e7);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|5|6|7) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendLog() {
        /*
            r9 = this;
        L_0x0000:
            java.util.concurrent.ConcurrentLinkedQueue<com.samsung.android.gallery.module.logger.AnalyticsLogger$LogItem> r0 = r9.mLogQueue     // Catch:{ Exception -> 0x0040 }
            java.lang.Object r0 = r0.poll()     // Catch:{ Exception -> 0x0040 }
            com.samsung.android.gallery.module.logger.AnalyticsLogger$LogItem r0 = (com.samsung.android.gallery.module.logger.AnalyticsLogger.LogItem) r0     // Catch:{ Exception -> 0x0040 }
            if (r0 == 0) goto L_0x004b
            Dd.e r1 = Dd.C0734e.a()     // Catch:{ Exception -> 0x0040 }
            java.util.Map r2 = r0.build()     // Catch:{ Exception -> 0x0040 }
            r1.getClass()     // Catch:{ Exception -> 0x0040 }
            java.lang.String r3 = "sendLog"
            a.C0068a.b(r3)     // Catch:{ Exception -> 0x0040 }
            Ed.b r1 = r1.d     // Catch:{ NullPointerException -> 0x003c }
            r1.getClass()     // Catch:{ NullPointerException -> 0x003c }
            java.lang.String r3 = "Tracker SendLog SingleThreadExecutor"
            r4 = 1468411569(0x57862eb1, float:2.95070192E14)
            android.os.Trace.beginAsyncSection(r3, r4)     // Catch:{ NullPointerException -> 0x003c }
            t1.i r5 = t1.i.e()     // Catch:{ NullPointerException -> 0x003c }
            D0.e r6 = new D0.e     // Catch:{ NullPointerException -> 0x003c }
            r7 = 3
            r8 = 0
            r6.<init>((java.lang.Object) r1, (java.lang.Object) r2, (boolean) r8, (int) r7)     // Catch:{ NullPointerException -> 0x003c }
            r5.getClass()     // Catch:{ NullPointerException -> 0x003c }
            t1.i.d(r6)     // Catch:{ NullPointerException -> 0x003c }
            android.os.Trace.endAsyncSection(r3, r4)     // Catch:{ NullPointerException -> 0x003c }
        L_0x003c:
            r0.recycle()     // Catch:{ Exception -> 0x0040 }
            goto L_0x0000
        L_0x0040:
            r9 = move-exception
            java.lang.String r0 = "AnalyticsLogger"
            java.lang.String r1 = "fail to send analytics log"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)
            r9.printStackTrace()
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.logger.AnalyticsLogger.sendLog():void");
    }

    private void startLogThread() {
        Log.d("AnalyticsLogger", "startLogThread");
        LogThread logThread = new LogThread(new t8.e(1, this));
        this.mLogThread = logThread;
        logThread.start();
    }

    private void stopLogThread() {
        Log.d("AnalyticsLogger", "stopLogThread");
        LogThread logThread = this.mLogThread;
        if (logThread != null) {
            logThread.stopThread();
            this.mLogThread = null;
        }
    }

    public synchronized void close() {
        if (this.mAnalyticsCount.decrementAndGet() == 0) {
            stopLogThread();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [Dd.c, java.lang.Object] */
    public void initialize(Application application) {
        try {
            ? obj = new Object();
            obj.e = -1;
            obj.f3331a = "755-399-529997";
            obj.f3332c = "17.5";
            obj.b = true;
            C0734e.b(application, obj);
            registerSettingsPref();
        } catch (Exception e) {
            Log.e((CharSequence) "AnalyticsLogger", "initialize analytics failed", (Throwable) e);
        }
    }

    public synchronized void open() {
        if (this.mAnalyticsCount.getAndIncrement() == 0) {
            startLogThread();
        }
    }

    public void postCustomDimension(String str, String str2, String str3, String str4) {
        postCustomDimension(str, str2, str3, (Pair<String, String>[]) new Pair[]{new Pair("det", str4)});
    }

    public void postLog(String str, String str2, long j2) {
        if (str == null || str2 == null || str.startsWith("null")) {
            StringBuilder q = C0086a.q("postLog failed {S=", str, ", E=", str2, ", V=");
            q.append(j2);
            q.append("}");
            Log.w("AnalyticsLogger", q.toString());
            return;
        }
        enqueueLog(LogItem.obtain(str, str2, j2));
    }

    public void postSettingLog(AnalyticsStatusId analyticsStatusId, String str) {
        if (analyticsStatusId == null) {
            Log.e("AnalyticsLogger", "postSettingLog failed {K=null, V=" + str + "}");
            return;
        }
        enqueueLog(LogItem.obtainSetting(analyticsStatusId.toString(), str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [int] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void postCustomDimension(java.lang.String r5, java.lang.String r6, android.util.Pair<java.lang.String, java.lang.String>[] r7) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L_0x002f
            if (r6 == 0) goto L_0x002f
            if (r7 == 0) goto L_0x002f
            java.lang.String r1 = "null"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L_0x0010
            goto L_0x002f
        L_0x0010:
            com.samsung.android.gallery.module.logger.AnalyticsLogger$LogItem r5 = com.samsung.android.gallery.module.logger.AnalyticsLogger.LogItem.obtain(r5, r6)
            int r6 = r7.length
        L_0x0015:
            if (r0 >= r6) goto L_0x002b
            r1 = r7[r0]
            if (r1 == 0) goto L_0x0028
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.mEventDetail
            java.lang.Object r3 = r1.first
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r1 = r1.second
            java.lang.String r1 = (java.lang.String) r1
            r2.put(r3, r1)
        L_0x0028:
            int r0 = r0 + 1
            goto L_0x0015
        L_0x002b:
            r4.enqueueLog(r5)
            return
        L_0x002f:
            java.lang.String r4 = ", E="
            java.lang.String r1 = ", D="
            java.lang.String r2 = "postCustomDimension failed {S="
            java.lang.StringBuilder r4 = c0.C0086a.q(r2, r5, r4, r6, r1)
            if (r7 == 0) goto L_0x003c
            r0 = 1
        L_0x003c:
            r4.append(r0)
            java.lang.String r5 = "}"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "AnalyticsLogger"
            com.samsung.android.gallery.support.utils.Log.e(r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.logger.AnalyticsLogger.postCustomDimension(java.lang.String, java.lang.String, android.util.Pair[]):void");
    }

    public void postLog(String str, String str2, String str3) {
        if (str == null || str2 == null || str.startsWith("null")) {
            StringBuilder q = C0086a.q("postLog failed {S=", str, ", E=", str2, ", D=");
            q.append(str3);
            q.append("}");
            Log.e("AnalyticsLogger", q.toString());
            return;
        }
        enqueueLog(LogItem.obtain(str, str2, str3));
    }

    public void postCustomDimension(String str, String str2, String str3, Pair<String, String>[] pairArr) {
        if (str3 == null) {
            postCustomDimension(str, str2, pairArr);
            return;
        }
        boolean z = false;
        if (str == null || str2 == null || pairArr == null || str.startsWith("null")) {
            StringBuilder q = C0086a.q("postCustomDimension failed {S=", str, ", E=", str2, ", L=");
            q.append(str3);
            q.append(", D=");
            if (pairArr != null) {
                z = true;
            }
            q.append(z);
            q.append("}");
            Log.e("AnalyticsLogger", q.toString());
            return;
        }
        LogItem obtain = LogItem.obtain(str, str2);
        ArrayList arrayList = new ArrayList();
        for (Pair<String, String> pair : pairArr) {
            if (pair != null) {
                obtain.mEventDetail.put((String) pair.first, (String) pair.second);
                arrayList.add((String) pair.first);
            }
        }
        if (!arrayList.isEmpty()) {
            obtain.mScreenLabelDetail.put(str3, (String[]) arrayList.toArray(new String[0]));
        }
        enqueueLog(obtain);
    }

    public void postLog(String str, String str2, long j2, String str3) {
        if (str == null || str2 == null || str.startsWith("null")) {
            StringBuilder q = C0086a.q("postLog failed {S=", str, ", E=", str2, ", V=");
            q.append(j2);
            q.append(", D=");
            q.append(str3);
            q.append("}");
            Log.e("AnalyticsLogger", q.toString());
            return;
        }
        enqueueLog(LogItem.obtain(str, str2, j2, str3));
    }

    public void postLog(String str, String str2) {
        if (str == null || str2 == null || str.startsWith("null")) {
            Log.e("AnalyticsLogger", j.d("postLog failed {S=", str, ", E=", str2, "}"));
        } else {
            enqueueLog(LogItem.obtain(str, str2));
        }
    }

    public void postLog(String str) {
        if (str == null || str.startsWith("null")) {
            Log.e("AnalyticsLogger", "postLog failed {S=null}");
        } else {
            enqueueLog(LogItem.obtain(str));
        }
    }
}
