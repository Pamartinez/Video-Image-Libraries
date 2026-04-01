package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.VideoMirroringUi;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2736a;
    public final /* synthetic */ VideoMirroringUi b;

    public /* synthetic */ v(VideoMirroringUi videoMirroringUi, int i2) {
        this.f2736a = i2;
        this.b = videoMirroringUi;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2736a;
        VideoMirroringUi videoMirroringUi = this.b;
        switch (i2) {
            case 0:
                videoMirroringUi.onVideoMirroringView(objArr);
                return;
            default:
                videoMirroringUi.updateVideoMirroringUi(objArr);
                return;
        }
    }
}
