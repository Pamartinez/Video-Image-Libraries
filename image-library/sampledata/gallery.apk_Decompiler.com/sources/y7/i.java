package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionImageHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2759a;
    public final /* synthetic */ TextExtractionImageHandler b;

    public /* synthetic */ i(TextExtractionImageHandler textExtractionImageHandler, int i2) {
        this.f2759a = i2;
        this.b = textExtractionImageHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2759a;
        TextExtractionImageHandler textExtractionImageHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionImageHandler.onCurrentItemChanged(objArr);
                return;
            case 1:
                textExtractionImageHandler.onGIFAnimationMode(objArr);
                return;
            case 2:
                textExtractionImageHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                textExtractionImageHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
