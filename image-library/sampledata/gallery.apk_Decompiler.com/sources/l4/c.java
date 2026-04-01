package l4;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabPresenter e;

    public /* synthetic */ c(BottomTabPresenter bottomTabPresenter, int i2) {
        this.d = i2;
        this.e = bottomTabPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        BottomTabPresenter bottomTabPresenter = this.e;
        switch (i2) {
            case 0:
                bottomTabPresenter.onUpdateBottomTabMenu(obj, bundle);
                return;
            case 1:
                bottomTabPresenter.onMoveEnter(obj, bundle);
                return;
            case 2:
                bottomTabPresenter.onMoveExit(obj, bundle);
                return;
            case 3:
                bottomTabPresenter.onSelectBottomTabMenu(obj, bundle);
                return;
            case 4:
                bottomTabPresenter.onUpdateNotificationsBadge(obj, bundle);
                return;
            case 5:
                bottomTabPresenter.onUpdateStoriesTabBadge(obj, bundle);
                return;
            case 6:
                bottomTabPresenter.onEssentialAlbumsChanged(obj, bundle);
                return;
            case 7:
                bottomTabPresenter.onSharedAlbumBlockerChanged(obj, bundle);
                return;
            case 8:
                bottomTabPresenter.onUpdateSharingTabBadge(obj, bundle);
                return;
            case 9:
                bottomTabPresenter.onUpdateBottomMenuTabBadge(obj, bundle);
                return;
            case 10:
                bottomTabPresenter.onRequestBottomMenuTabBadge(obj, bundle);
                return;
            case 11:
                bottomTabPresenter.blockFocus(obj, bundle);
                return;
            case 12:
                bottomTabPresenter.onUpdateBottomTabFloatingView(obj, bundle);
                return;
            case 13:
                bottomTabPresenter.onRemoveSiblingFragments(obj, bundle);
                return;
            case 14:
                bottomTabPresenter.onStartShrinkAnimation(obj, bundle);
                return;
            case 15:
                bottomTabPresenter.onSelectionEnabled(obj, bundle);
                return;
            default:
                bottomTabPresenter.onSelectionDisabled(obj, bundle);
                return;
        }
    }
}
