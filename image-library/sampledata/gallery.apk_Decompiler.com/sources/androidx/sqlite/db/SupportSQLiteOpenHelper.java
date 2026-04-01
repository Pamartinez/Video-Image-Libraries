package androidx.sqlite.db;

import Tf.v;
import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import java.io.Closeable;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0003\u0011\u0012\u0013J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\f\u001a\u0004\u0018\u00010\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Ljava/io/Closeable;", "", "enabled", "Lme/x;", "setWriteAheadLoggingEnabled", "(Z)V", "close", "()V", "", "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "Callback", "Configuration", "Factory", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SupportSQLiteOpenHelper extends Closeable {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b&\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\u000f\u0010\u000eJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H&¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0015\u0010\u000eJ\u0017\u0010\u0016\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0016\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "", "", "version", "<init>", "(I)V", "", "fileName", "Lme/x;", "deleteDatabaseFile", "(Ljava/lang/String;)V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "onConfigure", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "onCreate", "oldVersion", "newVersion", "onUpgrade", "(Landroidx/sqlite/db/SupportSQLiteDatabase;II)V", "onDowngrade", "onOpen", "onCorruption", "I", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        public static final Companion Companion = new Companion((e) null);
        public final int version;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback$Companion;", "", "()V", "TAG", "", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public Callback(int i2) {
            this.version = i2;
        }

        private final void deleteDatabaseFile(String str) {
            int i2;
            boolean z;
            if (!v.p0(str, ":memory:", true)) {
                int length = str.length() - 1;
                int i7 = 0;
                boolean z3 = false;
                while (i7 <= length) {
                    if (!z3) {
                        i2 = i7;
                    } else {
                        i2 = length;
                    }
                    if (j.f(str.charAt(i2), 32) <= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z3) {
                        if (!z) {
                            z3 = true;
                        } else {
                            i7++;
                        }
                    } else if (!z) {
                        break;
                    } else {
                        length--;
                    }
                }
                if (str.subSequence(i7, length + 1).toString().length() != 0) {
                    Log.w("SupportSQLite", "deleting the database file: " + str);
                    try {
                        SupportSQLiteCompat$Api16Impl.deleteDatabase(new File(str));
                    } catch (Exception e) {
                        Log.w("SupportSQLite", "delete failed: ", e);
                    }
                }
            }
        }

        public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "db");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
            if (r1 != null) goto L_0x003d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
            r4 = r1.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
            if (r4.hasNext() != false) goto L_0x0049;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
            r1 = ((android.util.Pair) r4.next()).second;
            kotlin.jvm.internal.j.d(r1, "p.second");
            deleteDatabaseFile((java.lang.String) r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
            r4 = r4.getPath();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
            if (r4 != null) goto L_0x0060;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
            deleteDatabaseFile(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0037 */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0083  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0035 A[ExcHandler: all (r2v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r1 
          PHI: (r1v12 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) = (r1v4 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r1v5 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r1v5 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) binds: [B:7:0x0030, B:10:0x0037, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:7:0x0030] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCorruption(androidx.sqlite.db.SupportSQLiteDatabase r4) {
            /*
                r3 = this;
                java.lang.String r0 = "p.second"
                java.lang.String r1 = "db"
                kotlin.jvm.internal.j.e(r4, r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Corruption reported by sqlite on database: "
                r1.<init>(r2)
                r1.append(r4)
                java.lang.String r2 = ".path"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "SupportSQLite"
                android.util.Log.e(r2, r1)
                boolean r1 = r4.isOpen()
                if (r1 != 0) goto L_0x002f
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x008c
                r3.deleteDatabaseFile(r4)
                return
            L_0x002f:
                r1 = 0
                java.util.List r1 = r4.getAttachedDbs()     // Catch:{ SQLiteException -> 0x0037, all -> 0x0035 }
                goto L_0x0037
            L_0x0035:
                r2 = move-exception
                goto L_0x003b
            L_0x0037:
                r4.close()     // Catch:{ IOException -> 0x0064, all -> 0x0035 }
                goto L_0x0064
            L_0x003b:
                if (r1 == 0) goto L_0x005a
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r4 = r1.iterator()
            L_0x0043:
                boolean r1 = r4.hasNext()
                if (r1 == 0) goto L_0x0063
                java.lang.Object r1 = r4.next()
                android.util.Pair r1 = (android.util.Pair) r1
                java.lang.Object r1 = r1.second
                kotlin.jvm.internal.j.d(r1, r0)
                java.lang.String r1 = (java.lang.String) r1
                r3.deleteDatabaseFile(r1)
                goto L_0x0043
            L_0x005a:
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x0063
                r3.deleteDatabaseFile(r4)
            L_0x0063:
                throw r2
            L_0x0064:
                if (r1 == 0) goto L_0x0083
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r4 = r1.iterator()
            L_0x006c:
                boolean r1 = r4.hasNext()
                if (r1 == 0) goto L_0x008c
                java.lang.Object r1 = r4.next()
                android.util.Pair r1 = (android.util.Pair) r1
                java.lang.Object r1 = r1.second
                kotlin.jvm.internal.j.d(r1, r0)
                java.lang.String r1 = (java.lang.String) r1
                r3.deleteDatabaseFile(r1)
                goto L_0x006c
            L_0x0083:
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x008c
                r3.deleteDatabaseFile(r4)
            L_0x008c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.SupportSQLiteOpenHelper.Callback.onCorruption(androidx.sqlite.db.SupportSQLiteDatabase):void");
        }

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i7);

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            j.e(supportSQLiteDatabase, "db");
        }

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i7);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bR\u0010\u0010\n\u001a\u00020\t8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "", "context", "Landroid/content/Context;", "name", "", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "useNoBackupDirectory", "", "allowDataLossOnRecovery", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;ZZ)V", "Builder", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Configuration {
        public static final Companion Companion = new Companion((e) null);
        public final boolean allowDataLossOnRecovery;
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allowDataLossOnRecovery", "", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "name", "", "useNoBackupDirectory", "build", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "noBackupDirectory", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Builder {
            private boolean allowDataLossOnRecovery;
            private Callback callback;
            private final Context context;
            private String name;
            private boolean useNoBackupDirectory;

            public Builder(Context context2) {
                j.e(context2, "context");
                this.context = context2;
            }

            public Builder allowDataLossOnRecovery(boolean z) {
                this.allowDataLossOnRecovery = z;
                return this;
            }

            public Configuration build() {
                String str;
                Callback callback2 = this.callback;
                if (callback2 == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (!this.useNoBackupDirectory || ((str = this.name) != null && str.length() != 0)) {
                    return new Configuration(this.context, this.name, callback2, this.useNoBackupDirectory, this.allowDataLossOnRecovery);
                } else {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
            }

            public Builder callback(Callback callback2) {
                j.e(callback2, Profile.PhoneNumberData.TYPE_CALLBACK);
                this.callback = callback2;
                return this;
            }

            public Builder name(String str) {
                this.name = str;
                return this;
            }

            public Builder noBackupDirectory(boolean z) {
                this.useNoBackupDirectory = z;
                return this;
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Companion;", "", "()V", "builder", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration$Builder;", "context", "Landroid/content/Context;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            public final Builder builder(Context context) {
                j.e(context, "context");
                return new Builder(context);
            }

            private Companion() {
            }
        }

        public Configuration(Context context2, String str, Callback callback2, boolean z, boolean z3) {
            j.e(context2, "context");
            j.e(callback2, Profile.PhoneNumberData.TYPE_CALLBACK);
            this.context = context2;
            this.name = str;
            this.callback = callback2;
            this.useNoBackupDirectory = z;
            this.allowDataLossOnRecovery = z3;
        }

        public static final Builder builder(Context context2) {
            return Companion.builder(context2);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "", "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);
}
