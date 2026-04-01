package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.settings.ui.SettingTipCard;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ P(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                SettingTipCard.AppUpgrade.lambda$build$1((ISettingView) this.e, (PreferenceCategory) this.f, view);
                return;
            case 1:
                Optional.ofNullable(((ISettingView) this.e).getActivity()).ifPresent(new Q((ISettingView) this.e, (PreferenceCategory) this.f));
                return;
            case 2:
                SettingTipCard.OneDrive.lambda$build$2((ISettingView) this.e, (PreferenceCategory) this.f, view);
                return;
            default:
                ((LabsFragment.DebugDumpWorker) this.e).lambda$onPostExecute$2((Context) this.f, view);
                return;
        }
    }
}
