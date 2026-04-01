package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.module.aiedit.AiEditBixbyRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiEditList e;
    public final /* synthetic */ AiEditBixbyRequest f;

    public /* synthetic */ g(AiEditList aiEditList, AiEditBixbyRequest aiEditBixbyRequest, int i2) {
        this.d = i2;
        this.e = aiEditList;
        this.f = aiEditBixbyRequest;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$requestDirectly$8(this.f);
                return;
            default:
                this.e.lambda$requestDirectly$9(this.f);
                return;
        }
    }
}
