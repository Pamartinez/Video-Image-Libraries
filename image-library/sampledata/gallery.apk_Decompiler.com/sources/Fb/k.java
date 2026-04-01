package Fb;

import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegateFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.QuickSharePrivacyTip;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharedAlbumLinkTip;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilmStripViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listview.ListAutoDragHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Timer.OnTimer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2819a;
    public final /* synthetic */ Object b;

    public /* synthetic */ k(int i2, Object obj) {
        this.f2819a = i2;
        this.b = obj;
    }

    public final void onTimer(int i2) {
        int i7 = this.f2819a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                ((ListAutoDragHandler) obj).onTimer(i2);
                return;
            case 1:
                ((QuickSharePrivacyTip) obj).lambda$showTip$1(i2);
                return;
            case 2:
                ((SharedAlbumLinkTip) obj).lambda$showAlbumLinkTip$1(i2);
                return;
            case 3:
                ((SlideDelegate) obj).onSlideshowTimer(i2);
                return;
            case 4:
                ((AbsDelegateFragment) obj).lambda$onViewCreated$0(i2);
                return;
            case 5:
                ((FilmStripViewDelegate) obj).lambda$initView$0(i2);
                return;
            default:
                ((LastPageView) obj).lambda$setNextPageTimer$4(i2);
                return;
        }
    }
}
