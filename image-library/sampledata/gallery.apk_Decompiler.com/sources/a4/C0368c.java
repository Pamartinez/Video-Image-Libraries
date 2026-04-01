package A4;

import android.database.Cursor;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import com.google.android.material.appbar.model.view.BasicViewPagerAppBarView;
import com.samsung.android.gallery.app.activity.external.CropImageNavigatorController;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListBottomHandler;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.CategoryStoriesCardHolder;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.InboundScroller;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionFilmStripScrollDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.module.album.AlbumNewLabelUpdater;
import com.samsung.android.gallery.module.publisher.CursorPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.sum.core.filter.DecoderFilter;
import com.samsung.android.sum.core.filter.EncoderFilter;

/* renamed from: A4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0368c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0368c(int i2, BaseInputConnection baseInputConnection) {
        this.d = 2;
        this.e = i2;
        this.f = baseInputConnection;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BaseListBottomHandler) this.f).lambda$moveToScrollOnBottomLine$1(this.e);
                return;
            case 1:
                ((AlbumNewLabelUpdater) this.f).lambda$update$0(this.e);
                return;
            case 2:
                ((BaseInputConnection) this.f).sendKeyEvent(new KeyEvent(1, this.e));
                return;
            case 3:
                ((AlbumsBaseViewAdapter) this.f).lambda$scrollToDataPosition$6(this.e);
                return;
            case 4:
                ((CropImageNavigatorController) this.f).lambda$finishActivityOnUiThread$0(this.e);
                return;
            case 5:
                ((AlbumsDragAdapter) this.f).lambda$moveFolderToPosition$0(this.e);
                return;
            case 6:
                ((TroubleshootingFragment) this.f).lambda$resolveIssue$4(this.e);
                return;
            case 7:
                ((SharingsFragment) this.f).lambda$onDataChangedOnUi$0(this.e);
                return;
            case 8:
                ((LiveMotionViewPager) this.f).lambda$setVisibility$9(this.e);
                return;
            case 9:
                ((AlbumPicturesFragment) this.f).lambda$handleOrientationChange$21(this.e);
                return;
            case 10:
                ((AlbumPicturesPresenter) this.f).lambda$notifyDataChanged$1(this.e);
                return;
            case 11:
                CursorPublisher.lambda$dumpDataCount$12((PreferenceName) this.f, this.e);
                return;
            case 12:
                StoriesDataPublisher.lambda$publishStoryAlbumFileData$2((Cursor[]) this.f, this.e);
                return;
            case 13:
                ((ScreenShotFilterDelegate) this.f).lambda$initScrollPosition$2(this.e);
                return;
            case 14:
                ((FixDateTimeCmd) this.f).lambda$onComplete$13(this.e);
                return;
            case 15:
                BasicViewPagerAppBarView.moveNextAndRemove$lambda$11$lambda$10((BasicViewPagerAppBarView) this.f, this.e);
                return;
            case 16:
                ((SaveHideRuleCmd) this.f).lambda$executeHide$8(this.e);
                return;
            case 17:
                ((InboundScroller) this.f).lambda$setPositionWithoutScroll$0(this.e);
                return;
            case 18:
                ((SelectionFilmStripScrollDelegate) this.f).lambda$onScrollStateChanged$0(this.e);
                return;
            case 19:
                ((ViewPagerDelegate) this.f).lambda$holdPosition$0(this.e);
                return;
            case 20:
                ((com.samsung.android.gallery.widget.story.transitory.ViewPagerDelegate) this.f).lambda$updateDataInternal$1(this.e);
                return;
            case 21:
                ((MyTagView) this.f).lambda$updateVisibilityAfterChanged$1(this.e);
                return;
            case 22:
                ((CategoryStoriesCardHolder) this.f).lambda$handleEffectOnPageSelected$1(this.e);
                return;
            case 23:
                ((RemoteClient) this.f).lambda$sendFiles$1(this.e);
                return;
            case 24:
                ((DecoderFilter) this.f).lambda$run$0(this.e);
                return;
            case 25:
                ((EncoderFilter) this.f).lambda$run$6(this.e);
                return;
            case 26:
                ((PicturesFragment) this.f).lambda$preloadViewPool$3(this.e);
                return;
            case 27:
                ((ListViewHolder) this.f).itemView.getLayoutParams().height = this.e;
                return;
            case 28:
                ((FilmStripDelegate) this.f).lambda$recoverFocus$4(this.e);
                return;
            default:
                ((CreatureSelectPresenterV2) this.f).lambda$generateAutoAlbum$8(this.e);
                return;
        }
    }

    public /* synthetic */ C0368c(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }
}
