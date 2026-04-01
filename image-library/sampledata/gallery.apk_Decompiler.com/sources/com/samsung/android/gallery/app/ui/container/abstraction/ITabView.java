package com.samsung.android.gallery.app.ui.container.abstraction;

import android.view.View;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITabView extends IMvpBaseView {
    boolean checkTabSelectable();

    void deliverEvent(EventMessage eventMessage);

    MvpBaseFragment getCurrentFragment();

    boolean isMoveMode();

    boolean isSelectionMode();

    void onUpdateBottomTabFloatingView(View view);

    void startShrinkAnimation();

    boolean supportTabLayout();

    void updateBadgeOnTab(int i2, boolean z);
}
