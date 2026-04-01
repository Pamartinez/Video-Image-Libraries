package k9;

import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FoldStateManager e;

    public /* synthetic */ b(FoldStateManager foldStateManager, int i2) {
        this.d = i2;
        this.e = foldStateManager;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FoldStateManager foldStateManager = this.e;
        DisplayManagerCompat displayManagerCompat = (DisplayManagerCompat) obj;
        switch (i2) {
            case 0:
                foldStateManager.lambda$unregister$2(displayManagerCompat);
                return;
            default:
                foldStateManager.lambda$initDisplayManager$3(displayManagerCompat);
                return;
        }
    }
}
