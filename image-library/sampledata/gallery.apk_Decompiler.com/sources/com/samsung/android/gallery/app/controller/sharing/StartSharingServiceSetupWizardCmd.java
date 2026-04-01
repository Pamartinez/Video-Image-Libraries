package com.samsung.android.gallery.app.controller.sharing;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSharingServiceSetupWizardCmd extends BaseCommand {
    private void startSharingServiceSetupWizard(Activity activity) {
        Intent intentInfoToUseShareService = MdeAuthHelper.getIntentInfoToUseShareService();
        if (intentInfoToUseShareService != null) {
            try {
                activity.startActivityForResult(intentInfoToUseShareService, SerializeOptions.SORT);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Activity activity = getActivity();
        if (activity != null && MdeSharingService.getInstance().isShareServiceAvailable() && objArr[0] == RequestSetupWizardType.SETUP_SHARING_SERVICE) {
            startSharingServiceSetupWizard(activity);
        }
    }
}
