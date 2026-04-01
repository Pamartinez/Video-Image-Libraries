package com.samsung.android.gallery.settings.ui;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Runnable {
    public final /* synthetic */ SettingCloud d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Consumer f;

    public /* synthetic */ F(SettingCloud settingCloud, boolean z, Consumer consumer) {
        this.d = settingCloud;
        this.e = z;
        this.f = consumer;
    }

    public final void run() {
        this.d.lambda$loadAccount$16(this.e, this.f);
    }
}
