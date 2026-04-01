package M9;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.CursorPublisher;
import com.samsung.android.gallery.module.publisher.MapDataPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ QueryParams f;

    public /* synthetic */ c(Cursor[] cursorArr, int i2, QueryParams queryParams) {
        this.d = i2;
        this.e = cursorArr;
        this.f = queryParams;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                CursorPublisher.lambda$publishFavoriteFileData$3(this.e, this.f);
                return;
            case 1:
                CursorPublisher.lambda$publishRecentFileData$5(this.e, this.f);
                return;
            case 2:
                CursorPublisher.lambda$publishRecentFileData$9(this.e, this.f);
                return;
            case 3:
                CursorPublisher.lambda$publishFavoriteFileData$0(this.e, this.f);
                return;
            case 4:
                MapDataPublisher.lambda$queryMapData$0(this.e, this.f);
                return;
            case 5:
                MapDataPublisher.lambda$queryMapData$1(this.e, this.f);
                return;
            default:
                StoriesDataPublisher.lambda$publishStoryAlbumFileData$1(this.e, this.f);
                return;
        }
    }
}
