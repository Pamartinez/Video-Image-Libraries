package androidx.sqlite.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJK\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0010\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"androidx/sqlite/db/SupportSQLiteCompat$Api16Impl", "", "<init>", "()V", "Ljava/io/File;", "file", "", "deleteDatabase", "(Ljava/io/File;)Z", "Landroid/database/sqlite/SQLiteDatabase;", "sQLiteDatabase", "", "sql", "", "selectionArgs", "editTable", "Landroid/os/CancellationSignal;", "cancellationSignal", "Landroid/database/sqlite/SQLiteDatabase$CursorFactory;", "cursorFactory", "Landroid/database/Cursor;", "rawQueryWithFactory", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/Cursor;", "isWriteAheadLoggingEnabled", "(Landroid/database/sqlite/SQLiteDatabase;)Z", "Landroid/database/sqlite/SQLiteOpenHelper;", "sQLiteOpenHelper", "enabled", "Lme/x;", "setWriteAheadLoggingEnabled", "(Landroid/database/sqlite/SQLiteOpenHelper;Z)V", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SupportSQLiteCompat$Api16Impl {
    public static final SupportSQLiteCompat$Api16Impl INSTANCE = new SupportSQLiteCompat$Api16Impl();

    private SupportSQLiteCompat$Api16Impl() {
    }

    public static final boolean deleteDatabase(File file) {
        j.e(file, "file");
        return SQLiteDatabase.deleteDatabase(file);
    }

    public static final boolean isWriteAheadLoggingEnabled(SQLiteDatabase sQLiteDatabase) {
        j.e(sQLiteDatabase, "sQLiteDatabase");
        return sQLiteDatabase.isWriteAheadLoggingEnabled();
    }

    public static final Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, CancellationSignal cancellationSignal, SQLiteDatabase.CursorFactory cursorFactory) {
        j.e(sQLiteDatabase, "sQLiteDatabase");
        j.e(str, "sql");
        j.e(strArr, "selectionArgs");
        j.e(cancellationSignal, "cancellationSignal");
        j.e(cursorFactory, "cursorFactory");
        String[] strArr2 = strArr;
        String str3 = str;
        SQLiteDatabase.CursorFactory cursorFactory2 = cursorFactory;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory2, str3, strArr2, str2, cancellationSignal2);
        j.d(rawQueryWithFactory, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
        return rawQueryWithFactory;
    }

    public static final void setWriteAheadLoggingEnabled(SQLiteOpenHelper sQLiteOpenHelper, boolean z) {
        j.e(sQLiteOpenHelper, "sQLiteOpenHelper");
        sQLiteOpenHelper.setWriteAheadLoggingEnabled(z);
    }
}
