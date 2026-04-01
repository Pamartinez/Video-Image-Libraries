package Bb;

import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripVideoViewHolder e;

    public /* synthetic */ f(FilmStripVideoViewHolder filmStripVideoViewHolder, int i2) {
        this.d = i2;
        this.e = filmStripVideoViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        FilmStripVideoViewHolder filmStripVideoViewHolder = this.e;
        switch (i2) {
            case 0:
                filmStripVideoViewHolder.lambda$new$0();
                return;
            default:
                filmStripVideoViewHolder.lambda$loadFrameBitmap$7();
                return;
        }
    }
}
