package N4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ScreenShotFilterListViewAdapter e;
    public final /* synthetic */ ScreenShotFilterListViewHolder f;

    public /* synthetic */ b(ScreenShotFilterListViewAdapter screenShotFilterListViewAdapter, ScreenShotFilterListViewHolder screenShotFilterListViewHolder, int i2) {
        this.d = i2;
        this.e = screenShotFilterListViewAdapter;
        this.f = screenShotFilterListViewHolder;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$onBindViewHolder$0(this.f, (MediaItem) obj);
                return;
            default:
                this.e.lambda$onBindViewHolder$1(this.f, (MediaItem) obj);
                return;
        }
    }
}
