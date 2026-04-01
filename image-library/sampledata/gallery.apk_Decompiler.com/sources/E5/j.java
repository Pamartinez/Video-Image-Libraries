package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Supplier {
    public final /* synthetic */ PicturesViewAdapter d;
    public final /* synthetic */ int e;

    public /* synthetic */ j(PicturesViewAdapter picturesViewAdapter, int i2) {
        this.d = picturesViewAdapter;
        this.e = i2;
    }

    public final Object get() {
        return this.d.lambda$requestThumbnail$0(this.e);
    }
}
