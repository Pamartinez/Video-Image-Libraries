package p4;

import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2694a;
    public final /* synthetic */ DrawerTabController b;

    public /* synthetic */ e(DrawerTabController drawerTabController, int i2) {
        this.f2694a = i2;
        this.b = drawerTabController;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2694a;
        DrawerTabController drawerTabController = this.b;
        switch (i2) {
            case 0:
                return drawerTabController.lambda$onHandleEvent$26();
            default:
                return drawerTabController.lambda$onEnterMoveMode$27();
        }
    }
}
