package A9;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.app.controller.internals.SaveAsRemasterCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestStreamingVideo;
import com.samsung.android.gallery.app.receiver.SharedAlbumNotificationReceiver;
import com.samsung.android.gallery.app.service.MotionPhotoClipService;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.RecapPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurInterface;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import com.samsung.android.gallery.module.lottie.service.RecapVideoMaker;
import com.samsung.android.gallery.module.mde.executor.CreateFamilyGroup;
import com.samsung.android.gallery.module.mde.executor.CreateGroup;
import com.samsung.android.gallery.module.mde.executor.CreateGroupForShare;
import com.samsung.android.gallery.module.receiver.UsbDetachReceiver;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.trash.factory.MpQTrashFactory;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.imagetranslation.inpainting.InpainterHelper;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import com.samsung.android.sum.core.filter.DecorateFilter;
import com.samsung.android.sum.core.filter.ImgpDecorateFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterPlaceHolder;
import com.samsung.android.sum.core.filter.MediaFilterRetriever;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.types.MediaType;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import x0.w;
import x6.C0538a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2766a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2767c;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f2766a = i2;
        this.b = obj;
        this.f2767c = obj2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2766a) {
            case 0:
                ((RecapVideoMaker) this.b).lambda$createVideo$1((Consumer) this.f2767c, (w) obj, (String) obj2);
                return;
            case 1:
                ((CreateFamilyGroup) this.b).lambda$execute$0((Bundle) this.f2767c, (Integer) obj, (String[]) obj2);
                return;
            case 2:
                ((CreateGroup) this.b).lambda$requestLocalGroupCreation$0((Bundle) this.f2767c, (Integer) obj, (String[]) obj2);
                return;
            case 3:
                ((CreateGroupForShare) this.b).lambda$execute$0((Bundle) this.f2767c, (Integer) obj, (String[]) obj2);
                return;
            case 4:
                ((SharingServiceFragment) this.b).lambda$loadPreference$6((Context) this.f2767c, (SwitchPreferenceCompat) obj, (SettingPreference) obj2);
                return;
            case 5:
                Blackboard.lambda$dump$6((PrintWriter) this.b, (String) this.f2767c, (String) obj, (ArrayList) obj2);
                return;
            case 6:
                ((DocumentScanCmd.DocumentScanSaveTask) this.b).lambda$execute$0((Activity) this.f2767c, (String) obj, (Uri) obj2);
                return;
            case 7:
                ((ChangeBestItemCmd2) this.b).lambda$onExecute$3((MediaItem[]) this.f2767c, (Integer) obj, (Integer) obj2);
                return;
            case 8:
                ((SaveAsRemasterCmd) this.b).lambda$saveAsFile$2((MediaItem) this.f2767c, (String) obj, (Uri) obj2);
                return;
            case 9:
                ((UsbDetachReceiver) this.b).lambda$closeDevice$1((Context) this.f2767c, (String) obj, (Blackboard) obj2);
                return;
            case 10:
                ((RequestStreamingVideo) this.b).lambda$requestStreamingVideo$2((FileItemInterface) this.f2767c, (Integer) obj, (Uri) obj2);
                return;
            case 11:
                ((SharedAlbumNotificationReceiver) this.b).lambda$isAlreadyOnSharingPictures$8((boolean[]) this.f2767c, (String) obj, (Blackboard) obj2);
                return;
            case 12:
                ((MotionPhotoClipService) this.b).lambda$doJob$0((MediaItem) this.f2767c, (Boolean) obj, (String) obj2);
                return;
            case 13:
                SharedTransition.lambda$addInternal$0((View) this.b, (String[]) this.f2767c, (String) obj, (View) obj2);
                return;
            case 14:
                ((InpainterHelper) this.b).lambda$getInpaintedFrame$0((InpainterParam) this.f2767c, (Bitmap) obj, (Throwable) obj2);
                return;
            case 15:
                MediaFilterRetriever.lambda$retrieve$4((MediaFilter) this.b, (MediaFilter) this.f2767c, (MediaFilterRetriever.Predictor) obj, (MediaFilterRetriever.PredicateHandler) obj2);
                return;
            case 16:
                MediaFilterRetriever.lambda$retrieve$0((DecorateFilter) this.b, (MediaFilter) this.f2767c, (MediaFilterRetriever.Predictor) obj, (MediaFilterRetriever.PredicateHandler) obj2);
                return;
            case 17:
                MediaFilterRetriever.lambda$retrieve$1((ImgpDecorateFilter) this.b, (MediaFilter) this.f2767c, (MediaFilterRetriever.Predictor) obj, (MediaFilterRetriever.PredicateHandler) obj2);
                return;
            case 18:
                MediaFilterRetriever.lambda$retrieve$3((MediaFilterPlaceHolder) this.b, (MediaFilter) this.f2767c, (MediaFilterRetriever.Predictor) obj, (MediaFilterRetriever.PredicateHandler) obj2);
                return;
            case 19:
                ((MediaMuxerFilter) this.b).lambda$run$14((ArrayList) this.f2767c, (MediaType) obj, (Pair) obj2);
                return;
            case 20:
                ((MediaMuxerFilter) this.b).lambda$onReceiveExtraData$12((Map) this.f2767c, (String) obj, obj2);
                return;
            case 21:
                ((RestoreUserData) this.b).lambda$loadUserTag$11((BiConsumer) this.f2767c, (Boolean) obj, (List) obj2);
                return;
            case 22:
                ((RecapPreviewDelegate) this.b).lambda$initView$1((View) this.f2767c, (w) obj, (String) obj2);
                return;
            case 23:
                ((MpQTrashFactory) this.b).lambda$bulkInsert$1((AtomicInteger) this.f2767c, (String) obj, (List) obj2);
                return;
            case 24:
                ((PdcSearchDelegate) this.b).lambda$updateFilterMap$8((HashSet) this.f2767c, (String) obj, (List) obj2);
                return;
            case 25:
                ((SearchClusterResultContainer) this.b).lambda$loadClusterDataIncludeCarousel$2((ClusterResult.OnUiUpdateListener) this.f2767c, (ClusterResultType) obj, (ArrayList) obj2);
                return;
            case 26:
                ((SearchClusterResultContainer) this.b).lambda$loadClusterData$0((String) this.f2767c, (ClusterResultType) obj, (ArrayList) obj2);
                return;
            default:
                ThreadUtil.runOnUiThread(new C0538a((BlurInterface) this.b, (MediaItem) this.f2767c, (Bitmap) obj, 0));
                return;
        }
    }
}
