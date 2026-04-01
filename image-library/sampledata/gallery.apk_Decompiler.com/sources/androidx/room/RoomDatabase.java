package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteCompat$Api19Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import ne.C1203u;
import ne.v;

@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0019\b&\u0018\u0000 y2\u00020\u0001:\u0006z{y|}~B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0017¢\u0006\u0004\b\u000b\u0010\fJ1\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u001a\u0010\u0010\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\bH$¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H$¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001c\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u00110\rH\u0015¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e0\u001eH\u0017¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\nH\u0017¢\u0006\u0004\b!\u0010\u0003J\u000f\u0010\"\u001a\u00020\nH\u0017¢\u0006\u0004\b\"\u0010\u0003J#\u0010$\u001a\u00020'2\u0006\u0010$\u001a\u00020#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%H\u0017¢\u0006\u0004\b$\u0010(J\u0017\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\nH\u0017¢\u0006\u0004\b.\u0010\u0003J\u000f\u0010/\u001a\u00020\nH\u0017¢\u0006\u0004\b/\u0010\u0003J\u000f\u00100\u001a\u00020\nH\u0017¢\u0006\u0004\b0\u0010\u0003J\u0017\u00103\u001a\u00020\n2\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J#\u00103\u001a\u00028\u0000\"\u0004\b\u0000\u001052\f\u00102\u001a\b\u0012\u0004\u0012\u00028\u000006H\u0016¢\u0006\u0004\b3\u00107J\u0017\u0010:\u001a\u00020\n2\u0006\u00109\u001a\u000208H\u0014¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>J-\u0010B\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010?2\f\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010A\u001a\u00020\u0016H\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\nH\u0002¢\u0006\u0004\bD\u0010\u0003J\u000f\u0010E\u001a\u00020\nH\u0002¢\u0006\u0004\bE\u0010\u0003R\u001e\u0010F\u001a\u0004\u0018\u0001088\u0004@\u0004X\u000e¢\u0006\f\n\u0004\bF\u0010G\u0012\u0004\bH\u0010\u0003R\u0016\u0010J\u001a\u00020I8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020I8\u0002@\u0002X.¢\u0006\u0006\n\u0004\bL\u0010KR\u0016\u0010M\u001a\u00020\u00168\u0002@\u0002X.¢\u0006\u0006\n\u0004\bM\u0010NR\u001a\u0010O\u001a\u00020\u00198\u0016X\u0004¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010\u001bR\u0016\u0010R\u001a\u00020<8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020<8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bT\u0010SR$\u0010V\u001a\n\u0012\u0004\u0012\u00020U\u0018\u00010\u00118\u0004@\u0004X\u000e¢\u0006\f\n\u0004\bV\u0010W\u0012\u0004\bX\u0010\u0003RR\u0010\u0010\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u000f0Y2\u001a\u0010Z\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u000f0Y8E@EX\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010[\u001a\u0004\b\\\u0010\u001d\"\u0004\b]\u0010^R\u0014\u0010`\u001a\u00020_8\u0002X\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bc\u0010dR\u001d\u0010g\u001a\b\u0012\u0004\u0012\u00020f0e8G¢\u0006\f\n\u0004\bg\u0010h\u001a\u0004\bi\u0010jR#\u0010k\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00010Y8G¢\u0006\f\n\u0004\bk\u0010[\u001a\u0004\bl\u0010\u001dR$\u0010m\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0004\u0012\u00020\u00010Y8\u0002X\u0004¢\u0006\u0006\n\u0004\bm\u0010[R\u0014\u0010p\u001a\u00020I8VX\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0014\u0010r\u001a\u00020I8VX\u0004¢\u0006\u0006\u001a\u0004\bq\u0010oR\u0014\u0010A\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0017\u0010u\u001a\u00020<8G¢\u0006\f\u0012\u0004\bv\u0010\u0003\u001a\u0004\bu\u0010>R\u0014\u0010x\u001a\u00020<8@X\u0004¢\u0006\u0006\u001a\u0004\bw\u0010>¨\u0006"}, d2 = {"Landroidx/room/RoomDatabase;", "", "<init>", "()V", "Ljava/util/concurrent/locks/Lock;", "getCloseLock$room_runtime_release", "()Ljava/util/concurrent/locks/Lock;", "getCloseLock", "Landroidx/room/DatabaseConfiguration;", "configuration", "Lme/x;", "init", "(Landroidx/room/DatabaseConfiguration;)V", "", "Ljava/lang/Class;", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "", "Landroidx/room/migration/Migration;", "getAutoMigrations", "(Ljava/util/Map;)Ljava/util/List;", "config", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "createOpenHelper", "(Landroidx/room/DatabaseConfiguration;)Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/InvalidationTracker;", "createInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "getRequiredTypeConverters", "()Ljava/util/Map;", "", "getRequiredAutoMigrationSpecs", "()Ljava/util/Set;", "assertNotMainThread", "assertNotSuspendingTransaction", "Landroidx/sqlite/db/SupportSQLiteQuery;", "query", "Landroid/os/CancellationSignal;", "signal", "Landroid/database/Cursor;", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "", "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "beginTransaction", "endTransaction", "setTransactionSuccessful", "Ljava/lang/Runnable;", "body", "runInTransaction", "(Ljava/lang/Runnable;)V", "V", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;)Ljava/lang/Object;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "internalInitInvalidationTracker", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "", "inTransaction", "()Z", "T", "clazz", "openHelper", "unwrapOpenHelper", "(Ljava/lang/Class;Landroidx/sqlite/db/SupportSQLiteOpenHelper;)Ljava/lang/Object;", "internalBeginTransaction", "internalEndTransaction", "mDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getMDatabase$annotations", "Ljava/util/concurrent/Executor;", "internalQueryExecutor", "Ljava/util/concurrent/Executor;", "internalTransactionExecutor", "internalOpenHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "invalidationTracker", "Landroidx/room/InvalidationTracker;", "getInvalidationTracker", "allowMainThreadQueries", "Z", "writeAheadLoggingEnabled", "Landroidx/room/RoomDatabase$Callback;", "mCallbacks", "Ljava/util/List;", "getMCallbacks$annotations", "", "<set-?>", "Ljava/util/Map;", "getAutoMigrationSpecs", "setAutoMigrationSpecs", "(Ljava/util/Map;)V", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "readWriteLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Landroidx/room/AutoCloser;", "autoCloser", "Landroidx/room/AutoCloser;", "Ljava/lang/ThreadLocal;", "", "suspendingTransactionId", "Ljava/lang/ThreadLocal;", "getSuspendingTransactionId", "()Ljava/lang/ThreadLocal;", "backingFieldMap", "getBackingFieldMap", "typeConverters", "getQueryExecutor", "()Ljava/util/concurrent/Executor;", "queryExecutor", "getTransactionExecutor", "transactionExecutor", "getOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "isOpenInternal", "isOpenInternal$annotations", "isMainThread$room_runtime_release", "isMainThread", "Companion", "Builder", "Callback", "JournalMode", "MigrationContainer", "PrepackagedDatabaseCallback", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RoomDatabase {
    public static final Companion Companion = new Companion((e) null);
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs = new LinkedHashMap();
    private final Map<String, Object> backingFieldMap;
    private SupportSQLiteOpenHelper internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    private final InvalidationTracker invalidationTracker = createInvalidationTracker();
    protected List<? extends Callback> mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> suspendingTransactionId = new ThreadLocal<>();
    private final Map<Class<?>, Object> typeConverters;
    private boolean writeAheadLoggingEnabled;

    @Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B)\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u001b\u0010\u0016J\u001d\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0004\b \u0010!R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010#R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010$R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0%8\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030%8\u0002X\u0004¢\u0006\u0006\n\u0004\b(\u0010'R\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020)0%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010'R\u0018\u0010+\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010-\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b-\u0010,R\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\r\u0010.R\u0016\u0010\u0015\u001a\u00020/8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0018\u00105\u001a\u0004\u0018\u0001048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020/8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00100R\u0016\u00108\u001a\u00020/8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b8\u00100R\u0016\u0010:\u001a\u0002098\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010=\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u001c\u0010D\u001a\b\u0012\u0004\u0012\u00020C0B8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u001e\u0010F\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010B8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bF\u0010ER\u0018\u0010G\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010$R\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u001e\u0010M\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010K8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bM\u0010N¨\u0006O"}, d2 = {"Landroidx/room/RoomDatabase$Builder;", "Landroidx/room/RoomDatabase;", "T", "", "Landroid/content/Context;", "context", "Ljava/lang/Class;", "klass", "", "name", "<init>", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;)V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "factory", "openHelperFactory", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;)Landroidx/room/RoomDatabase$Builder;", "", "Landroidx/room/migration/Migration;", "migrations", "addMigrations", "([Landroidx/room/migration/Migration;)Landroidx/room/RoomDatabase$Builder;", "allowMainThreadQueries", "()Landroidx/room/RoomDatabase$Builder;", "Ljava/util/concurrent/Executor;", "executor", "setQueryExecutor", "(Ljava/util/concurrent/Executor;)Landroidx/room/RoomDatabase$Builder;", "fallbackToDestructiveMigration", "Landroidx/room/RoomDatabase$Callback;", "callback", "addCallback", "(Landroidx/room/RoomDatabase$Callback;)Landroidx/room/RoomDatabase$Builder;", "build", "()Landroidx/room/RoomDatabase;", "Landroid/content/Context;", "Ljava/lang/Class;", "Ljava/lang/String;", "", "callbacks", "Ljava/util/List;", "typeConverters", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "queryExecutor", "Ljava/util/concurrent/Executor;", "transactionExecutor", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "", "Z", "Landroidx/room/RoomDatabase$JournalMode;", "journalMode", "Landroidx/room/RoomDatabase$JournalMode;", "Landroid/content/Intent;", "multiInstanceInvalidationIntent", "Landroid/content/Intent;", "requireMigration", "allowDestructiveMigrationOnDowngrade", "", "autoCloseTimeout", "J", "Ljava/util/concurrent/TimeUnit;", "autoCloseTimeUnit", "Ljava/util/concurrent/TimeUnit;", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationContainer", "Landroidx/room/RoomDatabase$MigrationContainer;", "", "", "migrationsNotRequiredFrom", "Ljava/util/Set;", "migrationStartAndEndVersions", "copyFromAssetPath", "Ljava/io/File;", "copyFromFile", "Ljava/io/File;", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder<T extends RoomDatabase> {
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout = -1;
        private List<AutoMigrationSpec> autoMigrationSpecs = new ArrayList();
        private final List<Callback> callbacks = new ArrayList();
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable<InputStream> copyFromInputStream;
        private SupportSQLiteOpenHelper.Factory factory;
        private JournalMode journalMode = JournalMode.AUTOMATIC;
        private final Class<T> klass;
        private final MigrationContainer migrationContainer = new MigrationContainer();
        private Set<Integer> migrationStartAndEndVersions;
        private Set<Integer> migrationsNotRequiredFrom = new LinkedHashSet();
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private Executor queryExecutor;
        private boolean requireMigration = true;
        private Executor transactionExecutor;
        private final List<Object> typeConverters = new ArrayList();

        public Builder(Context context2, Class<T> cls, String str) {
            j.e(context2, "context");
            j.e(cls, "klass");
            this.context = context2;
            this.klass = cls;
            this.name = str;
        }

        public Builder<T> addCallback(Callback callback) {
            j.e(callback, Profile.PhoneNumberData.TYPE_CALLBACK);
            this.callbacks.add(callback);
            return this;
        }

        public Builder<T> addMigrations(Migration... migrationArr) {
            j.e(migrationArr, "migrations");
            if (this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                Set<Integer> set = this.migrationStartAndEndVersions;
                j.b(set);
                set.add(Integer.valueOf(migration.startVersion));
                Set<Integer> set2 = this.migrationStartAndEndVersions;
                j.b(set2);
                set2.add(Integer.valueOf(migration.endVersion));
            }
            this.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(migrationArr, migrationArr.length));
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public T build() {
            int i2;
            int i7;
            Executor executor = this.queryExecutor;
            if (executor == null && this.transactionExecutor == null) {
                Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
                this.transactionExecutor = iOThreadExecutor;
                this.queryExecutor = iOThreadExecutor;
            } else if (executor != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor;
            } else if (executor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set<Integer> set = this.migrationStartAndEndVersions;
            if (set != null) {
                j.b(set);
                for (Integer intValue : set) {
                    int intValue2 = intValue.intValue();
                    if (this.migrationsNotRequiredFrom.contains(Integer.valueOf(intValue2))) {
                        throw new IllegalArgumentException(C0086a.i(intValue2, "Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ").toString());
                    }
                }
            }
            AutoClosingRoomOpenHelperFactory autoClosingRoomOpenHelperFactory = this.factory;
            if (autoClosingRoomOpenHelperFactory == null) {
                autoClosingRoomOpenHelperFactory = new FrameworkSQLiteOpenHelperFactory();
            }
            if (autoClosingRoomOpenHelperFactory != null) {
                if (this.autoCloseTimeout > 0) {
                    if (this.name != null) {
                        long j2 = this.autoCloseTimeout;
                        TimeUnit timeUnit = this.autoCloseTimeUnit;
                        if (timeUnit != null) {
                            Executor executor2 = this.queryExecutor;
                            if (executor2 != null) {
                                autoClosingRoomOpenHelperFactory = new AutoClosingRoomOpenHelperFactory(autoClosingRoomOpenHelperFactory, new AutoCloser(j2, timeUnit, executor2));
                            } else {
                                throw new IllegalArgumentException("Required value was null.");
                            }
                        } else {
                            throw new IllegalArgumentException("Required value was null.");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
                    }
                }
                String str = this.copyFromAssetPath;
                if (!(str == null && this.copyFromFile == null && this.copyFromInputStream == null)) {
                    if (this.name != null) {
                        int i8 = 0;
                        if (str == null) {
                            i2 = 0;
                        } else {
                            i2 = 1;
                        }
                        File file = this.copyFromFile;
                        if (file == null) {
                            i7 = 0;
                        } else {
                            i7 = 1;
                        }
                        Callable<InputStream> callable = this.copyFromInputStream;
                        if (callable != null) {
                            i8 = 1;
                        }
                        if (i2 + i7 + i8 == 1) {
                            autoClosingRoomOpenHelperFactory = new SQLiteCopyOpenHelperFactory(str, file, callable, autoClosingRoomOpenHelperFactory);
                        } else {
                            throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                    }
                }
            } else {
                autoClosingRoomOpenHelperFactory = null;
            }
            SQLiteCopyOpenHelperFactory sQLiteCopyOpenHelperFactory = autoClosingRoomOpenHelperFactory;
            if (sQLiteCopyOpenHelperFactory != null) {
                Context context2 = this.context;
                String str2 = this.name;
                MigrationContainer migrationContainer2 = this.migrationContainer;
                List<Callback> list = this.callbacks;
                boolean z = this.allowMainThreadQueries;
                JournalMode resolve$room_runtime_release = this.journalMode.resolve$room_runtime_release(context2);
                Executor executor3 = this.queryExecutor;
                if (executor3 != null) {
                    Executor executor4 = this.transactionExecutor;
                    if (executor4 != null) {
                        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context2, str2, sQLiteCopyOpenHelperFactory, migrationContainer2, list, z, resolve$room_runtime_release, executor3, executor4, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, (PrepackagedDatabaseCallback) null, this.typeConverters, this.autoMigrationSpecs);
                        T t = (RoomDatabase) Room.getGeneratedImplementation(this.klass, "_Impl");
                        t.init(databaseConfiguration);
                        return t;
                    }
                    throw new IllegalArgumentException("Required value was null.");
                }
                throw new IllegalArgumentException("Required value was null.");
            }
            throw new IllegalArgumentException("Required value was null.");
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory2) {
            this.factory = factory2;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            j.e(executor, "executor");
            this.queryExecutor = executor;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/room/RoomDatabase$Callback;", "", "<init>", "()V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "Lme/x;", "onCreate", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "onOpen", "onDestructiveMigration", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "db");
        }

        public void onDestructiveMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "db");
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "db");
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/RoomDatabase$Companion;", "", "()V", "MAX_BIND_PARAMETER_CNT", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Landroidx/room/RoomDatabase$JournalMode;", "", "(Ljava/lang/String;I)V", "isLowRamDevice", "", "activityManager", "Landroid/app/ActivityManager;", "resolve", "context", "Landroid/content/Context;", "resolve$room_runtime_release", "AUTOMATIC", "TRUNCATE", "WRITE_AHEAD_LOGGING", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private final boolean isLowRamDevice(ActivityManager activityManager) {
            return SupportSQLiteCompat$Api19Impl.isLowRamDevice(activityManager);
        }

        public final JournalMode resolve$room_runtime_release(Context context) {
            ActivityManager activityManager;
            j.e(context, "context");
            if (this != AUTOMATIC) {
                return this;
            }
            Object systemService = context.getSystemService("activity");
            if (systemService instanceof ActivityManager) {
                activityManager = (ActivityManager) systemService;
            } else {
                activityManager = null;
            }
            if (activityManager == null || isLowRamDevice(activityManager)) {
                return TRUNCATE;
            }
            return WRITE_AHEAD_LOGGING;
        }
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ=\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0015\u001a\u00020\u00062\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0013\"\u00020\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0018\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040\u00170\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\r¢\u0006\u0004\b\u001e\u0010\u001fR,\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040!0 8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\"¨\u0006#"}, d2 = {"Landroidx/room/RoomDatabase$MigrationContainer;", "", "<init>", "()V", "Landroidx/room/migration/Migration;", "migration", "Lme/x;", "addMigration", "(Landroidx/room/migration/Migration;)V", "", "result", "", "upgrade", "", "start", "end", "", "findUpMigrationPath", "(Ljava/util/List;ZII)Ljava/util/List;", "", "migrations", "addMigrations", "([Landroidx/room/migration/Migration;)V", "", "getMigrations", "()Ljava/util/Map;", "findMigrationPath", "(II)Ljava/util/List;", "startVersion", "endVersion", "contains", "(II)Z", "", "Ljava/util/TreeMap;", "Ljava/util/Map;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MigrationContainer {
        private final Map<Integer, TreeMap<Integer, Migration>> migrations = new LinkedHashMap();

        private final void addMigration(Migration migration) {
            int i2 = migration.startVersion;
            int i7 = migration.endVersion;
            Map<Integer, TreeMap<Integer, Migration>> map = this.migrations;
            Integer valueOf = Integer.valueOf(i2);
            TreeMap<Integer, Migration> treeMap = map.get(valueOf);
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                map.put(valueOf, treeMap);
            }
            TreeMap treeMap2 = treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i7))) {
                Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i7)) + " with " + migration);
            }
            treeMap2.put(Integer.valueOf(i7), migration);
        }

        private final List<Migration> findUpMigrationPath(List<Migration> list, boolean z, int i2, int i7) {
            Set set;
            boolean z3;
            Integer num;
            do {
                if (z) {
                    if (i2 >= i7) {
                        return list;
                    }
                } else if (i2 <= i7) {
                    return list;
                }
                TreeMap treeMap = this.migrations.get(Integer.valueOf(i2));
                if (treeMap == null) {
                    return null;
                }
                if (z) {
                    set = treeMap.descendingKeySet();
                } else {
                    set = treeMap.keySet();
                }
                Iterator it = set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z3 = false;
                        continue;
                        break;
                    }
                    num = (Integer) it.next();
                    if (!z) {
                        j.d(num, "targetVersion");
                        int intValue = num.intValue();
                        if (i7 <= intValue && intValue < i2) {
                            break;
                        }
                    } else {
                        int i8 = i2 + 1;
                        j.d(num, "targetVersion");
                        int intValue2 = num.intValue();
                        if (i8 <= intValue2 && intValue2 <= i7) {
                            break;
                        }
                    }
                }
                Object obj = treeMap.get(num);
                j.b(obj);
                list.add(obj);
                i2 = num.intValue();
                z3 = true;
                continue;
            } while (z3);
            return null;
        }

        public void addMigrations(Migration... migrationArr) {
            j.e(migrationArr, "migrations");
            for (Migration addMigration : migrationArr) {
                addMigration(addMigration);
            }
        }

        public final boolean contains(int i2, int i7) {
            Map<Integer, Map<Integer, Migration>> migrations2 = getMigrations();
            if (!migrations2.containsKey(Integer.valueOf(i2))) {
                return false;
            }
            Map map = migrations2.get(Integer.valueOf(i2));
            if (map == null) {
                map = C1203u.d;
            }
            return map.containsKey(Integer.valueOf(i7));
        }

        public List<Migration> findMigrationPath(int i2, int i7) {
            boolean z;
            if (i2 == i7) {
                return C1202t.d;
            }
            if (i7 > i2) {
                z = true;
            } else {
                z = false;
            }
            return findUpMigrationPath(new ArrayList(), z, i2, i7);
        }

        public Map<Integer, Map<Integer, Migration>> getMigrations() {
            return this.migrations;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b&\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class PrepackagedDatabaseCallback {
    }

    public RoomDatabase() {
        Map<String, Object> synchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        j.d(synchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = synchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    /* access modifiers changed from: private */
    public final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        getInvalidationTracker().syncTriggers$room_runtime_release(writableDatabase);
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    /* access modifiers changed from: private */
    public final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            getInvalidationTracker().refreshVersionsAsync();
        }
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                cancellationSignal = null;
            }
            return roomDatabase.query(supportSQLiteQuery, cancellationSignal);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
    }

    private final <T> T unwrapOpenHelper(Class<T> cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && isMainThread$room_runtime_release()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser2 = this.autoCloser;
        if (autoCloser2 == null) {
            internalBeginTransaction();
        } else {
            autoCloser2.executeRefCountingFunction(new RoomDatabase$beginTransaction$1(this));
        }
    }

    public SupportSQLiteStatement compileStatement(String str) {
        j.e(str, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(str);
    }

    public abstract InvalidationTracker createInvalidationTracker();

    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    public void endTransaction() {
        AutoCloser autoCloser2 = this.autoCloser;
        if (autoCloser2 == null) {
            internalEndTransaction();
        } else {
            autoCloser2.executeRefCountingFunction(new RoomDatabase$endTransaction$1(this));
        }
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        j.e(map, "autoMigrationSpecs");
        return C1202t.d;
    }

    public final Map<String, Object> getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock readLock = this.readWriteLock.readLock();
        j.d(readLock, "readWriteLock.readLock()");
        return readLock;
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.internalOpenHelper;
        if (supportSQLiteOpenHelper != null) {
            return supportSQLiteOpenHelper;
        }
        j.k("internalOpenHelper");
        throw null;
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        j.k("internalQueryExecutor");
        throw null;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return v.d;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return C1203u.d;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        j.k("internalTransactionExecutor");
        throw null;
    }

    public boolean inTransaction() {
        return getOpenHelper().getWritableDatabase().inTransaction();
    }

    public void init(DatabaseConfiguration databaseConfiguration) {
        boolean z;
        j.e(databaseConfiguration, "configuration");
        this.internalOpenHelper = createOpenHelper(databaseConfiguration);
        Set<Class<? extends AutoMigrationSpec>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator<Class<? extends AutoMigrationSpec>> it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            int i2 = -1;
            if (it.hasNext()) {
                Class next = it.next();
                int size = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i7 = size - 1;
                        if (next.isAssignableFrom(databaseConfiguration.autoMigrationSpecs.get(size).getClass())) {
                            bitSet.set(size);
                            i2 = size;
                            break;
                        } else if (i7 < 0) {
                            break;
                        } else {
                            size = i7;
                        }
                    }
                }
                if (i2 >= 0) {
                    this.autoMigrationSpecs.put(next, databaseConfiguration.autoMigrationSpecs.get(i2));
                } else {
                    throw new IllegalArgumentException(("A required auto migration spec (" + next.getCanonicalName() + ") is missing in the database configuration.").toString());
                }
            } else {
                int size2 = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size2 >= 0) {
                    while (true) {
                        int i8 = size2 - 1;
                        if (!bitSet.get(size2)) {
                            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                        } else if (i8 < 0) {
                            break;
                        } else {
                            size2 = i8;
                        }
                    }
                }
                for (Migration next2 : getAutoMigrations(this.autoMigrationSpecs)) {
                    if (!databaseConfiguration.migrationContainer.contains(next2.startVersion, next2.endVersion)) {
                        databaseConfiguration.migrationContainer.addMigrations(next2);
                    }
                }
                SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) unwrapOpenHelper(SQLiteCopyOpenHelper.class, getOpenHelper());
                if (sQLiteCopyOpenHelper != null) {
                    sQLiteCopyOpenHelper.setDatabaseConfiguration(databaseConfiguration);
                }
                AutoClosingRoomOpenHelper autoClosingRoomOpenHelper = (AutoClosingRoomOpenHelper) unwrapOpenHelper(AutoClosingRoomOpenHelper.class, getOpenHelper());
                if (autoClosingRoomOpenHelper != null) {
                    this.autoCloser = autoClosingRoomOpenHelper.autoCloser;
                    getInvalidationTracker().setAutoCloser$room_runtime_release(autoClosingRoomOpenHelper.autoCloser);
                }
                if (databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING) {
                    z = true;
                } else {
                    z = false;
                }
                getOpenHelper().setWriteAheadLoggingEnabled(z);
                this.mCallbacks = databaseConfiguration.callbacks;
                this.internalQueryExecutor = databaseConfiguration.queryExecutor;
                this.internalTransactionExecutor = new TransactionExecutor(databaseConfiguration.transactionExecutor);
                this.allowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
                this.writeAheadLoggingEnabled = z;
                if (databaseConfiguration.multiInstanceInvalidationServiceIntent != null) {
                    if (databaseConfiguration.name != null) {
                        getInvalidationTracker().startMultiInstanceInvalidation$room_runtime_release(databaseConfiguration.context, databaseConfiguration.name, databaseConfiguration.multiInstanceInvalidationServiceIntent);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                }
                Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                for (Map.Entry next3 : requiredTypeConverters.entrySet()) {
                    Class cls = (Class) next3.getKey();
                    Iterator it2 = ((List) next3.getValue()).iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Class cls2 = (Class) it2.next();
                            int size3 = databaseConfiguration.typeConverters.size() - 1;
                            if (size3 >= 0) {
                                while (true) {
                                    int i10 = size3 - 1;
                                    if (cls2.isAssignableFrom(databaseConfiguration.typeConverters.get(size3).getClass())) {
                                        bitSet2.set(size3);
                                        break;
                                    } else if (i10 < 0) {
                                        break;
                                    } else {
                                        size3 = i10;
                                    }
                                }
                            }
                            size3 = -1;
                            if (size3 >= 0) {
                                this.typeConverters.put(cls2, databaseConfiguration.typeConverters.get(size3));
                            } else {
                                throw new IllegalArgumentException(("A required type converter (" + cls2 + ") for " + cls.getCanonicalName() + " is missing in the database configuration.").toString());
                            }
                        }
                    }
                }
                int size4 = databaseConfiguration.typeConverters.size() - 1;
                if (size4 >= 0) {
                    while (true) {
                        int i11 = size4 - 1;
                        if (!bitSet2.get(size4)) {
                            throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                        } else if (i11 >= 0) {
                            size4 = i11;
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void internalInitInvalidationTracker(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        getInvalidationTracker().internalInit$room_runtime_release(supportSQLiteDatabase);
    }

    public final boolean isMainThread$room_runtime_release() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }

    public final boolean isOpenInternal() {
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        if (supportSQLiteDatabase == null || !supportSQLiteDatabase.isOpen()) {
            return false;
        }
        return true;
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        j.e(supportSQLiteQuery, Contract.QUERY);
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return getOpenHelper().getWritableDatabase().query(supportSQLiteQuery, cancellationSignal);
        }
        return getOpenHelper().getWritableDatabase().query(supportSQLiteQuery);
    }

    public void runInTransaction(Runnable runnable) {
        j.e(runnable, "body");
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public <V> V runInTransaction(Callable<V> callable) {
        j.e(callable, "body");
        beginTransaction();
        try {
            V call = callable.call();
            setTransactionSuccessful();
            return call;
        } finally {
            endTransaction();
        }
    }
}
