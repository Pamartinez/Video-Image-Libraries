package com.samsung.android.gallery.app.controller.externals;

import N2.j;
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
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetCoverScreenCmd extends BaseCommand {
    private void startWallPaper(ArrayList<Uri> arrayList) {
        try {
            Intent intent = new Intent(getIntentAction());
            intent.putExtra("selectedItems", arrayList);
            intent.setPackage("com.samsung.android.app.aodservice");
            intent.addFlags(1);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("set cover screen wallpaper activity not found"), this.TAG);
        }
    }

    public String getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SET_AS_COVER_SCREEN.toString();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER.toString();
    }

    public String getIntentAction() {
        return "com.samsung.android.app.aodservice.intent.action.SET_AS_COVERSCREEN";
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : objArr[0]) {
            if (arrayList.size() >= 100) {
                break;
            }
            Uri uri = ContentUri.getUri(mediaItem);
            if (uri == null) {
                Log.e(this.TAG, "item or uri is null");
                return;
            }
            if (PocFeatures.DUAL_PHOTO_PREVIEW && mediaItem.isStream()) {
                uri = ContentUri.getStreamUri(getContext(), mediaItem);
            }
            arrayList.add(uri);
        }
        startWallPaper(arrayList);
    }
}
