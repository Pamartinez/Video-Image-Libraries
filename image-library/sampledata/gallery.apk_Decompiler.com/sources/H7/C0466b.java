package h7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: h7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0466b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiEditItem e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ C0466b(AiEditItem aiEditItem, MediaItem mediaItem, boolean z, int i2) {
        this.d = i2;
        this.e = aiEditItem;
        this.f = mediaItem;
        this.g = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$executeItem$0(this.f, this.g);
                return;
            default:
                this.e.lambda$executeCloudOnly$2(this.f, this.g);
                return;
        }
    }
}
