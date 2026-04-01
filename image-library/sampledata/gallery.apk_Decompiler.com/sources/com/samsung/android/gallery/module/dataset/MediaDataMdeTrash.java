package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeTrash extends MediaDataMdeTimeline {
    public MediaDataMdeTrash(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createSubscriberList$0(SubscriberInfo subscriberInfo) {
        if (subscriberInfo.getKey().startsWith(DataKey.DATA_CURSOR("location://sharing/groupMembers")) || subscriberInfo.getKey().startsWith("command://SharingPicturesGroupMemberSynced")) {
            return true;
        }
        return false;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.removeIf(new C0598e(1));
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public void getGroupMemberData() {
    }
}
