package com.samsung.android.gallery.app.controller.externals;

import L5.b;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartStoryCollageEditorCmd extends CreateCollageCmd {
    private Object[] mGradientInfo;
    private int mLayoutNumber;
    private ArrayList<String> mSmartCropRectList;
    private final int[] mVideoPlayback = new int[2];

    private void loadVideoPlayback(MediaItem[] mediaItemArr, int[] iArr) {
        for (MediaItem mediaItem : mediaItemArr) {
            if (StoryHelper.isVideoItem(mediaItem)) {
                iArr[0] = (int) (mediaItem.getVideoThumbStartTime() / 1000);
                iArr[1] = Math.min(mediaItem.getFileDuration(), iArr[0] + TextToSpeechConst.MAX_SPEECH_INPUT);
                return;
            }
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_COLLAGE_EDIT.toString();
    }

    public Intent getIntent(MediaItem[] mediaItemArr) {
        ArrayList<Uri> uriList = getUriList(mediaItemArr);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.samsung.app.newtrim", "com.samsung.app.newtrim.multigrid.main.MultiGridActivity");
        intent.putExtra("selectedItems", uriList);
        intent.putExtra("CALLER_APP", "gallery_stories");
        intent.putExtra("layout_number", this.mLayoutNumber);
        intent.putExtra("smart_crop_rect_list", this.mSmartCropRectList);
        int[] iArr = this.mVideoPlayback;
        if (iArr[1] > 0) {
            intent.putExtra("startTime", iArr[0]);
            intent.putExtra("endTime", this.mVideoPlayback[1]);
        }
        Object[] objArr = this.mGradientInfo;
        if (objArr != null) {
            intent.putExtra("gradientDirection", ((Integer) objArr[0]).intValue());
            intent.putExtra("gradientColor", (int[]) this.mGradientInfo[1]);
        }
        return intent;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mLayoutNumber = objArr[1].intValue();
        this.mSmartCropRectList = objArr[2];
        this.mGradientInfo = objArr[3];
        loadVideoPlayback(mediaItemArr, this.mVideoPlayback);
        createMedia(mediaItemArr);
        Log.d(this.TAG, "collage edit info", Integer.valueOf(this.mLayoutNumber), (List) Arrays.stream(mediaItemArr).map(new b(18)).collect(Collectors.toList()), this.mSmartCropRectList, Integer.valueOf(this.mVideoPlayback[0]), Integer.valueOf(this.mVideoPlayback[1]));
    }
}
