package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import com.samsung.android.gallery.app.ui.list.sharings.ISharingsView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsPresenter;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPinchPresenter extends SharingsPresenter {
    public SharingPinchPresenter(Blackboard blackboard, ISharingsView iSharingsView) {
        super(blackboard, iSharingsView);
    }

    public int getCurrentViewDepth() {
        return ((ISharingsView) this.mView).getListView().getDepth();
    }
}
