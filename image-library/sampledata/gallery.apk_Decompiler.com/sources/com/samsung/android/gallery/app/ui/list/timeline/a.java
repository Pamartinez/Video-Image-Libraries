package com.samsung.android.gallery.app.ui.list.timeline;

import com.samsung.android.gallery.app.ui.list.timeline.SyncViewUpdateManager;
import com.samsung.android.gallery.module.smartswitch.SmartSwitchState;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Enum e;

    public /* synthetic */ a(Enum enumR, int i2) {
        this.d = i2;
        this.e = enumR;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Enum enumR = this.e;
        switch (i2) {
            case 0:
                ((SmartSwitchUpdateManager) obj).updateSmartSwitch((SmartSwitchState) enumR);
                return;
            default:
                ((SyncViewUpdateManager) obj).updateSyncState((SyncViewUpdateManager.UpdateState) enumR);
                return;
        }
    }
}
