package Lb;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ float f;
    public final /* synthetic */ Object g;

    public /* synthetic */ d(Object obj, int i2, float f5, int i7) {
        this.d = i7;
        this.g = obj;
        this.e = i2;
        this.f = f5;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((LiveMotionViewPager) this.g).lambda$onPageScrolled$1(this.e, this.f, (ViewPagerCallback) obj);
                return;
            default:
                ((DrawerTabController) this.g).lambda$onSlide$21(this.e, this.f, (GalleryGridLayoutManager) obj);
                return;
        }
    }
}
