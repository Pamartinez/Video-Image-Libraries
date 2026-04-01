package A4;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.ListViewAdapterHandler;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.dragdrop.SplitDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPickerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomSheetDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapFragment;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Fragment;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SystemBarDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.GroupPanelTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MyTagHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsHandler;
import com.samsung.android.gallery.app.ui.viewer2.menu.AwesomeIntelligenceMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.DownloadMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.settings.CmhFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;

/* renamed from: A4.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0385u implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2259a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0385u(int i2, Object obj) {
        this.f2259a = i2;
        this.b = obj;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2259a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((BaseListFragment) obj).isFadingEdgeExtended();
            case 1:
                Y y = (Y) ((MediaItemLoader.LoadingStatusInformer) obj);
                return ListViewAdapterHandler.lambda$delayLoadMediaItemStart$2((ListViewHolder) y.f, y.e);
            case 2:
                return ((AlbumsDragFragment) obj).lambda$onViewCreated$0();
            case 3:
                return ((StoriesPinchViewPresenter.StoriesPinchMenuUpdater) obj).lambda$updatePopupMenuAction$2();
            case 4:
                return ((RecapFragment) obj).onTouchToolBar();
            case 5:
                return ((SlideshowV2Fragment) obj).onTouchToolBar();
            case 6:
                return ((DetailsHandler) obj).lambda$initialize$3();
            case 7:
                return ((AlbumPicturesFragment) obj).lambda$createProtectionGradient$27();
            case 8:
                return ((SharingPicturesFragment) obj).lambda$createProtectionGradient$12();
            case 9:
                return ((AwesomeIntelligenceMenuItem) obj).lambda$setMenuAttribute$0();
            case 10:
                return ((DownloadMenuItem) obj).lambda$setMenuAttribute$0();
            case 11:
                return ((EditMenuItem) obj).lambda$setMenuAttribute$0();
            case 12:
                return ((SplitDragAndDropDelegate) obj).lambda$startDragInner$0();
            case 13:
                return DeepSkyHelper.lambda$isDonationEnabled$0((Context) obj);
            case 14:
                return ((AtomicBoolean) obj).get();
            case 15:
                return ((PrivateDatabase) obj).fixWrongOwner();
            case 16:
                return ((ReorderDragManager) obj).getReorderPossible();
            case 17:
                return ((StoryHighlightFragment) obj).onTouchToolbar();
            case 18:
                return CmhFeatures.loadFeature((String) obj);
            case 19:
                return ((SystemBarDelegate) obj).lambda$new$0();
            case 20:
                return ((ContainerModel) obj).isTableMode();
            case 21:
                return ((BgmPickerDelegate) obj).allowTouchIntercept();
            case 22:
                return ((BottomSheetDelegate) obj).allowTouchIntercept();
            case 23:
                return ((ViewPagerDelegate) obj).hasFocus();
            case 24:
                return ((ViewerMenuDelegate) obj).isPppDim();
            case 25:
                return ((GroupPanelTransitionDelegate) obj).supportGroupPanelLandscapeMode();
            case 26:
                return ((SearchPicturesFragment) obj).lambda$createProtectionGradient$0();
            case 27:
                return ((MyTagHandler) obj).isTagButtonVisible();
            default:
                return ((CaptureHandler) obj).lambda$bindView$10();
        }
    }
}
