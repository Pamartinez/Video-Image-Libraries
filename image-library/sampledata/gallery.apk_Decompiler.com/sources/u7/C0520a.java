package u7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.HistoryRecorder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MediaItemCorrector;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.PppBmpCacheHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.MotionPhotoCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.DummyObjectCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview.DirectorsViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: u7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0520a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2709a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0520a(int i2, Object obj) {
        this.f2709a = i2;
        this.b = obj;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2709a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((AbsViewerHolder) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                ((DualShotOptionsViewHandler) obj).onViewReady(objArr);
                return;
            case 2:
                ((HistoryRecorder) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 3:
                ((MediaItemCorrector) obj).onPreviewLoad(objArr);
                return;
            case 4:
                ((PppBmpCacheHandler) obj).onBitmapLoaded(objArr);
                return;
            case 5:
                ((MotionPhotoCaptureHandler) obj).onMotionPlayViewerChanged(objArr);
                return;
            case 6:
                ((DummyObjectCaptureHandler) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                ((DirectorsViewHandler) obj).lambda$addActionInvokeListener$0(objArr);
                return;
        }
    }
}
