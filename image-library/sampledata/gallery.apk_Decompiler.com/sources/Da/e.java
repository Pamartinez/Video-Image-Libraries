package Da;

import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveEffectActivity e;

    public /* synthetic */ e(LiveEffectActivity liveEffectActivity, int i2) {
        this.d = i2;
        this.e = liveEffectActivity;
    }

    public final void run() {
        int i2 = this.d;
        LiveEffectActivity liveEffectActivity = this.e;
        switch (i2) {
            case 0:
                liveEffectActivity.finish();
                return;
            case 1:
                liveEffectActivity.lambda$updateTableModeLayout$6();
                return;
            default:
                liveEffectActivity.lambda$renderLiveEffectByItemType$2();
                return;
        }
    }
}
