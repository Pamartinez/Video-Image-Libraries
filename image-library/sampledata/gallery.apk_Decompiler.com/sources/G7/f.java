package G7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShotModeHandler e;

    public /* synthetic */ f(ShotModeHandler shotModeHandler, int i2) {
        this.d = i2;
        this.e = shotModeHandler;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        ShotModeHandler shotModeHandler = this.e;
        switch (i2) {
            case 0:
                shotModeHandler.lambda$inflateExtraButtonView$13(view);
                return;
            case 1:
                shotModeHandler.lambda$inflateExtraButtonView$14(view);
                return;
            default:
                shotModeHandler.lambda$inflateView$10(view);
                return;
        }
    }
}
