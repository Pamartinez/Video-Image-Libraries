package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAlwaysOnDisplayCmd extends BaseCommand {
    public String getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SET_AS_ALWAYS_ON_DISPLAY.toString();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        if (SeApiCompat.isMobileKeyboardCovered(context)) {
            showToast(context.getString(R.string.remove_keyboard_cover_to_set_as_always_on_display));
        } else if (isUpsm()) {
            showToast(context.getString(R.string.cannot_use_this_function_while_mpsm_is_on));
        } else {
            MediaItem mediaItem = objArr[0];
            Uri uri = ContentUri.getUri(mediaItem);
            if (uri == null) {
                Log.e(this.TAG, "item or uri is null");
                return;
            }
            Intent intent = new Intent("com.samsung.android.app.aodservice.intent.action.SET_AS_AOD");
            intent.setComponent(new ComponentName("com.samsung.android.app.aodservice", "com.samsung.android.app.aodservice.settings.opreditor.ImageOprEditActivity"));
            if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
                intent.setDataAndType(uri, mediaItem.getMimeType());
                intent.putExtra("filePath", uri.toString());
            } else {
                Uri streamUri = ContentUri.getStreamUri(getContext(), mediaItem);
                intent.setDataAndType(streamUri, mediaItem.getMimeType());
                intent.putExtra("filePath", streamUri.toString());
            }
            intent.addFlags(1);
            intent.putExtra("callerApp", "Gallery");
            setIntentWithCommonExtra(intent);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                String str = this.TAG;
                Log.d(str, " FlashAnnotateActivity ActivityNotFoundException" + e);
            } catch (SecurityException e7) {
                String str2 = this.TAG;
                Log.rme(str2, " failed e=" + e7.getMessage());
            }
        }
    }
}
