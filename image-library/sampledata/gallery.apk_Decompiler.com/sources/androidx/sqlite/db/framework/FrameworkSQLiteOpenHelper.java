package androidx.sqlite.db.framework;

import L1.d;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteCompat$Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 (2\u00020\u0001:\u0003()*B7\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\bH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0016R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u001b\u0010 \u001a\u00020\u00188BX\u0002¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d*\u0004\b\u001e\u0010\u001fR\u0016\u0010#\u001a\u0004\u0018\u00010\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010'\u001a\u00020$8VX\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006+"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroid/content/Context;", "context", "", "name", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "callback", "", "useNoBackupDirectory", "allowDataLossOnRecovery", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "enabled", "Lme/x;", "setWriteAheadLoggingEnabled", "(Z)V", "close", "()V", "Landroid/content/Context;", "Ljava/lang/String;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "Z", "Lme/f;", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "lazyDelegate", "Lme/f;", "writeAheadLoggingEnabled", "getDelegate", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "getDelegate$delegate", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper;)Ljava/lang/Object;", "delegate", "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "Companion", "DBRefHolder", "OpenHelper", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public final boolean allowDataLossOnRecovery;
    /* access modifiers changed from: private */
    public final SupportSQLiteOpenHelper.Callback callback;
    /* access modifiers changed from: private */
    public final Context context;
    private final f lazyDelegate = d.q(new FrameworkSQLiteOpenHelper$lazyDelegate$1(this));
    /* access modifiers changed from: private */
    public final String name;
    /* access modifiers changed from: private */
    public final boolean useNoBackupDirectory;
    /* access modifiers changed from: private */
    public boolean writeAheadLoggingEnabled;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$Companion;", "", "()V", "TAG", "", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "", "db", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "(Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;)V", "getDb", "()Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "setDb", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DBRefHolder {

        /* renamed from: db  reason: collision with root package name */
        private FrameworkSQLiteDatabase f1032db;

        public DBRefHolder(FrameworkSQLiteDatabase frameworkSQLiteDatabase) {
            this.f1032db = frameworkSQLiteDatabase;
        }

        public final FrameworkSQLiteDatabase getDb() {
            return this.f1032db;
        }

        public final void setDb(FrameworkSQLiteDatabase frameworkSQLiteDatabase) {
            this.f1032db = frameworkSQLiteDatabase;
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000 92\u00020\u0001:\u0003:;9B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u000f¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010 \u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000fH\u0016¢\u0006\u0004\b#\u0010\u001cJ'\u0010$\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016¢\u0006\u0004\b$\u0010!J\u0017\u0010%\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000fH\u0016¢\u0006\u0004\b%\u0010\u001cJ\u000f\u0010&\u001a\u00020\u001aH\u0016¢\u0006\u0004\b&\u0010'R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00101\u001a\u0004\b2\u00103R\u0016\u00104\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00101R\u0014\u00106\u001a\u0002058\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b8\u00101¨\u0006<"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Landroid/content/Context;", "context", "", "name", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "dbRef", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "callback", "", "allowDataLossOnRecovery", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;Z)V", "writable", "Landroid/database/sqlite/SQLiteDatabase;", "innerGetDatabase", "(Z)Landroid/database/sqlite/SQLiteDatabase;", "getWritableOrReadableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getSupportDatabase", "(Z)Landroidx/sqlite/db/SupportSQLiteDatabase;", "sqLiteDatabase", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "getWrappedDb", "(Landroid/database/sqlite/SQLiteDatabase;)Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "Lme/x;", "onCreate", "(Landroid/database/sqlite/SQLiteDatabase;)V", "", "oldVersion", "newVersion", "onUpgrade", "(Landroid/database/sqlite/SQLiteDatabase;II)V", "db", "onConfigure", "onDowngrade", "onOpen", "close", "()V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "getDbRef", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "getCallback", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "Z", "getAllowDataLossOnRecovery", "()Z", "migrated", "Landroidx/sqlite/util/ProcessLock;", "lock", "Landroidx/sqlite/util/ProcessLock;", "opened", "Companion", "CallbackException", "CallbackName", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OpenHelper extends SQLiteOpenHelper {
        public static final Companion Companion = new Companion((e) null);
        private final boolean allowDataLossOnRecovery;
        private final SupportSQLiteOpenHelper.Callback callback;
        private final Context context;
        private final DBRefHolder dbRef;
        private final ProcessLock lock;
        private boolean migrated;
        private boolean opened;

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "callbackName", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "cause", "", "(Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;Ljava/lang/Throwable;)V", "getCallbackName", "()Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "getCause", "()Ljava/lang/Throwable;", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class CallbackException extends RuntimeException {
            private final CallbackName callbackName;
            private final Throwable cause;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public CallbackException(CallbackName callbackName2, Throwable th) {
                super(th);
                j.e(callbackName2, "callbackName");
                j.e(th, "cause");
                this.callbackName = callbackName2;
                this.cause = th;
            }

            public final CallbackName getCallbackName() {
                return this.callbackName;
            }

            public Throwable getCause() {
                return this.cause;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;", "", "(Ljava/lang/String;I)V", "ON_CONFIGURE", "ON_CREATE", "ON_UPGRADE", "ON_DOWNGRADE", "ON_OPEN", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum CallbackName {
            ON_CONFIGURE,
            ON_CREATE,
            ON_UPGRADE,
            ON_DOWNGRADE,
            ON_OPEN
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$OpenHelper$Companion;", "", "()V", "getWrappedDb", "Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "refHolder", "Landroidx/sqlite/db/framework/FrameworkSQLiteOpenHelper$DBRefHolder;", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            public final FrameworkSQLiteDatabase getWrappedDb(DBRefHolder dBRefHolder, SQLiteDatabase sQLiteDatabase) {
                j.e(dBRefHolder, "refHolder");
                j.e(sQLiteDatabase, "sqLiteDatabase");
                FrameworkSQLiteDatabase db2 = dBRefHolder.getDb();
                if (db2 != null && db2.isDelegate(sQLiteDatabase)) {
                    return db2;
                }
                FrameworkSQLiteDatabase frameworkSQLiteDatabase = new FrameworkSQLiteDatabase(sQLiteDatabase);
                dBRefHolder.setDb(frameworkSQLiteDatabase);
                return frameworkSQLiteDatabase;
            }

            private Companion() {
            }
        }

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[] r0 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_CONFIGURE     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_CREATE     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_UPGRADE     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_DOWNGRADE     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_OPEN     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.WhenMappings.<clinit>():void");
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public OpenHelper(android.content.Context r8, java.lang.String r9, androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.DBRefHolder r10, androidx.sqlite.db.SupportSQLiteOpenHelper.Callback r11, boolean r12) {
            /*
                r7 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.j.e(r8, r0)
                java.lang.String r0 = "dbRef"
                kotlin.jvm.internal.j.e(r10, r0)
                java.lang.String r0 = "callback"
                kotlin.jvm.internal.j.e(r11, r0)
                int r5 = r11.version
                androidx.sqlite.db.framework.a r6 = new androidx.sqlite.db.framework.a
                r6.<init>(r11, r10)
                r4 = 0
                r1 = r7
                r2 = r8
                r3 = r9
                r1.<init>(r2, r3, r4, r5, r6)
                r1.context = r2
                r1.dbRef = r10
                r1.callback = r11
                r1.allowDataLossOnRecovery = r12
                androidx.sqlite.util.ProcessLock r7 = new androidx.sqlite.util.ProcessLock
                if (r3 != 0) goto L_0x0037
                java.util.UUID r8 = java.util.UUID.randomUUID()
                java.lang.String r9 = r8.toString()
                java.lang.String r8 = "randomUUID().toString()"
                kotlin.jvm.internal.j.d(r9, r8)
                goto L_0x0038
            L_0x0037:
                r9 = r3
            L_0x0038:
                java.io.File r8 = r2.getCacheDir()
                r10 = 0
                r7.<init>(r9, r8, r10)
                r1.lock = r7
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.<init>(android.content.Context, java.lang.String, androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$DBRefHolder, androidx.sqlite.db.SupportSQLiteOpenHelper$Callback, boolean):void");
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(SupportSQLiteOpenHelper.Callback callback2, DBRefHolder dBRefHolder, SQLiteDatabase sQLiteDatabase) {
            j.e(callback2, "$callback");
            j.e(dBRefHolder, "$dbRef");
            Companion companion = Companion;
            j.d(sQLiteDatabase, "dbObj");
            callback2.onCorruption(companion.getWrappedDb(dBRefHolder, sQLiteDatabase));
        }

        private final SQLiteDatabase getWritableOrReadableDatabase(boolean z) {
            if (z) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                j.d(writableDatabase, "{\n                super.…eDatabase()\n            }");
                return writableDatabase;
            }
            SQLiteDatabase readableDatabase = getReadableDatabase();
            j.d(readableDatabase, "{\n                super.…eDatabase()\n            }");
            return readableDatabase;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:12|11|13|14|15|16|17) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003f */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final android.database.sqlite.SQLiteDatabase innerGetDatabase(boolean r5) {
            /*
                r4 = this;
                java.lang.String r0 = r4.getDatabaseName()
                boolean r1 = r4.opened
                if (r0 == 0) goto L_0x0032
                if (r1 != 0) goto L_0x0032
                android.content.Context r1 = r4.context
                java.io.File r1 = r1.getDatabasePath(r0)
                java.io.File r1 = r1.getParentFile()
                if (r1 == 0) goto L_0x0032
                r1.mkdirs()
                boolean r2 = r1.isDirectory()
                if (r2 != 0) goto L_0x0032
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "Invalid database parent file, not a directory: "
                r2.<init>(r3)
                r2.append(r1)
                java.lang.String r1 = r2.toString()
                java.lang.String r2 = "SupportSQLite"
                android.util.Log.w(r2, r1)
            L_0x0032:
                android.database.sqlite.SQLiteDatabase r4 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ all -> 0x0037 }
                return r4
            L_0x0037:
                super.close()
                r1 = 500(0x1f4, double:2.47E-321)
                java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x003f }
            L_0x003f:
                android.database.sqlite.SQLiteDatabase r4 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ all -> 0x0044 }
                return r4
            L_0x0044:
                r1 = move-exception
                super.close()
                boolean r2 = r1 instanceof androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackException
                if (r2 == 0) goto L_0x0071
                androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackException r1 = (androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackException) r1
                java.lang.Throwable r2 = r1.getCause()
                androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = r1.getCallbackName()
                int[] r3 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r3[r1]
                r3 = 1
                if (r1 == r3) goto L_0x0070
                r3 = 2
                if (r1 == r3) goto L_0x0070
                r3 = 3
                if (r1 == r3) goto L_0x0070
                r3 = 4
                if (r1 == r3) goto L_0x0070
                boolean r1 = r2 instanceof android.database.sqlite.SQLiteException
                if (r1 == 0) goto L_0x006f
                goto L_0x007b
            L_0x006f:
                throw r2
            L_0x0070:
                throw r2
            L_0x0071:
                boolean r2 = r1 instanceof android.database.sqlite.SQLiteException
                if (r2 == 0) goto L_0x008c
                if (r0 == 0) goto L_0x008b
                boolean r2 = r4.allowDataLossOnRecovery
                if (r2 == 0) goto L_0x008b
            L_0x007b:
                android.content.Context r1 = r4.context
                r1.deleteDatabase(r0)
                android.database.sqlite.SQLiteDatabase r4 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ CallbackException -> 0x0085 }
                return r4
            L_0x0085:
                r4 = move-exception
                java.lang.Throwable r4 = r4.getCause()
                throw r4
            L_0x008b:
                throw r1
            L_0x008c:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.innerGetDatabase(boolean):android.database.sqlite.SQLiteDatabase");
        }

        public void close() {
            try {
                ProcessLock.lock$default(this.lock, false, 1, (Object) null);
                super.close();
                this.dbRef.setDb((FrameworkSQLiteDatabase) null);
                this.opened = false;
            } finally {
                this.lock.unlock();
            }
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean z) {
            boolean z3;
            SupportSQLiteDatabase wrappedDb;
            try {
                ProcessLock processLock = this.lock;
                if (this.opened || getDatabaseName() == null) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                processLock.lock(z3);
                this.migrated = false;
                SQLiteDatabase innerGetDatabase = innerGetDatabase(z);
                if (this.migrated) {
                    close();
                    wrappedDb = getSupportDatabase(z);
                } else {
                    wrappedDb = getWrappedDb(innerGetDatabase);
                }
                this.lock.unlock();
                return wrappedDb;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            j.e(sQLiteDatabase, "sqLiteDatabase");
            return Companion.getWrappedDb(this.dbRef, sQLiteDatabase);
        }

        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            j.e(sQLiteDatabase, "db");
            if (!this.migrated && this.callback.version != sQLiteDatabase.getVersion()) {
                sQLiteDatabase.setMaxSqlCacheSize(1);
            }
            try {
                this.callback.onConfigure(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CONFIGURE, th);
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            j.e(sQLiteDatabase, "sqLiteDatabase");
            try {
                this.callback.onCreate(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CREATE, th);
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
            j.e(sQLiteDatabase, "db");
            this.migrated = true;
            try {
                this.callback.onDowngrade(getWrappedDb(sQLiteDatabase), i2, i7);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_DOWNGRADE, th);
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            j.e(sQLiteDatabase, "db");
            if (!this.migrated) {
                try {
                    this.callback.onOpen(getWrappedDb(sQLiteDatabase));
                } catch (Throwable th) {
                    throw new CallbackException(CallbackName.ON_OPEN, th);
                }
            }
            this.opened = true;
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
            j.e(sQLiteDatabase, "sqLiteDatabase");
            this.migrated = true;
            try {
                this.callback.onUpgrade(getWrappedDb(sQLiteDatabase), i2, i7);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_UPGRADE, th);
            }
        }
    }

    public FrameworkSQLiteOpenHelper(Context context2, String str, SupportSQLiteOpenHelper.Callback callback2, boolean z, boolean z3) {
        j.e(context2, "context");
        j.e(callback2, Profile.PhoneNumberData.TYPE_CALLBACK);
        this.context = context2;
        this.name = str;
        this.callback = callback2;
        this.useNoBackupDirectory = z;
        this.allowDataLossOnRecovery = z3;
    }

    private final OpenHelper getDelegate() {
        return (OpenHelper) this.lazyDelegate.getValue();
    }

    public void close() {
        if (this.lazyDelegate.isInitialized()) {
            getDelegate().close();
        }
    }

    public String getDatabaseName() {
        return this.name;
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getSupportDatabase(true);
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        if (this.lazyDelegate.isInitialized()) {
            SupportSQLiteCompat$Api16Impl.setWriteAheadLoggingEnabled(getDelegate(), z);
        }
        this.writeAheadLoggingEnabled = z;
    }
}
