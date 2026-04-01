package Ba;

import com.samsung.android.gallery.plugins.motionphoto.Functions;
import com.samsung.android.gallery.support.utils.MediaScannerListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements MediaScannerListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Functions e;
    public final /* synthetic */ String f;

    public /* synthetic */ b(Functions functions, String str, int i2) {
        this.d = i2;
        this.e = functions;
        this.f = str;
    }

    public final void onCompleted() {
        switch (this.d) {
            case 0:
                this.e.lambda$saveVideo$8(this.f);
                return;
            default:
                this.e.lambda$capture$0(this.f);
                return;
        }
    }
}
