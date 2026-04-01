package com.samsung.android.gallery.app.ui.viewer2.container.delegate.share;

import B7.d;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.H;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import r7.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareSheetDelegate extends AbsVuDelegate<IVuContainerView> {
    public ShareSheetDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private boolean isQuickViewExceptFromCamera() {
        if (!((ContainerModel) this.mModel).getStateHelper().isQuickView() || LaunchIntent.isFromCamera(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    private boolean isScreenLocked() {
        if (!LaunchIntent.isFromLockScreen(this.mBlackboard) || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepareShareSheet$0() {
        this.mActionInvoker.invoke(ViewerAction.SHARE_SHEET_CLEARED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem lambda$onPrepareShareSheet$1(int i2, MediaItem mediaItem, MediaData mediaData, Integer num) {
        if (num.intValue() == i2) {
            return mediaItem;
        }
        return mediaData.read(num.intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepareShareSheet$2() {
        this.mActionInvoker.invoke(ViewerAction.SHARE_SHEET_CLEARED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onPrepareShareSheet(Object... objArr) {
        ViewerObjectComposite currentViewer;
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET && (currentViewer = ((IVuContainerView) this.mView).getCurrentViewer()) != null) {
            MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
            MediaItem mediaItem = currentViewer.getModel().getMediaItem();
            ContentModel model = currentViewer.getModel();
            if (isScreenLocked() || mediaItem == null || mediaItem.getFileId() <= 0 || ((ContainerModel) this.mModel).getStateHelper().isRevitalization()) {
                Log.w((CharSequence) this.TAG, "fail prepare shareSheet", model, mediaItem);
                return;
            }
            int position = model.getPosition();
            if (isQuickViewExceptFromCamera()) {
                ShareProvider.prepareExtendedShareList(((IVuContainerView) this.mView).getContext(), this.mBlackboard, new MediaItem[]{mediaItem}, new a(this, 0));
            } else {
                ShareProvider.prepareExtendedShareList(((IVuContainerView) this.mView).getContext(), this.mBlackboard, new H(position, mediaItem, mediaData), mediaData.getCount(), position, position, new a(this, 1));
            }
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.PREPARE_SHARE_SHEET, new d(27, this));
    }
}
