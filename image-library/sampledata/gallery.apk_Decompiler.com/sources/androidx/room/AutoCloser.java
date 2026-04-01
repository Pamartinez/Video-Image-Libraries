package androidx.room;

import Ae.b;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import g0.C0188a;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 F2\u00020\u0001:\u0001FB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\f¢\u0006\u0004\b\u0019\u0010\u0018J\u0015\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\u000b\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\u000eR\u0014\u0010#\u001a\u00020\"8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010\u001dR\u0014\u0010*\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\"\u00101\u001a\u0002008\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00107\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b7\u0010-\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R$\u0010<\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010\u0016\"\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\bD\u0010&R\u0014\u0010E\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\bE\u0010&¨\u0006G"}, d2 = {"Landroidx/room/AutoCloser;", "", "", "autoCloseTimeoutAmount", "Ljava/util/concurrent/TimeUnit;", "autoCloseTimeUnit", "Ljava/util/concurrent/Executor;", "autoCloseExecutor", "<init>", "(JLjava/util/concurrent/TimeUnit;Ljava/util/concurrent/Executor;)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "delegateOpenHelper", "Lme/x;", "init", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)V", "V", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "block", "executeRefCountingFunction", "(LAe/b;)Ljava/lang/Object;", "incrementCountAndEnsureDbIsOpen", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "decrementCountAndScheduleClose", "()V", "closeDatabaseIfOpen", "Ljava/lang/Runnable;", "onAutoClose", "setAutoCloseCallback", "(Ljava/lang/Runnable;)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getDelegateOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "setDelegateOpenHelper", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "onAutoCloseCallback", "Ljava/lang/Runnable;", "getOnAutoCloseCallback$room_runtime_release", "()Ljava/lang/Runnable;", "setOnAutoCloseCallback$room_runtime_release", "lock", "Ljava/lang/Object;", "autoCloseTimeoutInMs", "J", "executor", "Ljava/util/concurrent/Executor;", "", "refCount", "I", "getRefCount$room_runtime_release", "()I", "setRefCount$room_runtime_release", "(I)V", "lastDecrementRefCountTimeStamp", "getLastDecrementRefCountTimeStamp$room_runtime_release", "()J", "setLastDecrementRefCountTimeStamp$room_runtime_release", "(J)V", "delegateDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getDelegateDatabase$room_runtime_release", "setDelegateDatabase$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "", "manuallyClosed", "Z", "executeAutoCloser", "autoCloser", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoCloser {
    public static final Companion Companion = new Companion((e) null);
    private long autoCloseTimeoutInMs;
    private final Runnable autoCloser;
    private SupportSQLiteDatabase delegateDatabase;
    public SupportSQLiteOpenHelper delegateOpenHelper;
    private final Runnable executeAutoCloser;
    private final Executor executor;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long lastDecrementRefCountTimeStamp;
    private final Object lock = new Object();
    private boolean manuallyClosed;
    private Runnable onAutoCloseCallback;
    private int refCount;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/AutoCloser$Companion;", "", "()V", "autoCloseBug", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public AutoCloser(long j2, TimeUnit timeUnit, Executor executor2) {
        j.e(timeUnit, "autoCloseTimeUnit");
        j.e(executor2, "autoCloseExecutor");
        this.autoCloseTimeoutInMs = timeUnit.toMillis(j2);
        this.executor = executor2;
        this.lastDecrementRefCountTimeStamp = SystemClock.uptimeMillis();
        this.executeAutoCloser = new C0188a(this, 0);
        this.autoCloser = new C0188a(this, 1);
    }

    /* access modifiers changed from: private */
    public static final void autoCloser$lambda$3(AutoCloser autoCloser2) {
        x xVar;
        j.e(autoCloser2, "this$0");
        synchronized (autoCloser2.lock) {
            try {
                if (SystemClock.uptimeMillis() - autoCloser2.lastDecrementRefCountTimeStamp >= autoCloser2.autoCloseTimeoutInMs) {
                    if (autoCloser2.refCount == 0) {
                        Runnable runnable = autoCloser2.onAutoCloseCallback;
                        if (runnable != null) {
                            runnable.run();
                            xVar = x.f4917a;
                        } else {
                            xVar = null;
                        }
                        if (xVar != null) {
                            SupportSQLiteDatabase supportSQLiteDatabase = autoCloser2.delegateDatabase;
                            if (supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen()) {
                                supportSQLiteDatabase.close();
                            }
                            autoCloser2.delegateDatabase = null;
                            return;
                        }
                        throw new IllegalStateException("onAutoCloseCallback is null but it should have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void executeAutoCloser$lambda$0(AutoCloser autoCloser2) {
        j.e(autoCloser2, "this$0");
        autoCloser2.executor.execute(autoCloser2.autoCloser);
    }

    public final void closeDatabaseIfOpen() {
        synchronized (this.lock) {
            try {
                this.manuallyClosed = true;
                SupportSQLiteDatabase supportSQLiteDatabase = this.delegateDatabase;
                if (supportSQLiteDatabase != null) {
                    supportSQLiteDatabase.close();
                }
                this.delegateDatabase = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void decrementCountAndScheduleClose() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            int r1 = r5.refCount     // Catch:{ all -> 0x001d }
            if (r1 <= 0) goto L_0x0021
            int r1 = r1 + -1
            r5.refCount = r1     // Catch:{ all -> 0x001d }
            if (r1 != 0) goto L_0x001f
            androidx.sqlite.db.SupportSQLiteDatabase r1 = r5.delegateDatabase     // Catch:{ all -> 0x001d }
            if (r1 != 0) goto L_0x0013
            monitor-exit(r0)
            return
        L_0x0013:
            android.os.Handler r1 = r5.handler     // Catch:{ all -> 0x001d }
            java.lang.Runnable r2 = r5.executeAutoCloser     // Catch:{ all -> 0x001d }
            long r3 = r5.autoCloseTimeoutInMs     // Catch:{ all -> 0x001d }
            r1.postDelayed(r2, r3)     // Catch:{ all -> 0x001d }
            goto L_0x001f
        L_0x001d:
            r5 = move-exception
            goto L_0x0029
        L_0x001f:
            monitor-exit(r0)
            return
        L_0x0021:
            java.lang.String r5 = "ref count is 0 or lower but we're supposed to decrement"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x001d }
            r1.<init>(r5)     // Catch:{ all -> 0x001d }
            throw r1     // Catch:{ all -> 0x001d }
        L_0x0029:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.AutoCloser.decrementCountAndScheduleClose():void");
    }

    public final <V> V executeRefCountingFunction(b bVar) {
        j.e(bVar, "block");
        try {
            return bVar.invoke(incrementCountAndEnsureDbIsOpen());
        } finally {
            decrementCountAndScheduleClose();
        }
    }

    public final SupportSQLiteDatabase getDelegateDatabase$room_runtime_release() {
        return this.delegateDatabase;
    }

    public final SupportSQLiteOpenHelper getDelegateOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.delegateOpenHelper;
        if (supportSQLiteOpenHelper != null) {
            return supportSQLiteOpenHelper;
        }
        j.k("delegateOpenHelper");
        throw null;
    }

    public final SupportSQLiteDatabase incrementCountAndEnsureDbIsOpen() {
        synchronized (this.lock) {
            this.handler.removeCallbacks(this.executeAutoCloser);
            this.refCount++;
            if (!this.manuallyClosed) {
                SupportSQLiteDatabase supportSQLiteDatabase = this.delegateDatabase;
                if (supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen()) {
                    return supportSQLiteDatabase;
                }
                SupportSQLiteDatabase writableDatabase = getDelegateOpenHelper().getWritableDatabase();
                this.delegateDatabase = writableDatabase;
                return writableDatabase;
            }
            throw new IllegalStateException("Attempting to open already closed database.");
        }
    }

    public final void init(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        j.e(supportSQLiteOpenHelper, "delegateOpenHelper");
        setDelegateOpenHelper(supportSQLiteOpenHelper);
    }

    public final void setAutoCloseCallback(Runnable runnable) {
        j.e(runnable, "onAutoClose");
        this.onAutoCloseCallback = runnable;
    }

    public final void setDelegateOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        j.e(supportSQLiteOpenHelper, "<set-?>");
        this.delegateOpenHelper = supportSQLiteOpenHelper;
    }
}
