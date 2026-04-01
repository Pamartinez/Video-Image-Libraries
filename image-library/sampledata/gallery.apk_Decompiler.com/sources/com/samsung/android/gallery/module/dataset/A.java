package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements MediaDataFactory.MediaDataConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2935a;

    public /* synthetic */ A(int i2) {
        this.f2935a = i2;
    }

    public final MediaData create(Blackboard blackboard, String str) {
        switch (this.f2935a) {
            case 0:
                return new MediaDataQuickView(blackboard, str);
            case 1:
                return new MediaDataMdeInvitations(blackboard, str);
            case 2:
                return new MediaDataSuggestions(blackboard, str);
            case 3:
                return new MediaDataCleanOut(blackboard, str);
            case 4:
                return new MediaDataCleanOutDuplicate(blackboard, str);
            case 5:
                return new MediaDataTimeline(blackboard, str);
            case 6:
                return new MediaDataHideRule(blackboard, str);
            case 7:
                return new MediaDataHideCreatureSelection(blackboard, str);
            case 8:
                return new MediaDataHideSceneSelection(blackboard, str);
            case 9:
                return MediaDataFactory.lambda$static$2(blackboard, str);
            case 10:
                return MediaDataFactory.lambda$static$3(blackboard, str);
            case 11:
                return new MediaDataList(blackboard, str);
            case 12:
                return new MediaDataMap(blackboard, str);
            case 13:
                return new MediaDataTimeline2(blackboard, str);
            case 14:
                return new MediaDataTrash(blackboard, str);
            case 15:
                return new MediaDataMdeTrash(blackboard, str);
            case 16:
                return new MediaDataSearch(blackboard, str);
            case 17:
                return new MediaDataMdeTimeline(blackboard, str);
            case 18:
                return new MediaDataMdeGroup(blackboard, str);
            case 19:
                return new MediaDataMdeGroupMember(blackboard, str);
            default:
                return new MediaDataMdeSpace(blackboard, str);
        }
    }
}
