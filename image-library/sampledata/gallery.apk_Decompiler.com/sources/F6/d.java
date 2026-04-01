package F6;

import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryListView e;
    public final /* synthetic */ int f;

    public /* synthetic */ d(GalleryListView galleryListView, int i2, int i7) {
        this.d = i7;
        this.e = galleryListView;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.scrollToPositionWithOffset(this.f, 10);
                return;
            default:
                this.e.lambda$smoothScrollToPositionJumpIfNeeded$7(this.f);
                return;
        }
    }
}
