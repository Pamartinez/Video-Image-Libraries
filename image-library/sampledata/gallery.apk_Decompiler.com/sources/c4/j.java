package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.MenuUpdater;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2286a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ j(boolean z, int i2) {
        this.f2286a = i2;
        this.b = z;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2286a;
        boolean z = this.b;
        switch (i2) {
            case 0:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$checkMoveToKnoxMenu$10(z);
            case 1:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$checkMoveToKnoxMenu$11(z);
            case 2:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$checkMoveToKnoxMenu$8(z);
            case 3:
                return AlbumsBasePresenter.AlbumsMenuUpdater.lambda$checkMoveToKnoxMenu$9(z);
            case 4:
                return StoriesPresenter.StoriesMenuUpdater.lambda$updateOptionsMenuAction$0(z);
            case 5:
                return MenuUpdater.lambda$updateFavoriteMenu$0(z);
            default:
                return MenuUpdater.lambda$updateFavoriteMenu$1(z);
        }
    }
}
