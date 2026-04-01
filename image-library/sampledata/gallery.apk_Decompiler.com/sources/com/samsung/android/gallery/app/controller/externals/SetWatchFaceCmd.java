package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
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
public class SetWatchFaceCmd extends BaseCommand {
    public String getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SET_AS_WATCH_FACE.toString();
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
            Intent intent = new Intent("com.samsung.android.set.image.myphoto");
            if (PocFeatures.DUAL_PHOTO_PREVIEW && mediaItem.isStream()) {
                uri = ContentUri.getStreamUri(getContext(), mediaItem);
            }
            intent.setClipData(ClipData.newRawUri((CharSequence) null, uri));
            intent.setDataAndType(uri, mediaItem.getMimeType());
            intent.addFlags(335544321);
            getContext().startActivity(Intent.createChooser(intent, "Select a watch"));
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("set as watch face activity not found"), this.TAG);
        }
    }
}
