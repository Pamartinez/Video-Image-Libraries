package O5;

import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ CharSequence e;

    public /* synthetic */ b(CharSequence charSequence, int i2) {
        this.d = i2;
        this.e = charSequence;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        CharSequence charSequence = this.e;
        GalleryToolbar galleryToolbar = (GalleryToolbar) obj;
        switch (i2) {
            case 0:
                galleryToolbar.setTitle(charSequence);
                return;
            default:
                galleryToolbar.setSubtitle(charSequence);
                return;
        }
    }
}
