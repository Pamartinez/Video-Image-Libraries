package Bb;

import com.samsung.android.gallery.widget.filmstrip3.FilmStripGroupViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripGroupViewHolder e;

    public /* synthetic */ b(FilmStripGroupViewHolder filmStripGroupViewHolder, int i2) {
        this.d = i2;
        this.e = filmStripGroupViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        FilmStripGroupViewHolder filmStripGroupViewHolder = this.e;
        switch (i2) {
            case 0:
                filmStripGroupViewHolder.postExpandEvent();
                return;
            default:
                filmStripGroupViewHolder.lambda$loadFrameBitmap$4();
                return;
        }
    }
}
