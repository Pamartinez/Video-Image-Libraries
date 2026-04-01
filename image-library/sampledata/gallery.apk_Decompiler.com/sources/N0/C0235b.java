package n0;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import androidx.work.WorkerParameters;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkLauncherImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.CancelWorkRunnable;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureChoiceDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.NameViewHolder;
import com.samsung.android.gallery.app.ui.list.search.editcreature.RegisteredCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.SearchResultStoriesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcResultAdapter;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.RecapPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularGradientHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RecapCoverPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.CropCalculator;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularCollageViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MediaItemCorrector;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.bottom.BottomMoveBar;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import x0.w;

/* renamed from: n0.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0235b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0235b(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                WorkLauncherImpl.startWork$lambda$0((WorkLauncherImpl) this.e, (StartStopToken) this.f, (WorkerParameters.RuntimeExtras) this.g);
                return;
            case 1:
                ((CreatureCategoryHeaderContainer) this.e).lambda$bindFaceMergeSuggestionIfNeeded$1((SuggesterView) this.f, (List) this.g);
                return;
            case 2:
                ((SuggestedCreatureSelectPresenter) this.e).lambda$setAsRelation$5((String) this.f, (ArrayList) this.g);
                return;
            case 3:
                ImageView imageView = (ImageView) this.f;
                ((BottomMoveBar) this.e).lambda$bindImage$0(imageView, (Bitmap) this.g);
                return;
            case 4:
                ((CategoryLocationHeaderViewHolder) this.e).lambda$loadMarkerBitmap$0((MediaItem) this.f, (Bitmap) this.g);
                return;
            case 5:
                ((RecapPreviewDelegate) this.e).lambda$initView$2((String) this.f, (View) this.g);
                return;
            case 6:
                ((RecapPreviewDelegate) this.e).lambda$initView$0((View) this.f, (w) this.g);
                return;
            case 7:
                ((RecapPreviewDelegate) this.e).lambda$startPreview$3((w) this.f, (LottieAnimationView) this.g);
                return;
            case 8:
                ((DrawerTabViewAdapter) this.e).lambda$onBindViewHolder$3((DrawerTabItemViewHolder) this.f, (DrawerTabItem) this.g);
                return;
            case 9:
                ((EditCreatureNameFragment) this.e).lambda$createRecyclerView$13((RegisteredCreatureAdapter) this.f, (CreatureNameData.ContactType[]) this.g);
                return;
            case 10:
                ((NameViewHolder) this.e).lambda$bindImage$0((CreatureNameData) this.f, (Bitmap) this.g);
                return;
            case 11:
                ((PageDataLoader) this.e).lambda$loadData$1((List) this.f, (Consumer) this.g);
                return;
            case 12:
                ((KnoxAlbumOperator) this.e).lambda$dataObtained$6((Cursor[]) this.f, (Context) this.g);
                return;
            case 13:
                ((KnoxAlbumOperator) this.e).lambda$dataObtained$5((Context) this.f, (List) this.g);
                return;
            case 14:
                ((IrregularGradientHelper) this.e).lambda$setGradientBackground$2((View) this.f, (IrregularView) this.g);
                return;
            case 15:
                ((RecapCoverPage) this.e).lambda$onThumbnailLoadCompleted$3((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 16:
                RelatedPage.lambda$preloadBlurBitmap$3((MediaItem) this.e, (Blackboard) this.f, (Runnable) this.g);
                return;
            case 17:
                ((RelatedViewAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            case 18:
                ((YearQueryCache) this.e).lambda$syncIfDataChanged$0((String) this.f, (Runnable) this.g);
                return;
            case 19:
                ((IrregularCollageViewHolder) this.e).lambda$setViewMatrix$1((CropCalculator) this.f, (RectF) this.g);
                return;
            case 20:
                ((TransitionDelegate) this.e).lambda$prepareGroupPanelReturnTransition$9((MediaItem) this.f, (PhotoViewCompat) this.g);
                return;
            case 21:
                ((TransitionDelegate) this.e).lambda$setPreview$5((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 22:
                CancelWorkRunnable.forNameInline$lambda$0((WorkDatabase) this.e, (String) this.f, (WorkManagerImpl) this.g);
                return;
            case 23:
                MergeCreatureChoiceDialog.lambda$loadThumbnail$2((ImageView) this.e, (Bitmap) this.f, (MediaItem) this.g);
                return;
            case 24:
                ((SearchResultStoriesPresenter) this.e).lambda$onStoriesClicked$0((MediaItem) this.f, (ListViewHolder) this.g);
                return;
            case 25:
                ((PdcResultAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            case 26:
                ((PhotoViewCompat) this.e).setImage((MediaItem) this.f, (Bitmap) this.g);
                return;
            case 27:
                ((ContentViewerHolder) this.e).lambda$onPreviewLoaded$9((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 28:
                ((MediaItemCorrector) this.e).lambda$onPreviewLoad$0((Bitmap) this.f, (MediaItem) this.g);
                return;
            default:
                ((VuLauncher) this.e).lambda$launch$0((MediaItem) this.f, (String) this.g);
                return;
        }
    }
}
