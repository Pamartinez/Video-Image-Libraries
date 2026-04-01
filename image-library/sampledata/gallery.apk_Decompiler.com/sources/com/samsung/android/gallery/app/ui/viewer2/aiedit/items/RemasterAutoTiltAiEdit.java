package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterAutoTiltAiEdit extends AbsRemasterAiEdit {
    public RemasterAutoTiltAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.AutoTilt);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
        Boolean bool = Boolean.FALSE;
        actionInvoker.invoke(viewerAction, bool);
        new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_genedit, null, bool, new ModeInfo(4, (Uri) null, z));
    }

    public String getEstimatorText() {
        return "AUTOTILT";
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isValidValue(String str) {
        try {
            if (new JSONObject(str).optDouble("score", MapUtil.INVALID_LOCATION) != MapUtil.INVALID_LOCATION) {
                return true;
            }
            return false;
        } catch (JSONException e) {
            String str2 = this.TAG;
            Log.e(str2, "parseAnalyzedTag failed. e=" + e.getMessage());
            return false;
        }
    }

    public boolean support(MediaItem mediaItem) {
        if (!supportImageSize(mediaItem) || !supportFormat(mediaItem)) {
            return false;
        }
        return true;
    }
}
