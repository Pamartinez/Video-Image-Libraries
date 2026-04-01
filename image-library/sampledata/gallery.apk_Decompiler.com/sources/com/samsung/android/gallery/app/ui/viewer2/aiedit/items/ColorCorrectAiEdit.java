package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartVideoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ColorCorrectAiEdit extends AiEditItem {
    private final boolean SUPPORT_DIRECT_LAUNCH_EDITOR = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;

    public ColorCorrectAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.ColorCorrect);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        if (mediaItem != null) {
            MediaItemUtil.setColorCorrectAutoSave(mediaItem, z);
            if (z) {
                this.mEventContext.getBlackboard().post("data://bixby_command_done", new Object[]{"no_error"});
            }
            if (this.SUPPORT_DIRECT_LAUNCH_EDITOR) {
                this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
                new StartVideoEditorCmd().addConfig(2).execute(this.mEventContext, mediaItem, Boolean.FALSE);
            } else {
                new VuLauncher(this.mEventContext.getBlackboard()).publishData().setIsTemp().disableTimeline().forcePlayVideoInGallery(PreferenceFeatures.VideoPlayerFeature.SUPPORT_SETTING).launch("location://colorCorrectView", 0, mediaItem);
            }
            PreferenceCache.LogVideoTipCount.setInt(3);
            return;
        }
        Log.e(this.TAG, "execute failed. item is null");
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        if (this.SUPPORT_DIRECT_LAUNCH_EDITOR) {
            return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
        }
        return ExecuteTriggerType.COLLAPSE_ONLY_KEEP_DECOR;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        if (MediaItemUtil.isApv(mediaItem)) {
            return null;
        }
        if ((!mediaItem.is8K() || Features.isEnabled(Features.SUPPORT_COLOR_CORRECT_8K)) && !mediaItem.isCloudOnly() && mediaItem.isLogVideo()) {
            return List.of(this);
        }
        return null;
    }

    public boolean supportAutoSave() {
        return true;
    }
}
