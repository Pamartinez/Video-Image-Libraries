package N8;

import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* renamed from: N8.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0575b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureFileHandler e;
    public final /* synthetic */ int f;
    public final /* synthetic */ MediaItem g;

    public /* synthetic */ C0575b(ObjectCaptureFileHandler objectCaptureFileHandler, int i2, MediaItem mediaItem, int i7) {
        this.d = i7;
        this.e = objectCaptureFileHandler;
        this.f = i2;
        this.g = mediaItem;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$handleShare$10(this.f, this.g, 0, (Boolean) obj);
                return;
            default:
                this.e.lambda$handleCopy$4(this.f, this.g, 0, (Boolean) obj);
                return;
        }
    }
}
