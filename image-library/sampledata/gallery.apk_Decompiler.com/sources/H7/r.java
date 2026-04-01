package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaPlayerAppsTransition;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2352a;
    public final /* synthetic */ MediaPlayerAppsTransition b;

    public /* synthetic */ r(MediaPlayerAppsTransition mediaPlayerAppsTransition, int i2) {
        this.f2352a = i2;
        this.b = mediaPlayerAppsTransition;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2352a;
        MediaPlayerAppsTransition mediaPlayerAppsTransition = this.b;
        switch (i2) {
            case 0:
                mediaPlayerAppsTransition.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                mediaPlayerAppsTransition.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                mediaPlayerAppsTransition.onPrepareVideoAppTransition(objArr);
                return;
            case 3:
                mediaPlayerAppsTransition.onPrepareVideoEditorTransition(objArr);
                return;
            case 4:
                mediaPlayerAppsTransition.onReenterFromVideoEditor(objArr);
                return;
            case 5:
                mediaPlayerAppsTransition.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                mediaPlayerAppsTransition.prepareLastFrame(objArr);
                return;
        }
    }
}
