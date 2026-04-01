package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2756a;
    public final /* synthetic */ TextExtractionHandler b;

    public /* synthetic */ b(TextExtractionHandler textExtractionHandler, int i2) {
        this.f2756a = i2;
        this.b = textExtractionHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2756a;
        TextExtractionHandler textExtractionHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionHandler.onBottomSheetStateChanged(objArr);
                return;
            case 1:
                textExtractionHandler.onToggleOsd(objArr);
                return;
            case 2:
                textExtractionHandler.disableTextExtractionView(objArr);
                return;
            case 3:
                textExtractionHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 4:
                textExtractionHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 5:
                textExtractionHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            case 6:
                textExtractionHandler.onWindowFocusChanged(objArr);
                return;
            case 7:
                textExtractionHandler.onUpdateDecorVisibility(objArr);
                return;
            default:
                textExtractionHandler.onConsumeEvent(objArr);
                return;
        }
    }
}
