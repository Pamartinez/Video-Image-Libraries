package com.samsung.android.gallery.app.controller.externals;

import A.a;
import M3.c;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UrlLookup;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartMeituCmd extends BaseCommand {
    private static final String MT_ACTION = UrlLookup.getData("meitu.action");
    private static final String MT_PACKAGE_NAME = UrlLookup.getData("meitu");

    private void startStoreForMeitu(Activity activity) {
        Intent a7 = C0280e.a("android.intent.action.VIEW", "com.sec.android.app.samsungapps");
        a7.setData(Uri.parse("samsungapps://ProductDetail/" + MT_PACKAGE_NAME));
        String str = this.TAG;
        Log.d(str, "start store for install " + a7);
        activity.startActivity(a7);
    }

    private boolean supportExpression() {
        if (!PreferenceFeatures.OneUi7x.IS_ONE_UI_70 || PackageMonitorCompat.getInstance().getPackageVersion(MT_PACKAGE_NAME) < 101600) {
            return false;
        }
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        StringBuilder sb2;
        String str2;
        String str3;
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "can not launch. item is null");
        } else if (mediaItem.isCloudOnly()) {
            executeAfterDownload(eventContext, mediaItem, DownloadType.EDIT, new c(eventContext, 2));
        } else {
            try {
                postAnalyticsLog(AnalyticsEventId.EVENT_OPEN_MEITU);
                Activity activity = eventContext.getActivity();
                PackageManager packageManager = activity.getPackageManager();
                String str4 = MT_PACKAGE_NAME;
                if (packageManager.getLaunchIntentForPackage(str4) == null) {
                    startStoreForMeitu(activity);
                    return;
                }
                boolean supportExpression = supportExpression();
                Uri uri = ContentUri.getUri(mediaItem);
                if (supportExpression) {
                    sb2 = new StringBuilder();
                    sb2.append(MT_ACTION);
                    str = ".EXTERNAL_SHARE";
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(MT_ACTION);
                    str = ".SAMSUNG_SHARE";
                }
                sb2.append(str);
                Intent intent = new Intent(sb2.toString());
                intent.setPackage(str4);
                intent.setDataAndType(uri, "image/*");
                if (uri != null) {
                    intent.putExtra("imageUri", uri.toString());
                }
                if (supportExpression) {
                    str2 = "ai_draw,expression_remold";
                } else {
                    str2 = "ai_draw";
                }
                intent.putExtra("function", str2);
                intent.putExtra("source", "Samsung");
                intent.addFlags(1);
                try {
                    activity.grantUriPermission(str4, uri, 1);
                } catch (Exception e) {
                    String str5 = this.TAG;
                    Log.e(str5, "grant uri permission failed. e=" + e.getMessage());
                }
                String str6 = this.TAG;
                StringBuilder sb3 = new StringBuilder("start activity ");
                if (supportExpression) {
                    str3 = "expression";
                } else {
                    str3 = "draw";
                }
                sb3.append(str3);
                sb3.append(Logger.toString(intent));
                Log.d(str6, sb3.toString());
                activity.startActivityForResult(intent, 0);
            } catch (ActivityNotFoundException unused) {
                Log.d(this.TAG, "couldn't find activity -> start store to download/update");
                startStoreForMeitu(eventContext.getActivity());
            } catch (Exception e7) {
                a.s(e7, new StringBuilder("start failed. e="), this.TAG);
            }
        }
    }
}
