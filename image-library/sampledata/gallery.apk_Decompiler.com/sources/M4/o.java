package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2398a;
    public final /* synthetic */ AlbumPicturesPresenter.AlbumPicturesMenuUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2399c;

    public /* synthetic */ o(AlbumPicturesPresenter.AlbumPicturesMenuUpdater albumPicturesMenuUpdater, MediaItem mediaItem, int i2) {
        this.f2398a = i2;
        this.b = albumPicturesMenuUpdater;
        this.f2399c = mediaItem;
    }

    public final boolean getAsBoolean() {
        switch (this.f2398a) {
            case 0:
                return this.b.lambda$updateOptionsMenuAction$2(this.f2399c);
            case 1:
                return this.b.lambda$updateOptionsMenuAction$3(this.f2399c);
            case 2:
                return this.b.lambda$updateOptionsMenuAction$4(this.f2399c);
            case 3:
                return this.b.lambda$updateOptionsMenuAction$5(this.f2399c);
            case 4:
                return this.b.lambda$updateOptionsMenuAction$6(this.f2399c);
            case 5:
                return this.b.lambda$updateOptionsMenuAction$7(this.f2399c);
            default:
                return this.b.lambda$updateOptionsMenuAction$8(this.f2399c);
        }
    }
}
