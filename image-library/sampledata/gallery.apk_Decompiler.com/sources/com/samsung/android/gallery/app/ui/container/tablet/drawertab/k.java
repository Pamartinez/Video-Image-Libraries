package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2519a;
    public final /* synthetic */ TabItemEnableCondition b;

    public /* synthetic */ k(TabItemEnableCondition tabItemEnableCondition, int i2) {
        this.f2519a = i2;
        this.b = tabItemEnableCondition;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2519a;
        TabItemEnableCondition tabItemEnableCondition = this.b;
        String str = (String) obj;
        switch (i2) {
            case 0:
                return tabItemEnableCondition.lambda$onEnterSelectionMode$2(str);
            case 1:
                return tabItemEnableCondition.lambda$onDragStarted$9(str);
            default:
                return tabItemEnableCondition.lambda$onDragStarted$10(str);
        }
    }
}
