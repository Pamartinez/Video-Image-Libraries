package A4;

import android.view.MenuItem;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListMenuUpdater e;

    public /* synthetic */ T(ListMenuUpdater listMenuUpdater, int i2) {
        this.d = i2;
        this.e = listMenuUpdater;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ListMenuUpdater listMenuUpdater = this.e;
        switch (i2) {
            case 0:
                listMenuUpdater.lambda$updateCommonPopupMenuAction$0((MenuItem) obj);
                return;
            case 1:
                listMenuUpdater.lambda$updateCommonPopupMenuAction$1((MenuItem) obj);
                return;
            case 2:
                listMenuUpdater.lambda$updateCommonPopupMenuAction$3((MenuItem) obj);
                return;
            default:
                listMenuUpdater.lambda$updateOptionsMenuActionDone$8((View) obj);
                return;
        }
    }
}
