package O5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesFragment e;

    public /* synthetic */ f(SharingPicturesFragment sharingPicturesFragment, int i2) {
        this.d = i2;
        this.e = sharingPicturesFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SharingPicturesFragment sharingPicturesFragment = this.e;
        GalleryToolbar galleryToolbar = (GalleryToolbar) obj;
        switch (i2) {
            case 0:
                sharingPicturesFragment.lambda$onBindView$3(galleryToolbar);
                return;
            default:
                sharingPicturesFragment.lambda$onConfigurationChanged$11(galleryToolbar);
                return;
        }
    }
}
