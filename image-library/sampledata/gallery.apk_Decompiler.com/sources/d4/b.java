package d4;

import android.os.Bundle;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsProgressService e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Bundle g;

    public /* synthetic */ b(AbsProgressService absProgressService, Object obj, Bundle bundle, int i2) {
        this.d = i2;
        this.e = absProgressService;
        this.f = obj;
        this.g = bundle;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onRename$1(this.f, this.g);
                return;
            default:
                this.e.lambda$onOption$0(this.f, this.g);
                return;
        }
    }
}
