package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesViewNavigatorController extends ViewNavigatorController {
    public StoriesViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    public void onNavigatorCreated() {
        new StoriesViewNavigatorDelegate(getLaunchIntent(), this.mBlackboard).onHandle((String) null);
    }
}
