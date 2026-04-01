package com.samsung.android.gallery.app.controller.externals;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ConversionAsyncTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareHeifWithConversionCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem == null) {
                Log.e(this.TAG, "Unable to operate. item is null");
                return;
            }
            new ConversionAsyncTask(eventContext, mediaItem, ConversionAsyncTask.ConvertType.HeifToJpeg).execute(new EventContext[]{eventContext});
        }
    }
}
