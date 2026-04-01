package B7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverViewerHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2270a;
    public final /* synthetic */ FlipCoverViewerHandler b;

    public /* synthetic */ g(FlipCoverViewerHandler flipCoverViewerHandler, int i2) {
        this.f2270a = i2;
        this.b = flipCoverViewerHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2270a;
        FlipCoverViewerHandler flipCoverViewerHandler = this.b;
        switch (i2) {
            case 0:
                flipCoverViewerHandler.setPhotoView(objArr);
                return;
            default:
                flipCoverViewerHandler.setMediaView(objArr);
                return;
        }
    }
}
