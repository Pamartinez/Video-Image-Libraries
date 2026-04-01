package G7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2331a;
    public final /* synthetic */ ShotModeHandler b;

    public /* synthetic */ b(ShotModeHandler shotModeHandler, int i2) {
        this.f2331a = i2;
        this.b = shotModeHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2331a;
        ShotModeHandler shotModeHandler = this.b;
        switch (i2) {
            case 0:
                shotModeHandler.lambda$addActionInvokeListener$3(objArr);
                return;
            case 1:
                shotModeHandler.lambda$addActionInvokeListener$6(objArr);
                return;
            case 2:
                shotModeHandler.lambda$addActionInvokeListener$7(objArr);
                return;
            case 3:
                shotModeHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 4:
                shotModeHandler.restartAudio(objArr);
                return;
            case 5:
                shotModeHandler.onBottomSheetStateChanged(objArr);
                return;
            case 6:
                shotModeHandler.onExecuteCurrentShotMode(objArr);
                return;
            case 7:
                shotModeHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 8:
                shotModeHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                shotModeHandler.onMotionPlayModeChanged(objArr);
                return;
        }
    }
}
