package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesViewAdapter e;
    public final /* synthetic */ ListViewHolder f;

    public /* synthetic */ n(PicturesViewAdapter picturesViewAdapter, ListViewHolder listViewHolder, int i2) {
        this.d = i2;
        this.e = picturesViewAdapter;
        this.f = listViewHolder;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateViewSize$6(this.f);
                return;
            default:
                this.e.lambda$updateViewSize$7(this.f);
                return;
        }
    }
}
