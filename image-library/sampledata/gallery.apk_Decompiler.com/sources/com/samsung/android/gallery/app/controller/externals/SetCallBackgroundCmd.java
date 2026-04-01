package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetCallBackgroundCmd extends BaseCommand {
    public String getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SET_AS_CALL_BACKGROUND.toString();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        Uri uri = ContentUri.getUri(mediaItem);
        if (uri == null) {
            Log.e(this.TAG, "item or uri is null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.app.telephonyui.action.OPEN_CALL_BACKGROUND_EXTERNAL_ACTIVITY");
            intent.setPackage("com.samsung.android.app.telephonyui");
            intent.putExtra("from", "gallery");
            if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
                intent.setDataAndType(uri, mediaItem.getMimeType());
            } else {
                intent.setDataAndType(ContentUri.getStreamUri(getContext(), mediaItem), mediaItem.getMimeType());
            }
            intent.addFlags(1);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e((CharSequence) this.TAG, "call background failed", (Throwable) e);
        }
    }
}
