package com.samsung.android.gallery.app.ui.container.picker;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabPresenter;
import com.samsung.android.gallery.app.ui.container.phone.IBottomTabView;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabPickerPresenter<V extends IBottomTabView> extends BottomTabPresenter<V> {
    public BottomTabPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void updateToolbar(Toolbar toolbar) {
        if (((LaunchModeType) this.mBlackboard.read("data://launch_mode_type")) == LaunchModeType.ACTION_PICK) {
            super.updateToolbar(toolbar);
        }
    }
}
