package X7;

import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowViewerHolder;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2468a;
    public final /* synthetic */ SlideshowViewerHolder b;

    public /* synthetic */ a(SlideshowViewerHolder slideshowViewerHolder, int i2) {
        this.f2468a = i2;
        this.b = slideshowViewerHolder;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2468a;
        SlideshowViewerHolder slideshowViewerHolder = this.b;
        switch (i2) {
            case 0:
                slideshowViewerHolder.onMediaViewStubRequest(objArr);
                return;
            case 1:
                slideshowViewerHolder.onPreviewLoaded(objArr);
                return;
            case 2:
                slideshowViewerHolder.onImageLoaded(objArr);
                return;
            default:
                slideshowViewerHolder.onLastPreviewData(objArr);
                return;
        }
    }
}
