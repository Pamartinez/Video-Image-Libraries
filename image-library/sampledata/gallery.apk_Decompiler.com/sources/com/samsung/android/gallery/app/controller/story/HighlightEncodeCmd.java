package com.samsung.android.gallery.app.controller.story;

import Qb.e;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightEncodeCmd extends BaseCommand {
    private boolean isServiceRunning() {
        return !Blackboard.getApplicationInstance().isEmpty("data://running_story_highlight_encoding");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        Utils.showToast(getContext(), (int) R.string.wait_for_other_video_finish_saved);
    }

    private void startService(ExportOptions exportOptions) {
        String str;
        getBlackboard().publish("data://user/highlight_encode_ken_burns", exportOptions.getKenBurnsMap());
        Intent intent = new Intent();
        BgmExtraInfo bgmExtraInfo = exportOptions.getBgmExtraInfo();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.HighlightEncodeService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("key-duration", exportOptions.getPlayTimeMs());
        intent.putExtra("key-title", exportOptions.getTitle());
        intent.putExtra("key-subtitle", exportOptions.getSubTitle());
        if (bgmExtraInfo.isMyMusic) {
            str = bgmExtraInfo.path;
        } else {
            str = bgmExtraInfo.bgmName;
        }
        intent.putExtra("key-bgm", str);
        intent.putExtra("key-effect-filter-name", exportOptions.getFilterName());
        intent.putExtra("key-request-code", exportOptions.getRequestCode());
        intent.putExtra("key-unique-key", exportOptions.getUniqueKey());
        intent.putExtra("output_file_path", exportOptions.getOutPath());
        intent.putExtra("story_id", exportOptions.getStoryId());
        intent.putExtra("key-export-width", exportOptions.getTargetWidth());
        intent.putExtra("key-export-height", exportOptions.getTargetHeight());
        getContext().startService(intent);
    }

    public String getEventId() {
        return super.getEventId();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ExportOptions exportOptions;
        if (!isLowStorage()) {
            if (isServiceRunning()) {
                Log.w(this.TAG, "service is running");
                ThreadUtil.runOnUiThread(new e(25, this));
                return;
            }
            if (objArr == null || objArr.length < 1) {
                exportOptions = null;
            } else {
                exportOptions = objArr[0];
            }
            if (exportOptions != null) {
                startService(exportOptions);
                String str = this.TAG;
                Log.d(str, "op=" + exportOptions);
                return;
            }
            Log.e(this.TAG, "fail to save due to no export options");
        }
    }
}
