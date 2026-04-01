package com.samsung.android.gallery.module.mde;

import android.content.Intent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeSocialHelper {
    public static Intent getIntentForGdprErrorPopup() {
        return MdeSharingService.getInstance().getIntentForGdprErrorPopup();
    }

    public static boolean isSocialServiceEnabled() {
        return MdeSharingService.getInstance().isSocialServiceEnabled();
    }
}
