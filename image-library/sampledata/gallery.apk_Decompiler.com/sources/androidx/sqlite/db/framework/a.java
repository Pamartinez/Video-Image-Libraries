package androidx.sqlite.db.framework;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DatabaseErrorHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SupportSQLiteOpenHelper.Callback f1033a;
    public final /* synthetic */ FrameworkSQLiteOpenHelper.DBRefHolder b;

    public /* synthetic */ a(SupportSQLiteOpenHelper.Callback callback, FrameworkSQLiteOpenHelper.DBRefHolder dBRefHolder) {
        this.f1033a = callback;
        this.b = dBRefHolder;
    }

    public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
        FrameworkSQLiteOpenHelper.OpenHelper._init_$lambda$0(this.f1033a, this.b, sQLiteDatabase);
    }
}
