package S7;

import android.view.MotionEvent;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnZoomDetectListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements OnZoomDetectListener, OnFlingListener, ViewerContentLayout.InterceptTouchEventListener {
    public final /* synthetic */ RemasterViewerHolder d;

    public /* synthetic */ j(RemasterViewerHolder remasterViewerHolder) {
        this.d = remasterViewerHolder;
    }

    public void isZoomDetected(boolean z) {
        this.d.onZoomDetected(z);
    }

    public void onFling(boolean z) {
        this.d.lambda$onInitialized$1(z);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d.onContentViewTouch(motionEvent);
    }
}
