package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.module.authentication.TwoFactorAuth;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements Runnable {
    public final void run() {
        TwoFactorAuth.getInstance().checkVerification(true);
    }
}
