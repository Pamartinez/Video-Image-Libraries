package Fb;

import com.samsung.android.gallery.widget.listview.GalleryListAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryListAdapter e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(int i2, int i7, GalleryListAdapter galleryListAdapter) {
        this.d = i7;
        this.e = galleryListAdapter;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$syncSelectionWithClipboard$4(this.f);
                return;
            default:
                this.e.lambda$updateCheckboxOnBindMediaItem$0(this.f);
                return;
        }
    }
}
