package dc;

import com.samsung.android.gallery.widget.tip.PopupTipBuilder;

/* renamed from: dc.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0686a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PopupTipBuilder e;

    public /* synthetic */ C0686a(PopupTipBuilder popupTipBuilder, int i2) {
        this.d = i2;
        this.e = popupTipBuilder;
    }

    public final void run() {
        int i2 = this.d;
        PopupTipBuilder popupTipBuilder = this.e;
        switch (i2) {
            case 0:
                popupTipBuilder.dismissInternal();
                return;
            default:
                popupTipBuilder.lambda$dismissInternal$5();
                return;
        }
    }
}
