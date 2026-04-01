package A4;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class U implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListMenuUpdater e;
    public final /* synthetic */ int f;

    public /* synthetic */ U(ListMenuUpdater listMenuUpdater, int i2, int i7) {
        this.d = i7;
        this.e = listMenuUpdater;
        this.f = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateCommonPopupMenuAction$2(this.f, (MenuItem) obj);
                return;
            case 1:
                this.e.lambda$updateCommonPopupMenuAction$4(this.f, (MenuItem) obj);
                return;
            case 2:
                this.e.lambda$updateCommonPopupMenuAction$5(this.f, (MenuItem) obj);
                return;
            case 3:
                this.e.lambda$updateCommonPopupMenuAction$6(this.f, (MenuItem) obj);
                return;
            default:
                this.e.lambda$updateCommonPopupMenuAction$7(this.f, (MenuItem) obj);
                return;
        }
    }
}
