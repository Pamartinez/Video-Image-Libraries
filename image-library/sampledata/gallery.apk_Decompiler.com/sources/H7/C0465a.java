package h7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;

/* renamed from: h7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0465a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsRemasterAiEdit e;

    public /* synthetic */ C0465a(AbsRemasterAiEdit absRemasterAiEdit, int i2) {
        this.d = i2;
        this.e = absRemasterAiEdit;
    }

    public final void run() {
        int i2 = this.d;
        AbsRemasterAiEdit absRemasterAiEdit = this.e;
        switch (i2) {
            case 0:
                absRemasterAiEdit.invokeRemasterSelected();
                return;
            case 1:
                absRemasterAiEdit.lambda$invokeRemasterSelected$0();
                return;
            default:
                absRemasterAiEdit.lambda$invokeRemasterSelected$1();
                return;
        }
    }
}
