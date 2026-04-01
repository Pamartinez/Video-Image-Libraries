package o8;

import android.database.sqlite.SQLiteDatabase;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SQLiteDatabase e;

    public /* synthetic */ a(SQLiteDatabase sQLiteDatabase, int i2) {
        this.d = i2;
        this.e = sQLiteDatabase;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SQLiteDatabase sQLiteDatabase = this.e;
        switch (i2) {
            case 0:
                ((LocalTable) obj).createTablesOnTransaction(sQLiteDatabase);
                return;
            default:
                sQLiteDatabase.execSQL("DROP INDEX IF EXISTS " + ((String) obj));
                return;
        }
    }
}
