package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2522a;

    public /* synthetic */ m(int i2) {
        this.f2522a = i2;
    }

    public final boolean test(Object obj) {
        String str = (String) obj;
        switch (this.f2522a) {
            case 0:
                return TabItemEnableCondition.lambda$onEnterMoveMode$5(str);
            case 1:
                return TabItemEnableCondition.lambda$onEnterMoveMode$6(str);
            case 2:
                return TabItemEnableCondition.lambda$onExitSelectionMode$3(str);
            case 3:
                return TabItemEnableCondition.lambda$onExitSelectionMode$4(str);
            case 4:
                return TabItemEnableCondition.lambda$new$0(str);
            case 5:
                return TabItemEnableCondition.lambda$new$1(str);
            case 6:
                return TabItemEnableCondition.lambda$onExitMoveMode$7(str);
            default:
                return TabItemEnableCondition.lambda$onExitMoveMode$8(str);
        }
    }
}
