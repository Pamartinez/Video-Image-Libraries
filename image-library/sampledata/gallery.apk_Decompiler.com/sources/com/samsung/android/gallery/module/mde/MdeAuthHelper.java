package com.samsung.android.gallery.module.mde;

import android.content.Intent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeAuthHelper {
    public static Intent getIntentInfoToUseShareService() {
        return MdeSharingService.getInstance().getIntentInfoToUseShareService();
    }

    public static boolean isAuthServiceEnabled() {
        return MdeSharingService.getInstance().isAuthServiceEnabled();
    }

    public static boolean isReadyToUseShareService() {
        if (!MdeSharingService.getInstance().isShareServiceAvailable() || !MdeSharingService.getInstance().isReadyToUseShareService()) {
            return false;
        }
        return true;
    }
}
