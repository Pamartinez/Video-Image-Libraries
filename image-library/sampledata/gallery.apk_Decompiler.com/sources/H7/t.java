package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2353a;
    public final /* synthetic */ MediaViewPlayerHandler b;

    public /* synthetic */ t(MediaViewPlayerHandler mediaViewPlayerHandler, int i2) {
        this.f2353a = i2;
        this.b = mediaViewPlayerHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2353a;
        MediaViewPlayerHandler mediaViewPlayerHandler = this.b;
        switch (i2) {
            case 0:
                mediaViewPlayerHandler.onEnablePreviewPlay(objArr);
                return;
            case 1:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$7(objArr);
                return;
            case 2:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$8(objArr);
                return;
            case 3:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 4:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$9(objArr);
                return;
            case 5:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$10(objArr);
                return;
            case 6:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$11(objArr);
                return;
            case 7:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$12(objArr);
                return;
            case 8:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$13(objArr);
                return;
            case 9:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$14(objArr);
                return;
            case 10:
                mediaViewPlayerHandler.onPrepareStartGroupItemPanelView(objArr);
                return;
            case 11:
                mediaViewPlayerHandler.onReplacedComposite(objArr);
                return;
            case 12:
                mediaViewPlayerHandler.onVideoSpeedChanged(objArr);
                return;
            case 13:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$15(objArr);
                return;
            case 14:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            case 15:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$16(objArr);
                return;
            case 16:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$17(objArr);
                return;
            case 17:
                mediaViewPlayerHandler.onReturnTransitionEnd(objArr);
                return;
            case 18:
                mediaViewPlayerHandler.onRequestVideoSeekBegin(objArr);
                return;
            case 19:
                mediaViewPlayerHandler.onRequestVideoSeekFinish(objArr);
                return;
            case 20:
                mediaViewPlayerHandler.onRequestVideoSeek(objArr);
                return;
            case 21:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$3(objArr);
                return;
            case 22:
                mediaViewPlayerHandler.onPauseForMultiWindow(objArr);
                return;
            case 23:
                mediaViewPlayerHandler.onResumeForMultiWindow(objArr);
                return;
            case 24:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$4(objArr);
                return;
            case 25:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 26:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$5(objArr);
                return;
            case 27:
                mediaViewPlayerHandler.lambda$addActionInvokeListener$6(objArr);
                return;
            case 28:
                mediaViewPlayerHandler.onRequestTimeTickUpdate(objArr);
                return;
            default:
                mediaViewPlayerHandler.onReenterTransitionEnd(objArr);
                return;
        }
    }
}
