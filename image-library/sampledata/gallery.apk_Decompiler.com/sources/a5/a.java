package A5;

import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.controller.album.RemoveAutoUpdatedItemsCmd;
import com.samsung.android.gallery.app.controller.internals.KeepCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveLiveEffectCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation.FaceClusterAnimationHelper;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewPresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandRecommendPresenter;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapPresenter;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuMap;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.PeopleDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.ListClusterTable;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.module.lottie.service.RecapVideoMaker;
import com.samsung.android.gallery.plugins.portrait.InitProgressDelegate;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.LabsConfigFragment;
import com.samsung.android.gallery.support.cache.CacheHelper;
import com.samsung.android.gallery.support.library.v0.GedApiCompatImpl;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowViewPager;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2263a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f2263a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2263a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return FaceClusterAnimationHelper.lambda$startTipCardAlphaAnimation$0((Animation.AnimationListener) obj2, (View) obj);
            case 1:
                return ((RecapVideoMaker) obj2).lambda$startRecord$4((Integer) obj);
            case 2:
                return ((SearchRelationshipPreviewPresenter) obj2).lambda$getTagName$0((PdcSearchDelegate) obj);
            case 3:
                return Boolean.valueOf(((RecommendationFragment) obj2).setInputBlock((String) obj));
            case 4:
                return Integer.valueOf(((AtomicInteger) obj2).getAndIncrement() / 100);
            case 5:
                return ((InitProgressDelegate) obj2).lambda$showDimAnimation$2((View) obj);
            case 6:
                return ((LabsAlbumBnRFragment) obj2).toReadableBackupName((String) obj);
            case 7:
                return LabsConfigFragment.lambda$clearPreference$3((Map) obj2, (BooleanFeatures) obj);
            case 8:
                return ((Preference) obj2).getContext().getString(((Integer) obj).intValue());
            case 9:
                return Boolean.valueOf(((GalleryListAdapter) obj2).isData(((Integer) obj).intValue()));
            case 10:
                return ((RecapPresenter) obj2).loadMediaItemForShare(((Integer) obj).intValue());
            case 11:
                return ((PenSelectionHandler) obj2).lambda$getChildDisHandler$2((GalleryListView) obj);
            case 12:
                return ((RemoveAutoUpdatedItemsCmd) obj2).lambda$getIdStringFromItems$1((MediaItem) obj);
            case 13:
                return Integer.valueOf(((ArrayList) obj2).indexOf((MediaItem) obj));
            case 14:
                return ((SuggestionContainerFragment) obj2).createFragment((String) obj);
            case 15:
                return ((LiveMotionViewPager) obj2).schedule((Integer) obj);
            case 16:
                return (CacheHelper) ((SparseArray) obj2).get(((Integer) obj).intValue());
            case 17:
                return Long.valueOf(((KeepCleanOutCmd) obj2).getId((MediaItem) obj));
            case 18:
                return ((MotionPhotoExportCmd) obj2).lambda$onItemSelected$0((String) obj);
            case 19:
                return ((RemoveLiveEffectCmd) obj2).lambda$executeInternal$0((FileItemInterface) obj);
            case 20:
                return Boolean.valueOf(((RevertOriginalImageCmd) obj2).updateFile((FileItemInterface) obj));
            case 21:
                return ((ViewerMenuMap) obj2).lambda$get$1((String) obj);
            case 22:
                return ((GedApiCompatImpl) obj2).newPrivateApi((String) obj);
            case 23:
                return ((VirtualAlbumPicturesFragment) obj2).lambda$createTipCard$4((TipCardView) obj);
            case 24:
                return ((ListPopupMenuDelegate) obj2).lambda$showPreview$10((ImageView) obj);
            case 25:
                return ((SlideDelegate) obj2).lambda$onSlideshowTimer$0((PeopleDataDelegate) obj);
            case 26:
                return ((SimpleSlideShowViewPager) obj2).schedule((Integer) obj);
            case 27:
                return ((ListClusterTable) obj2).createRecordInstance((MediaItem) obj);
            case 28:
                return ((SearchSorter) obj2).lambda$createCategorySorter$4((String) obj);
            default:
                return ((OnDemandRecommendPresenter) obj2).lambda$loadData$1((String) obj);
        }
    }
}
