package com.samsung.android.gallery.app.controller.creature;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoiceCmd extends BaseCommand {
    Boolean mIsCustomCover;
    MediaItem mMediaItem;

    private void startCreatureCoverChoice() {
        String str;
        UriBuilder appendArg = new UriBuilder("location://search/CreatureCoverChoice").appendArg("sub", this.mMediaItem.getSubCategory());
        if (this.mMediaItem.isPeople()) {
            str = "recommended_id";
        } else {
            str = "pet_recommended_id";
        }
        getBlackboard().post("command://MoveURL", appendArg.appendArg("term", str).appendArg("disableTimeline", true).appendArg("searchCurrentCoverItem", this.mMediaItem.getFileId()).appendArg("searchCurrentCoverItemIsCustom", this.mIsCustomCover.booleanValue()).appendArg("searchCurrentCoverFaceGroupId", String.valueOf(CreatureData.of(this.mMediaItem).faceGroupId)).build());
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        this.mIsCustomCover = objArr[1];
        if (mediaItem == null) {
            Log.e(this.TAG, "item is null. stop CreatureCoverChoiceCmd");
        } else {
            startCreatureCoverChoice();
        }
    }
}
