package Bb;

import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.ViewHolderAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ ViewHolderAnimation d;
    public final /* synthetic */ FilmStripView e;
    public final /* synthetic */ float f;
    public final /* synthetic */ ViewHolderAnimation.OnFinish g;

    public /* synthetic */ o(ViewHolderAnimation viewHolderAnimation, FilmStripView filmStripView, float f5, ViewHolderAnimation.OnFinish onFinish) {
        this.d = viewHolderAnimation;
        this.e = filmStripView;
        this.f = f5;
        this.g = onFinish;
    }

    public final void run() {
        this.d.lambda$toSeeker$0(this.e, this.f, this.g);
    }
}
