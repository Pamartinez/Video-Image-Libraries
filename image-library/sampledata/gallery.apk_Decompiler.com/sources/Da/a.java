package Da;

import com.samsung.android.gallery.plugins.portrait.InitProgressDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ InitProgressDelegate e;

    public /* synthetic */ a(InitProgressDelegate initProgressDelegate, int i2) {
        this.d = i2;
        this.e = initProgressDelegate;
    }

    public final void run() {
        int i2 = this.d;
        InitProgressDelegate initProgressDelegate = this.e;
        switch (i2) {
            case 0:
                initProgressDelegate.lambda$showInitProgress$0();
                return;
            case 1:
                initProgressDelegate.lambda$dismissInitProgress$1();
                return;
            default:
                initProgressDelegate.lambda$updateEffectViewSize$3();
                return;
        }
    }
}
