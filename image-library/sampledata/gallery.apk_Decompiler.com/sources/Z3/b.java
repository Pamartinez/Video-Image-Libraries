package Z3;

import android.content.Intent;
import com.samsung.android.gallery.app.remote.SlideshowServiceOnRemoteV2;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SlideshowServiceOnRemoteV2 e;

    public /* synthetic */ b(SlideshowServiceOnRemoteV2 slideshowServiceOnRemoteV2, int i2) {
        this.d = i2;
        this.e = slideshowServiceOnRemoteV2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SlideshowServiceOnRemoteV2 slideshowServiceOnRemoteV2 = this.e;
        Intent intent = (Intent) obj;
        switch (i2) {
            case 0:
                slideshowServiceOnRemoteV2.startSlideshow(intent);
                return;
            case 1:
                slideshowServiceOnRemoteV2.stopSlideshow(intent);
                return;
            case 2:
                slideshowServiceOnRemoteV2.resumeSlideshow(intent);
                return;
            default:
                slideshowServiceOnRemoteV2.pauseSlideshow(intent);
                return;
        }
    }
}
