package M9;

import android.database.Cursor;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.publisher.SharingsDataPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ MdeDatabase f;

    public /* synthetic */ k(Cursor[] cursorArr, MdeDatabase mdeDatabase, int i2) {
        this.d = i2;
        this.e = cursorArr;
        this.f = mdeDatabase;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SharingsDataPublisher.lambda$publishSharingsData$4(this.e, this.f);
                return;
            case 1:
                SharingsDataPublisher.lambda$publishSharingsData$6(this.e, this.f);
                return;
            default:
                SharingsDataPublisher.lambda$publishSharingsData$7(this.e, this.f);
                return;
        }
    }
}
