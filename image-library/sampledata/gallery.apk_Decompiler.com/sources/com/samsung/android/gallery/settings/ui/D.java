package com.samsung.android.gallery.settings.ui;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingCloud e;

    public /* synthetic */ D(SettingCloud settingCloud, int i2) {
        this.d = i2;
        this.e = settingCloud;
    }

    public final void run() {
        int i2 = this.d;
        SettingCloud settingCloud = this.e;
        switch (i2) {
            case 0:
                settingCloud.lambda$update2FaStatusOn$26();
                return;
            case 1:
                settingCloud.handleCloudSyncOn();
                return;
            case 2:
                settingCloud.lambda$update2FaStatus$25();
                return;
            default:
                settingCloud.lambda$update2FaStatus$24();
                return;
        }
    }
}
