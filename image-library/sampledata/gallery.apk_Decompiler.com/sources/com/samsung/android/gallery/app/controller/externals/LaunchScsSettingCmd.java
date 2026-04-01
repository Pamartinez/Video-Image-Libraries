package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.config.Component$SamsungSearch;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LaunchScsSettingCmd extends BaseCommand {
    private Intent createIntentForFilesAccessPermission() {
        Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.fromParts("package", Component$SamsungSearch.PACKAGE, (String) null));
        intent.addFlags(268435456);
        return intent;
    }

    private Intent createIntentForPDI() {
        Intent intent = new Intent("com.samsung.android.smartsuggestions.LAUNCH_SETTINGS");
        intent.putExtra("highlight_menu", "main_switch");
        return intent;
    }

    private Intent createIntentForUsefulFeatures() {
        Intent intent = new Intent("com.samsung.settings.USEFUL_FEATURE_MAIN_SETTINGS");
        Bundle bundle = new Bundle();
        bundle.putString(":settings:fragment_args_key", "samsung_core_services");
        intent.putExtra(":settings:show_fragment_args", bundle);
        return intent;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Intent intent;
        try {
            String str = objArr[0];
            if ("APP_OPS".equals(str)) {
                intent = createIntentForFilesAccessPermission();
            } else if (!"SETTINGS".equals(str)) {
                Log.e((CharSequence) this.TAG, "not support disableReason", str);
                return;
            } else if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                intent = createIntentForPDI();
            } else {
                intent = createIntentForUsefulFeatures();
            }
            getActivity().startActivity(intent);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "Launch SCS Setting failed", (Throwable) e);
        }
    }
}
