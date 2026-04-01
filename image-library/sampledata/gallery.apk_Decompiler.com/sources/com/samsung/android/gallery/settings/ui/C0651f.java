package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.samsung.android.gallery.settings.ui.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0651f implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Serializable e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3118h;

    public /* synthetic */ C0651f(int i2, Serializable serializable, Object obj, Object obj2, Object obj3) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = serializable;
        this.f3118h = obj3;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                ((FixFaceTable) this.f).lambda$showSelectBucketDlg$5((ArrayList) this.g, (AtomicInteger) this.e, (Context) this.f3118h, dialogInterface, i2);
                return;
            case 1:
                ((LabsBaseFragment.ChoiceDialogBuilder) this.f).lambda$attach$1((AtomicInteger) this.e, (Preference) this.g, (String[]) this.f3118h, dialogInterface, i2);
                return;
            default:
                ((LabsBaseFragment.MultiChoiceDialogBuilder) this.f).lambda$attach$5((String[]) this.g, (boolean[]) this.e, (Preference) this.f3118h, dialogInterface, i2);
                return;
        }
    }

    public /* synthetic */ C0651f(LabsBaseFragment.ChoiceDialogBuilder choiceDialogBuilder, AtomicInteger atomicInteger, Preference preference, String[] strArr) {
        this.d = 1;
        this.f = choiceDialogBuilder;
        this.e = atomicInteger;
        this.g = preference;
        this.f3118h = strArr;
    }
}
