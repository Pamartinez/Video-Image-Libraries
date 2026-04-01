package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2355a;
    public final /* synthetic */ MediaViewPlayerHandler b;

    public /* synthetic */ v(MediaViewPlayerHandler mediaViewPlayerHandler, int i2) {
        this.f2355a = i2;
        this.b = mediaViewPlayerHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2355a;
        MediaViewPlayerHandler mediaViewPlayerHandler = this.b;
        switch (i2) {
            case 0:
                mediaViewPlayerHandler.onBottomSheetChanged(objArr);
                return;
            default:
                mediaViewPlayerHandler.onBackKeyPrepare(objArr);
                return;
        }
    }
}
