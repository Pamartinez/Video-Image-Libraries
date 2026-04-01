package D8;

import android.net.Uri;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2789a = 0;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2790c;
    public final /* synthetic */ Cloneable d;

    public /* synthetic */ b(SasCount sasCount, HashMap hashMap, int i2) {
        this.f2790c = sasCount;
        this.d = hashMap;
        this.b = i2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2789a) {
            case 0:
                ((SasCount) this.f2790c).lambda$logScreenshot$3((HashMap) this.d, this.b, (String) obj, (Integer) obj2);
                return;
            default:
                ((ObjectCaptureFileHandler) this.f2790c).lambda$handleSaveAsImage$8(this.b, (MediaItem) this.d, (String) obj, (Uri) obj2);
                return;
        }
    }

    public /* synthetic */ b(ObjectCaptureFileHandler objectCaptureFileHandler, int i2, MediaItem mediaItem) {
        this.f2790c = objectCaptureFileHandler;
        this.b = i2;
        this.d = mediaItem;
    }
}
