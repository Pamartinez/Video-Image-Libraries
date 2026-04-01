package j0;

import Ae.e;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.framework.FrameworkSQLiteDatabase;

/* renamed from: j0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0218a implements SQLiteDatabase.CursorFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1788a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0218a(int i2, Object obj) {
        this.f1788a = i2;
        this.b = obj;
    }

    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        int i2 = this.f1788a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return FrameworkSQLiteDatabase.query$lambda$1((SupportSQLiteQuery) obj, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
            default:
                return FrameworkSQLiteDatabase.query$lambda$0((e) obj, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
        }
    }
}
