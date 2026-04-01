package v7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DexZoomButtonUi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DexZoomButtonUi e;

    public /* synthetic */ i(DexZoomButtonUi dexZoomButtonUi, int i2) {
        this.d = i2;
        this.e = dexZoomButtonUi;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DexZoomButtonUi dexZoomButtonUi = this.e;
        switch (i2) {
            case 0:
                dexZoomButtonUi.lambda$setListener$5(view);
                return;
            default:
                dexZoomButtonUi.lambda$setListener$6(view);
                return;
        }
    }
}
