package com.samsung.android.gallery.app.ui.container.abstraction;

import android.view.View;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.IBaseFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IListContainerView extends ITabView {
    void addChildFragment(String str);

    void changeFocus(String str);

    ITabController[] getControllers();

    String getCurrentAlbumKey();

    String getLocationKey();

    boolean hasChildFragments();

    boolean isAlbumFirstSelect();

    boolean isTimelineFirstSelect();

    boolean needBadgeOnBottomMenuTab();

    boolean needBadgeOnNavigationDrawerButton();

    void onChildFragmentViewCreated(IBaseFragment iBaseFragment);

    void onCurrentFragmentChanged();

    void onMenuItemSelected(String str);

    boolean refreshChildFragment(String str);

    boolean removeChildFragment();

    void removeSiblingFragments(String[] strArr);

    void selectBottomNavigationMenu(String str);

    void selectView(String str, boolean z);

    void setArgumentOnSwitchFragment(Fragment fragment, String str);

    void setBottomBarMargin(int i2);

    void setBottomMoveBarMargin(int i2, int i7);

    void setToolbarStartMargin(int i2);

    void settingsBadgeUpdated(boolean z);

    void updateBottomNavigationMenu();

    void updateCurrentLocationKey(String str);

    void updateDrawerSpace(int i2);

    void updateDrawerSpaceRelative(int i2, int i7, int i8, int i10);

    void updatePrimaryNavigationFragment();

    void updateToolbarNavigation(int i2, View.OnClickListener onClickListener, int i7);

    void updateToolbarNavigation(View.OnClickListener onClickListener, int i2);

    void updateToolbarNavigationRipple(boolean z);
}
