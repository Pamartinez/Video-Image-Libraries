package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UrlLookup;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartTianYiCloudCmd extends BaseCommand {
    private static final String TY_PACKAGE_NAME = UrlLookup.getData("tianyi");

    private Intent createIntent(Context context) {
        StringBuilder sb2 = new StringBuilder();
        String str = TY_PACKAGE_NAME;
        Intent intent = new Intent(C0212a.p(sb2, str, ".action.Cloud_Album"));
        intent.setComponent(new ComponentName(str, C0212a.A(str, ".activity.MainPageActivity")));
        setIntentWithCommonExtra(intent);
        return intent;
    }

    private void startCloud(Context context) {
        try {
            context.startActivity(createIntent(context));
        } catch (ActivityNotFoundException unused) {
            Log.e(this.TAG, "intent not found");
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        String str = TY_PACKAGE_NAME;
        if (!isPackageInstalled(str)) {
            guideDownloadPackage(str, context.getString(R.string.tianyi_cloud));
        } else {
            startCloud(context);
        }
    }
}
