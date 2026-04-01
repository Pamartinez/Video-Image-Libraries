package Da;

import android.view.View;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveEffectActivity e;

    public /* synthetic */ c(LiveEffectActivity liveEffectActivity, int i2) {
        this.d = i2;
        this.e = liveEffectActivity;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        LiveEffectActivity liveEffectActivity = this.e;
        switch (i2) {
            case 0:
                liveEffectActivity.lambda$bindGyroButton$10(view);
                return;
            case 1:
                liveEffectActivity.lambda$renderLiveEffectByItemType$3(view);
                return;
            case 2:
                liveEffectActivity.lambda$onCreate$1(view);
                return;
            default:
                liveEffectActivity.lambda$bindToolbar$7(view);
                return;
        }
    }
}
