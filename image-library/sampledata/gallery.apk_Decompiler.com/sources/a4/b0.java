package A4;

import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PreviewViewHolder e;

    public /* synthetic */ b0(PreviewViewHolder previewViewHolder, int i2) {
        this.d = i2;
        this.e = previewViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        PreviewViewHolder previewViewHolder = this.e;
        switch (i2) {
            case 0:
                previewViewHolder.stopPreview();
                return;
            case 1:
                previewViewHolder.startPreview();
                return;
            case 2:
                previewViewHolder.lambda$hidePreviewView$1();
                return;
            default:
                previewViewHolder.stopPreviewForever();
                return;
        }
    }
}
