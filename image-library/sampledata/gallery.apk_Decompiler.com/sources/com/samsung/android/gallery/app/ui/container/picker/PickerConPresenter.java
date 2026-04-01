package com.samsung.android.gallery.app.ui.container.picker;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PickerConPresenter extends MvpBasePresenter<IPickerConView> {
    public PickerConPresenter(Blackboard blackboard, IPickerConView iPickerConView) {
        super(blackboard, iPickerConView);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://UiEventStartShrinkAnimation", new a(this)).setWorkingOnUI());
    }

    public void onStartShrinkAnimation(Object obj, Bundle bundle) {
        ((IPickerConView) this.mView).startShrinkAnimation();
    }

    public void initMenu() {
    }
}
