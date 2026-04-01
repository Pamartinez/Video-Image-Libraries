package com.samsung.android.gallery.plugins.portrait;

import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((LiveEffectDelegate) obj).lambda$dismissDialog$21();
                return;
            case 1:
                ((LiveEffectDelegate) obj).lambda$share$6();
                return;
            case 2:
                ((LiveEffectDelegate) obj).lambda$playDynamicsVIAnimation$0();
                return;
            case 3:
                ((LiveEffectDelegate) obj).lambda$playDynamicsVIAnimation$1();
                return;
            case 4:
                ((LiveEffectDelegate) obj).lambda$playDynamicsVIAnimation$2();
                return;
            case 5:
                ((LiveEffectDelegate) obj).lambda$playDynamicsVIAnimation$3();
                return;
            case 6:
                ((LiveEffectDelegate) obj).lambda$setAsWallpaper$7();
                return;
            default:
                ((LiveEffectActivity.AnonymousClass1) obj).lambda$onTableModeChanged$0();
                return;
        }
    }
}
