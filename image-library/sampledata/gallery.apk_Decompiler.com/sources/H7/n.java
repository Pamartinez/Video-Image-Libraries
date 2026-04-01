package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements PopupTipBuilder.OnDismissListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ InstantSlowMoTipHandler e;

    public /* synthetic */ n(InstantSlowMoTipHandler instantSlowMoTipHandler, int i2) {
        this.d = i2;
        this.e = instantSlowMoTipHandler;
    }

    public final void onDismiss() {
        int i2 = this.d;
        InstantSlowMoTipHandler instantSlowMoTipHandler = this.e;
        switch (i2) {
            case 0:
                instantSlowMoTipHandler.lambda$showInstantSlowMoEditAndShareGuide$3();
                return;
            default:
                instantSlowMoTipHandler.lambda$showInstantSlowMoTip$1();
                return;
        }
    }
}
