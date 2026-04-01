package androidx.appcompat.widget;

import android.view.Menu;
import android.view.Window;
import androidx.appcompat.view.menu.MenuPresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int i2);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenu(Menu menu, MenuPresenter.Callback callback);

    void setMenuPrepared();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
