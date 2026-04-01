package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaExtractor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.story.transcode.decoder.video.metadata.VideoMetaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProcessorFactory {
    public static Processor createDecodeProcessor(MediaExtractor mediaExtractor, VideoMetaData videoMetaData) {
        return new DecodeProcessor(mediaExtractor, videoMetaData);
    }

    public static Processor createFilterProcessor(String str, int i2) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter) && !TextUtils.isEmpty(str)) {
            FilterProcessor filterProcessor = new FilterProcessor(str, i2);
            if (filterProcessor.support()) {
                return filterProcessor;
            }
            Log.w("ProcessorFactory", "filter is unsupported");
        }
        return new NonFilterProcessor();
    }
}
