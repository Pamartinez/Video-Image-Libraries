package G7;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.externals.StartRemoteServerCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionMotionPhotoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.menu.OpenInVideoPlayerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewPresenter;
import com.samsung.android.gallery.bixby.bixby.handler.AiEditActionHandler;
import com.samsung.android.gallery.bixby.bixby.handler.ShowSingleContentActionHandler;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements InstantSubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((ShotModeHandler) obj2).lambda$setDecorVisibilityWhenEnterVideoPlayer$21(obj, bundle);
                return;
            case 1:
                StartRemoteServerCmd.lambda$onExecute$6((AlertDialog) obj2, obj, bundle);
                return;
            case 2:
                ((OpenInVideoPlayerMenuItem) obj2).lambda$onMenuSelectInternal$3(obj, bundle);
                return;
            case 3:
                ((SelectionViewPresenter) obj2).lambda$handleEvent$0(obj, bundle);
                return;
            case 4:
                ((SaveCaptureCmd) obj2).onUpdateQuickCropMediaItem(obj, bundle);
                return;
            case 5:
                ((AiEditActionHandler) obj2).lambda$handleExecutable$0(obj, bundle);
                return;
            case 6:
                ((ShowSingleContentActionHandler) obj2).lambda$handleExecutable$0(obj, bundle);
                return;
            case 7:
                ((DlnaDelegate) obj2).lambda$launchVideoPlayer$5(obj, bundle);
                return;
            case 8:
                ((TextExtractionHandler) obj2).lambda$setState$14(obj, bundle);
                return;
            default:
                ((TextExtractionMotionPhotoHandler) obj2).lambda$onMotionPlayViewerChanged$6(obj, bundle);
                return;
        }
    }
}
