package k6;

import Te.G;
import Vf.C0867e0;
import android.graphics.RectF;
import androidx.work.ListenableFutureKt;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.container.picker.PickerConFragment;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerPresenter;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilmStripViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GuideDecoViewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.CacheDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.widget.appbar.BlurCustomCover;
import com.samsung.android.gallery.widget.effects.LightingEffectView;
import com.samsung.android.gallery.widget.listview.SingleTakenListView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((StoryHighlightListV2Fragment) obj).lambda$handleResolutionChange$2();
                return;
            case 1:
                ((CacheDelegate) obj).lambda$clearCurrentItemCache$0();
                return;
            case 2:
                ((DlnaDelegate) obj).lambda$launchVideoPlayer$4();
                return;
            case 3:
                ((HdrContentsDelegate) obj).lambda$new$6();
                return;
            case 4:
                ((BlurCustomCover) obj).lambda$updateImageView$1();
                return;
            case 5:
                ((BottomTabFragment) obj).lambda$setScreenMode$1();
                return;
            case 6:
                ((FilmStripDelegate) obj).lambda$setFilmStripAdapter$3();
                return;
            case 7:
                ((LightingEffectView) obj).applyEffect();
                return;
            case 8:
                ListenableFutureKt.launchFuture$lambda$1$lambda$0((C0867e0) obj);
                return;
            case 9:
                ((PickerConFragment) obj).lambda$finishChildFragment$1();
                return;
            case 10:
                G g = (G) obj;
                Iterator it = ((ArrayList) g.f3753h).iterator();
                while (it.hasNext()) {
                    ((Ae.b) it.next()).invoke((RectF) g.b);
                }
                g.f3751a = false;
                return;
            case 11:
                ((ListContainerPresenter) obj).lambda$onEssentialAlbumsChanged$2();
                return;
            case 12:
                ((CreatureCategoryHeaderContainer) obj).lambda$bindFaceMergeSuggestionIfNeeded$3();
                return;
            case 13:
                ((CreatureCategoryPresenter) obj).lambda$checkHiddenCreature$0();
                return;
            case 14:
                ((CreatureHidePresenter) obj).lambda$finish$12();
                return;
            case 15:
                ((ICreatureSelectView) obj).finish();
                return;
            case 16:
                ((PeopleSelectPresenter) obj).lambda$finish$7();
                return;
            case 17:
                ((SuggestedCreatureHeaderView) obj).updateViewVisibility();
                return;
            case 18:
                ((SuggestedCreatureSelectPresenter) obj).handleNextRelationship();
                return;
            case 19:
                ((ReorderHandler) obj).lambda$reorder$2();
                return;
            case 20:
                ((GalleryToolbar) obj).setVisibility(0);
                return;
            case 21:
                ((SingleTakenListView) obj).playPreview();
                return;
            case 22:
                ((BottomTabController) obj).lambda$onEnterSelectionMode$0();
                return;
            case 23:
                ((CategoryLocationHeaderViewHolder) obj).loadMarkerItem();
                return;
            case 24:
                ((CategoryLocationItemViewHolder) obj).lambda$bindImage$0();
                return;
            case 25:
                ((CategoryPeopleItemViewHolder) obj).lambda$bindImage$0();
                return;
            case 26:
                ((BgmPlayerV2) obj).pause();
                return;
            case 27:
                ((BottomDecoViewDelegate) obj).lambda$setVisible$8();
                return;
            case 28:
                ((FilmStripViewDelegate) obj).lambda$onConfigurationChanged$2();
                return;
            default:
                ((GuideDecoViewDelegate) obj).lambda$hideGuideDeco$1();
                return;
        }
    }
}
