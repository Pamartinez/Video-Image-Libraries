package l4;

import com.samsung.android.gallery.app.ui.container.abstraction.ITabConsumer;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BottomTabTouchDelegate.OnMenuTabTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2663a;
    public final /* synthetic */ ITabConsumer b;

    public /* synthetic */ a(ITabConsumer iTabConsumer, int i2) {
        this.f2663a = i2;
        this.b = iTabConsumer;
    }

    public final void onMenuTabTouched() {
        int i2 = this.f2663a;
        ITabConsumer iTabConsumer = this.b;
        switch (i2) {
            case 0:
                ((BottomTabFragment) iTabConsumer).onMenuTabTouched();
                return;
            default:
                ((BottomTabController) iTabConsumer).onMenuTabTouched();
                return;
        }
    }
}
