package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;

/* renamed from: g7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0458a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiEditHandler e;

    public /* synthetic */ C0458a(AiEditHandler aiEditHandler, int i2) {
        this.d = i2;
        this.e = aiEditHandler;
    }

    public final void run() {
        int i2 = this.d;
        AiEditHandler aiEditHandler = this.e;
        switch (i2) {
            case 0:
                aiEditHandler.lambda$hideProgress$6();
                return;
            case 1:
                aiEditHandler.lambda$showProgress$5();
                return;
            default:
                aiEditHandler.lambda$onTableModeChanged$9();
                return;
        }
    }
}
