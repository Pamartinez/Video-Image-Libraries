package k7;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.KeyguardDelegate;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ l(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        switch (this.d) {
            case 0:
                ((KeyguardDelegate) this.e).lambda$createGlobalSubscriberList$0((Subscriber) this.f, obj, bundle);
                return;
            default:
                ((KnoxAlbumOperator) this.e).lambda$fetchMediaItemsFromAlbums$1((Context) this.f, obj, bundle);
                return;
        }
    }
}
