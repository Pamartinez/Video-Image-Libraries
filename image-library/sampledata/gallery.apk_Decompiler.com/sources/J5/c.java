package J5;

import android.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.search.category.people.SelectMePresenter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchFiltersDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandHandler;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.bixby.activity.GalleryBixbyActivity;
import com.samsung.android.gallery.module.cloud.scpm.GotoLink;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.SurfaceChannelImpl;
import com.samsung.android.sum.core.descriptor.MFDescriptorBuilder;
import com.samsung.android.sum.core.descriptor.MFDescriptorHolder;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.filter.EncoderFilter;
import com.samsung.android.sum.core.filter.NNFWFilterGroup;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.StaplePluginFilter;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.service.RemoteServiceProxy;
import com.samsung.scsp.media.api.database.url.OneDriveUrlDbManager;
import com.samsung.scsp.media.api.job.MediaCreateUploadUrlJobImpl;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ((SearchFiltersDelegate) obj).lambda$updateData$0();
            case 1:
                return Blackboard.lambda$getContext$5((List) obj);
            case 2:
                return ((LiveMotionViewPager) obj).getAdapter();
            case 3:
                return Boolean.valueOf(((AlbumPicturesFragment) obj).loadSplitModeFromPreference());
            case 4:
                return ((ImageProcessor) obj).createBitmapOptions();
            case 5:
                return ((GotoLink) obj).loadJsonFile();
            case 6:
                return (FileOutputStream) obj;
            case 7:
                return ((EventContext) obj).getSelectedItems();
            case 8:
                return OneDriveUrlDbManager.lambda$getUrlForNDEType$0((Pair) obj);
            case 9:
                return ((OnDemandHandler) obj).lambda$initToolbar$0();
            case 10:
                return ((StoriesOnDemandDelegate) obj).lambda$handleConfirmRelation$3();
            case 11:
                return ((GalleryBixbyActivity) obj).getIntent();
            case 12:
                return ((SearchWordCollector) obj).lambda$isRubinEnabled$2();
            case 13:
                return CommonsKt.toCompletableFuture$lambda$34((FutureTask) obj);
            case 14:
                return ((MediaBufferGroup) obj).asList().get(0);
            case 15:
                return ((SurfaceChannelImpl) obj).lambda$new$1();
            case 16:
                return ((MFDescriptorHolder) obj).lambda$reset$1();
            case 17:
                return ((MFDescriptorBuilder) obj).loadType;
            case 18:
                return ((NNDescriptor) obj).getLoadingType();
            case 19:
                return ((EncoderFilter) obj).lambda$run$5();
            case 20:
                return NNFWFilterGroup.lambda$loadModel$2((ModelSelector.Item) obj);
            case 21:
                return ((NNFilter) obj).lambda$getDescriptor$0();
            case 22:
                return StaplePluginFilter.lambda$run$1((MutableMediaBuffer) obj);
            case 23:
                return RemoteServiceProxy.lambda$requestOneWay$1((Response) obj);
            case 24:
                return MediaCreateUploadUrlJobImpl.lambda$onStream$0((com.samsung.scsp.framework.core.api.Response) obj);
            case 25:
                return ((MvpBaseFragment) obj).getBlackboard();
            case 26:
                return ((PicturesFragment) obj).lambda$onBindView$1();
            case 27:
                return Integer.valueOf(((GalleryGridLayoutManager) ((GalleryGridLayoutManager) obj)).getHintHorizontalPadding(1));
            case 28:
                return ReorderDragManager.lambda$new$0((IBaseListView) obj);
            default:
                return ((SelectMePresenter) obj).lambda$assignMe$2();
        }
    }
}
