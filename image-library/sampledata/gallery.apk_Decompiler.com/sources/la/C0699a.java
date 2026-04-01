package la;

import com.samsung.android.gallery.module.story.transcode.decoder.video.processor.FilterProcessor;

/* renamed from: la.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0699a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilterProcessor e;

    public /* synthetic */ C0699a(FilterProcessor filterProcessor, int i2) {
        this.d = i2;
        this.e = filterProcessor;
    }

    public final void run() {
        int i2 = this.d;
        FilterProcessor filterProcessor = this.e;
        switch (i2) {
            case 0:
                filterProcessor.inputProcessInternal();
                return;
            default:
                filterProcessor.outputProcessInternal();
                return;
        }
    }
}
