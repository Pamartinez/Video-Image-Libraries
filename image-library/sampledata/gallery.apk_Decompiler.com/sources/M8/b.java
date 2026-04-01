package M8;

import android.content.Context;
import com.samsung.android.gallery.module.camera.PostProcessingHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ PostProcessingHelper e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ long g;

    public /* synthetic */ b(PostProcessingHelper postProcessingHelper, Context context, long j2, int i2) {
        this.d = i2;
        this.e = postProcessingHelper;
        this.f = context;
        this.g = j2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$pppCompleted$2(this.f, this.g, (MediaItem) obj);
                return;
            default:
                this.e.lambda$pppCompleted$3(this.f, this.g, (MediaItem) obj);
                return;
        }
    }
}
