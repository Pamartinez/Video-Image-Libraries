package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.settings.widget.SwitchExSummaryPreference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingCloud e;

    public /* synthetic */ K(SettingCloud settingCloud, int i2) {
        this.d = i2;
        this.e = settingCloud;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateOneDriveBadge$18((SwitchExSummaryPreference) obj);
                return;
            case 1:
                this.e.updateCloudCategories(((Boolean) obj).booleanValue());
                return;
            case 2:
                this.e.lambda$onResume$0((SamsungAccountManager) obj);
                return;
            default:
                this.e.lambda$updateCloudCategories$13((SamsungAccountManager) obj);
                return;
        }
    }
}
