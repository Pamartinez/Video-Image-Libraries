package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.FilmStripDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripDelegate e;

    public /* synthetic */ b(FilmStripDelegate filmStripDelegate, int i2) {
        this.d = i2;
        this.e = filmStripDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FilmStripDelegate filmStripDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                filmStripDelegate.onVideoPlayTimeUpdated(objArr);
                return;
            case 1:
                filmStripDelegate.onPlayPauseButtonClicked(objArr);
                return;
            default:
                filmStripDelegate.onReplayVideo(objArr);
                return;
        }
    }
}
