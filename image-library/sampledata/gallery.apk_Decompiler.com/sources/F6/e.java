package F6;

import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ GalleryAppBarLayout e;
    public final /* synthetic */ GalleryListView f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2322h;

    public /* synthetic */ e(boolean z, GalleryAppBarLayout galleryAppBarLayout, GalleryListView galleryListView, int i2, int i7) {
        this.d = z;
        this.e = galleryAppBarLayout;
        this.f = galleryListView;
        this.g = i2;
        this.f2322h = i7;
    }

    public final void run() {
        StoriesPinchViewPresenter.lambda$scrollCompletelyVisible$3(this.d, this.e, this.f, this.g, this.f2322h);
    }
}
