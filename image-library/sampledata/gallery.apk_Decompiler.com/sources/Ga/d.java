package Ga;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.controller.externals.PlayMotionPhotoByVpCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements InstantSubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ d(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        switch (this.d) {
            case 0:
                ((DialogDelegate) this.e).lambda$showGdprDialog$4((Consumer) this.f, obj, bundle);
                return;
            case 1:
                ((PlayMotionPhotoByVpCmd) this.e).lambda$onExecute$0((String) this.f, obj, bundle);
                return;
            case 2:
                ((MotionPhotoExportCmd) this.e).lambda$createGif$2((String) this.f, obj, bundle);
                return;
            default:
                GalleryMapFactory.lambda$downloadGalleryPlugins$0((Fragment) this.e, (FragmentActivity) this.f, obj, bundle);
                return;
        }
    }
}
