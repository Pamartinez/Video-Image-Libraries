package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.superslow.SuperSlowUtils;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuperSlowAiEdit extends AiEditItem {
    public SuperSlowAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.SuperSlowMo);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        if (mediaItem != null) {
            new VuLauncher(this.mEventContext.getBlackboard()).publishData().setIsTemp().disableTimeline().launch("location://superSlowViewList", 0, false, mediaItem);
        } else {
            Log.e(this.TAG, "execute failed. item is null");
        }
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_ONLY;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem.isCloudOnly() || !SuperSlowUtils.isSuperSlow(mediaItem)) {
            return null;
        }
        return List.of(this);
    }
}
