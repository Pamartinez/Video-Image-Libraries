package B8;

import X3.c;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapPresenter;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.livemotion.theme.Transform;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoFormat;
import com.samsung.android.scs.ai.sdkcommon.asr.DialogInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.SpeechInfo;
import com.samsung.android.sdk.scs.ai.visual.VisualErrorCode;
import com.samsung.android.sum.core.DebugUtils;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.ServiceStatus;
import com.samsung.android.sum.core.types.Status;
import com.samsung.scsp.framework.core.network.base.NetworkImpl;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2771a;
    public final /* synthetic */ int b;

    public /* synthetic */ b(int i2, int i7) {
        this.f2771a = i7;
        this.b = i2;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2771a;
        int i7 = this.b;
        switch (i2) {
            case 0:
                return MediaItemUtil.containsAlbum((MediaItem) ((Blackboard) obj).read("data://albums/current", null), i7);
            case 1:
                return StoriesPinchViewPresenter.lambda$updateStoryTheme$5(i7, (MediaItem) obj);
            case 2:
                return RecapPresenter.lambda$findRecapItem$1(i7, (MediaItem) obj);
            case 3:
                return UpdateOrderCmd.lambda$getOrgIndex$2(i7, (MediaItem) obj);
            case 4:
                return Transform.lambda$isTranslate$0(i7, (Transform.TYPE) obj);
            case 5:
                return StoriesPinModel.lambda$getMediaItemByAlbumId$9(i7, (MediaItem) obj);
            case 6:
                return StoryLevel2Cat.lambda$isPet$0(i7, (StoryLevel2Cat) obj);
            case 7:
                return StoryLevel2Cat.lambda$isCollection$1(i7, (StoryLevel2Cat) obj);
            case 8:
                return StoryType.lambda$getCategory$0(i7, (StoryType) obj);
            case 9:
                return StoryType.lambda$getTypesByCategory$1(i7, (StoryType) obj);
            case 10:
                return StoryType.lambda$getTypeByValue$2(i7, (StoryType) obj);
            case 11:
                return MediaItemUtil.lambda$containsAlbum$3(i7, (MediaItem) obj);
            case 12:
                return RecapDataVars.lambda$updatePeopleName$45(i7, (RecapImage) obj);
            case 13:
                return Integer.valueOf(((MotionPhotoFormat) obj).versionCode).equals(Integer.valueOf(i7));
            case 14:
                return DialogInfo.lambda$getSpeechInfosById$0(i7, (SpeechInfo) obj);
            case 15:
                return VisualErrorCode.lambda$toCoreErrorCode$0(i7, (VisualErrorCode) obj);
            case 16:
                return DebugUtils.SumDebugLevel.lambda$nameOf$0(i7, (DebugUtils.SumDebugLevel) obj);
            case 17:
                return DebugUtils.SystemDebugLevel.lambda$valueOf$0(i7, (DebugUtils.SystemDebugLevel) obj);
            case 18:
                return Arrays.stream((int[]) ((Map.Entry) obj).getValue()).anyMatch(new c(i7, 1));
            case 19:
                return DataType.lambda$from$0(i7, (DataType) obj);
            case 20:
                return MediaType.lambda$of$0(i7, (MediaType) obj);
            case 21:
                return ServiceStatus.lambda$from$0(i7, (ServiceStatus) obj);
            case 22:
                return Status.lambda$from$0(i7, (Status) obj);
            case 23:
                return NetworkImpl.lambda$close$5(i7, (HttpURLConnection) obj);
            case 24:
                return PicturesViewAdapter.lambda$updateAllDividerCheckbox$4(i7, (Integer) obj);
            case 25:
                return BitmapUtils.lambda$calcBestFrameTime$0(i7, (Pair) obj);
            case 26:
                return ((RelatedInfo) obj).isSameStory(i7);
            default:
                return FileListFragment.lambda$computePositionMap$6(i7, (Integer) obj);
        }
    }
}
