package com.samsung.android.motionphoto.utils.ex;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.objectcapture.multi.MultiObjectViewManager;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularCollagePage;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverFiles;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.fileio.cmh.CmhGroupMediaFileOperation;
import com.samsung.android.gallery.module.fileio.mp.MpGroupMediaFileOperation;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptorHolder;
import com.samsung.android.sum.core.filter.EncoderFilter;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.filter.OutStreamPluginFilter;
import com.samsung.android.sum.core.filter.SyncFilter;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.Animator;
import com.sec.android.gallery3d.R;
import i3.a;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3244a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f3244a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3244a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MotionPhotoVideoUtils) obj2).sortSEFDataMap((Map) obj);
            case 1:
                return Double.valueOf(Math.pow((double) (((Point) obj).y - ((Point) obj2).y), 2.0d) + Math.pow((double) (((Point) obj).x - ((Point) obj2).x), 2.0d));
            case 2:
                return (IInterface) ((AiServiceExecutorFactory.ServiceStubFactory) obj2).createStub((IBinder) obj);
            case 3:
                return C2paManifestList.checkInvalid$lambda$21((com.samsung.android.sdk.scs.ai.visual.c2pa.b) obj2, obj);
            case 4:
                return ((MFDescriptorHolder) obj2).lambda$reset$0((Function) obj);
            case 5:
                return EncoderFilter.lambda$new$0((CodecDescriptor) obj2, (String) obj);
            case 6:
                return ((MediaFilter) obj).run((MediaBuffer) obj2);
            case 7:
                return ((ImgpFilter) obj2).lambda$init$0(obj);
            case 8:
                return ((MediaMuxerFilter) obj2).lambda$run$15((File) obj);
            case 9:
                return ((NNFilter) obj2).lambda$run$2((MediaBuffer) obj);
            case 10:
                return ((ModelSelector) obj).select(MediaBuffer.mutableOf((MediaFormat) obj2));
            case 11:
                return ((OutStreamPluginFilter) obj2).lambda$init$0((String) obj);
            case 12:
                return ((SyncFilter) obj2).lambda$run$0((MediaBuffer) obj);
            case 13:
                return ((MediaFilter) obj2).run((MediaBuffer) obj);
            case 14:
                return NumericEnum.fromValue((Class) obj2, Integer.parseInt((String) obj));
            case 15:
                return ((Animator) obj2).lambda$getInterpolatedParams$1((AnimationParams) obj);
            case 16:
                return Integer.valueOf(((MultiClusterAdapter) obj2).getDataPosition(((Integer) obj).intValue()));
            case 17:
                return ((MediaData) obj2).read(((Integer) obj).intValue());
            case 18:
                return Integer.valueOf(((CmhGroupMediaFileOperation) obj2).getStorageTypeFromContentValues((FileItemInterface) obj));
            case 19:
                return ((StoryHighlightPresenter) obj2).loadMediaItemForShare(((Integer) obj).intValue());
            case 20:
                return ((AiEditList) obj2).bindAndGetView((AiEditItem) obj);
            case 21:
                return ((AiProcessingViewHandler) obj2).lambda$showDimAnimation$6((View) obj);
            case 22:
                return SimilarPhotoHelper.lambda$clearSimilarPhoto$3((ContentValues) obj2, (MediaItem) obj);
            case 23:
                return MultiObjectViewManager.fixDimensions$lambda$6((a) obj2, obj);
            case 24:
                return Integer.valueOf(((MpGroupMediaFileOperation) obj2).getStorageTypeFromContentValues((FileItemInterface) obj));
            case 25:
                return ((CategoryFragment) obj2).lambda$createTipCard$4((TipCardView) obj);
            case 26:
                return FoldStateManager.lambda$getInstance$1((Activity) obj2, (String) obj);
            case 27:
                return RecoverFiles.lambda$update$1((HashMap) obj2, (String) obj);
            case 28:
                return ((IrregularCollagePage) obj2).rectToString((RectF) obj);
            default:
                return ((View) obj2).findViewById(R.id.content_container);
        }
    }
}
