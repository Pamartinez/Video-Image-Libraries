package k7;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.dialog.FileOperationDialog;
import com.samsung.android.gallery.app.ui.dialog.GroupShotCheckBoxDialog;
import com.samsung.android.gallery.app.ui.dialog.RenameFileDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleConfirmProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialogPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.utils.AudioPermissionHelper;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.JumpToTimelineDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.KeyguardDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.UsbOtgStateDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPositionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview.DirectorsViewHandler;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ j(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((JumpToTimelineDelegate) obj2).onFragmentLifecycleResumed(obj, bundle);
                return;
            case 1:
                ((KeyguardDelegate) obj2).lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
            case 2:
                ((UsbOtgStateDelegate) obj2).onUsbStorageVolumeChanged(obj, bundle);
                return;
            case 3:
                ((FileOperationDialog) obj2).lambda$new$0(obj, bundle);
                return;
            case 4:
                ((GroupShotCheckBoxDialog) obj2).lambda$new$0(obj, bundle);
                return;
            case 5:
                ((RenameFileDialog) obj2).lambda$new$0(obj, bundle);
                return;
            case 6:
                ((SimpleConfirmProgressDialog) obj2).lambda$new$0(obj, bundle);
                return;
            case 7:
                ((ViewPositionDelegate) obj2).scrollToUndoDeletePos(obj, bundle);
                return;
            case 8:
                ((CreaturePickerDialogPresenter) obj2).onDismissDialog(obj, bundle);
                return;
            case 9:
                ((AudioPermissionHelper) obj2).lambda$new$0(obj, bundle);
                return;
            case 10:
                ((FaceClusterMergeDelegate) obj2).lambda$new$0(obj, bundle);
                return;
            default:
                ((DirectorsViewHandler) obj2).updateDirectorsUi(obj, bundle);
                return;
        }
    }
}
