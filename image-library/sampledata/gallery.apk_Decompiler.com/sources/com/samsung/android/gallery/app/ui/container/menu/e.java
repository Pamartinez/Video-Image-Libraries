package com.samsung.android.gallery.app.ui.container.menu;

import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2517a;
    public final /* synthetic */ BottomTabMenu.BottomMenuListAdapter b;

    public /* synthetic */ e(BottomTabMenu.BottomMenuListAdapter bottomMenuListAdapter, int i2) {
        this.f2517a = i2;
        this.b = bottomMenuListAdapter;
    }

    public final boolean test(int i2) {
        int i7 = this.f2517a;
        BottomTabMenu.BottomMenuListAdapter bottomMenuListAdapter = this.b;
        switch (i7) {
            case 0:
                return bottomMenuListAdapter.lambda$onUsbStorageChanged$0(i2);
            default:
                return bottomMenuListAdapter.lambda$onUsbStorageChanged$1(i2);
        }
    }
}
