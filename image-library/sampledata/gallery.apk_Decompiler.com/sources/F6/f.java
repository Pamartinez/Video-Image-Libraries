package F6;

import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingFragment;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditDisabledMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2323a;

    public /* synthetic */ f(int i2) {
        this.f2323a = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2323a) {
            case 0:
                return StoriesPinchViewPresenter.StoriesPinchMenuUpdater.lambda$updateOptionsMenuAction$1();
            case 1:
                return GalleryListView.lambda$new$0();
            case 2:
                return GalleryListView.lambda$new$1();
            case 3:
                return GalleryListView.lambda$new$2();
            case 4:
                return AdvancedMouseSelectionHandler.lambda$new$0();
            case 5:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumSplitMode);
            case 6:
                return AnalyticsStatusId.lambda$static$1();
            case 7:
                return GenEditDisabledMenuItem.lambda$setMenuAttribute$0();
            case 8:
                return ViewerMenuItem.lambda$static$0();
            case 9:
                return StoriesCategory2Fragment.lambda$onCreate$0();
            case 10:
                return OnDemandFloatingFragment.lambda$onCreate$0();
            case 11:
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_KNOX);
            case 12:
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_KNOX);
            case 13:
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
            case 14:
                return KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
            default:
                return CodecCompat.loadVideoApvCapability();
        }
    }
}
