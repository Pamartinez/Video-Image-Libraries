package Bb;

import V3.b;
import android.view.View;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MyTagHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;
import com.samsung.android.gallery.widget.appbar.BlurCustomCover;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnLayoutChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        switch (this.d) {
            case 0:
                ((FilmStripVideoViewHolder) this.e).onLayoutChange(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 1:
                ((FilmStripViewHolder) this.e).onLayoutChange(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 2:
                ((ShotModeHandler) this.e).lambda$inflateExtraButtonView$12(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 3:
                ((StoriesPinchViewHolder) this.e).onLayoutChanged(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 4:
                ((PreviewHdrVideo) this.e).lambda$new$0(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 5:
                ((AlbumTitleCountViewHolder) this.e).lambda$new$0(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 6:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.e;
                if (i2 != i11 || i7 != i12 || i8 != i13 || i10 != i14) {
                    view.post(new b(22, carouselLayoutManager));
                    return;
                }
                return;
            case 7:
                ((PicturesViewAdapter) this.e).onLayoutChanged(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 8:
                ((SimpleAutoScroller) this.e).onLayoutChange(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 9:
                ((SimpleShrinkView) this.e).onLayoutChange(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 10:
                ((AiEditHandler) this.e).lambda$addLayoutChangeListener$12(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 11:
                ((BlurCustomCover) this.e).lambda$new$0(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            case 12:
                ((MyTagHandler) this.e).lambda$addLayoutChangeListener$2(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            default:
                ((AGSLBlurEffect) this.e).lambda$addBlurAreaChangedListener$0(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
        }
    }
}
