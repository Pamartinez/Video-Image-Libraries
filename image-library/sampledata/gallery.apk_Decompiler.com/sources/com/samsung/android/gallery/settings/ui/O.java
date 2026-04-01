package com.samsung.android.gallery.settings.ui;

import android.view.View;
import com.samsung.android.gallery.settings.ui.SettingTipCard;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ O(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                SettingTipCard.AppUpgrade.lambda$build$0((ISettingView) obj, view);
                return;
            default:
                ((TroubleshootingFragment.TroubleResolverViewHolder) obj).lambda$new$0(view);
                return;
        }
    }
}
