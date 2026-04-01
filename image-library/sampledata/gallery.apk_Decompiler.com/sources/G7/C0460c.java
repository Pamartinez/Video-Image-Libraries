package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BooleanSupplier;

/* renamed from: g7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0460c implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2643a;
    public final /* synthetic */ AiEditHandler b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2644c;

    public /* synthetic */ C0460c(AiEditHandler aiEditHandler, MediaItem mediaItem, int i2) {
        this.f2643a = i2;
        this.b = aiEditHandler;
        this.f2644c = mediaItem;
    }

    public final boolean getAsBoolean() {
        switch (this.f2643a) {
            case 0:
                return this.b.lambda$updateAiEditVisibility$10(this.f2644c);
            default:
                return this.b.lambda$onRequestAiEditDirectly$14(this.f2644c);
        }
    }
}
