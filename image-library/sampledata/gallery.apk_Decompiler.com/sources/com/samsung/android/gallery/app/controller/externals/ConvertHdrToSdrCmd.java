package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConvertHdrToSdrCmd extends BaseCommand {
    private String makeOutputFilePath(String str) {
        return new FileNameBuilder(str).setExtension(IFormat.FORMAT_MP4).buildUnique();
    }

    private void startConversionService(EventContext eventContext, MediaItem mediaItem) {
        getBlackboard().publish("data://user/selected", new MediaItem[]{mediaItem});
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.VideoConversionService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", eventContext.getLocationKey());
        intent.putExtra("usage_type", ConvertingUsageType.NONE.ordinal());
        intent.putExtra("output_file_path", makeOutputFilePath(mediaItem.getPath()));
        getContext().startService(intent);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_CONVERT_FROM_HDR_TO_SDR.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem == null) {
                Log.e(this.TAG, "Unable to operate. item is null");
                return;
            }
            try {
                startConversionService(eventContext, mediaItem);
            } catch (Exception unused) {
                Log.e(this.TAG, "error while start service");
            }
        }
    }
}
