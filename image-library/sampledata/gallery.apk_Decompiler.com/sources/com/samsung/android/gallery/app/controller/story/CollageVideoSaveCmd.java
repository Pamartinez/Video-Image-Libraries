package com.samsung.android.gallery.app.controller.story;

import Ob.a;
import T3.e;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.EncodeEventListener;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.Mode;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageVideoSaveCmd extends BaseCommand {
    ProgressDialogCompat dialogCompat;

    private EncodeInfo createEncodeInfo(ExportOptions exportOptions) {
        String str;
        BgmExtraInfo bgmExtraInfo = exportOptions.getBgmExtraInfo();
        EncodeInfo duration = new EncodeInfo().setTitle("").setSubtitle("").setDuration(exportOptions.getPlayTimeMs());
        if (bgmExtraInfo.isMyMusic) {
            str = bgmExtraInfo.path;
        } else {
            str = bgmExtraInfo.bgmName;
        }
        return duration.setBgmName(str).setFilterName(exportOptions.getFilterName()).setKenBurnMap(exportOptions.getKenBurnsMap()).setOutSize(new int[]{exportOptions.getTargetWidth(), exportOptions.getTargetHeight()}).setOutFilePath(StoryHelper.createSaveFilePath(StorageInfo.getDefault().stories)).setStoryId(exportOptions.getStoryId()).setMode(Mode.COLLAGE).setBackgroundBitmap(exportOptions.getBackgroundBitmap()).setForegroundBitmap(exportOptions.getForegroundBitmap()).setDisplayPositionRect(exportOptions.getDisplayPositionRect()).setCornerRadius(exportOptions.getCornerRadius());
    }

    /* access modifiers changed from: private */
    public void handleComplete(HighlightEncoder highlightEncoder, boolean z, String str) {
        Log.d(this.TAG, "encode done", Boolean.valueOf(z), highlightEncoder);
        try {
            Optional.ofNullable(this.dialogCompat).ifPresent(new e(8));
        } catch (Exception unused) {
        }
        Blackboard.getApplicationInstance().erase("data://running_story_highlight_encoding");
        if (z) {
            showSuccessToast(str);
            return;
        }
        showFailToast();
        SimpleThreadPool instance = SimpleThreadPool.getInstance();
        Objects.requireNonNull(highlightEncoder);
        instance.execute(new Qb.e(22, highlightEncoder));
    }

    private boolean isServiceRunning() {
        return !Blackboard.getApplicationInstance().isEmpty("data://running_story_highlight_encoding");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        Utils.showToast(getContext(), (int) R.string.wait_for_other_video_finish_saved);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSaveVideo$2(HighlightEncoder highlightEncoder) {
        handleComplete(highlightEncoder, false, "");
    }

    private void prepareStart() {
        Blackboard.getApplicationInstance().publish("data://running_story_highlight_encoding", Boolean.TRUE);
    }

    private static void showFailToast() {
        Utils.showToast(AppResources.getAppContext(), (int) R.string.video_save_fail);
    }

    private static void showSuccessToast(String str) {
        Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.collage_saved_in, BucketUtils.translatePath(str, false)));
    }

    /* access modifiers changed from: private */
    /* renamed from: startSaveVideo */
    public void lambda$onExecute$1(ExportOptions exportOptions) {
        prepareStart();
        this.dialogCompat = new ProgressDialogCompat(getContext()).setCancelable(false).setCanceledOnTouchOutside(false).build().show();
        final HighlightEncoder highlightEncoder = new HighlightEncoder(createEncodeInfo(exportOptions));
        Log.d(this.TAG, "create encoder", highlightEncoder);
        highlightEncoder.setListener(new EncodeEventListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onCompleted$0(HighlightEncoder highlightEncoder, boolean z) {
                CollageVideoSaveCmd.this.handleComplete(highlightEncoder, z, highlightEncoder.getResultPath());
            }

            public void onCompleted(boolean z) {
                ThreadUtil.runOnUiThread(new a(this, highlightEncoder, z));
            }

            public void onStarted() {
                Log.d(CollageVideoSaveCmd.this.TAG, "encode started");
            }

            public void onProgressChanged(float f) {
            }
        });
        try {
            highlightEncoder.encode();
        } catch (Exception e) {
            e.printStackTrace();
            ThreadUtil.runOnUiThread(new a(22, this, highlightEncoder));
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_COLLAGE_DOWNLOAD.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ExportOptions exportOptions;
        if (!isLowStorage()) {
            if (isServiceRunning()) {
                Log.w(this.TAG, "service is running");
                ThreadUtil.runOnUiThread(new Qb.e(23, this));
                return;
            }
            if (objArr == null || objArr.length < 1) {
                exportOptions = null;
            } else {
                exportOptions = objArr[0];
            }
            if (exportOptions != null) {
                ThreadUtil.runOnUiThread(new a(21, this, exportOptions));
                String str = this.TAG;
                Log.d(str, "op=" + exportOptions);
                return;
            }
            Log.e(this.TAG, "fail to save due to no export options");
        }
    }
}
