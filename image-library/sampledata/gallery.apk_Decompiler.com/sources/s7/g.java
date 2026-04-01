package S7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2430a;
    public final /* synthetic */ RemasterViewerHolder b;

    public /* synthetic */ g(RemasterViewerHolder remasterViewerHolder, int i2) {
        this.f2430a = i2;
        this.b = remasterViewerHolder;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2430a;
        RemasterViewerHolder remasterViewerHolder = this.b;
        switch (i2) {
            case 0:
                remasterViewerHolder.onPreviewLoaded(objArr);
                return;
            case 1:
                remasterViewerHolder.onBitmapLoaded(objArr);
                return;
            case 2:
                remasterViewerHolder.updateSize(objArr);
                return;
            case 3:
                remasterViewerHolder.unblockMotionControl(objArr);
                return;
            case 4:
                remasterViewerHolder.resetScale(objArr);
                return;
            case 5:
                remasterViewerHolder.onUpdateBitmapPair(objArr);
                return;
            case 6:
                remasterViewerHolder.onUpdateScaleRelative(objArr);
                return;
            case 7:
                remasterViewerHolder.zoomWithRect(objArr);
                return;
            default:
                remasterViewerHolder.onReadyRemasterView(objArr);
                return;
        }
    }
}
