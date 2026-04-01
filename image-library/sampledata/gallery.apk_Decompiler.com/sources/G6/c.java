package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.MenuUpdater;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2640a;
    public final /* synthetic */ MenuUpdater b;

    public /* synthetic */ c(MenuUpdater menuUpdater, int i2) {
        this.f2640a = i2;
        this.b = menuUpdater;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2640a;
        MenuUpdater menuUpdater = this.b;
        switch (i2) {
            case 0:
                return menuUpdater.enableDownloadAllMenu();
            default:
                return menuUpdater.hideFromStoryMenuVisible();
        }
    }
}
