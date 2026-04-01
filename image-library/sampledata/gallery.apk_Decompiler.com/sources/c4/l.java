package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2289a;
    public final /* synthetic */ long b;

    public /* synthetic */ l(long j2, int i2) {
        this.f2289a = i2;
        this.b = j2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2289a) {
            case 0:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$updateOptionsMenuAction$2(this.b);
            case 1:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$updateOptionsMenuAction$3(this.b);
            case 2:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$updateOptionsMenuAction$4(this.b);
            case 3:
                return FolderViewPresenter.FoldersMenuUpdater.lambda$updateOptionsMenuAction$0(this.b);
            case 4:
                return FolderViewPresenter.FoldersMenuUpdater.lambda$updateOptionsMenuAction$1(this.b);
            default:
                return FolderViewPresenter.FoldersMenuUpdater.lambda$updateOptionsMenuAction$2(this.b);
        }
    }
}
