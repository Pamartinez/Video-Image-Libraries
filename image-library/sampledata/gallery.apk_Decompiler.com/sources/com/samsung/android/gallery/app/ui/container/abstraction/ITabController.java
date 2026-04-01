package com.samsung.android.gallery.app.ui.container.abstraction;

import android.view.View;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITabController {
    void bindView(View view);

    void changeFocus(String str, boolean z);

    void createSubscriberList(ArrayList<SubscriberInfo> arrayList);

    String getFocusKey();

    void handleDensityChange();

    void handleResolutionChange();

    void onChildViewCreated(IBaseFragment iBaseFragment);

    boolean onHandleEvent(EventMessage eventMessage) {
        return false;
    }

    void setVisibility(boolean z, boolean z3, boolean z7);

    void unbindView();

    void updateBadgeOnHamburger();

    void updateBadgeOnTab(int i2, boolean z);

    void updateBottomNavigationMenu();

    void updateNavigationIcon();

    void onMultiWindowModeChanged() {
    }

    void updateCollapsedAlbumsTabFocus() {
    }

    void updateExpandedAlbumsTab() {
    }

    void updateFloatingView(View view) {
    }

    void updateSettingBadge(boolean z) {
    }
}
