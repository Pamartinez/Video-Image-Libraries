package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ContentDescriptionHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2721a;
    public final /* synthetic */ ContentDescriptionHandler b;

    public /* synthetic */ e(ContentDescriptionHandler contentDescriptionHandler, int i2) {
        this.f2721a = i2;
        this.b = contentDescriptionHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2721a;
        ContentDescriptionHandler contentDescriptionHandler = this.b;
        switch (i2) {
            case 0:
                contentDescriptionHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                contentDescriptionHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                contentDescriptionHandler.onOverlayStateChanged(objArr);
                return;
        }
    }
}
