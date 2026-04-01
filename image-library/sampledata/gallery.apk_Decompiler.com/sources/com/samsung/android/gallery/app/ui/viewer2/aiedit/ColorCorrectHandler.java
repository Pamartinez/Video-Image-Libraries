package com.samsung.android.gallery.app.ui.viewer2.aiedit;

import B7.d;
import com.samsung.android.gallery.app.controller.externals.MediaCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.o3dp.lib3dphotography.i;
import e5.C0451a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ColorCorrectHandler extends ViewerObject implements FragmentLifeCycle {
    private void executed() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (isColorCorrectView()) {
            ThreadUtil.postOnUiThreadDelayed(new i(29, this, mediaItem), 500);
        }
    }

    private boolean isColorCorrectView() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!LocationKey.isColorCorrectView(this.mModel.getContainerModel().getLocationKey()) || mediaItem == null || !mediaItem.isLogVideo() || !MediaItemUtil.isColorCorrectAutoSave(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        executed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executed$2(MediaItem mediaItem) {
        Log.d("ColorCorrectHandler", "isLogVideo[" + mediaItem.isLogVideo() + "] autosave[" + MediaItemUtil.isColorCorrectAutoSave(mediaItem) + "]");
        new MediaCaptureCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem, ConvertingUsageType.NONE, MediaCaptureMode.NONE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$1() {
        this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new d(13, this));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1117) {
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (isColorCorrectView()) {
                MediaItemUtil.setColorCorrectAutoSave(mediaItem, false);
                Log.d("ColorCorrectHandler", "Media capture finished");
                ThreadUtil.postOnUiThreadDelayed(new C0451a(17, this), 500);
            }
        }
        return false;
    }
}
