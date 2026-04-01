package com.samsung.android.gallery.app.controller.creature.people;

import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveSubscribedCreatureCmd extends BaseCommand {
    private int mDataPosition;
    private MediaItem mMediaItem;

    /* access modifiers changed from: private */
    public void onDataComplete(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && ((Boolean) arrayList.get(0)).booleanValue()) {
            onRemoveSubscribedPerson();
        }
    }

    private void onRemoveSubscribedPerson() {
        getBlackboard().postEvent(EventMessage.obtain(UniImgp.OPTION_IMGP_TYPE, this.mDataPosition, 0, this.mMediaItem));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mDataPosition = objArr[0].intValue();
        this.mMediaItem = objArr[1];
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/UnsubscribeCreature").appendArg("name", CreatureData.of(this.mMediaItem).creatureName).build()).setOnDataCompleteListener(new h(28, this)).start();
    }
}
