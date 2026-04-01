package androidx.room;

import A.a;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\r\b\u0017\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001e\u001f B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0012\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\u000fJ'\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0019\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001a\u0010\u000fR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001cR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001dR\u0014\u0010\b\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001d¨\u0006!"}, d2 = {"Landroidx/room/RoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "Landroidx/room/DatabaseConfiguration;", "configuration", "Landroidx/room/RoomOpenHelper$Delegate;", "delegate", "", "identityHash", "legacyHash", "<init>", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;Ljava/lang/String;)V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "Lme/x;", "checkIdentity", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "updateIdentity", "createMasterTableIfNotExists", "onConfigure", "onCreate", "", "oldVersion", "newVersion", "onUpgrade", "(Landroidx/sqlite/db/SupportSQLiteDatabase;II)V", "onDowngrade", "onOpen", "Landroidx/room/DatabaseConfiguration;", "Landroidx/room/RoomOpenHelper$Delegate;", "Ljava/lang/String;", "Companion", "Delegate", "ValidationResult", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    public static final Companion Companion = new Companion((e) null);
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/room/RoomOpenHelper$Companion;", "", "()V", "hasEmptySchema", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "hasEmptySchema$room_runtime_release", "hasRoomMasterTable", "hasRoomMasterTable$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
            B1.a.g(r2, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r3) {
            /*
                r2 = this;
                java.lang.String r2 = "db"
                kotlin.jvm.internal.j.e(r3, r2)
                java.lang.String r2 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
                android.database.Cursor r2 = r3.query((java.lang.String) r2)
                java.io.Closeable r2 = (java.io.Closeable) r2
                r3 = r2
                android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x001f }
                boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x001f }
                r1 = 0
                if (r0 == 0) goto L_0x0021
                int r3 = r3.getInt(r1)     // Catch:{ all -> 0x001f }
                if (r3 != 0) goto L_0x0021
                r1 = 1
                goto L_0x0021
            L_0x001f:
                r3 = move-exception
                goto L_0x0026
            L_0x0021:
                r3 = 0
                B1.a.g(r2, r3)
                return r1
            L_0x0026:
                throw r3     // Catch:{ all -> 0x0027 }
            L_0x0027:
                r0 = move-exception
                B1.a.g(r2, r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
            B1.a.g(r2, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r3) {
            /*
                r2 = this;
                java.lang.String r2 = "db"
                kotlin.jvm.internal.j.e(r3, r2)
                java.lang.String r2 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
                android.database.Cursor r2 = r3.query((java.lang.String) r2)
                java.io.Closeable r2 = (java.io.Closeable) r2
                r3 = r2
                android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x001f }
                boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x001f }
                r1 = 0
                if (r0 == 0) goto L_0x0021
                int r3 = r3.getInt(r1)     // Catch:{ all -> 0x001f }
                if (r3 == 0) goto L_0x0021
                r1 = 1
                goto L_0x0021
            L_0x001f:
                r3 = move-exception
                goto L_0x0026
            L_0x0021:
                r3 = 0
                B1.a.g(r2, r3)
                return r1
            L_0x0026:
                throw r3     // Catch:{ all -> 0x0027 }
            L_0x0027:
                r0 = move-exception
                B1.a.g(r2, r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\f\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\r\u0010\nJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\nJ\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/room/RoomOpenHelper$Delegate;", "", "", "version", "<init>", "(I)V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "Lme/x;", "dropAllTables", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "createAllTables", "onOpen", "onCreate", "Landroidx/room/RoomOpenHelper$ValidationResult;", "onValidateSchema", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Landroidx/room/RoomOpenHelper$ValidationResult;", "onPreMigrate", "onPostMigrate", "I", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Delegate {
        public final int version;

        public Delegate(int i2) {
            this.version = i2;
        }

        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomOpenHelper$ValidationResult;", "", "isValid", "", "expectedFoundMsg", "", "(ZLjava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate2, String str, String str2) {
        super(delegate2.version);
        j.e(databaseConfiguration, "configuration");
        j.e(delegate2, "delegate");
        j.e(str, "identityHash");
        j.e(str2, "legacyHash");
        this.configuration = databaseConfiguration;
        this.delegate = delegate2;
        this.identityHash = str;
        this.legacyHash = str2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        B1.a.g(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase r4) {
        /*
            r3 = this;
            androidx.room.RoomOpenHelper$Companion r0 = Companion
            boolean r0 = r0.hasRoomMasterTable$room_runtime_release(r4)
            if (r0 == 0) goto L_0x0058
            androidx.sqlite.db.SimpleSQLiteQuery r0 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r1 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r0.<init>(r1)
            android.database.Cursor r4 = r4.query((androidx.sqlite.db.SupportSQLiteQuery) r0)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r0 = r4
            android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ all -> 0x0025 }
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0025 }
            r2 = 0
            if (r1 == 0) goto L_0x0027
            r1 = 0
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x0025 }
            goto L_0x0028
        L_0x0025:
            r3 = move-exception
            goto L_0x0052
        L_0x0027:
            r0 = r2
        L_0x0028:
            B1.a.g(r4, r2)
            java.lang.String r4 = r3.identityHash
            boolean r4 = kotlin.jvm.internal.j.a(r4, r0)
            if (r4 != 0) goto L_0x0051
            java.lang.String r4 = r3.legacyHash
            boolean r4 = kotlin.jvm.internal.j.a(r4, r0)
            if (r4 == 0) goto L_0x003c
            goto L_0x0051
        L_0x003c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: "
            r1.<init>(r2)
            java.lang.String r3 = r3.identityHash
            java.lang.String r2 = ", found: "
            java.lang.String r3 = N2.j.f(r1, r3, r2, r0)
            r4.<init>(r3)
            throw r4
        L_0x0051:
            return
        L_0x0052:
            throw r3     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r0 = move-exception
            B1.a.g(r4, r3)
            throw r0
        L_0x0058:
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            androidx.room.RoomOpenHelper$ValidationResult r0 = r0.onValidateSchema(r4)
            boolean r1 = r0.isValid
            if (r1 == 0) goto L_0x006b
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            r0.onPostMigrate(r4)
            r3.updateIdentity(r4)
            return
        L_0x006b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "Pre-packaged database has an invalid schema: "
            r4.<init>(r1)
            java.lang.String r0 = r0.expectedFoundMsg
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    private final void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        createMasterTableIfNotExists(supportSQLiteDatabase);
        supportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }

    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        super.onConfigure(supportSQLiteDatabase);
    }

    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        boolean hasEmptySchema$room_runtime_release = Companion.hasEmptySchema$room_runtime_release(supportSQLiteDatabase);
        this.delegate.createAllTables(supportSQLiteDatabase);
        if (!hasEmptySchema$room_runtime_release) {
            ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
            if (!onValidateSchema.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg);
            }
        }
        updateIdentity(supportSQLiteDatabase);
        this.delegate.onCreate(supportSQLiteDatabase);
    }

    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i7) {
        j.e(supportSQLiteDatabase, "db");
        onUpgrade(supportSQLiteDatabase, i2, i7);
    }

    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        super.onOpen(supportSQLiteDatabase);
        checkIdentity(supportSQLiteDatabase);
        this.delegate.onOpen(supportSQLiteDatabase);
        this.configuration = null;
    }

    public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i7) {
        List<Migration> findMigrationPath;
        j.e(supportSQLiteDatabase, "db");
        DatabaseConfiguration databaseConfiguration = this.configuration;
        if (databaseConfiguration == null || (findMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(i2, i7)) == null) {
            DatabaseConfiguration databaseConfiguration2 = this.configuration;
            if (databaseConfiguration2 == null || databaseConfiguration2.isMigrationRequired(i2, i7)) {
                throw new IllegalStateException(a.d(i2, i7, "A migration from ", " to ", " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods."));
            }
            this.delegate.dropAllTables(supportSQLiteDatabase);
            this.delegate.createAllTables(supportSQLiteDatabase);
            return;
        }
        this.delegate.onPreMigrate(supportSQLiteDatabase);
        for (Migration migrate : findMigrationPath) {
            migrate.migrate(supportSQLiteDatabase);
        }
        ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
        if (onValidateSchema.isValid) {
            this.delegate.onPostMigrate(supportSQLiteDatabase);
            updateIdentity(supportSQLiteDatabase);
            return;
        }
        throw new IllegalStateException("Migration didn't properly handle: " + onValidateSchema.expectedFoundMsg);
    }
}
