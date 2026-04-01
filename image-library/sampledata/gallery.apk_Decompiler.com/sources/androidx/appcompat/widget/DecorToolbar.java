package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewPropertyAnimatorCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DecorToolbar {
    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    int getDisplayOptions();

    Menu getMenu();

    int getNavigationMode();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    boolean hasExpandedActionView();

    boolean hideOverflowMenu();

    void initIndeterminateProgress();

    void initProgress();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setCollapsible(boolean z);

    void setDisplayOptions(int i2);

    void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView);

    void setHomeButtonEnabled(boolean z);

    void setIcon(int i2);

    void setIcon(Drawable drawable);

    void setLogo(int i2);

    void setMenu(Menu menu, MenuPresenter.Callback callback);

    void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2);

    void setMenuPrepared();

    void setTitle(CharSequence charSequence);

    void setVisibility(int i2);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i2, long j2);

    boolean showOverflowMenu();
}
