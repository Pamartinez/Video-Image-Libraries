package h4;

import android.content.ContentProviderResult;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.TabFragment;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisHelper;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterAiEditComposite;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupPanelSelectionMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.story.transcode.decoder.video.Decoder;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: h4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0464a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2648a;

    public /* synthetic */ C0464a(int i2) {
        this.f2648a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2648a) {
            case 0:
                return TabFragment.lambda$supportExitPredictiveBackInCurrent$1((MvpBaseFragment) obj);
            case 1:
                return SearchAnalysisHelper.lambda$checkAnalysisTipCondition$0((Map.Entry) obj);
            case 2:
                return RemasterAiEditComposite.lambda$getDetectedResultList$2((AbsRemasterAiEdit) obj);
            case 3:
                return SimilarPhotoHelper.lambda$clearSimilarPhoto$4((ContentProviderResult) obj);
            case 4:
                return ((ITranscoder) obj).isRunning();
            case 5:
                return ((ITranscoder) obj).hasOutputFormat();
            case 6:
                return ((ITranscoder) obj).isReady();
            case 7:
                return Objects.nonNull((View) obj);
            case 8:
                return MediaItemStory.isUserCuration((MediaItem) obj);
            case 9:
                return Objects.nonNull((Decoder) obj);
            case 10:
                return ((IBaseFragment) obj).isVolatileFragment();
            case 11:
                return CreatureData.of((MediaItem) obj).isCreatureHide;
            case 12:
                return CreatureHidePresenter.lambda$fillCreatureList$7((Map.Entry) obj);
            case 13:
                return Objects.nonNull((String) obj);
            case 14:
                return IdentityCreatureUtil.isPerson((String) obj);
            case 15:
                return IdentityCreatureUtil.isPet((String) obj);
            case 16:
                return ((MediaItem) obj).isPrivateItem();
            case 17:
                return GroupPanelSelectionMenuDelegate.lambda$getBestItem$3((MediaItem) obj);
            case 18:
                return GroupPanelSelectionMenuDelegate.lambda$allItemSelected$2((MediaItem) obj);
            case 19:
                return ((DrawerTabItem) obj).getLocationKey().startsWith("location://albums/otg/fileList");
            case 20:
                return LocationKey.isAlbumPictures((String) obj);
            case 21:
                return ViewerMenuDelegate.lambda$getToolbarMoreMenuItems$20((ViewerMenuItem) obj);
            case 22:
                return ViewerMenuDelegate.lambda$applyMenu$16((ViewerMenuItem) obj);
            case 23:
                return ((CreatureNameData) obj).isEmptyName();
            case 24:
                return ((CreatureNameData) obj).isTagged();
            case 25:
                return Objects.nonNull((PageItem) obj);
            case 26:
                return MapUtil.isValidLocation(((MediaItem) obj).getLatitude(), ((MediaItem) obj).getLongitude());
            case 27:
                return PageDataLoader.lambda$getNewTransitoryStories$8((MediaItem) obj);
            case 28:
                return BucketUtils.isRecent(((MediaItem) obj).getBucketID());
            default:
                return Objects.nonNull((Bitmap) obj);
        }
    }
}
