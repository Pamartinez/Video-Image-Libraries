package O3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Consumer {
    public final /* synthetic */ ViewLiveEffectCmd d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ A(ViewLiveEffectCmd viewLiveEffectCmd, MediaItem mediaItem, boolean z, boolean z3) {
        this.d = viewLiveEffectCmd;
        this.e = mediaItem;
        this.f = z;
        this.g = z3;
    }

    public final void accept(Object obj) {
        this.d.lambda$onExecute$1(this.e, this.f, this.g, (Bitmap) obj);
    }
}
