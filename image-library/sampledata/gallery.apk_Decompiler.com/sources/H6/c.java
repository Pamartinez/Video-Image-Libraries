package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.FilmStripDelegate;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripDelegate e;
    public final /* synthetic */ FilmStripViewHolder f;

    public /* synthetic */ c(FilmStripDelegate filmStripDelegate, FilmStripViewHolder filmStripViewHolder, int i2) {
        this.d = i2;
        this.e = filmStripDelegate;
        this.f = filmStripViewHolder;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onVideoPlayTimeUpdated$1(this.f);
                return;
            default:
                this.e.lambda$onReplayVideo$0(this.f);
                return;
        }
    }
}
