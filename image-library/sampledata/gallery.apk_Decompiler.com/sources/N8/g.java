package N8;

import android.net.Uri;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureHelper e;

    public /* synthetic */ g(ObjectCaptureHelper objectCaptureHelper, int i2) {
        this.d = i2;
        this.e = objectCaptureHelper;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ObjectCaptureHelper objectCaptureHelper = this.e;
        switch (i2) {
            case 0:
                objectCaptureHelper.onSaveGif(obj);
                return;
            default:
                objectCaptureHelper.lambda$onSaveGif$5((Uri) obj);
                return;
        }
    }
}
