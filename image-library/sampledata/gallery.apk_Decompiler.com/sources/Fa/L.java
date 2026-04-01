package Fa;

import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class L implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchSettingFragment e;

    public /* synthetic */ L(SearchSettingFragment searchSettingFragment, int i2) {
        this.d = i2;
        this.e = searchSettingFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SearchSettingFragment searchSettingFragment = this.e;
        switch (i2) {
            case 0:
                searchSettingFragment.lambda$onViewCreated$1((GalleryToolbar) obj);
                return;
            case 1:
                searchSettingFragment.lambda$onViewCreated$2((GalleryAppBarLayout) obj);
                return;
            default:
                searchSettingFragment.lambda$initCustomizePreference$9(obj);
                return;
        }
    }
}
