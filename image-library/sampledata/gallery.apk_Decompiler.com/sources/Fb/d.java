package Fb;

import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryListView e;

    public /* synthetic */ d(int i2, GalleryListView galleryListView) {
        this.d = i2;
        this.e = galleryListView;
    }

    public final void run() {
        int i2 = this.d;
        GalleryListView galleryListView = this.e;
        switch (i2) {
            case 0:
                galleryListView.lambda$updateTouch$3();
                return;
            default:
                galleryListView.onTouchUp();
                return;
        }
    }
}
