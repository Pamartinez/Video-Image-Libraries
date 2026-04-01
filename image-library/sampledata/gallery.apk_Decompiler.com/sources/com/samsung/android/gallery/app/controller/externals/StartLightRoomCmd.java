package com.samsung.android.gallery.app.controller.externals;

import M3.c;
import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartLightRoomCmd extends BaseCommand {
    private Intent getAddLibraryLRIntent(MediaItem mediaItem) {
        Intent intent = new Intent();
        intent.setClassName("com.adobe.lrmobile", "com.adobe.lrmobile.lrimport.openewithlrimport.AddToLrActivity");
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProviderUtil.getUri(getContext(), mediaItem.getPath()));
        intent.setType("image/*");
        intent.addFlags(3);
        return intent;
    }

    private Intent getEditModeLRIntent(MediaItem mediaItem) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.EDIT");
        intent.setType("image/*");
        for (ResolveInfo next : getContext().getPackageManager().queryIntentActivities(intent, 65536)) {
            if (TextUtils.equals(next.activityInfo.packageName, "com.adobe.lrmobile")) {
                ActivityInfo activityInfo = next.activityInfo;
                intent.setClassName(activityInfo.packageName, activityInfo.name);
                intent.setData(FileProviderUtil.getUri(getContext(), mediaItem.getPath()));
                intent.addFlags(3);
                return intent;
            }
        }
        return null;
    }

    private void startActivity(Intent intent, int i2) {
        try {
            Activity activity = getActivity();
            if (i2 > 0) {
                activity.startActivityForResult(intent, i2);
            } else {
                activity.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("start activity failed e="), this.TAG);
        }
    }

    private void startGalaxyStore() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("samsungapps://ProductDetail/com.adobe.lrmobile"));
        intent.putExtra("type", "cover");
        intent.addFlags(335544352);
        startActivity(intent, -1);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        boolean booleanValue = objArr[1].booleanValue();
        if (mediaItem == null) {
            Log.e(this.TAG, "can not launch lightroom. item is null");
        } else if (mediaItem.isCloudOnly()) {
            executeAfterDownload(eventContext, mediaItem, DownloadType.EDIT, new c(eventContext, 1));
        } else {
            postAnalyticsLog(AnalyticsEventId.EVENT_OPEN_LIGHTROOM);
            Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage("com.adobe.lrmobile");
            if (launchIntentForPackage == null) {
                Log.d(this.TAG, "start samsung store for install LR");
                startGalaxyStore();
            } else if (booleanValue) {
                Log.d(this.TAG, "start LR without action");
                startActivity(launchIntentForPackage, -1);
            } else {
                Intent editModeLRIntent = getEditModeLRIntent(mediaItem);
                if (editModeLRIntent == null) {
                    Log.d(this.TAG, "start LR - add library");
                    startActivity(getAddLibraryLRIntent(mediaItem), 796);
                    return;
                }
                Log.d(this.TAG, "start LR - launch edit mode");
                startActivity(editModeLRIntent, -1);
            }
        }
    }
}
