package com.samsung.android.gallery.settings.ui;

import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.settings.ui.SettingTipCard;
import com.samsung.android.gallery.support.utils.TimeTickLog;

/* renamed from: com.samsung.android.gallery.settings.ui.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0655j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3119h;

    public /* synthetic */ C0655j(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f3119h = obj4;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((LabsBaseFragment) this.e).lambda$showConfirmDialog$6((String) this.f, (String) this.g, (Runnable) this.f3119h);
                return;
            default:
                ((SettingTipCard) this.e).lambda$initPreference$0((TimeTickLog) this.f, (SettingTipCard.TipCard) this.g, (PreferenceCategory) this.f3119h);
                return;
        }
    }
}
