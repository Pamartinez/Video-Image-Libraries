package Bb;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripViewHolder e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Bitmap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ ImageView f2783h;

    public /* synthetic */ i(FilmStripViewHolder filmStripViewHolder, int i2, Bitmap bitmap, ImageView imageView, int i7) {
        this.d = i7;
        this.e = filmStripViewHolder;
        this.f = i2;
        this.g = bitmap;
        this.f2783h = imageView;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onFilterThumbnailLoaded$8(this.f, this.g, this.f2783h);
                return;
            default:
                this.e.lambda$setDefaultImage$1(this.f, this.g, this.f2783h);
                return;
        }
    }
}
