package com.samsung.android.gallery.settings.ui;

import android.app.Activity;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.settings.ui.SettingTipCard;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements Consumer {
    public final /* synthetic */ ISettingView d;
    public final /* synthetic */ PreferenceCategory e;

    public /* synthetic */ Q(ISettingView iSettingView, PreferenceCategory preferenceCategory) {
        this.d = iSettingView;
        this.e = preferenceCategory;
    }

    public final void accept(Object obj) {
        SettingTipCard.OneDrive.lambda$build$0(this.d, this.e, (Activity) obj);
    }
}
