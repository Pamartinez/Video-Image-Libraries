package Wf;

import Ae.b;
import P1.e;
import android.content.Context;
import androidx.appfunctions.AppFunctionUriGrant;
import androidx.appfunctions.ExecuteAppFunctionResponse;
import androidx.appfunctions.internal.AppSearchAppFunctionReader;
import androidx.appsearch.app.SearchResult;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttImageTranslationEngine;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl;
import com.samsung.android.motionphoto.utils.v2.io.HEIFMetaWriter;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter;
import com.samsung.android.vexfwk.docscan.VexFwkDocDewarpModes;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefineModes;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1;
import com.samsung.android.vexfwk.param.VexFwkDepthMapParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthParams;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDewarper;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner;
import com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.t;
import kotlin.jvm.internal.u;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                Throwable th = (Throwable) obj;
                ((e) this.e).d.removeCallbacks((e) this.f);
                return x.f4917a;
            case 1:
                return MotionPhotoEditImpl.addSEFData$lambda$4((MotionPhotoEditImpl) this.e, (MediaFile) this.f, (MediaFile) obj);
            case 2:
                return MotionPhotoEditImpl.addMPVDBox$lambda$17((MotionPhotoEditImpl) this.e, (b) this.f, (FileChannel) obj);
            case 3:
                return HEIFMetaWriter.removeCameraDebugInfo$lambda$7((t) this.e, (t) this.f, (FileChannel) obj);
            case 4:
                return Integer.valueOf(HEIFMetaWriter.writeXMP$lambda$4((ImageMetaReader.Box) this.e, (ByteBuffer) this.f, (FileChannel) obj));
            case 5:
                return JPEGMetaWriter.writeCameraDebugInfo$lambda$4((JPEGMetaWriter) this.e, (byte[]) this.f, (FileChannel) obj);
            case 6:
                return VexFwkDocDewarper.dewarpDocumentImpl$lambda$12$lambda$3((VexFwkDocumentCorners) this.e, (VexFwkDocDewarpModes.Companion.DewarpMode) this.f, (VexFwkMetadataNative) obj);
            case 7:
                return VexFwkDocRefiner.batchRefineDocument$lambda$10((VexFwkDocumentRefinerParams) this.e, (VexFwkDocumentCorners) this.f, (VexFwkMetadataNative) obj);
            case 8:
                return VexFwkDocRefiner.refineDocumentImpl$lambda$9$lambda$3((VexFwkDocRefineModes.Companion.ColorFilterType) this.e, (VexFwkDocRefineModes.Companion.EnhancementMode) this.f, (VexFwkMetadataNative) obj);
            case 9:
                return ExecuteAppFunctionResponse.Success.grantUriAccess$lambda$0((Context) this.e, (String) this.f, (AppFunctionUriGrant) obj);
            case 10:
                return AppSearchAppFunctionReader.searchTopLevelComponent$lambda$1((AppSearchAppFunctionReader) this.e, (LinkedHashMap) this.f, (SearchResult) obj);
            case 11:
                return VexFwkPortraitBokehEffect.applyBokehEffectInternal$lambda$13((VexFwkBokehEffectParamsV1) this.e, (String) this.f, (VexFwkMetadataNative) obj);
            case 12:
                return VexFwkDepthMapProcessor.getDepthInternal$lambda$5((VexFwkDepthMapParams) this.e, (String) this.f, (VexFwkMetadataNative) obj);
            case 13:
                return VexFwkFocalLengthProcessor.getFocalLengthInternal$lambda$5((VexFwkFocalLengthParams) this.e, (String) this.f, (VexFwkMetadataNative) obj);
            case 14:
                return VexFwkImageTranslator.showInstallPopupAsync$lambda$28((List) this.e, (Context) this.f, (String) obj);
            case 15:
                return ImageTranslator.showTranslationDialog$lambda$7((u) this.e, (ImageTranslator) this.f, (x) obj);
            default:
                return LttImageTranslationEngine.doImageTranslation$lambda$3((LttImageTranslationEngine) this.e, (ImageTranslateListener) this.f, (x) obj);
        }
    }
}
