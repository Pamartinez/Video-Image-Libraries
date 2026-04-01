package N4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterDelegate;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ScreenShotFilterDelegate e;

    public /* synthetic */ a(ScreenShotFilterDelegate screenShotFilterDelegate, int i2) {
        this.d = i2;
        this.e = screenShotFilterDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ScreenShotFilterDelegate screenShotFilterDelegate = this.e;
        switch (i2) {
            case 0:
                screenShotFilterDelegate.lambda$updateVisibility$0((ScreenShotFilterView) obj);
                return;
            default:
                screenShotFilterDelegate.onListItemClicked((String) obj);
                return;
        }
    }
}
