package com.samsung.android.gallery.app.controller.internals;

import M9.o;
import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteFromCleanOutCmd extends DeleteCmd {
    private ArrayList<Long> getSelectedContentsIds() {
        long j2;
        ArrayList<Long> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : this.mItems) {
            if (mediaItem != null) {
                if (Features.isEnabled(Features.SUPPORT_CLOUD_SUGGESTIONS)) {
                    j2 = mediaItem.getFileId();
                } else {
                    j2 = mediaItem.getMediaId();
                }
                arrayList.add(Long.valueOf(j2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operateDelete$0() {
        SuggestedHelper.getInstance().deleteCleanOutItems(getContext(), getSelectedContentsIds());
    }

    public void addExtraInfo(Intent intent) {
        super.addExtraInfo(intent);
        if (LocationKey.isCleanOutBurstSimilarPhoto(getHandler().getLocationKey())) {
            intent.putExtra("is_burst_shot_selection", true);
        }
    }

    public void operateDelete() {
        super.operateDelete();
        SimpleThreadPool.getInstance().execute(new o(9, this));
    }
}
