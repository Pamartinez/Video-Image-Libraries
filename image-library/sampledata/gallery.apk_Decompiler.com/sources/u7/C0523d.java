package u7;

import android.view.MotionEvent;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.widget.OnSecondaryClickListener;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.abstraction.ViewerContentLayout;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnLongPressListener;
import com.samsung.android.gallery.widget.photoview.OnMatrixChangeListener;
import com.samsung.android.gallery.widget.photoview.OnZoomDetectListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;

/* renamed from: u7.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0523d implements OnZoomDetectListener, OnFlingListener, OnTranslationListener, ScaleEndListener, OnSecondaryClickListener, OnLongPressListener, OnMatrixChangeListener, ViewerContentLayout.InterceptTouchEventListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ContentViewerHolder e;

    public /* synthetic */ C0523d(ContentViewerHolder contentViewerHolder, int i2) {
        this.d = i2;
        this.e = contentViewerHolder;
    }

    public void a(MotionEvent motionEvent) {
        this.e.lambda$onInitialized$4(motionEvent);
    }

    public void b() {
        this.e.onVideoScaleEnd();
    }

    public boolean isTranslated() {
        int i2 = this.d;
        ContentViewerHolder contentViewerHolder = this.e;
        switch (i2) {
            case 2:
                return contentViewerHolder.lambda$onMediaPlayerViewStubRequest$0();
            default:
                return contentViewerHolder.lambda$onInitialized$5();
        }
    }

    public void isZoomDetected(boolean z) {
        this.e.onZoomDetected(z);
    }

    public void onFling(boolean z) {
        int i2 = this.d;
        ContentViewerHolder contentViewerHolder = this.e;
        switch (i2) {
            case 1:
                contentViewerHolder.lambda$onInitialized$3(z);
                return;
            default:
                contentViewerHolder.lambda$onMediaPlayerViewStubRequest$1(z);
                return;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.e.onContentViewTouch(motionEvent);
    }
}
