package I5;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2371h;

    public /* synthetic */ c(Object obj, int i2, int i7, int i8, int i10) {
        this.d = i10;
        this.f2371h = obj;
        this.e = i2;
        this.f = i7;
        this.g = i8;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FaceClusterMergeView) this.f2371h).lambda$setCustomNavigationItemView$8(this.e, this.f, this.g, (MenuItem) obj);
                return;
            case 1:
                ((SearchCreatureHeader2View) this.f2371h).lambda$setCustomNavigationItemView$12(this.e, this.f, this.g, (MenuItem) obj);
                return;
            default:
                ((SearchCreatureHeaderView) this.f2371h).lambda$setCustomNavigationItemView$19(this.e, this.f, this.g, (MenuItem) obj);
                return;
        }
    }
}
