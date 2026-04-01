package ia;

import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;

/* renamed from: ia.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0694a implements OnErrorListener, MediaScannerListener {
    public final /* synthetic */ HighlightEncoder d;

    public /* synthetic */ C0694a(HighlightEncoder highlightEncoder) {
        this.d = highlightEncoder;
    }

    public void onCompleted() {
        this.d.lambda$executeFileOperation$3();
    }

    public void onError(Exception exc) {
        this.d.onError(exc);
    }
}
