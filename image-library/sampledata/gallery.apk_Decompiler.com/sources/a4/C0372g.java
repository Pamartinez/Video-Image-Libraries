package A4;

import B2.C0003d;
import B2.l;
import android.widget.PopupMenu;
import com.google.android.material.textfield.TextInputLayout;
import com.samsung.android.gallery.app.activity.external.GalleryExternalActivity;
import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;
import com.samsung.android.gallery.app.activity.external.StoriesViewNavigatorDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.HeaderSimpleData;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderPeople;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupCountUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupShotItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.HighlightGroupItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SuperSlowGroupItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.plugins.motionphoto.Functions;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewHolder;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMoreMenu;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripLayoutManager;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import z2.r;

/* renamed from: A4.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0372g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0372g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((PopupMenu) obj).dismiss();
                return;
            case 1:
                ((BaseListViewAdapter) obj).notifyDataSetChanged();
                return;
            case 2:
                ((FastOptionMoreMenu) obj).lambda$updateAnchor$1();
                return;
            case 3:
                ((C0003d) obj).s(true);
                return;
            case 4:
                l lVar = (l) obj;
                boolean isPopupShowing = lVar.f48h.isPopupShowing();
                lVar.s(isPopupShowing);
                lVar.m = isPopupShowing;
                return;
            case 5:
                ((TextInputLayout) obj).g.requestLayout();
                return;
            case 6:
                ((AlbumsFragment) obj).lambda$onFolderCreated$1();
                return;
            case 7:
                ((AlbumsViewAdapter) obj).notifyDataSetChanged();
                return;
            case 8:
                ((HeaderSimpleData) obj).reloadData();
                return;
            case 9:
                ((StoryHeaderPeople) obj).lambda$loadPeopleData$1();
                return;
            case 10:
                ((Functions) obj).lambda$share$5();
                return;
            case 11:
                ((MotionPhotoViewHolder) obj).stopPlayback();
                return;
            case 12:
                ((PhotoPreView) obj).requestLayout();
                return;
            case 13:
                ((FilmStripLayoutManager) obj).lambda$stopOverScroll$1();
                return;
            case 14:
                ((AlbumsBasePinchAnimationManager) obj).lambda$setFakeViewLayoutAlpha$0();
                return;
            case 15:
                ((ThumbnailRequestHolder) obj).bindResult();
                return;
            case 16:
                ((AlbumsBaseViewAdapter) obj).lambda$new$0();
                return;
            case 17:
                ((GroupCountUi) obj).lambda$setCountViewText$0();
                return;
            case 18:
                ((GroupShotItemLoader) obj).lambda$onBindInternal$0();
                return;
            case 19:
                ((HighlightGroupItemLoader) obj).lambda$onBindInternal$0();
                return;
            case 20:
                ((SuperSlowGroupItemLoader) obj).lambda$onBindInternal$0();
                return;
            case 21:
                ((FlexBoxListView) obj).retryLayout();
                return;
            case 22:
                ((GalleryExternalActivity) obj).lambda$setLockScreenView$0();
                return;
            case 23:
                ((LocationPickerActivity) obj).lambda$requestCurrentLocation$5();
                return;
            case 24:
                ((r) obj).l();
                return;
            case 25:
                ((StoriesViewNavigatorDelegate) obj).lambda$finishByFailure$4();
                return;
            case 26:
                ((AlbumChoicePresenter) obj).lambda$exitChoiceFragment$1();
                return;
            case 27:
                ((FloatingRecommendationDelegate) obj).startSuggestionSpringAnimation();
                return;
            case 28:
                ((ForegroundViewController) obj).lambda$handleResolutionChange$3();
                return;
            default:
                ((ImageLoader) obj).lambda$processLoadedBitmap$4();
                return;
        }
    }
}
