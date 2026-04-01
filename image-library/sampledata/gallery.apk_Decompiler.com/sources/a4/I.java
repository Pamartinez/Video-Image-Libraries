package A4;

import Ad.f;
import Bd.C0725a;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.WindowMetrics;
import androidx.window.embedding.EmbeddingAdapter;
import androidx.window.embedding.SplitRule;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.UriBarcodeAdapterFactory;
import com.samsung.android.gallery.app.activity.GalleryActivity;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuBuilder;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.service.download.CloudDownloadTask;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.motionphoto.utils.v2.SEFEditImpl;
import com.samsung.android.scs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.tts.TtsPackageInfo;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.SendChannelRouter;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.service.ServiceStub;
import com.samsung.o3dp.lib3dphotography.O3DPObjType;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRect;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import h3.a;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class I implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2238a;
    public final /* synthetic */ Object b;

    public /* synthetic */ I(int i2, Object obj) {
        this.f2238a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2238a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MenuDataBinding) obj2).hasItem(((Integer) obj).intValue());
            case 1:
                return ((ThumbnailPreviewDelegate) obj2).canPlayback((PreviewViewHolder) obj);
            case 2:
                return ((GalleryActivity) obj2).isDialogShowing((String) obj);
            case 3:
                return ((AlbumsBaseViewAdapter) obj2).isNewAlbum((MediaItem) obj);
            case 4:
                return AnalyticsQuery.lambda$loadSearchAnalysisStatus$2((HashMap) obj2, (String) obj);
            case 5:
                return ((GalleryListAdapter) obj2).isData(((Integer) obj).intValue());
            case 6:
                return ((MxAlbumsViewAdapter) obj2).isAlbumData((Integer) obj);
            case 7:
                return ((BiPredicate) obj2).test(((Map.Entry) obj).getKey(), (Bitmap) ((Map.Entry) obj).getValue());
            case 8:
                return ((SparseArray) obj2).contains(((Integer) obj).intValue());
            case 9:
                return ((ViewerMenuBuilder) obj2).lambda$build$0((ViewerMenuItem) obj);
            case 10:
                return ((ContentDownloadResult.DownloadedContent) obj).getItemId().equals(MediaItemMde.getItemId((FileItemInterface) obj2));
            case 11:
                return LocalProviderDebugHelper.lambda$buildIdList$0((Function) obj2, (FileItemInterface) obj);
            case 12:
                return VibeRenderEffectBase.hasVisibleView$lambda$3((C0725a) obj2, obj);
            case 13:
                return EmbeddingAdapter.m30translateParentMetricsPredicate$lambda4((SplitRule) obj2, (WindowMetrics) obj);
            case 14:
                return ((ClusterResultsEntry) obj2).filter((ClusterResultsEntity) obj);
            case 15:
                return ((FilterResultsEntry) obj2).lambda$initEntry$0((FilterResultsEntity) obj);
            case 16:
                return ((ThumbnailInterface) obj).getPath().equals(((MotionPhotoUtils.SectionInfo) obj2).filePath);
            case 17:
                return SEFEditImpl.removeData$lambda$18((f) obj2, obj);
            case 18:
                return SEFEditImpl.removeData$lambda$16((f) obj2, obj);
            case 19:
                return ((BTCLocaleInfo) obj2).lambda$getDefaultPackages$0((TtsPackageInfo) obj);
            case 20:
                return ((com.samsung.android.sivs.ai.sdkcommon.asr.BTCLocaleInfo) obj2).lambda$getDefaultPackages$0((com.samsung.android.sivs.ai.sdkcommon.tts.TtsPackageInfo) obj);
            case 21:
                return SendChannelRouter.lambda$evaluate$4((MediaBuffer) obj2, (Map.Entry) obj);
            case 22:
                return ((NNFWDescriptor) ((MediaFilter) obj).getDescriptor()).getNNFileDescriptor().getName().equals(((ModelSelector.Item) obj2).name);
            case 23:
                return ServiceStub.lambda$request$0((Request) obj2, (Integer) obj);
            case 24:
                return O3DPRectUtil.lambda$extractTargetsFromList$0((O3DPObjType) obj2, (O3DPRect) obj);
            case 25:
                return ((Class) ((Map.Entry) obj).getKey()).isInstance(obj2);
            case 26:
                return ((Class) ((Map.Entry) obj).getKey()).isInstance((Throwable) obj2);
            case 27:
                return ((CloudDownloadTask) obj2).filter((MediaItem) obj);
            case 28:
                return ((BaseCmd) obj2).findMatched((Map.Entry) obj);
            default:
                return UriBarcodeAdapterFactory.isQuickShareUri$lambda$1((a) obj2, obj);
        }
    }
}
