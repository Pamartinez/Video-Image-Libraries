package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LabsFragment f3112a;
    public final /* synthetic */ PackageMonitorCompat b;

    public /* synthetic */ A(LabsFragment labsFragment, PackageMonitorCompat packageMonitorCompat) {
        this.f3112a = labsFragment;
        this.b = packageMonitorCompat;
    }

    public final Object apply(Object obj) {
        return this.f3112a.lambda$makeDeveloperOptionsSummary$9(this.b, (LabsFragment.PackageHolder.PackageData) obj);
    }
}
