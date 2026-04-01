package O5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesFragment e;
    public final /* synthetic */ CharSequence f;

    public /* synthetic */ c(SharingPicturesFragment sharingPicturesFragment, CharSequence charSequence, int i2) {
        this.d = i2;
        this.e = sharingPicturesFragment;
        this.f = charSequence;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateCustomCover$16(this.f, (GalleryToolbar) obj);
                return;
            default:
                this.e.lambda$updateCustomCover$13(this.f, (GalleryToolbar) obj);
                return;
        }
    }
}
