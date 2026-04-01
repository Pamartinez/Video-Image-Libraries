package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.scpm.GotoLink;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.spage.card.event.Event;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartBrowserCmd extends BaseCommand {
    private static final boolean SUPPORT_APP_LINK = PocFeatures.isEnabled(PocFeatures.GotoAppLink);
    private String mExecutedApp;

    public static boolean containsInAllowedSet(String str) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.IgnoreGoToSourceAllowList) || GotoLink.getInstance().contains(str)) {
            return true;
        }
        return false;
    }

    public static boolean supportAppLink(String str, String str2) {
        if (SUPPORT_APP_LINK) {
            return !TextUtils.isEmpty(str2);
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str == null || containsInAllowedSet(str)) {
            return true;
        }
        return false;
    }

    public String getAnalyticsDetail() {
        return this.mExecutedApp;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_GO_TO_URL.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "StartBrowse failed");
            return;
        }
        DetailsData of2 = DetailsData.of(mediaItem);
        String str = this.TAG;
        Log.d(str, "onExecute", Logger.getEncodedString(of2.capturedApp + ArcCommonLog.TAG_COMMA + of2.capturedUrl));
        boolean startBrowser = startBrowser(getContext(), of2.capturedUrl, of2.capturedApp);
        if (!startBrowser) {
            startBrowser = startBrowser(getContext(), of2.capturedUrl, (String) null);
        }
        if (!startBrowser) {
            Toast.makeText(getContext(), R.string.no_internet_browser_toast, 0).show();
        }
    }

    public boolean startBrowser(Context context, String str, String str2) {
        String str3 = this.TAG;
        Log.d(str3, "startBrowser", Logger.getEncodedString(str + ArcCommonLog.TAG_COMMA + str2));
        if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "url is empty");
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            if (!TextUtils.isEmpty(str2)) {
                intent.setPackage(str2);
            }
            setIntentWithCommonExtra(intent);
            intent.addFlags(268435456);
            context.startActivity(intent);
            if (TextUtils.isEmpty(str2)) {
                str2 = Event.DEFAULT_EVENT_TYPE;
            }
            this.mExecutedApp = str2;
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("startBrowserWithApp failed e="), this.TAG);
            return false;
        }
    }
}
