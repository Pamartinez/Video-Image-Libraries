package h9;

import android.content.ContentProviderOperation;
import android.content.Context;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DatabaseOperation e;
    public final /* synthetic */ DatabaseOpObject f;

    public /* synthetic */ a(DatabaseOperation databaseOperation, DatabaseOpObject databaseOpObject, int i2) {
        this.d = i2;
        this.e = databaseOperation;
        this.f = databaseOpObject;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$add$8(this.f, (Context) obj);
                return;
            default:
                this.e.lambda$add$9(this.f, (ContentProviderOperation.Builder) obj);
                return;
        }
    }
}
