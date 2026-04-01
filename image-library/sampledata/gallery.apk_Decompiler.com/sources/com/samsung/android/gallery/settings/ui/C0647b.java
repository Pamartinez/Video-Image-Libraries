package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.settings.ui.FixFaceTable;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.ArrayList;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0647b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0647b(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FixFaceTable) this.e).lambda$fix$0((Context) this.f, (ArrayList) this.g);
                return;
            case 1:
                ((FixFaceTable) this.e).lambda$showDeleteConfirmDlg$6((Context) this.f, (FixFaceTable.WrongFaceData) this.g);
                return;
            case 2:
                ((SettingCloud) this.e).lambda$loadAccount$15((Consumer) this.f, (SamsungAccountManager) this.g);
                return;
            default:
                ((SettingTipCard) this.e).lambda$initPreference$1((TimeTickLog) this.f, (PreferenceCategory) this.g);
                return;
        }
    }
}
