package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.HighlightMediaViewPlayerHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2346a;
    public final /* synthetic */ HighlightMediaViewPlayerHandler b;

    public /* synthetic */ g(HighlightMediaViewPlayerHandler highlightMediaViewPlayerHandler, int i2) {
        this.f2346a = i2;
        this.b = highlightMediaViewPlayerHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2346a;
        HighlightMediaViewPlayerHandler highlightMediaViewPlayerHandler = this.b;
        switch (i2) {
            case 0:
                highlightMediaViewPlayerHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                highlightMediaViewPlayerHandler.onHighlightFrcViewClicked(objArr);
                return;
            default:
                highlightMediaViewPlayerHandler.handleSaveHighlightFrc(objArr);
                return;
        }
    }
}
