package Ad;

import Ae.b;
import Ff.q;
import Tf.h;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Message;
import android.view.textclassifier.TextClassification;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.PhotoEditorServiceManager;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode.TextExtractionActionModeHelper;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl;
import com.samsung.android.motionphoto.utils.v2.SEFDataInfo;
import com.samsung.android.motionphoto.utils.v2.SEFEditImpl;
import com.samsung.android.motionphoto.utils.v2.SEFInfoImpl;
import com.samsung.android.motionphoto.utils.v2.XMPEditImpl;
import com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError;
import com.samsung.android.sesl.visualeffect.utils.WeakViewHashSet;
import com.samsung.android.vexfwk.imageenhancer.EnhanceType;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferStyles;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkImageEffectParams;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBaseFuture;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor;
import com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer;
import com.samsung.android.vexfwk.sdk.imgstyletransfer.VexFwkImageStyleTransfer;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor;
import fg.c;
import java.lang.ref.WeakReference;
import java.nio.channels.FileChannel;
import java.util.function.Consumer;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1183a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((VexFwkMetadataNative) obj).set(VexFwkMetadataKey.ROTATION_DEGREE.INSTANCE, Integer.valueOf(((VexFwkOrientation) obj2).getValue()));
                return x.f4917a;
            case 1:
                j.e(obj, "it");
                return ((q) obj2).invoke();
            case 2:
                return Boolean.valueOf(((Class) obj2).isInstance(obj));
            case 3:
                return PhotoEditorServiceManager.clientMessenger$lambda$0((PhotoEditorServiceManager) obj2, (Message) obj);
            case 4:
                return MotionPhotoEditImpl.addMPVDBox$lambda$18((byte[]) obj2, (FileChannel) obj);
            case 5:
                return Boolean.valueOf(MotionPhotoInfoImpl.isValid$lambda$9$lambda$8((Long) obj2, (FileChannel) obj));
            case 6:
                return Boolean.valueOf(SEFEditImpl.removeData$lambda$17((int[]) obj2, (SEFDataInfo) obj));
            case 7:
                return Boolean.valueOf(SEFEditImpl.removeData$lambda$15((String[]) obj2, (SEFDataInfo) obj));
            case 8:
                return Long.valueOf(SEFEditImpl.commit$lambda$39((SEFEditImpl) obj2, (FileChannel) obj));
            case 9:
                return SEFInfoImpl.load$lambda$8((SEFInfoImpl) obj2, (FileChannel) obj);
            case 10:
                return Long.valueOf(XMPEditImpl.commit$lambda$10((XMPEditImpl) obj2, (FileChannel) obj));
            case 11:
                return JPEGMetaWriter.removeCameraDebugInfo$lambda$5((JPEGMetaWriter) obj2, (FileChannel) obj);
            case 12:
                return C2paError.Companion.fromErrorString$lambda$0((C2paError) obj2, (h) obj);
            case 13:
                return VexFwkHelperBaseFuture._init_$lambda$0((VexFwkHelperBaseFuture) obj2, (Throwable) obj);
            case 14:
                return VexFwkHelperConfigurationFuture._init_$lambda$1((VexFwkHelperConfigurationFuture) obj2, (Throwable) obj);
            case 15:
                return WeakViewHashSet.forEachAlive$lambda$2((Consumer) obj2, (WeakReference) obj);
            case 16:
                Throwable th = (Throwable) obj;
                ((c) obj2).d((Object) null);
                return x.f4917a;
            case 17:
                if (obj == ((C1183a) obj2)) {
                    return "(this Collection)";
                }
                return String.valueOf(obj);
            case 18:
                return TextExtractionActionModeHelper.startActionMode$lambda$0((TextExtractionActionModeHelper) obj2, (TextClassification) obj);
            case 19:
                return VexFwkImageEffectProcessor.applyEffectImpl$lambda$9$lambda$3((VexFwkImageEffectParams) obj2, (VexFwkMetadataNative) obj);
            case 20:
                return VexFwkImageEnhancer.applyImageEnhancer$lambda$11$lambda$2((EnhanceType) obj2, (VexFwkMetadataNative) obj);
            case 21:
                ((VexFwkSessionRequest.BufferSetter) obj).add(0, (Bitmap) obj2);
                return x.f4917a;
            case 22:
                return VexFwkImageStyleTransfer.applyStyleTransferImpl$lambda$9$lambda$3((VexFwkImageStyleTransferStyles.Companion.StyleTransferType) obj2, (VexFwkMetadataNative) obj);
            default:
                return VexFwkObjectExtractor.extractObject$lambda$7$lambda$2((PointF) obj2, (VexFwkMetadataNative) obj);
        }
    }

    public /* synthetic */ f(c cVar, fg.b bVar) {
        this.d = 16;
        this.e = cVar;
    }
}
