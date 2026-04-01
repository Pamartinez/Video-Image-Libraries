package com.samsung.android.gallery.app.ui.container.phone;

import com.samsung.android.gallery.app.ui.container.abstraction.ITabView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBottomTabView extends ITabView {
    void blockFocus(boolean z);

    void hideTabLayout();

    void hideTabMenu();

    void removeSiblingFragments(String[] strArr);

    void selectBottomNavigationMenu(String str);

    void showTabLayout();

    void updateBottomNavigationMenu();
}
