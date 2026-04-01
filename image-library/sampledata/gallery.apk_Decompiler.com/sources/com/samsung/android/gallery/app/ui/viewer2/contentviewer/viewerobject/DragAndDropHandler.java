package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.OnViewLongPress;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import v7.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragAndDropHandler extends ViewerObject implements OnViewLongPress {
    private PhotoViewCompat mPhotoView;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public void onObjectCaptureDone(Object... objArr) {
        if (!objArr[0].booleanValue() && supportDragAndDrop(this.mModel.getMediaItem())) {
            new ViewerDragAndDrop().startDragAndDrop(this.mModel.getContext(), this.mPhotoView, this.mModel.getBitmap(), this.mModel.getMediaItem());
            postAnalyticsDetailLogPressLog();
        }
    }

    private void postAnalyticsDetailLogPressLog() {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_LONG_PRESS_CAPTURE.toString(), AnalyticsDetail.EVENT_DETAIL_FULL_LONG_PRESS_TYPE_DRAG.toString());
        }
    }

    private boolean supportDragAndDrop(MediaItem mediaItem) {
        if (!SystemUi.isInMultiWindowMode(this.mModel.getActivity()) || mediaItem == null || mediaItem.isMtp() || mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isPrivateItem() || this.mModel.isTextExtractionFullState()) {
            return false;
        }
        return true;
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new k(this, 0));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_DONE, new k(this, 1));
    }

    public void onInitialized() {
        this.mActionInvoker.invoke(ViewerAction.REG_VIEW_LONG_PRESS_LISTENER, this, 0);
    }

    public boolean onViewLongPress(float f, float f5) {
        if (!supportDragAndDrop(this.mModel.getMediaItem())) {
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE, Float.valueOf(f), Float.valueOf(f5), null, null);
        return true;
    }
}
