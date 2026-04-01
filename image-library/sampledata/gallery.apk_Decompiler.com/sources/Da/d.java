package Da;

import android.net.Uri;
import android.view.View;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2797a;
    public final /* synthetic */ LiveEffectActivity b;

    public /* synthetic */ d(LiveEffectActivity liveEffectActivity, int i2) {
        this.f2797a = i2;
        this.b = liveEffectActivity;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2797a) {
            case 0:
                this.b.onSaveDone((String) obj, (Uri) obj2);
                return;
            default:
                this.b.lambda$renderLiveEffectByItemType$5((View) obj, (Boolean) obj2);
                return;
        }
    }
}
