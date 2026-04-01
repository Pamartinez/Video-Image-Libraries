package Qb;

import com.samsung.android.gallery.widget.previewable.Previewable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Previewable.PreviewListener e;

    public /* synthetic */ a(Previewable.PreviewListener previewListener, int i2) {
        this.d = i2;
        this.e = previewListener;
    }

    public final void run() {
        int i2 = this.d;
        Previewable.PreviewListener previewListener = this.e;
        switch (i2) {
            case 0:
                previewListener.onPreviewEnd();
                return;
            default:
                previewListener.onPreviewStart();
                return;
        }
    }
}
