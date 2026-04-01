package M9;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.CursorPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CursorPublisher e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ QueryParams g;

    public /* synthetic */ d(CursorPublisher cursorPublisher, Cursor[] cursorArr, QueryParams queryParams, int i2) {
        this.d = i2;
        this.e = cursorPublisher;
        this.f = cursorArr;
        this.g = queryParams;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishRecentFileData$6(this.f, this.g);
                return;
            default:
                this.e.lambda$publishFavoriteFileData$1(this.f, this.g);
                return;
        }
    }
}
