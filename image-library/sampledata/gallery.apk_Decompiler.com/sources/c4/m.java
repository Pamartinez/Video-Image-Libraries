package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2290a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2291c;
    public final /* synthetic */ AlbumsBasePresenter.AlbumsMenuUpdater d;

    public /* synthetic */ m(AlbumsBasePresenter.AlbumsMenuUpdater albumsMenuUpdater, int i2, long j2, int i7) {
        this.f2290a = i7;
        this.d = albumsMenuUpdater;
        this.b = i2;
        this.f2291c = j2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2290a) {
            case 0:
                return this.d.lambda$updateOptionsMenuAction$6(this.b, this.f2291c);
            default:
                return ((FolderViewPresenter.FoldersMenuUpdater) this.d).lambda$updateOptionsMenuAction$4(this.b, this.f2291c);
        }
    }
}
