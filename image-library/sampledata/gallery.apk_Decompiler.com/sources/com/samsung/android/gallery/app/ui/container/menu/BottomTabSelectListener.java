package com.samsung.android.gallery.app.ui.container.menu;

import com.google.android.material.tabs.c;
import com.samsung.android.gallery.app.ui.container.abstraction.ITabConsumer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SimpleTabSelectedListener;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import k4.C0477c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabSelectListener extends SimpleTabSelectedListener {
    private final ITabConsumer mTabConsumer;

    public BottomTabSelectListener(ITabConsumer iTabConsumer) {
        this.mTabConsumer = iTabConsumer;
    }

    /* access modifiers changed from: private */
    public void blockFocus(Blackboard blackboard) {
        blackboard.publish("data://bottomtab/focus", Boolean.FALSE);
    }

    private boolean isMenuTab(c cVar) {
        Integer num;
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU || (num = cVar.f1501a) == null || num.intValue() != R.id.action_menu_list) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTabReselected$0(Blackboard blackboard) {
        blackboard.postEvent(EventMessage.obtain(1053, (Object) null));
        blockFocus(blackboard);
    }

    public void onTabReselected(c cVar) {
        Optional.ofNullable(this.mTabConsumer.getBlackboard()).ifPresent(new C0477c(this, 1));
    }

    public void onTabSelected(c cVar) {
        if (!this.mTabConsumer.checkTabSelectable()) {
            this.mTabConsumer.cancel(false);
        } else if (isMenuTab(cVar)) {
            this.mTabConsumer.showMenuList();
            this.mTabConsumer.cancel(true);
        } else {
            this.mTabConsumer.select(cVar);
            Optional.ofNullable(this.mTabConsumer.getBlackboard()).ifPresent(new C0477c(this, 0));
        }
    }
}
