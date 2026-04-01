package androidx.room;

import B1.a;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import c0.C0086a;
import e5.C0451a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.v;
import ne.z;
import oe.C1220i;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 i2\u00020\u0001:\u0004ijklBS\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u0004\u0012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010!\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b#\u0010\"J\u000f\u0010'\u001a\u00020$H\u0000¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020\u000fH\u0016¢\u0006\u0004\b(\u0010)J#\u0010+\u001a\u00020\u000f2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u0005H\u0007¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0013H\u0000¢\u0006\u0004\b-\u0010\u0015J\u000f\u0010.\u001a\u00020\u000fH\u0000¢\u0006\u0004\b-\u0010)J\u000f\u0010/\u001a\u00020\u000fH\u0002¢\u0006\u0004\b/\u0010)J\u001f\u00103\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u00132\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0004\b3\u00104J\u001f\u00105\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u00132\u0006\u00102\u001a\u000201H\u0002¢\u0006\u0004\b5\u00104J'\u00107\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t2\u000e\u00106\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\tH\u0002¢\u0006\u0004\b7\u00108R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u00109\u001a\u0004\b:\u0010;R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010<R&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010<R&\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002010\u00048\u0000X\u0004¢\u0006\f\n\u0004\b=\u0010<\u001a\u0004\b>\u0010?R\"\u0010@\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t8\u0000X\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010DR\u001a\u0010F\u001a\u00020E8GX\u0004¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020$8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR$\u0010M\u001a\u0004\u0018\u00010L8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0004¢\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0004¢\u0006\u0006\n\u0004\bW\u0010XR&\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020Z0Y8\u0000X\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R\u0018\u0010`\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b`\u0010aR\u0014\u0010b\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010d\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\bd\u0010cR\u001a\u0010f\u001a\u00020e8\u0006X\u0004¢\u0006\f\n\u0004\bf\u0010g\u0012\u0004\bh\u0010)¨\u0006m"}, d2 = {"Landroidx/room/InvalidationTracker;", "", "Landroidx/room/RoomDatabase;", "database", "", "", "shadowTablesMap", "", "viewTables", "", "tableNames", "<init>", "(Landroidx/room/RoomDatabase;Ljava/util/Map;Ljava/util/Map;[Ljava/lang/String;)V", "Landroidx/room/AutoCloser;", "autoCloser", "Lme/x;", "setAutoCloser$room_runtime_release", "(Landroidx/room/AutoCloser;)V", "setAutoCloser", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "internalInit$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "internalInit", "Landroid/content/Context;", "context", "name", "Landroid/content/Intent;", "serviceIntent", "startMultiInstanceInvalidation$room_runtime_release", "(Landroid/content/Context;Ljava/lang/String;Landroid/content/Intent;)V", "startMultiInstanceInvalidation", "Landroidx/room/InvalidationTracker$Observer;", "observer", "addObserver", "(Landroidx/room/InvalidationTracker$Observer;)V", "removeObserver", "", "ensureInitialization$room_runtime_release", "()Z", "ensureInitialization", "refreshVersionsAsync", "()V", "tables", "notifyObserversByTableNames", "([Ljava/lang/String;)V", "syncTriggers$room_runtime_release", "syncTriggers", "onAutoCloseCallback", "db", "", "tableId", "stopTrackingTable", "(Landroidx/sqlite/db/SupportSQLiteDatabase;I)V", "startTrackingTable", "names", "resolveViews", "([Ljava/lang/String;)[Ljava/lang/String;", "Landroidx/room/RoomDatabase;", "getDatabase$room_runtime_release", "()Landroidx/room/RoomDatabase;", "Ljava/util/Map;", "tableIdLookup", "getTableIdLookup$room_runtime_release", "()Ljava/util/Map;", "tablesNames", "[Ljava/lang/String;", "getTablesNames$room_runtime_release", "()[Ljava/lang/String;", "Landroidx/room/AutoCloser;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pendingRefresh", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getPendingRefresh", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "initialized", "Z", "Landroidx/sqlite/db/SupportSQLiteStatement;", "cleanupStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "getCleanupStatement$room_runtime_release", "()Landroidx/sqlite/db/SupportSQLiteStatement;", "setCleanupStatement$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteStatement;)V", "Landroidx/room/InvalidationTracker$ObservedTableTracker;", "observedTableTracker", "Landroidx/room/InvalidationTracker$ObservedTableTracker;", "Landroidx/room/InvalidationLiveDataContainer;", "invalidationLiveDataContainer", "Landroidx/room/InvalidationLiveDataContainer;", "Landroidx/arch/core/internal/SafeIterableMap;", "Landroidx/room/InvalidationTracker$ObserverWrapper;", "observerMap", "Landroidx/arch/core/internal/SafeIterableMap;", "getObserverMap$room_runtime_release", "()Landroidx/arch/core/internal/SafeIterableMap;", "Landroidx/room/MultiInstanceInvalidationClient;", "multiInstanceInvalidationClient", "Landroidx/room/MultiInstanceInvalidationClient;", "syncTriggersLock", "Ljava/lang/Object;", "trackerLock", "Ljava/lang/Runnable;", "refreshRunnable", "Ljava/lang/Runnable;", "getRefreshRunnable$annotations", "Companion", "ObservedTableTracker", "Observer", "ObserverWrapper", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InvalidationTracker {
    public static final Companion Companion = new Companion((e) null);
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    /* access modifiers changed from: private */
    public AutoCloser autoCloser;
    private volatile SupportSQLiteStatement cleanupStatement;
    private final RoomDatabase database;
    private volatile boolean initialized;
    private final InvalidationLiveDataContainer invalidationLiveDataContainer;
    private MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    private final ObservedTableTracker observedTableTracker;
    private final SafeIterableMap<Observer, ObserverWrapper> observerMap;
    private final AtomicBoolean pendingRefresh = new AtomicBoolean(false);
    public final Runnable refreshRunnable;
    private final Map<String, String> shadowTablesMap;
    private final Object syncTriggersLock;
    private final Map<String, Integer> tableIdLookup;
    private final String[] tablesNames;
    private final Object trackerLock;
    private final Map<String, Set<String>> viewTables;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0017\u0010\u0011¨\u0006\u0018"}, d2 = {"Landroidx/room/InvalidationTracker$Companion;", "", "<init>", "()V", "", "tableName", "triggerType", "getTriggerName$room_runtime_release", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "getTriggerName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "database", "Lme/x;", "beginTransactionInternal$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "beginTransactionInternal", "CREATE_TRACKING_TABLE_SQL", "Ljava/lang/String;", "INVALIDATED_COLUMN_NAME", "TABLE_ID_COLUMN_NAME", "", "TRIGGERS", "[Ljava/lang/String;", "UPDATE_TABLE_NAME", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final void beginTransactionInternal$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "database");
            if (supportSQLiteDatabase.isWriteAheadLoggingEnabled()) {
                supportSQLiteDatabase.beginTransactionNonExclusive();
            } else {
                supportSQLiteDatabase.beginTransaction();
            }
        }

        public final String getTriggerName$room_runtime_release(String str, String str2) {
            j.e(str, "tableName");
            j.e(str2, "triggerType");
            return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0002\b\f\b\u0000\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\n\u0010\u0007\u001a\u00020\u0006\"\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u00020\b2\n\u0010\u0007\u001a\u00020\u0006\"\u00020\u0002¢\u0006\u0004\b\u000b\u0010\nJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\"\u0010\u001b\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Landroidx/room/InvalidationTracker$ObservedTableTracker;", "", "", "tableCount", "<init>", "(I)V", "", "tableIds", "", "onAdded", "([I)Z", "onRemoved", "Lme/x;", "resetTriggerState", "()V", "getTablesToSync", "()[I", "", "tableObservers", "[J", "getTableObservers", "()[J", "", "triggerStates", "[Z", "triggerStateChanges", "[I", "needsSync", "Z", "getNeedsSync", "()Z", "setNeedsSync", "(Z)V", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObservedTableTracker {
        public static final Companion Companion = new Companion((e) null);
        private boolean needsSync;
        private final long[] tableObservers;
        private final int[] triggerStateChanges;
        private final boolean[] triggerStates;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/InvalidationTracker$ObservedTableTracker$Companion;", "", "()V", "ADD", "", "NO_OP", "REMOVE", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public ObservedTableTracker(int i2) {
            this.tableObservers = new long[i2];
            this.triggerStates = new boolean[i2];
            this.triggerStateChanges = new int[i2];
        }

        public final int[] getTablesToSync() {
            boolean z;
            synchronized (this) {
                try {
                    if (!this.needsSync) {
                        return null;
                    }
                    long[] jArr = this.tableObservers;
                    int length = jArr.length;
                    int i2 = 0;
                    int i7 = 0;
                    while (i2 < length) {
                        int i8 = i7 + 1;
                        int i10 = 1;
                        if (jArr[i2] > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean[] zArr = this.triggerStates;
                        if (z != zArr[i7]) {
                            int[] iArr = this.triggerStateChanges;
                            if (!z) {
                                i10 = 2;
                            }
                            iArr[i7] = i10;
                        } else {
                            this.triggerStateChanges[i7] = 0;
                        }
                        zArr[i7] = z;
                        i2++;
                        i7 = i8;
                    }
                    this.needsSync = false;
                    int[] iArr2 = (int[]) this.triggerStateChanges.clone();
                    return iArr2;
                } finally {
                }
            }
        }

        public final boolean onAdded(int... iArr) {
            boolean z;
            j.e(iArr, "tableIds");
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.tableObservers;
                    long j2 = jArr[i2];
                    jArr[i2] = 1 + j2;
                    if (j2 == 0) {
                        z = true;
                        this.needsSync = true;
                    }
                }
            }
            return z;
        }

        public final boolean onRemoved(int... iArr) {
            boolean z;
            j.e(iArr, "tableIds");
            synchronized (this) {
                z = false;
                for (int i2 : iArr) {
                    long[] jArr = this.tableObservers;
                    long j2 = jArr[i2];
                    jArr[i2] = j2 - 1;
                    if (j2 == 1) {
                        z = true;
                        this.needsSync = true;
                    }
                }
            }
            return z;
        }

        public final void resetTriggerState() {
            synchronized (this) {
                Arrays.fill(this.triggerStates, false);
                this.needsSync = true;
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&¢\u0006\u0004\b\t\u0010\nR\"\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8PX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/room/InvalidationTracker$Observer;", "", "", "", "tables", "<init>", "([Ljava/lang/String;)V", "", "Lme/x;", "onInvalidated", "(Ljava/util/Set;)V", "[Ljava/lang/String;", "getTables$room_runtime_release", "()[Ljava/lang/String;", "", "isRemote$room_runtime_release", "()Z", "isRemote", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Observer {
        private final String[] tables;

        public Observer(String[] strArr) {
            j.e(strArr, "tables");
            this.tables = strArr;
        }

        public final String[] getTables$room_runtime_release() {
            return this.tables;
        }

        public boolean isRemote$room_runtime_release() {
            return false;
        }

        public abstract void onInvalidated(Set<String> set);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u0011\u001a\u00020\u000e2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/room/InvalidationTracker$ObserverWrapper;", "", "Landroidx/room/InvalidationTracker$Observer;", "observer", "", "tableIds", "", "", "tableNames", "<init>", "(Landroidx/room/InvalidationTracker$Observer;[I[Ljava/lang/String;)V", "", "", "invalidatedTablesIds", "Lme/x;", "notifyByTableInvalidStatus$room_runtime_release", "(Ljava/util/Set;)V", "notifyByTableInvalidStatus", "tables", "notifyByTableNames$room_runtime_release", "([Ljava/lang/String;)V", "notifyByTableNames", "Landroidx/room/InvalidationTracker$Observer;", "getObserver$room_runtime_release", "()Landroidx/room/InvalidationTracker$Observer;", "[I", "getTableIds$room_runtime_release", "()[I", "[Ljava/lang/String;", "singleTableSet", "Ljava/util/Set;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObserverWrapper {
        private final Observer observer;
        private final Set<String> singleTableSet;
        private final int[] tableIds;
        private final String[] tableNames;

        public ObserverWrapper(Observer observer2, int[] iArr, String[] strArr) {
            boolean z;
            Set<String> set;
            j.e(observer2, "observer");
            j.e(iArr, "tableIds");
            j.e(strArr, "tableNames");
            this.observer = observer2;
            this.tableIds = iArr;
            this.tableNames = strArr;
            if (strArr.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                set = a.S(strArr[0]);
            } else {
                set = v.d;
            }
            this.singleTableSet = set;
            if (iArr.length != strArr.length) {
                throw new IllegalStateException("Check failed.");
            }
        }

        public final int[] getTableIds$room_runtime_release() {
            return this.tableIds;
        }

        public final void notifyByTableInvalidStatus$room_runtime_release(Set<Integer> set) {
            j.e(set, "invalidatedTablesIds");
            int[] iArr = this.tableIds;
            int length = iArr.length;
            Set set2 = v.d;
            if (length != 0) {
                int i2 = 0;
                if (length != 1) {
                    C1220i iVar = new C1220i();
                    int[] iArr2 = this.tableIds;
                    int length2 = iArr2.length;
                    int i7 = 0;
                    while (i2 < length2) {
                        int i8 = i7 + 1;
                        if (set.contains(Integer.valueOf(iArr2[i2]))) {
                            iVar.add(this.tableNames[i7]);
                        }
                        i2++;
                        i7 = i8;
                    }
                    set2 = a.d(iVar);
                } else if (set.contains(Integer.valueOf(iArr[0]))) {
                    set2 = this.singleTableSet;
                }
            }
            if (!set2.isEmpty()) {
                this.observer.onInvalidated(set2);
            }
        }

        public final void notifyByTableNames$room_runtime_release(String[] strArr) {
            j.e(strArr, "tables");
            int length = this.tableNames.length;
            Set set = v.d;
            if (length != 0) {
                if (length != 1) {
                    C1220i iVar = new C1220i();
                    for (String str : strArr) {
                        for (String str2 : this.tableNames) {
                            if (Tf.v.p0(str2, str, true)) {
                                iVar.add(str2);
                            }
                        }
                    }
                    set = a.d(iVar);
                } else {
                    int length2 = strArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        } else if (Tf.v.p0(strArr[i2], this.tableNames[0], true)) {
                            set = this.singleTableSet;
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            if (!set.isEmpty()) {
                this.observer.onInvalidated(set);
            }
        }
    }

    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        String str;
        j.e(roomDatabase, "database");
        j.e(map, "shadowTablesMap");
        j.e(map2, "viewTables");
        j.e(strArr, "tableNames");
        this.database = roomDatabase;
        this.shadowTablesMap = map;
        this.viewTables = map2;
        this.observedTableTracker = new ObservedTableTracker(strArr.length);
        this.invalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        this.observerMap = new SafeIterableMap<>();
        this.syncTriggersLock = new Object();
        this.trackerLock = new Object();
        this.tableIdLookup = new LinkedHashMap();
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String str2 = strArr[i2];
            Locale locale = Locale.US;
            j.d(locale, "US");
            String lowerCase = str2.toLowerCase(locale);
            j.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            this.tableIdLookup.put(lowerCase, Integer.valueOf(i2));
            String str3 = this.shadowTablesMap.get(strArr[i2]);
            if (str3 != null) {
                str = str3.toLowerCase(locale);
                j.d(str, "this as java.lang.String).toLowerCase(locale)");
            } else {
                str = null;
            }
            if (str != null) {
                lowerCase = str;
            }
            strArr2[i2] = lowerCase;
        }
        this.tablesNames = strArr2;
        for (Map.Entry next : this.shadowTablesMap.entrySet()) {
            Locale locale2 = Locale.US;
            j.d(locale2, "US");
            String lowerCase2 = ((String) next.getValue()).toLowerCase(locale2);
            j.d(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            if (this.tableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = ((String) next.getKey()).toLowerCase(locale2);
                j.d(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
                Map<String, Integer> map3 = this.tableIdLookup;
                map3.put(lowerCase3, z.Y(lowerCase2, map3));
            }
        }
        this.refreshRunnable = new InvalidationTracker$refreshRunnable$1(this);
    }

    /* access modifiers changed from: private */
    public final void onAutoCloseCallback() {
        synchronized (this.trackerLock) {
            this.initialized = false;
            this.observedTableTracker.resetTriggerState();
            SupportSQLiteStatement supportSQLiteStatement = this.cleanupStatement;
            if (supportSQLiteStatement != null) {
                supportSQLiteStatement.close();
            }
        }
    }

    private final String[] resolveViews(String[] strArr) {
        C1220i iVar = new C1220i();
        for (String str : strArr) {
            Map<String, Set<String>> map = this.viewTables;
            Locale locale = Locale.US;
            j.d(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            j.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (map.containsKey(lowerCase)) {
                Map<String, Set<String>> map2 = this.viewTables;
                String lowerCase2 = str.toLowerCase(locale);
                j.d(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                Set<String> set = map2.get(lowerCase2);
                j.b(set);
                iVar.addAll(set);
            } else {
                iVar.add(str);
            }
        }
        return (String[]) a.d(iVar).toArray(new String[0]);
    }

    private final void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i2 + ", 0)");
        String str = this.tablesNames[i2];
        for (String str2 : TRIGGERS) {
            StringBuilder sb2 = new StringBuilder("CREATE TEMP TRIGGER IF NOT EXISTS ");
            sb2.append(Companion.getTriggerName$room_runtime_release(str, str2));
            sb2.append(" AFTER ");
            C0086a.z(sb2, str2, " ON `", str, "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = ");
            sb2.append(i2);
            sb2.append(" AND invalidated = 0; END");
            String sb3 = sb2.toString();
            j.d(sb3, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase.execSQL(sb3);
        }
    }

    private final void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i2) {
        String str = this.tablesNames[i2];
        for (String str2 : TRIGGERS) {
            String str3 = "DROP TRIGGER IF EXISTS " + Companion.getTriggerName$room_runtime_release(str, str2);
            j.d(str3, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase.execSQL(str3);
        }
    }

    public void addObserver(Observer observer) {
        ObserverWrapper putIfAbsent;
        j.e(observer, "observer");
        String[] resolveViews = resolveViews(observer.getTables$room_runtime_release());
        ArrayList arrayList = new ArrayList(resolveViews.length);
        int length = resolveViews.length;
        int i2 = 0;
        while (i2 < length) {
            String str = resolveViews[i2];
            Map<String, Integer> map = this.tableIdLookup;
            Locale locale = Locale.US;
            j.d(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            j.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Integer num = map.get(lowerCase);
            if (num != null) {
                arrayList.add(num);
                i2++;
            } else {
                throw new IllegalArgumentException("There is no table with name ".concat(str));
            }
        }
        int[] j12 = C1194l.j1(arrayList);
        ObserverWrapper observerWrapper = new ObserverWrapper(observer, j12, resolveViews);
        synchronized (this.observerMap) {
            putIfAbsent = this.observerMap.putIfAbsent(observer, observerWrapper);
        }
        if (putIfAbsent == null && this.observedTableTracker.onAdded(Arrays.copyOf(j12, j12.length))) {
            syncTriggers$room_runtime_release();
        }
    }

    public final boolean ensureInitialization$room_runtime_release() {
        if (!this.database.isOpenInternal()) {
            return false;
        }
        if (!this.initialized) {
            this.database.getOpenHelper().getWritableDatabase();
        }
        if (this.initialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public final SupportSQLiteStatement getCleanupStatement$room_runtime_release() {
        return this.cleanupStatement;
    }

    public final RoomDatabase getDatabase$room_runtime_release() {
        return this.database;
    }

    public final SafeIterableMap<Observer, ObserverWrapper> getObserverMap$room_runtime_release() {
        return this.observerMap;
    }

    public final AtomicBoolean getPendingRefresh() {
        return this.pendingRefresh;
    }

    public final Map<String, Integer> getTableIdLookup$room_runtime_release() {
        return this.tableIdLookup;
    }

    public final void internalInit$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "database");
        synchronized (this.trackerLock) {
            if (this.initialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
            supportSQLiteDatabase.execSQL("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            syncTriggers$room_runtime_release(supportSQLiteDatabase);
            this.cleanupStatement = supportSQLiteDatabase.compileStatement("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            this.initialized = true;
        }
    }

    public final void notifyObserversByTableNames(String... strArr) {
        j.e(strArr, "tables");
        synchronized (this.observerMap) {
            Iterator<T> it = this.observerMap.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                j.d(entry, "(observer, wrapper)");
                ObserverWrapper observerWrapper = (ObserverWrapper) entry.getValue();
                if (!((Observer) entry.getKey()).isRemote$room_runtime_release()) {
                    observerWrapper.notifyByTableNames$room_runtime_release(strArr);
                }
            }
        }
    }

    public void refreshVersionsAsync() {
        if (this.pendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser2 = this.autoCloser;
            if (autoCloser2 != null) {
                autoCloser2.incrementCountAndEnsureDbIsOpen();
            }
            this.database.getQueryExecutor().execute(this.refreshRunnable);
        }
    }

    public void removeObserver(Observer observer) {
        ObserverWrapper remove;
        j.e(observer, "observer");
        synchronized (this.observerMap) {
            remove = this.observerMap.remove(observer);
        }
        if (remove != null) {
            ObservedTableTracker observedTableTracker2 = this.observedTableTracker;
            int[] tableIds$room_runtime_release = remove.getTableIds$room_runtime_release();
            if (observedTableTracker2.onRemoved(Arrays.copyOf(tableIds$room_runtime_release, tableIds$room_runtime_release.length))) {
                syncTriggers$room_runtime_release();
            }
        }
    }

    public final void setAutoCloser$room_runtime_release(AutoCloser autoCloser2) {
        j.e(autoCloser2, "autoCloser");
        this.autoCloser = autoCloser2;
        autoCloser2.setAutoCloseCallback(new C0451a(15, this));
    }

    public final void startMultiInstanceInvalidation$room_runtime_release(Context context, String str, Intent intent) {
        j.e(context, "context");
        j.e(str, "name");
        j.e(intent, "serviceIntent");
        this.multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, intent, this, this.database.getQueryExecutor());
    }

    public final void syncTriggers$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
        Lock closeLock$room_runtime_release;
        j.e(supportSQLiteDatabase, "database");
        if (!supportSQLiteDatabase.inTransaction()) {
            try {
                closeLock$room_runtime_release = this.database.getCloseLock$room_runtime_release();
                closeLock$room_runtime_release.lock();
                synchronized (this.syncTriggersLock) {
                    int[] tablesToSync = this.observedTableTracker.getTablesToSync();
                    if (tablesToSync != null) {
                        Companion.beginTransactionInternal$room_runtime_release(supportSQLiteDatabase);
                        try {
                            int length = tablesToSync.length;
                            int i2 = 0;
                            int i7 = 0;
                            while (i2 < length) {
                                int i8 = tablesToSync[i2];
                                int i10 = i7 + 1;
                                if (i8 == 1) {
                                    startTrackingTable(supportSQLiteDatabase, i7);
                                } else if (i8 == 2) {
                                    stopTrackingTable(supportSQLiteDatabase, i7);
                                }
                                i2++;
                                i7 = i10;
                            }
                            supportSQLiteDatabase.setTransactionSuccessful();
                            supportSQLiteDatabase.endTransaction();
                        } finally {
                            supportSQLiteDatabase.endTransaction();
                        }
                    }
                }
                closeLock$room_runtime_release.unlock();
            } catch (IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            } catch (SQLiteException e7) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e7);
            } catch (Throwable th) {
                closeLock$room_runtime_release.unlock();
                throw th;
            }
        }
    }

    public final void syncTriggers$room_runtime_release() {
        if (this.database.isOpenInternal()) {
            syncTriggers$room_runtime_release(this.database.getOpenHelper().getWritableDatabase());
        }
    }
}
