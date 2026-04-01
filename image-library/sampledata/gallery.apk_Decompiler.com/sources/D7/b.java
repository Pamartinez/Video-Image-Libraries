package D7;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.AbsImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterImageLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ int f;
    public final /* synthetic */ AbsImageLoader g;

    public /* synthetic */ b(AbsImageLoader absImageLoader, MediaItem mediaItem, int i2, int i7) {
        this.d = i7;
        this.g = absImageLoader;
        this.e = mediaItem;
        this.f = i2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        switch (this.d) {
            case 0:
                this.g.lambda$subscribe$2(this.e, this.f, obj, bundle);
                return;
            default:
                ((RemasterImageLoader) this.g).lambda$subscribe$0(this.e, this.f, obj, bundle);
                return;
        }
    }
}
