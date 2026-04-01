package A4;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.activity.FragmentDelegate;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.media.MediaMotionPhotoCaptureWorker;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.settings.ui.LabsConfigFragment;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.type.ShotModeType;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: A4.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0375j implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2251a;

    public /* synthetic */ C0375j(int i2) {
        this.f2251a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2251a) {
            case 0:
                return BaseListFragment.lambda$isDialogVisible$11((Fragment) obj);
            case 1:
                return ShotModeType.isLiveFocus(((MediaItem) obj).getSefFileType());
            case 2:
                return ((List) obj).isEmpty();
            case 3:
                return ((FilterResultsEntity) obj).isCreature();
            case 4:
                return FragmentDelegate.lambda$isBackPressCallbackAvailable$1((MvpBaseFragment) obj);
            case 5:
                return ViewUtils.isVisible(((ListViewHolder) obj).getRootView());
            case 6:
                return ((MediaItem) obj).isVirtualAlbum();
            case 7:
                return ((MediaItem) obj).isMergedAlbum();
            case 8:
                return ViewUtils.isVisible(((ListViewHolder) obj).getRootView());
            case 9:
                return AddressCompat.lambda$toAddressText$1((String) obj);
            case 10:
                return AddressCompat.lambda$toAddressTextChn$0((String) obj);
            case 11:
                return PickerSelectionHandler.lambda$getUriList$6((MediaItem) obj);
            case 12:
                return RelatedStoryLoader.lambda$pickRelatedStories$1((RelatedInfo) obj);
            case 13:
                return ((RelatedInfo) obj).hasLocation();
            case 14:
                return ((RelatedInfo) obj).hasPerson();
            case 15:
                return ((RelatedInfo) obj).isSameCategory();
            case 16:
                return AnalyticsQuery.lambda$getShotModeCount$0((Map.Entry) obj);
            case 17:
                return MdeAlbumHelper.lambda$getMediaTypeOfItems$0((FileItemInterface) obj);
            case 18:
                return MdeGroupHelper.lambda$hasNoFamilyAlbum$11((MediaItem) obj);
            case 19:
                return MediaItemMde.isUploading((FileItemInterface) obj);
            case 20:
                return AlbumChoiceBaseAdapter.lambda$initDisabledAlbumType$0((AlbumType) obj);
            case 21:
                return Objects.isNull((FileItemInterface) obj);
            case 22:
                return MediaUri.getInstance().matches(((Uri) obj).toString());
            case 23:
                return ((Uri) obj).toString().contains(".share");
            case 24:
                return ((MotionPhotoViewMode) obj).isEnabled();
            case 25:
                return InstantSlowMoUtils.lambda$checkInstantSlowMoSaveClipTitleShownPreference$0((Integer) obj);
            case 26:
                return MediaMotionPhotoCaptureWorker.lambda$prepareInternal$0((MediaPlayback) obj);
            case 27:
                return LabsConfigFragment.KnownSet.set.contains(((BooleanFeatures) obj).getKey());
            case 28:
                return ((File) obj).isFile();
            default:
                return ((String) obj).endsWith(".zip");
        }
    }
}
