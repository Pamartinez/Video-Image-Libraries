package com.samsung.android.gallery.settings.activity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseActivity e;

    public /* synthetic */ d(BaseActivity baseActivity, int i2) {
        this.d = i2;
        this.e = baseActivity;
    }

    public final void run() {
        int i2 = this.d;
        BaseActivity baseActivity = this.e;
        switch (i2) {
            case 0:
                baseActivity.handleHomeAsUpAsync();
                return;
            default:
                ((SettingActivity) baseActivity).onActivityCreateBg();
                return;
        }
    }
}
