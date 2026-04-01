package n4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListContainerPresenter e;

    public /* synthetic */ d(ListContainerPresenter listContainerPresenter, int i2) {
        this.d = i2;
        this.e = listContainerPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        ListContainerPresenter listContainerPresenter = this.e;
        switch (i2) {
            case 0:
                listContainerPresenter.onUpdateBottomTabMenu(obj, bundle);
                return;
            case 1:
                listContainerPresenter.onRemoveChildFragment(obj, bundle);
                return;
            case 2:
                listContainerPresenter.onCreateChildFragmentView(obj, bundle);
                return;
            case 3:
                listContainerPresenter.onStartShrinkAnimation(obj, bundle);
                return;
            case 4:
                listContainerPresenter.onSelectBottomTabMenu(obj, bundle);
                return;
            case 5:
                listContainerPresenter.onSharingServiceDisabled(obj, bundle);
                return;
            case 6:
                listContainerPresenter.onUpdateBottomTabFloatingView(obj, bundle);
                return;
            case 7:
                listContainerPresenter.onUpdateNotificationsBadge(obj, bundle);
                return;
            case 8:
                listContainerPresenter.onEssentialAlbumsChanged(obj, bundle);
                return;
            case 9:
                listContainerPresenter.onUpdateStoriesTabBadge(obj, bundle);
                return;
            case 10:
                listContainerPresenter.onUpdateSharingTabBadge(obj, bundle);
                return;
            case 11:
                listContainerPresenter.onUpdateBottomMenuTabBadge(obj, bundle);
                return;
            case 12:
                listContainerPresenter.onChangeTabFocus(obj, bundle);
                return;
            case 13:
                listContainerPresenter.onSharedAlbumsBadgeUpdated(obj, bundle);
                return;
            case 14:
                listContainerPresenter.onSettingsBadgeUpdated(obj, bundle);
                return;
            case 15:
                listContainerPresenter.lambda$createSubscriberList$0(obj, bundle);
                return;
            case 16:
                listContainerPresenter.onRemoveSiblingFragments(obj, bundle);
                return;
            default:
                listContainerPresenter.onAddChildFragment(obj, bundle);
                return;
        }
    }
}
