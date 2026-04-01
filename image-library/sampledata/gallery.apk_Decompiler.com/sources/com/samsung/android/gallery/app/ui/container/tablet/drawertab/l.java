package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2520a;
    public final /* synthetic */ TabItemEnableCondition b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f2521c;

    public /* synthetic */ l(TabItemEnableCondition tabItemEnableCondition, boolean z, int i2) {
        this.f2520a = i2;
        this.b = tabItemEnableCondition;
        this.f2521c = z;
    }

    public final boolean test(Object obj) {
        switch (this.f2520a) {
            case 0:
                return this.b.lambda$onDragEnded$11(this.f2521c, (String) obj);
            default:
                return this.b.lambda$onDragEnded$12(this.f2521c, (String) obj);
        }
    }
}
