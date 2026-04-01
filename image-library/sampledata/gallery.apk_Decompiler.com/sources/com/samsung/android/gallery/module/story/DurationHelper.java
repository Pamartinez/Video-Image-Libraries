package com.samsung.android.gallery.module.story;

import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DurationHelper {
    public static int getFixedDuration(ThumbnailInterface thumbnailInterface) {
        return 3000;
    }

    public static int getItemDuration(ThumbnailInterface thumbnailInterface) {
        return getItemDuration(thumbnailInterface, new int[]{MediaItemStory.isAiEditEffect(thumbnailInterface) ? 3000 : 2000, MediaItemStory.isLiveEffectItem(thumbnailInterface) ? 7000 : TextToSpeechConst.MAX_SPEECH_INPUT});
    }

    private static int getVideoDuration(ThumbnailInterface thumbnailInterface, int i2) {
        long videoThumbStartTime = thumbnailInterface.getVideoThumbStartTime() / 1000;
        int fileDuration = thumbnailInterface.getFileDuration();
        if (videoThumbStartTime > 0) {
            long j2 = (long) fileDuration;
            if (j2 > videoThumbStartTime) {
                fileDuration = (int) (Math.min(((long) i2) + videoThumbStartTime, j2) - videoThumbStartTime);
            }
        }
        return Math.max(1000, Math.min((int) (Math.floor(((double) fileDuration) / 1000.0d) * 1000.0d), i2));
    }

    public static int getItemDuration(ThumbnailInterface thumbnailInterface, int[] iArr) {
        if (StoryHelper.isVideoItem(thumbnailInterface)) {
            return getVideoDuration(thumbnailInterface, iArr[1]);
        }
        return iArr[0];
    }
}
