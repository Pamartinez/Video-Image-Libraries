package com.samsung.android.gallery.app.controller.creature;

import E5.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideCreatureCmd extends BaseCommand {
    private void addIds(MediaItem[] mediaItemArr, ArrayList<Long> arrayList, ArrayList<Long> arrayList2) {
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isPet()) {
                arrayList.add(Long.valueOf(getUnifiedId(mediaItem)));
            } else {
                arrayList2.add(Long.valueOf(getUnifiedId(mediaItem)));
            }
        }
    }

    private long getUnifiedId(MediaItem mediaItem) {
        return IdentityCreatureUtil.getUnifiedIdentityId(mediaItem.getSubCategory());
    }

    private void hidePeople(ArrayList<Long> arrayList) {
        if (!arrayList.isEmpty()) {
            PeopleDataManager.hidePeople(arrayList, MyQueryUtil.getInterface());
        }
    }

    private void hidePet(ArrayList<Long> arrayList) {
        if (!arrayList.isEmpty()) {
            PetDataManager.updatePetHideState(arrayList, new ArrayList());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$0(ArrayList arrayList, ArrayList arrayList2, ThreadPool.JobContext jobContext) {
        hidePeople(arrayList);
        hidePet(arrayList2);
        getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        return null;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems.length != 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            addIds(selectedItems, arrayList2, arrayList);
            ThreadPool.getInstance().submit(new a(this, arrayList, arrayList2, 1));
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }
}
