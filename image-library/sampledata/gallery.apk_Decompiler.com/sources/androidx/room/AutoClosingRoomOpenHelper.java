package androidx.room;

import Ae.b;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.sqlite.db.SupportSQLiteCompat$Api19Impl;
import androidx.sqlite.db.SupportSQLiteCompat$Api23Impl;
import androidx.sqlite.db.SupportSQLiteCompat$Api29Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1195m;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0003\u001e\u001f B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u00018\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00168\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u001a8WX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006!"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "delegate", "Landroidx/room/AutoCloser;", "autoCloser", "<init>", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;Landroidx/room/AutoCloser;)V", "", "enabled", "Lme/x;", "setWriteAheadLoggingEnabled", "(Z)V", "close", "()V", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/AutoCloser;", "Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "autoClosingDb", "Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "", "getDatabaseName", "()Ljava/lang/String;", "databaseName", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getWritableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "writableDatabase", "AutoClosingSupportSQLiteDatabase", "AutoClosingSupportSqliteStatement", "KeepAliveCursor", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    public final AutoCloser autoCloser;
    private final AutoClosingSupportSQLiteDatabase autoClosingDb;
    private final SupportSQLiteOpenHelper delegate;

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\bJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\bJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\bJ\u000f\u0010\u0011\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\bJ\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0015\u0010\u0017J\u0017\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0015\u0010\u0019J!\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0017¢\u0006\u0004\b\u0015\u0010\u001cJE\u0010&\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\t2\u0012\u0010%\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010$\u0018\u00010#H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b(\u0010)J)\u0010(\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0010\u0010*\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010$0#H\u0016¢\u0006\u0004\b(\u0010+J\u000f\u0010,\u001a\u00020\u0006H\u0016¢\u0006\u0004\b,\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010-R\u0014\u0010.\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0014R\u0016\u00101\u001a\u0004\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00102\u001a\u00020\u00128WX\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0014R(\u00107\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t04\u0018\u0001038VX\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u00068"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "Landroidx/room/AutoCloser;", "autoCloser", "<init>", "(Landroidx/room/AutoCloser;)V", "Lme/x;", "pokeOpen", "()V", "", "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "beginTransaction", "beginTransactionNonExclusive", "endTransaction", "setTransactionSuccessful", "", "inTransaction", "()Z", "query", "Landroid/database/Cursor;", "(Ljava/lang/String;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "(Landroidx/sqlite/db/SupportSQLiteQuery;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "table", "", "conflictAlgorithm", "Landroid/content/ContentValues;", "values", "whereClause", "", "", "whereArgs", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "execSQL", "(Ljava/lang/String;)V", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "close", "Landroidx/room/AutoCloser;", "isOpen", "getPath", "()Ljava/lang/String;", "path", "isWriteAheadLoggingEnabled", "", "Landroid/util/Pair;", "getAttachedDbs", "()Ljava/util/List;", "attachedDbs", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        private final AutoCloser autoCloser;

        public AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser2) {
            j.e(autoCloser2, "autoCloser");
            this.autoCloser = autoCloser2;
        }

        public void beginTransaction() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionNonExclusive() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void close() {
            this.autoCloser.closeDatabaseIfOpen();
        }

        public SupportSQLiteStatement compileStatement(String str) {
            j.e(str, "sql");
            return new AutoClosingSupportSqliteStatement(str, this.autoCloser);
        }

        public void endTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() != null) {
                try {
                    SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
                    j.b(delegateDatabase$room_runtime_release);
                    delegateDatabase$room_runtime_release.endTransaction();
                } finally {
                    this.autoCloser.decrementCountAndScheduleClose();
                }
            } else {
                throw new IllegalStateException("End transaction called but delegateDb is null");
            }
        }

        public void execSQL(String str) {
            j.e(str, "sql");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$1(str));
        }

        public List<Pair<String, String>> getAttachedDbs() {
            return (List) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1.INSTANCE);
        }

        public String getPath() {
            return (String) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1.INSTANCE);
        }

        public boolean inTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                return false;
            }
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$inTransaction$1.INSTANCE)).booleanValue();
        }

        public boolean isOpen() {
            SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release == null) {
                return false;
            }
            return delegateDatabase$room_runtime_release.isOpen();
        }

        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isWriteAheadLoggingEnabled$1.INSTANCE)).booleanValue();
        }

        public final void pokeOpen() {
            this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pokeOpen$1.INSTANCE);
        }

        public Cursor query(String str) {
            j.e(str, Contract.QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(str), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void setTransactionSuccessful() {
            x xVar;
            SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release != null) {
                delegateDatabase$room_runtime_release.setTransactionSuccessful();
                xVar = x.f4917a;
            } else {
                xVar = null;
            }
            if (xVar == null) {
                throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null");
            }
        }

        public int update(String str, int i2, ContentValues contentValues, String str2, Object[] objArr) {
            j.e(str, "table");
            j.e(contentValues, StateHandler.VALUES);
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(str, i2, contentValues, str2, objArr))).intValue();
        }

        public void execSQL(String str, Object[] objArr) {
            j.e(str, "sql");
            j.e(objArr, "bindArgs");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$2(str, objArr));
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
            j.e(supportSQLiteQuery, Contract.QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
            j.e(supportSQLiteQuery, Contract.QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery, cancellationSignal), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u000e\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\t0\tH\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\fH\u0001¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0012\u001a\n \n*\u0004\u0018\u00010\u00110\u00112\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0014\u0010\u0015J \u0010\u0017\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00160\u0016H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J \u0010\u0019\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00160\u0016H\u0001¢\u0006\u0004\b\u0019\u0010\u0018J \u0010\u001a\u001a\n \n*\u0004\u0018\u00010\u00160\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ6\u0010\u001d\u001a(\u0012\f\u0012\n \n*\u0004\u0018\u00010\u00160\u0016 \n*\u0014\u0012\u000e\b\u0001\u0012\n \n*\u0004\u0018\u00010\u00160\u0016\u0018\u00010\u001c0\u001cH\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u001f\u0010\u0015J\u0018\u0010!\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b!\u0010\"J\u0018\u0010$\u001a\n \n*\u0004\u0018\u00010#0#H\u0001¢\u0006\u0004\b$\u0010%J\u0018\u0010'\u001a\u00020&2\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b'\u0010(J\u0018\u0010)\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b)\u0010*J\u0018\u0010,\u001a\u00020+2\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0007H\u0001¢\u0006\u0004\b.\u0010\u0015J\u0018\u00100\u001a\u00020/2\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b0\u00101J \u00102\u001a\n \n*\u0004\u0018\u00010\u00160\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b2\u0010\u001bJ\u0018\u00103\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b3\u0010*J\u0010\u00105\u001a\u000204H\u0001¢\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u000204H\u0001¢\u0006\u0004\b7\u00106J\u0010\u00108\u001a\u000204H\u0001¢\u0006\u0004\b8\u00106J\u0010\u00109\u001a\u000204H\u0001¢\u0006\u0004\b9\u00106J\u0010\u0010:\u001a\u000204H\u0001¢\u0006\u0004\b:\u00106J\u0010\u0010;\u001a\u000204H\u0001¢\u0006\u0004\b;\u00106J\u0018\u0010<\u001a\u0002042\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b<\u0010=J\u0018\u0010>\u001a\u0002042\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b>\u0010=J\u0010\u0010?\u001a\u000204H\u0001¢\u0006\u0004\b?\u00106J\u0010\u0010@\u001a\u000204H\u0001¢\u0006\u0004\b@\u00106J\u0010\u0010A\u001a\u000204H\u0001¢\u0006\u0004\bA\u00106J\u0018\u0010B\u001a\u0002042\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\bB\u0010=J\u0010\u0010C\u001a\u000204H\u0001¢\u0006\u0004\bC\u00106J \u0010E\u001a\u00020\f2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010D0DH\u0001¢\u0006\u0004\bE\u0010FJ \u0010H\u001a\u00020\f2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010G0GH\u0001¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u000204H\u0001¢\u0006\u0004\bJ\u00106J(\u0010K\u001a\n \n*\u0004\u0018\u00010#0#2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010#0#H\u0001¢\u0006\u0004\bK\u0010LJ0\u0010O\u001a\u00020\f2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010M0M2\u000e\u0010\u000b\u001a\n \n*\u0004\u0018\u00010N0NH\u0001¢\u0006\u0004\bO\u0010PJ \u0010Q\u001a\u00020\f2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010D0DH\u0001¢\u0006\u0004\bQ\u0010FJ \u0010R\u001a\u00020\f2\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010G0GH\u0001¢\u0006\u0004\bR\u0010IJ\u000f\u0010S\u001a\u00020\fH\u0016¢\u0006\u0004\bS\u0010\u0010J%\u0010W\u001a\u00020\f2\u0006\u0010T\u001a\u00020M2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020N0UH\u0017¢\u0006\u0004\bW\u0010XJ\u000f\u0010Y\u001a\u00020NH\u0017¢\u0006\u0004\bY\u0010ZJ\u0015\u0010[\u001a\b\u0012\u0004\u0012\u00020N0UH\u0017¢\u0006\u0004\b[\u0010\\J\u0017\u0010^\u001a\u00020\f2\u0006\u0010]\u001a\u00020#H\u0017¢\u0006\u0004\b^\u0010_R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010`R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010a¨\u0006b"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$KeepAliveCursor;", "Landroid/database/Cursor;", "delegate", "Landroidx/room/AutoCloser;", "autoCloser", "<init>", "(Landroid/database/Cursor;Landroidx/room/AutoCloser;)V", "", "p0", "Landroid/database/CharArrayBuffer;", "kotlin.jvm.PlatformType", "p1", "Lme/x;", "copyStringToBuffer", "(ILandroid/database/CharArrayBuffer;)V", "deactivate", "()V", "", "getBlob", "(I)[B", "getColumnCount", "()I", "", "getColumnIndex", "(Ljava/lang/String;)I", "getColumnIndexOrThrow", "getColumnName", "(I)Ljava/lang/String;", "", "getColumnNames", "()[Ljava/lang/String;", "getCount", "", "getDouble", "(I)D", "Landroid/os/Bundle;", "getExtras", "()Landroid/os/Bundle;", "", "getFloat", "(I)F", "getInt", "(I)I", "", "getLong", "(I)J", "getPosition", "", "getShort", "(I)S", "getString", "getType", "", "getWantsAllOnMoveCalls", "()Z", "isAfterLast", "isBeforeFirst", "isClosed", "isFirst", "isLast", "isNull", "(I)Z", "move", "moveToFirst", "moveToLast", "moveToNext", "moveToPosition", "moveToPrevious", "Landroid/database/ContentObserver;", "registerContentObserver", "(Landroid/database/ContentObserver;)V", "Landroid/database/DataSetObserver;", "registerDataSetObserver", "(Landroid/database/DataSetObserver;)V", "requery", "respond", "(Landroid/os/Bundle;)Landroid/os/Bundle;", "Landroid/content/ContentResolver;", "Landroid/net/Uri;", "setNotificationUri", "(Landroid/content/ContentResolver;Landroid/net/Uri;)V", "unregisterContentObserver", "unregisterDataSetObserver", "close", "cr", "", "uris", "setNotificationUris", "(Landroid/content/ContentResolver;Ljava/util/List;)V", "getNotificationUri", "()Landroid/net/Uri;", "getNotificationUris", "()Ljava/util/List;", "extras", "setExtras", "(Landroid/os/Bundle;)V", "Landroid/database/Cursor;", "Landroidx/room/AutoCloser;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class KeepAliveCursor implements Cursor {
        private final AutoCloser autoCloser;
        private final Cursor delegate;

        public KeepAliveCursor(Cursor cursor, AutoCloser autoCloser2) {
            j.e(cursor, "delegate");
            j.e(autoCloser2, "autoCloser");
            this.delegate = cursor;
            this.autoCloser = autoCloser2;
        }

        public void close() {
            this.delegate.close();
            this.autoCloser.decrementCountAndScheduleClose();
        }

        public void copyStringToBuffer(int i2, CharArrayBuffer charArrayBuffer) {
            this.delegate.copyStringToBuffer(i2, charArrayBuffer);
        }

        public void deactivate() {
            this.delegate.deactivate();
        }

        public byte[] getBlob(int i2) {
            return this.delegate.getBlob(i2);
        }

        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        public int getColumnIndex(String str) {
            return this.delegate.getColumnIndex(str);
        }

        public int getColumnIndexOrThrow(String str) {
            return this.delegate.getColumnIndexOrThrow(str);
        }

        public String getColumnName(int i2) {
            return this.delegate.getColumnName(i2);
        }

        public String[] getColumnNames() {
            return this.delegate.getColumnNames();
        }

        public int getCount() {
            return this.delegate.getCount();
        }

        public double getDouble(int i2) {
            return this.delegate.getDouble(i2);
        }

        public Bundle getExtras() {
            return this.delegate.getExtras();
        }

        public float getFloat(int i2) {
            return this.delegate.getFloat(i2);
        }

        public int getInt(int i2) {
            return this.delegate.getInt(i2);
        }

        public long getLong(int i2) {
            return this.delegate.getLong(i2);
        }

        public Uri getNotificationUri() {
            return SupportSQLiteCompat$Api19Impl.getNotificationUri(this.delegate);
        }

        public List<Uri> getNotificationUris() {
            return SupportSQLiteCompat$Api29Impl.getNotificationUris(this.delegate);
        }

        public int getPosition() {
            return this.delegate.getPosition();
        }

        public short getShort(int i2) {
            return this.delegate.getShort(i2);
        }

        public String getString(int i2) {
            return this.delegate.getString(i2);
        }

        public int getType(int i2) {
            return this.delegate.getType(i2);
        }

        public boolean getWantsAllOnMoveCalls() {
            return this.delegate.getWantsAllOnMoveCalls();
        }

        public boolean isAfterLast() {
            return this.delegate.isAfterLast();
        }

        public boolean isBeforeFirst() {
            return this.delegate.isBeforeFirst();
        }

        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        public boolean isFirst() {
            return this.delegate.isFirst();
        }

        public boolean isLast() {
            return this.delegate.isLast();
        }

        public boolean isNull(int i2) {
            return this.delegate.isNull(i2);
        }

        public boolean move(int i2) {
            return this.delegate.move(i2);
        }

        public boolean moveToFirst() {
            return this.delegate.moveToFirst();
        }

        public boolean moveToLast() {
            return this.delegate.moveToLast();
        }

        public boolean moveToNext() {
            return this.delegate.moveToNext();
        }

        public boolean moveToPosition(int i2) {
            return this.delegate.moveToPosition(i2);
        }

        public boolean moveToPrevious() {
            return this.delegate.moveToPrevious();
        }

        public void registerContentObserver(ContentObserver contentObserver) {
            this.delegate.registerContentObserver(contentObserver);
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.registerDataSetObserver(dataSetObserver);
        }

        public boolean requery() {
            return this.delegate.requery();
        }

        public Bundle respond(Bundle bundle) {
            return this.delegate.respond(bundle);
        }

        public void setExtras(Bundle bundle) {
            j.e(bundle, "extras");
            SupportSQLiteCompat$Api23Impl.setExtras(this.delegate, bundle);
        }

        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
            this.delegate.setNotificationUri(contentResolver, uri);
        }

        public void setNotificationUris(ContentResolver contentResolver, List<? extends Uri> list) {
            j.e(contentResolver, "cr");
            j.e(list, "uris");
            SupportSQLiteCompat$Api29Impl.setNotificationUris(this.delegate, contentResolver, list);
        }

        public void unregisterContentObserver(ContentObserver contentObserver) {
            this.delegate.unregisterContentObserver(contentObserver);
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper, AutoCloser autoCloser2) {
        j.e(supportSQLiteOpenHelper, "delegate");
        j.e(autoCloser2, "autoCloser");
        this.delegate = supportSQLiteOpenHelper;
        this.autoCloser = autoCloser2;
        autoCloser2.init(getDelegate());
        this.autoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser2);
    }

    public void close() {
        this.autoClosingDb.close();
    }

    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010!\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u001bH\u0016¢\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0002H\u0016¢\u0006\u0004\b&\u0010'J\u001f\u0010)\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010+R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010,R(\u0010/\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130-j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`.8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00061"}, d2 = {"Landroidx/room/AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement;", "Landroidx/sqlite/db/SupportSQLiteStatement;", "", "sql", "Landroidx/room/AutoCloser;", "autoCloser", "<init>", "(Ljava/lang/String;Landroidx/room/AutoCloser;)V", "T", "Lkotlin/Function1;", "block", "executeSqliteStatementWithRefCount", "(LAe/b;)Ljava/lang/Object;", "supportSQLiteStatement", "Lme/x;", "doBinds", "(Landroidx/sqlite/db/SupportSQLiteStatement;)V", "", "bindIndex", "", "value", "saveBinds", "(ILjava/lang/Object;)V", "close", "()V", "executeUpdateDelete", "()I", "", "executeInsert", "()J", "index", "bindNull", "(I)V", "bindLong", "(IJ)V", "", "bindDouble", "(ID)V", "bindString", "(ILjava/lang/String;)V", "", "bindBlob", "(I[B)V", "Ljava/lang/String;", "Landroidx/room/AutoCloser;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "binds", "Ljava/util/ArrayList;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AutoClosingSupportSqliteStatement implements SupportSQLiteStatement {
        private final AutoCloser autoCloser;
        private final ArrayList<Object> binds = new ArrayList<>();
        /* access modifiers changed from: private */
        public final String sql;

        public AutoClosingSupportSqliteStatement(String str, AutoCloser autoCloser2) {
            j.e(str, "sql");
            j.e(autoCloser2, "autoCloser");
            this.sql = str;
            this.autoCloser = autoCloser2;
        }

        /* access modifiers changed from: private */
        public final void doBinds(SupportSQLiteStatement supportSQLiteStatement) {
            Iterator<T> it = this.binds.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                it.next();
                int i7 = i2 + 1;
                if (i2 >= 0) {
                    Object obj = this.binds.get(i2);
                    if (obj == null) {
                        supportSQLiteStatement.bindNull(i7);
                    } else if (obj instanceof Long) {
                        supportSQLiteStatement.bindLong(i7, ((Number) obj).longValue());
                    } else if (obj instanceof Double) {
                        supportSQLiteStatement.bindDouble(i7, ((Number) obj).doubleValue());
                    } else if (obj instanceof String) {
                        supportSQLiteStatement.bindString(i7, (String) obj);
                    } else if (obj instanceof byte[]) {
                        supportSQLiteStatement.bindBlob(i7, (byte[]) obj);
                    }
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
        }

        private final <T> T executeSqliteStatementWithRefCount(b bVar) {
            return this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(this, bVar));
        }

        private final void saveBinds(int i2, Object obj) {
            int size;
            int i7 = i2 - 1;
            if (i7 >= this.binds.size() && (size = this.binds.size()) <= i7) {
                while (true) {
                    this.binds.add((Object) null);
                    if (size == i7) {
                        break;
                    }
                    size++;
                }
            }
            this.binds.set(i7, obj);
        }

        public void bindBlob(int i2, byte[] bArr) {
            j.e(bArr, "value");
            saveBinds(i2, bArr);
        }

        public void bindDouble(int i2, double d) {
            saveBinds(i2, Double.valueOf(d));
        }

        public void bindLong(int i2, long j2) {
            saveBinds(i2, Long.valueOf(j2));
        }

        public void bindNull(int i2) {
            saveBinds(i2, (Object) null);
        }

        public void bindString(int i2, String str) {
            j.e(str, "value");
            saveBinds(i2, str);
        }

        public long executeInsert() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1.INSTANCE)).longValue();
        }

        public int executeUpdateDelete() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1.INSTANCE)).intValue();
        }

        public void close() {
        }
    }
}
