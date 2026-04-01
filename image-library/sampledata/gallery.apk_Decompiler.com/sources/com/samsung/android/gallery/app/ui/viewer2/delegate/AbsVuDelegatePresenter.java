package com.samsung.android.gallery.app.ui.viewer2.delegate;

import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegatePresenter;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsVuDelegatePresenter<V extends IVuDelegateView> extends AbsDelegatePresenter<V, ContainerModel> {
    public AbsVuDelegatePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }
}
