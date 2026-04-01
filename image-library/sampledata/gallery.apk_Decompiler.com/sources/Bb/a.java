package Bb;

import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripGroupViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.ViewHolderAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ViewHolderAnimation.OnFinish, ObservableList.OnListChangedCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripGroupViewHolder e;

    public /* synthetic */ a(FilmStripGroupViewHolder filmStripGroupViewHolder, int i2) {
        this.d = i2;
        this.e = filmStripGroupViewHolder;
    }

    public void onFinish() {
        int i2 = this.d;
        FilmStripGroupViewHolder filmStripGroupViewHolder = this.e;
        switch (i2) {
            case 0:
                filmStripGroupViewHolder.lambda$restoreFrameView$5();
                return;
            default:
                filmStripGroupViewHolder.lambda$expandSeekerMode$0();
                return;
        }
    }

    public void onItemRangeChanged(ObservableList observableList, int i2, int i7) {
        this.e.lambda$createBitmapList$2(observableList, i2, i7);
    }
}
