package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;
import com.samsung.android.gallery.widget.clip.ClipView;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ClipView.OnToggleConsumeListener, ObjectCaptureView.OnViewLongClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ObjectCaptureHandler f2745a;

    public /* synthetic */ c(ObjectCaptureHandler objectCaptureHandler) {
        this.f2745a = objectCaptureHandler;
    }

    public void onToggleConsumed() {
        this.f2745a.lambda$onViewReady$6();
    }
}
