package com.samsung.android.gallery.app.activity.preload;

import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PickerDataRequester extends DataRequester {
    public PickerDataRequester(Blackboard blackboard) {
        super(blackboard);
    }

    public void preload(String str) {
        String str2;
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (LocationKey.isTimeline(removeArgs) || LocationKey.isAlbumsMatch(str)) {
            if (LocationKey.isTimeline(removeArgs)) {
                str2 = "location://timeline/fake";
            } else if (PickerUtil.isPickerFilterMode(this.mBlackboard)) {
                str2 = "location://albums";
            } else {
                str2 = "location://albums/cache";
            }
            publishDataByLocationKey(PickerUtil.appendPickerArgs(this.mBlackboard, str2));
            openPreloadData(str);
        }
    }
}
