package p7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.MenuTipPopupDelegate;
import com.samsung.android.widget.SemTipPopup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SemTipPopup.OnStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MenuTipPopupDelegate f2695a;

    public /* synthetic */ a(MenuTipPopupDelegate menuTipPopupDelegate) {
        this.f2695a = menuTipPopupDelegate;
    }

    public final void onStateChanged(int i2) {
        this.f2695a.lambda$showMenuTipPopup$0(i2);
    }
}
