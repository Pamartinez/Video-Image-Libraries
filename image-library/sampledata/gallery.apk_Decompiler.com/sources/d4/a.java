package d4;

import android.os.Bundle;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsProgressService e;

    public /* synthetic */ a(AbsProgressService absProgressService, int i2) {
        this.d = i2;
        this.e = absProgressService;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        AbsProgressService absProgressService = this.e;
        switch (i2) {
            case 0:
                absProgressService.onCancel(obj, bundle);
                return;
            case 1:
                absProgressService.option(obj, bundle);
                return;
            default:
                absProgressService.rename(obj, bundle);
                return;
        }
    }
}
