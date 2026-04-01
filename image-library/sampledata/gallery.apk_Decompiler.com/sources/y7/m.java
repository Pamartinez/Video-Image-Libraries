package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionVideoHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2762a;
    public final /* synthetic */ TextExtractionVideoHandler b;

    public /* synthetic */ m(TextExtractionVideoHandler textExtractionVideoHandler, int i2) {
        this.f2762a = i2;
        this.b = textExtractionVideoHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2762a;
        TextExtractionVideoHandler textExtractionVideoHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionVideoHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                textExtractionVideoHandler.onRequestVideoSeek(objArr);
                return;
            case 2:
                textExtractionVideoHandler.onRequestVideoSeekFinish(objArr);
                return;
            case 3:
                textExtractionVideoHandler.onVideoStarted(objArr);
                return;
            case 4:
                textExtractionVideoHandler.onVideoStopped(objArr);
                return;
            case 5:
                textExtractionVideoHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                textExtractionVideoHandler.onRequestUpdateMargin(objArr);
                return;
        }
    }
}
