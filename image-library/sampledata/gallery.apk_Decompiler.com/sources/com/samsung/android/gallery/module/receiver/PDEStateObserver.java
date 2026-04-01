package com.samsung.android.gallery.module.receiver;

import android.net.Uri;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PDEStateObserver extends AbsContentObserver {
    public PDEStateObserver() {
        super(ThreadUtil.createMainThreadHandler());
    }

    public ArrayList<Uri> getWatchUris() {
        return new ArrayList<>(Arrays.asList(new Uri[]{Uri.parse("content://com.samsung.android.app.deepsky.DeepSkyQuery.provider/smart_suggestions_enabled"), Uri.parse("content://com.samsung.android.app.deepsky.DeepSkyQuery.provider/app_recommendation_enabled/com.sec.android.gallery3d"), Uri.parse("content://com.samsung.android.pde.setting.datatouse/gallery"), Uri.parse("content://com.samsung.android.pde.setting.datatouse/contacts")}));
    }

    public /* bridge */ /* synthetic */ void onChange(boolean z) {
        super.onChange(z);
    }

    public void onChange(boolean z, Uri uri) {
        if (uri != null) {
            Log.d(this.TAG, "ContactLink PDEState onChange ", Logger.getEncodedString((Object) uri));
            Blackboard.getApplicationInstance().post("event/personalDataStateChanged", (Object) null);
        }
        super.onChange(z, uri);
    }
}
