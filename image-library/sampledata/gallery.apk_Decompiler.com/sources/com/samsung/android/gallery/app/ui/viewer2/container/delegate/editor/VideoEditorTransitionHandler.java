package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import a6.g;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEditorTransitionHandler extends AbsEditorTransitionHandler {
    private final VideoEditorReceiveHandler mVideoEditorReceiver = new VideoEditorReceiveHandler(this.mInfo);

    public VideoEditorTransitionHandler(IVuContainerView iVuContainerView, ActionInvoker actionInvoker) {
        super(iVuContainerView, actionInvoker);
    }

    private Bundle getAppExtras() {
        return this.mVideoEditorReceiver.getAppExtras();
    }

    /* access modifiers changed from: private */
    public void handleItemEditedEvent() {
        log(AbsEditorTransitionHandler.Index.handleItemEditedEvent, "");
        VideoEditorReceiveHandler videoEditorReceiveHandler = this.mVideoEditorReceiver;
        if (videoEditorReceiveHandler == null || !videoEditorReceiveHandler.isVideoEditingSavedAction()) {
            reenterFromVideoEditingApp();
            return;
        }
        MediaItem currentItem = getCurrentItem();
        if (currentItem == null || !currentItem.isSharing()) {
            waitNewMediaItemLoaded(getEditedItemUri(), (String) null);
        }
    }

    private boolean isCanceledAction() {
        return this.mVideoEditorReceiver.isVideoEditingCanceledAction();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reenterFromVideoEditingApp$0() {
        this.mContainer.getBlackboard().postEvent(EventMessage.obtain(GroupAuthority.REASON_NOT_ACCEPTED_THE_INVITATION_YET));
    }

    /* access modifiers changed from: private */
    public void reenterFromVideoEditingApp() {
        if (AbsEditorTransitionHandler.SUPPORT_TRANSITION) {
            log(AbsEditorTransitionHandler.Index.reenterFromVE, "");
            if (isCanceledAction() || hasEditedItemUri()) {
                this.mActionInvoker.invoke(ViewerAction.REENTER_FROM_VIDEO_EDITOR, getAppExtras(), Boolean.valueOf(hasEditedItemUri()));
                ThreadUtil.postOnUiThreadDelayed(new c(this, 1), 500);
                return;
            }
            Log.d(this.TAG, "failed to call onReenterFromVideoEditingApp", Boolean.valueOf(isCanceledAction()), Boolean.valueOf(hasEditedItemUri()));
        }
    }

    public void log(AbsEditorTransitionHandler.Index index, String str) {
        String str2 = this.TAG;
        Log.at(str2, "v#" + index.id + " " + index.name() + " " + str);
    }

    public void onHandleEvent(EventMessage eventMessage) {
        super.onHandleEvent(eventMessage);
        if (eventMessage.what == 10003) {
            String str = this.TAG;
            Log.at(str, "onHandleEvent" + Logger.v(Integer.valueOf(eventMessage.what), "EVENT_APP_TRANSITION_VIDEO_EDITOR_SERIES"));
            this.mVideoEditorReceiver.parseVideoEditingAppIntent((Intent) eventMessage.obj);
            handleItemEditedEvent();
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageInvalidate(i2, viewerObjectComposite);
        ThreadUtil.postOnUiThread(new c(this, 0));
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageSelected(i2, viewerObjectComposite);
        ThreadUtil.postOnUiThread(new c(this, 0));
    }

    public void onReenterTransitionEnd() {
        super.onReenterTransitionEnd();
        this.mActionInvoker.invoke(ViewerAction.VIDEO_EDITOR_REENTER_TRANSITION_END, Boolean.valueOf(hasEditedItemUri()));
    }

    public void prepareEditor(MediaItem mediaItem) {
        super.prepareEditor(mediaItem);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_VIDEO_EDITOR_TRANSITION, new Object[0]);
        this.mVideoEditorReceiver.registerReceiver(this.mContainer.getContext(), new g(19, this));
    }

    public void prepareReturnTransition(long j2, MediaItem mediaItem, MediaItem mediaItem2) {
        super.lambda$waitNewMediaItemLoaded$3(j2, mediaItem, mediaItem2);
        ((ContainerModel) this.mContainer.getModel()).setReservedPosition(j2);
        if (!MediaItemUtil.equalsBitmap(mediaItem, mediaItem2)) {
            Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem2, ThumbKind.MEDIUM_KIND);
            String str = this.TAG;
            Log.at(str, "preload thumb " + mediaItem2.getFileId() + Logger.toString(loadThumbnailSync));
        }
        reloadData();
    }

    public void unRegisterBroadcastReceiver() {
        this.mVideoEditorReceiver.clear(this.mContainer.getContext());
    }
}
