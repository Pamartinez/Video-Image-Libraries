package com.samsung.android.gallery.app.activity.external;

import android.content.Context;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaceTaggingNavigatorController extends ViewNavigatorController {
    public FaceTaggingNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    public void onNavigatorCreated() {
        IdentityCreatureUtil.Category category;
        LaunchIntent launchIntent = getLaunchIntent();
        if (launchIntent.getCreatureId() != 0) {
            int creatureType = launchIntent.getCreatureType();
            long creatureId = launchIntent.getCreatureId();
            if (creatureType == 0) {
                category = IdentityCreatureUtil.Category.PEOPLE;
            } else {
                category = IdentityCreatureUtil.Category.PET;
            }
            this.mBlackboard.post("command://MoveURL", new UriBuilder("location://search/EditCreatureName").appendArg("sub", IdentityCreatureUtil.createWithUnifiedId(creatureId, category)).build());
            return;
        }
        Utils.showToast((Context) this.mActivityView.getActivity(), "Invalid Creature ID!!!!");
        this.mActivityView.getActivity().finish();
    }

    public boolean supportDrawerLayout() {
        return false;
    }
}
