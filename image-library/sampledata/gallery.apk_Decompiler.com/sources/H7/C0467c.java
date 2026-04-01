package h7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import java.util.function.Consumer;

/* renamed from: h7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0467c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiEditItem e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ C0467c(AiEditItem aiEditItem, boolean z, int i2) {
        this.d = i2;
        this.e = aiEditItem;
        this.f = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$executeCloudOnly$3(this.f, obj);
                return;
            default:
                this.e.lambda$executeCloudOnly$1(this.f, obj);
                return;
        }
    }
}
