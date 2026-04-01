package Yb;

import android.graphics.Bitmap;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewHolder e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ a(ListViewHolder listViewHolder, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = listViewHolder;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.bindImage(this.f);
                return;
            default:
                this.e.bindImage(this.f);
                return;
        }
    }
}
