package com.samsung.android.gallery.app.ui.list.trash.container;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashContainerPresenter extends MvpBasePresenter<ITrashContainerView> {
    public TrashContainerPresenter(Blackboard blackboard, ITrashContainerView iTrashContainerView) {
        super(blackboard, iTrashContainerView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        ((ITrashContainerView) this.mView).onSelectionModeChanged(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        ((ITrashContainerView) this.mView).onSelectionModeChanged(false);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://UiEventStartShrinkAnimation", new a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new a(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new a(this, 2)).setWorkingOnUI());
    }

    public void onStartShrinkAnimation(Object obj, Bundle bundle) {
        if (((ITrashContainerView) this.mView).isViewActive() && !((ITrashContainerView) this.mView).isDrawerMode()) {
            ((ITrashContainerView) this.mView).startShrinkAnimation();
        }
    }

    public void initMenu() {
    }
}
