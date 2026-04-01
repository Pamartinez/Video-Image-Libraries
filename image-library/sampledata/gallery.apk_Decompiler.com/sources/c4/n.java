package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2292a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem[] f2293c;

    public /* synthetic */ n(int i2, MediaItem[] mediaItemArr, int i7) {
        this.f2292a = i7;
        this.b = i2;
        this.f2293c = mediaItemArr;
    }

    public final boolean getAsBoolean() {
        switch (this.f2292a) {
            case 0:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$updateOptionsMenuAction$7(this.b, this.f2293c);
            default:
                return FolderViewPresenter.FoldersMenuUpdater.lambda$updateOptionsMenuAction$7(this.b, this.f2293c);
        }
    }
}
