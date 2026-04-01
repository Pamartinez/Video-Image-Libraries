package com.samsung.android.gallery.app.controller.internals;

import B5.a;
import E5.b;
import M4.j;
import M9.o;
import android.content.Intent;
import android.widget.ImageView;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExportVideoClipsCmd extends BaseCommand {
    private int[] getExportSize(MediaItem mediaItem) {
        boolean z;
        int i2;
        int[] iArr = new int[2];
        if (mediaItem.getOrientation() % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        int i7 = 1080;
        if (z) {
            i2 = (mediaItem.getWidth() * 1080) / mediaItem.getHeight();
        } else {
            i2 = 1080;
        }
        iArr[0] = i2;
        if (!z) {
            i7 = (mediaItem.getWidth() * 1080) / mediaItem.getHeight();
        }
        iArr[1] = i7;
        iArr[0] = iArr[0] & -2;
        iArr[1] = i7 & -2;
        return iArr;
    }

    private List<MediaItem> getFilteredList(MediaItem[] mediaItemArr) {
        return (List) Arrays.stream(mediaItemArr).sorted(Comparator.comparingLong(new b(4))).filter(new j(15)).collect(Collectors.toList());
    }

    private String getSaveFilePath(List<MediaItem> list) {
        return FileUtils.getDirectoryFromPath(list.get(0).getPath(), false);
    }

    private boolean isServiceRunning() {
        return !Blackboard.getApplicationInstance().isEmpty("data://running_story_highlight_encoding");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFilteredList$2(MediaItem mediaItem) {
        if (!mediaItem.isMotionPhoto() || !mediaItem.isLocal()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        Utils.showToast(getContext(), (int) R.string.wait_for_other_video_finish_saved);
    }

    private void startEncodeService(List<MediaItem> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        list.forEach(new a(linkedHashMap, 1));
        getBlackboard().publish("data://user/highlight_encode_ken_burns", linkedHashMap);
        int[] exportSize = getExportSize(list.get(0));
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.HighlightEncodeService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("key-duration", -1);
        intent.putExtra("key-bgm", list.get(0).getPath());
        intent.putExtra("key-request-code", 2321);
        intent.putExtra("output_file_path", StoryHelper.createSaveFilePath(getSaveFilePath(list)));
        intent.putExtra("key-export-width", exportSize[0]);
        intent.putExtra("key-export-height", exportSize[1]);
        intent.putExtra("key-export-scale-type", ImageView.ScaleType.CENTER_CROP.name());
        getContext().startService(intent);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MOTION_PHOTO_MERGE_VIDEO_CLIP.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            if (isServiceRunning()) {
                Log.w(this.TAG, "service is running");
                ThreadUtil.runOnUiThread(new o(11, this));
                return;
            }
            getBlackboard().postEvent(EventMessage.obtain(1003));
            List<MediaItem> filteredList = getFilteredList(objArr[0]);
            if (!filteredList.isEmpty()) {
                startEncodeService(filteredList);
            } else {
                Log.e(this.TAG, "no motionPhoto");
            }
        }
    }
}
