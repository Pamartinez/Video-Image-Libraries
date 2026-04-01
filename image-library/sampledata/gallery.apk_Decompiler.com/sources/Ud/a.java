package Ud;

import android.database.sqlite.SQLiteDatabase;
import com.samsung.scsp.media.api.database.url.OneDriveUrlDbHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ OneDriveUrlDbHelper e;

    public /* synthetic */ a(OneDriveUrlDbHelper oneDriveUrlDbHelper, int i2) {
        this.d = i2;
        this.e = oneDriveUrlDbHelper;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        OneDriveUrlDbHelper oneDriveUrlDbHelper = this.e;
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        switch (i2) {
            case 0:
                oneDriveUrlDbHelper.createAllTable(sQLiteDatabase);
                return;
            default:
                oneDriveUrlDbHelper.lambda$onUpgrade$0(sQLiteDatabase);
                return;
        }
    }
}
