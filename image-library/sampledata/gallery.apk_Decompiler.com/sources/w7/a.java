package W7;

import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenViewerHolder;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2465a;
    public final /* synthetic */ SingleTakenViewerHolder b;

    public /* synthetic */ a(SingleTakenViewerHolder singleTakenViewerHolder, int i2) {
        this.f2465a = i2;
        this.b = singleTakenViewerHolder;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2465a;
        SingleTakenViewerHolder singleTakenViewerHolder = this.b;
        switch (i2) {
            case 0:
                singleTakenViewerHolder.onPreviewLoaded(objArr);
                return;
            case 1:
                singleTakenViewerHolder.onSubItemsUpdated(objArr);
                return;
            case 2:
                singleTakenViewerHolder.onTransitionEnd(objArr);
                return;
            case 3:
                singleTakenViewerHolder.onImageLoaded(objArr);
                return;
            case 4:
                singleTakenViewerHolder.onVideoStarted(objArr);
                return;
            default:
                singleTakenViewerHolder.onBottomSheetSlide(objArr);
                return;
        }
    }
}
