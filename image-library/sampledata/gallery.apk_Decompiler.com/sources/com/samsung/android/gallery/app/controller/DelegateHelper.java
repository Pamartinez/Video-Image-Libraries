package com.samsung.android.gallery.app.controller;

import A4.H;
import D9.a;
import F3.b;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DelegateHelper {
    public static Intent buildShareIntent(ArrayList<Uri> arrayList, int i2, int i7) {
        String str;
        Intent intent = new Intent();
        if (arrayList.size() == 1) {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", arrayList.get(0));
        } else {
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        }
        if (i2 > 0 && i7 > 0) {
            str = "*/*";
        } else if (i7 > 0) {
            str = "video/*";
        } else {
            str = "image/*";
        }
        intent.setType(str);
        return intent;
    }

    public static void checkNetworkStatus(EventContext eventContext, boolean z, Consumer<Boolean> consumer) {
        Context applicationContext = eventContext.getApplicationContext();
        if (!NetworkUtils.isNetworkConnected(applicationContext)) {
            Log.d("DelegateHelper", "checkNetworkStatus : no network");
            ThreadUtil.postOnUiThread(new b(applicationContext, applicationContext.getString(R.string.could_not_connect_to_cloud, new Object[]{StringResources.getCloudBrand()}), 0));
        } else if (!z || NetworkUtils.isWifiConnected(applicationContext) || isNetworkUsageWarning()) {
            Log.d("DelegateHelper", "checkNetworkStatus : accepted");
            consumer.accept(Boolean.TRUE);
        } else {
            Log.d("DelegateHelper", "checkNetworkStatus : confirm required");
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", applicationContext.getString(R.string.connect_via_mobile_network)).appendArg("description", applicationContext.getString(R.string.wifi_is_turned_off_description)).appendArg("option1", applicationContext.getString(R.string.ok)).appendArg("screenId", eventContext.getScreenId()).appendArg("option1EventId", AnalyticsEventId.EVENT_DETAIL_VIEW_POPUP_CONNECT_USING_MOBILE_DATA_OK.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_DETAIL_VIEW_POPUP_CONNECT_USING_MOBILE_DATA_CANCEL.toString()).build()).setOnDataCompleteListener(new a(6, consumer)).start();
        }
    }

    private static boolean isNetworkUsageWarning() {
        return PreferenceCache.NetworkUsageWarning.getBoolean();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$checkNetworkStatus$2(Consumer consumer, EventContext eventContext, ArrayList arrayList) {
        if (arrayList == null || ((Integer) arrayList.get(0)).intValue() != 1) {
            consumer.accept(Boolean.FALSE);
            return;
        }
        PreferenceCache.NetworkUsageWarning.setBoolean(true);
        consumer.accept(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startAppInfo$0(String str, Context context, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            try {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(268435456);
                intent.setData(Uri.fromParts("package", str, (String) null));
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Utils.showToast(context, (int) R.string.can_not_open_file_type_not_support);
            }
        }
    }

    public static void startAppInfo(EventContext eventContext, String str, String str2) {
        Context context = eventContext.getContext();
        if (context == null) {
            Log.e("DelegateHelper", "startAppInfo failed. null context");
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/StartAppInfo").appendArg("appName", str).appendArg(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, str2).build()).setOnDataCompleteListener(new H(8, (Object) context, str2)).start();
    }
}
