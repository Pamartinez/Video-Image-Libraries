package G4;

import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2329a;
    public final /* synthetic */ FolderViewPresenter.FoldersMenuUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2330c;
    public final /* synthetic */ MediaItem[] d;

    public /* synthetic */ a(FolderViewPresenter.FoldersMenuUpdater foldersMenuUpdater, int i2, MediaItem[] mediaItemArr, int i7) {
        this.f2329a = i7;
        this.b = foldersMenuUpdater;
        this.f2330c = i2;
        this.d = mediaItemArr;
    }

    public final boolean getAsBoolean() {
        switch (this.f2329a) {
            case 0:
                return this.b.lambda$updateOptionsMenuAction$5(this.f2330c, this.d);
            default:
                return this.b.lambda$updateOptionsMenuAction$6(this.f2330c, this.d);
        }
    }
}
