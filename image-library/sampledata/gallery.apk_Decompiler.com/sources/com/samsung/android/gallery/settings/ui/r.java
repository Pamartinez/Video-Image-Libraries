package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ r(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                LabsBaseFragment.BooleanChoiceDialogBuilder.lambda$new$0((BooleanFeatures) obj2, (Integer) obj);
                return;
            case 1:
                ((LabsFragment.PrivateRecoveryWorker) obj2).lambda$onExecute$1((Float) obj);
                return;
            default:
                ((SettingCloud2) obj2).lambda$createGlobalSubscriberList$0((SamsungAccountManager) obj);
                return;
        }
    }
}
