package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import java.util.Set;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean[] f3121a;
    public final /* synthetic */ Set b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String[] f3122c;

    public /* synthetic */ t(boolean[] zArr, Set set, String[] strArr) {
        this.f3121a = zArr;
        this.b = set;
        this.f3122c = strArr;
    }

    public final void accept(int i2) {
        LabsBaseFragment.MultiChoiceDialogBuilder.lambda$attach$1(this.f3121a, this.b, this.f3122c, i2);
    }
}
