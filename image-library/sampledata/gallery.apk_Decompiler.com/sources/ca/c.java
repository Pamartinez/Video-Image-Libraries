package Ca;

import android.view.View;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelperImpl;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;
import com.samsung.android.gallery.widget.search.searchbar.NewSearchToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ((PanoramaActivity) obj).lambda$new$9(view, windowInsets);
            case 1:
                return ((LiveEffectActivity) obj).lambda$new$13(view, windowInsets);
            case 2:
                return ((NewSearchToolbar) obj).lambda$initSyncWithInsetsAnimation$2(view, windowInsets);
            case 3:
                return ((MvpBaseFragment) obj).onApplyWindowInsets(view, windowInsets);
            case 4:
                return ((BottomTabController) obj).onApplyWindowInsets(view, windowInsets);
            case 5:
                return ((DrawerTabController) obj).onApplyWindowInsets(view, windowInsets);
            case 6:
                return ((EditCreatureNameFragment) obj).lambda$initSyncWithInsetsAnimation$4(view, windowInsets);
            default:
                return TranslateLanguageHelperImpl.initLangCodeButton$lambda$3$lambda$2((LinearLayout) obj, view, windowInsets);
        }
    }
}
