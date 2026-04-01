package Bb;

import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.ViewHolderAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ViewHolderAnimation.OnFinish, ObservableList.OnListChangedCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripVideoViewHolder e;

    public /* synthetic */ e(FilmStripVideoViewHolder filmStripVideoViewHolder, int i2) {
        this.d = i2;
        this.e = filmStripVideoViewHolder;
    }

    public void onFinish() {
        int i2 = this.d;
        FilmStripVideoViewHolder filmStripVideoViewHolder = this.e;
        switch (i2) {
            case 0:
                filmStripVideoViewHolder.lambda$restoreFrameView$4();
                return;
            default:
                filmStripVideoViewHolder.lambda$expandSeekerMode$1();
                return;
        }
    }

    public void onItemRangeChanged(ObservableList observableList, int i2, int i7) {
        this.e.lambda$createBitmapList$6(observableList, i2, i7);
    }
}
