package M9;

import android.os.Bundle;
import com.samsung.android.gallery.module.publisher.SharingsDataPublisher;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingsDataPublisher e;

    public /* synthetic */ j(SharingsDataPublisher sharingsDataPublisher, int i2) {
        this.d = i2;
        this.e = sharingsDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SharingsDataPublisher sharingsDataPublisher = this.e;
        switch (i2) {
            case 0:
                sharingsDataPublisher.lambda$publishSharingsDataCache$2(obj, bundle);
                return;
            case 1:
                sharingsDataPublisher.publishSharingsFamilySuggestedData(obj, bundle);
                return;
            case 2:
                sharingsDataPublisher.publishFamilySharedTrashData(obj, bundle);
                return;
            case 3:
                sharingsDataPublisher.publishSharingPicturesData(obj, bundle);
                return;
            case 4:
                sharingsDataPublisher.publishSharingPicturesHeaderDataMediaCount(obj, bundle);
                return;
            case 5:
                sharingsDataPublisher.publishSharingGroupsData(obj, bundle);
                return;
            case 6:
                sharingsDataPublisher.publishSharingGroupMembersData(obj, bundle);
                return;
            case 7:
                sharingsDataPublisher.publishSharingChoiceData(obj, bundle);
                return;
            case 8:
                sharingsDataPublisher.publishSharingInvitationsData(obj, bundle);
                return;
            case 9:
                sharingsDataPublisher.publishSharingStorageUsageData(obj, bundle);
                return;
            default:
                sharingsDataPublisher.publishSharingPicturesStorageUsageData(obj, bundle);
                return;
        }
    }
}
