package c4;

import com.samsung.android.gallery.app.service.HighlightEncodeService;

/* renamed from: c4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0433c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HighlightEncodeService e;
    public final /* synthetic */ String f;

    public /* synthetic */ C0433c(HighlightEncodeService highlightEncodeService, String str, int i2) {
        this.d = i2;
        this.e = highlightEncodeService;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$showSuccessToast$8(this.f);
                return;
            default:
                this.e.lambda$showSuccessToast$9(this.f);
                return;
        }
    }
}
