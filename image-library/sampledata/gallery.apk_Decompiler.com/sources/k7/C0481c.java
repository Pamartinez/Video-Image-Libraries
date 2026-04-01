package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;

/* renamed from: k7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0481c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BixbyDelegate e;

    public /* synthetic */ C0481c(BixbyDelegate bixbyDelegate, int i2) {
        this.d = i2;
        this.e = bixbyDelegate;
    }

    public final void run() {
        int i2 = this.d;
        BixbyDelegate bixbyDelegate = this.e;
        switch (i2) {
            case 0:
                bixbyDelegate.lambda$handlePendedShare$6();
                return;
            case 1:
                bixbyDelegate.lambda$handlePendedShare$5();
                return;
            default:
                bixbyDelegate.lambda$handleAddTagDone$3();
                return;
        }
    }
}
