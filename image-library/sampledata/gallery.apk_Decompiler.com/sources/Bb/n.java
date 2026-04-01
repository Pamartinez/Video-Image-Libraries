package Bb;

import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.ViewHolderAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements ViewHolderAnimation.OnFinish {
    public final /* synthetic */ ViewHolderAnimation d;
    public final /* synthetic */ FilmStripView e;
    public final /* synthetic */ float f;
    public final /* synthetic */ ViewHolderAnimation.OnFinish g;

    public /* synthetic */ n(ViewHolderAnimation viewHolderAnimation, FilmStripView filmStripView, float f5, ViewHolderAnimation.OnFinish onFinish) {
        this.d = viewHolderAnimation;
        this.e = filmStripView;
        this.f = f5;
        this.g = onFinish;
    }

    public final void onFinish() {
        this.d.lambda$toSeeker$1(this.e, this.f, this.g);
    }
}
