package A4;

import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.settings.widget.ChunkingBackgroundView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.function.Consumer;

/* renamed from: A4.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0382q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ WindowInsets e;

    public /* synthetic */ C0382q(WindowInsets windowInsets, int i2) {
        this.d = i2;
        this.e = windowInsets;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        WindowInsets windowInsets = this.e;
        switch (i2) {
            case 0:
                BaseListFragment.lambda$adjustAppbarHeightOffset$2(windowInsets, (GalleryAppBarLayout) obj);
                return;
            case 1:
                ((ChunkingBackgroundView) obj).onApplyWindowInsets(windowInsets);
                return;
            case 2:
                ViewMarginUtils.setTopPadding((View) ((GalleryToolbar) obj).getParent(), WindowUtils.getSystemInsetsTop(windowInsets));
                return;
            default:
                ((ForegroundViewController) obj).updateViews(windowInsets);
                return;
        }
    }
}
