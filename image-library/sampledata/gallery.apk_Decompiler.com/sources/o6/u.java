package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.OsdUiDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ OsdUiDelegate e;

    public /* synthetic */ u(OsdUiDelegate osdUiDelegate, int i2) {
        this.d = i2;
        this.e = osdUiDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        OsdUiDelegate osdUiDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                osdUiDelegate.initializeUiState(objArr);
                return;
            case 1:
                osdUiDelegate.onBottomSheetStateChanged(objArr);
                return;
            case 2:
                osdUiDelegate.toggleOsd(objArr);
                return;
            case 3:
                osdUiDelegate.setOnOffOsd(objArr);
                return;
            case 4:
                osdUiDelegate.updateOsd(objArr);
                return;
            default:
                osdUiDelegate.updateDecoUiVisibility(objArr);
                return;
        }
    }
}
