package Fb;

import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryRecyclerView e;

    public /* synthetic */ g(GalleryRecyclerView galleryRecyclerView, int i2) {
        this.d = i2;
        this.e = galleryRecyclerView;
    }

    public final void run() {
        int i2 = this.d;
        GalleryRecyclerView galleryRecyclerView = this.e;
        switch (i2) {
            case 0:
                galleryRecyclerView.lambda$setEmptyViewVisible$0();
                return;
            default:
                galleryRecyclerView.lambda$updateScrollOffset$4();
                return;
        }
    }
}
