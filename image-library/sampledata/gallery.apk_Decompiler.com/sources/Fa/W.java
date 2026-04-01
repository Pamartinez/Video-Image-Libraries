package Fa;

import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.SettingPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class W implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingPresenter e;

    public /* synthetic */ W(SettingPresenter settingPresenter, int i2) {
        this.d = i2;
        this.e = settingPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SettingPresenter settingPresenter = this.e;
        switch (i2) {
            case 0:
                settingPresenter.onEditMenuOptionsBaiduChanged(obj, bundle);
                return;
            case 1:
                settingPresenter.lambda$setGlobalSubscriberList$0(obj, bundle);
                return;
            case 2:
                settingPresenter.onEditMenuOptionsTencentChanged(obj, bundle);
                return;
            case 3:
                settingPresenter.lambda$setGlobalSubscriberList$1(obj, bundle);
                return;
            case 4:
                settingPresenter.onAutoPlayMotionPhotoChanged(obj, bundle);
                return;
            case 5:
                settingPresenter.onOpenInVideoPlayerChanged(obj, bundle);
                return;
            case 6:
                settingPresenter.onMeituChanged(obj, bundle);
                return;
            case 7:
                settingPresenter.onShowHdrImagesChanged(obj, bundle);
                return;
            case 8:
                settingPresenter.onKnoxRestrictionsChanged(obj, bundle);
                return;
            case 9:
                settingPresenter.onDetailEnhancerChanged(obj, bundle);
                return;
            case 10:
                settingPresenter.onAutoStoryChanged(obj, bundle);
                return;
            case 11:
                settingPresenter.onSelectEssentialAlbumsChanged(obj, bundle);
                return;
            case 12:
                settingPresenter.onStoryNotificationStateChanged(obj, bundle);
                return;
            case 13:
                settingPresenter.onMergeAlbumsChanged(obj, bundle);
                return;
            case 14:
                settingPresenter.onSharingNotificationChanged(obj, bundle);
                return;
            case 15:
                settingPresenter.onSharingBlockerChanged(obj, bundle);
                return;
            case 16:
                settingPresenter.onCmhProviderChanged(obj, bundle);
                return;
            case 17:
                settingPresenter.onLocationAuthChanged(obj, bundle);
                return;
            case 18:
                settingPresenter.onTrashChanged(obj, bundle);
                return;
            case 19:
                settingPresenter.onHeifTranscodingChanged(obj, bundle);
                return;
            default:
                settingPresenter.onHdr10TransCodingChanged(obj, bundle);
                return;
        }
    }
}
