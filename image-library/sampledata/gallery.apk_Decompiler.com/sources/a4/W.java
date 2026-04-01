package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class W implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2243a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2244c;

    public /* synthetic */ W(int i2, Object obj, Object obj2) {
        this.f2243a = i2;
        this.b = obj;
        this.f2244c = obj2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2243a) {
            case 0:
                return ((ListMenuUpdater) this.b).lambda$updateClipboardMenu$10((MenuDataBinding.SelectionMode) this.f2244c);
            case 1:
                return ((AlbumsBasePresenter.AlbumsMenuUpdater) this.b).lambda$updateOptionsMenuAction$0((MediaItem[]) this.f2244c);
            case 2:
                return ((StoriesPinchViewPresenter.StoriesPinchMenuUpdater) this.b).lambda$updateOptionsMenuAction$0((MenuDataBinding.SelectionMode) this.f2244c);
            case 3:
                return ((FolderViewPresenter.FoldersMenuUpdater) this.b).lambda$updateOptionsMenuAction$3((MediaItem[]) this.f2244c);
            default:
                return ((ReorderDragManager) this.b).lambda$beginDrag$1((GalleryListView) this.f2244c);
        }
    }
}
