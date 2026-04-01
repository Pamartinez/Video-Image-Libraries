package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSeekController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2340a;
    public final /* synthetic */ VideoSeekController b;

    public /* synthetic */ C(VideoSeekController videoSeekController, int i2) {
        this.f2340a = i2;
        this.b = videoSeekController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2340a;
        VideoSeekController videoSeekController = this.b;
        switch (i2) {
            case 0:
                videoSeekController.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                videoSeekController.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
