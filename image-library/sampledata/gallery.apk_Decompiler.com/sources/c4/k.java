package C4;

import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import java.io.Serializable;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2287a;
    public final /* synthetic */ ListMenuUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2288c;
    public final /* synthetic */ Serializable d;

    public /* synthetic */ k(ListMenuUpdater listMenuUpdater, MenuDataBinding.SelectionMode selectionMode, int i2) {
        this.f2287a = 2;
        this.b = listMenuUpdater;
        this.d = selectionMode;
        this.f2288c = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2287a) {
            case 0:
                return ((AlbumsBasePresenter.AlbumsMenuUpdater) this.b).lambda$updateOptionsMenuAction$1(this.f2288c, (MediaItem[]) this.d);
            case 1:
                return ((AlbumsBasePresenter.AlbumsMenuUpdater) this.b).lambda$updateOptionsMenuAction$5(this.f2288c, (MediaItem[]) this.d);
            default:
                int i2 = this.f2288c;
                return this.b.lambda$updateClipboardMenu$9((MenuDataBinding.SelectionMode) this.d, i2);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.samsung.android.gallery.module.data.MediaItem[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ k(com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter.AlbumsMenuUpdater r1, int r2, com.samsung.android.gallery.module.data.MediaItem[] r3, int r4) {
        /*
            r0 = this;
            r0.f2287a = r4
            r0.b = r1
            r0.f2288c = r2
            r0.d = r3
            r0.<init>()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: C4.k.<init>(com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter$AlbumsMenuUpdater, int, com.samsung.android.gallery.module.data.MediaItem[], int):void");
    }
}
