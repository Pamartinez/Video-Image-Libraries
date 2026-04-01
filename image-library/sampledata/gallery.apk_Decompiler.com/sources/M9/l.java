package M9;

import android.os.Bundle;
import com.samsung.android.gallery.module.publisher.SharingsDataPublisher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingsDataPublisher e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Bundle g;

    public /* synthetic */ l(SharingsDataPublisher sharingsDataPublisher, Object obj, Bundle bundle, int i2) {
        this.d = i2;
        this.e = sharingsDataPublisher;
        this.f = obj;
        this.g = bundle;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishSharingsDataCache$1(this.f, this.g);
                return;
            case 1:
                this.e.lambda$publishSharingsDataCache$3(this.f, this.g);
                return;
            case 2:
                this.e.lambda$publishSharingsDataCache$2(this.f, this.g);
                return;
            default:
                this.e.lambda$publishSharingsDataCache$0(this.f, this.g);
                return;
        }
    }
}
