package j4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.mtp.MtpFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsFragment;
import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ActionModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.PppBmpCacheHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.MotionPhotoCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionMotionPhotoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectFeature;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParams;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1;
import com.samsung.android.vexfwk.param.VexFwkDepthMapParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerModes;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthParams;
import com.samsung.android.vexfwk.param.VexFwkImageEffectParams;
import com.samsung.android.vexfwk.param.VexFwkImageEnhancerParams;
import com.samsung.android.vexfwk.param.VexFwkImageTaggerResult;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new VirtualAlbumPicturesFragment();
            case 1:
                return new TrashContainerFragment();
            case 2:
                return new AlbumPicturesFragment();
            case 3:
                return new SuggestionsFragment();
            case 4:
                return new CategoryFragment();
            case 5:
                return new SearchLocationFragment();
            case 6:
                return new MtpFragment();
            case 7:
                return new LiveEffectVideoController();
            case 8:
                return new ShotModeHandler();
            case 9:
                return ContentViewCompositeFactory.lambda$createLiveEffectVideo$0();
            case 10:
                return new TextExtractionMotionPhotoHandler();
            case 11:
                return new ActionModeHandler();
            case 12:
                return new MotionVideoController();
            case 13:
                return new MotionPhotoCaptureHandler();
            case 14:
                return new PppBmpCacheHandler();
            case 15:
                return new MotionPhotoViewModeHandler();
            case 16:
                return Boolean.TRUE;
            case 17:
                return VexFwkImageOcrResultVersion.Companion._init_$lambda$0();
            case 18:
                return BitmapUtils.lambda$calcBestFrameTime$1();
            case 19:
                return VexFwkBokehEffectFeature.Companion._init_$lambda$0();
            case 20:
                return VexFwkBokehEffectParams.Companion._init_$lambda$0();
            case 21:
                return VexFwkBokehEffectParamsV1.Companion._init_$lambda$0();
            case 22:
                return VexFwkDepthMapParams.Companion._init_$lambda$0();
            case 23:
                return VexFwkDocumentRefinerModes.Companion._init_$lambda$0();
            case 24:
                return VexFwkDocumentRefinerParams.Companion._init_$lambda$0();
            case 25:
                return VexFwkDocumentRefinerResult.Companion._init_$lambda$0();
            case 26:
                return VexFwkFocalLengthParams.Companion._init_$lambda$0();
            case 27:
                return VexFwkImageEffectParams.Companion._init_$lambda$0();
            case 28:
                return VexFwkImageEnhancerParams.Companion._init_$lambda$0();
            default:
                return VexFwkImageTaggerResult.Companion._init_$lambda$0();
        }
    }
}
