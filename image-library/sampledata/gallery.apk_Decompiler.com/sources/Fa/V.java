package Fa;

import com.samsung.android.gallery.module.settings.CmhProviderPermission;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class V implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Consumer e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ V(Consumer consumer, int i2, boolean z) {
        this.d = i2;
        this.e = consumer;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SettingFragment.lambda$updateStoryFeatureState$32(this.e, this.f);
                return;
            case 1:
                this.e.accept(Boolean.valueOf(this.f));
                return;
            default:
                CmhProviderPermission.lambda$load$0(this.e, this.f);
                return;
        }
    }
}
