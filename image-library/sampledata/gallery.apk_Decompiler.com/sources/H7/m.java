package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements IntSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2349a;
    public final /* synthetic */ InstantSlowMoTipHandler b;

    public /* synthetic */ m(InstantSlowMoTipHandler instantSlowMoTipHandler, int i2) {
        this.f2349a = i2;
        this.b = instantSlowMoTipHandler;
    }

    public final int getAsInt() {
        int i2 = this.f2349a;
        InstantSlowMoTipHandler instantSlowMoTipHandler = this.b;
        switch (i2) {
            case 0:
                return instantSlowMoTipHandler.getOptionsPopupTipBottomMargin();
            case 1:
                return instantSlowMoTipHandler.getOptionsPopupTipGravity();
            case 2:
                return instantSlowMoTipHandler.getPopupTipHorizontalMargin();
            case 3:
                return instantSlowMoTipHandler.getPopupTipTopMargin();
            case 4:
                return instantSlowMoTipHandler.getOptionsPopupTipStartMargin();
            case 5:
                return instantSlowMoTipHandler.getOptionsPopupTipTopMargin();
            default:
                return instantSlowMoTipHandler.getOptionsPopupTipEndMargin();
        }
    }
}
