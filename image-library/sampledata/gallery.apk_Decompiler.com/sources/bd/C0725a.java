package Bd;

import Ae.b;
import Sf.k;
import Vf.C0886x;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoEditImpl;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.android.motionphoto.utils.v2.SEFDataInfo;
import com.samsung.android.motionphoto.utils.v2.SEFEditImpl;
import com.samsung.android.motionphoto.utils.v2.io.HEIFMetaWriter;
import com.samsung.android.motionphoto.utils.v2.video.VideoTask;
import com.samsung.android.sdk.moneta.common.SafeJson;
import com.samsung.android.sdk.moneta.event.service.EventContentProviderClient;
import com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternal;
import com.samsung.android.sdk.scs.ai.visual.c2pa.Action;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAssertion;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifest;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import com.samsung.android.sdk.scs.ai.visual.c2pa.ValidationStatus;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDewarper;
import com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import java.nio.channels.FileChannel;
import kotlin.jvm.internal.j;
import lg.g;
import qe.C1230f;

/* renamed from: Bd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0725a implements b {
    public final /* synthetic */ int d;

    public /* synthetic */ C0725a(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        boolean z;
        switch (this.d) {
            case 0:
                return VexFwkWineDetector.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 1:
                return VexFwkWineDetector.configure$lambda$1((VexFwkWineDetector) obj);
            case 2:
                return SafeJson.json$lambda$0((g) obj);
            case 3:
                return EventContentProviderClient.getSerializableEvent$lambda$10((g) obj);
            case 4:
                return EventContentProviderClient.queryEvents$lambda$2$lambda$1$lambda$0((g) obj);
            case 5:
                return EventContentProviderClient.queryEvents$lambda$16$lambda$15$lambda$14((g) obj);
            case 6:
                return TravelPlanInternal.Companion.fromString$lambda$2((g) obj);
            case 7:
                k kVar = (k) obj;
                j.e(kVar, "it");
                return kVar.iterator();
            case 8:
                return obj;
            case 9:
                if (obj == null) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 10:
                C1230f fVar = (C1230f) obj;
                if (fVar instanceof C0886x) {
                    return (C0886x) fVar;
                }
                return null;
            case 11:
                return Boolean.valueOf(VibeRenderEffectBase.hasVisibleView$lambda$2((WeakReference) obj));
            case 12:
                return CommonsKt.toHexString$lambda$43$lambda$42(((Byte) obj).byteValue());
            case 13:
                return Long.valueOf(MediaFile.size$lambda$12((FileChannel) obj));
            case 14:
                return Boolean.valueOf(MediaFile.mimeType_delegate$lambda$5$lambda$4$lambda$0((FileChannel) obj));
            case 15:
                return Boolean.valueOf(MediaFile.mimeType_delegate$lambda$5$lambda$4$lambda$1((FileChannel) obj));
            case 16:
                return Boolean.valueOf(MediaFile.mimeType_delegate$lambda$5$lambda$4$lambda$2((FileChannel) obj));
            case 17:
                return Boolean.valueOf(MediaFile.mimeType_delegate$lambda$5$lambda$4$lambda$3((FileChannel) obj));
            case 18:
                return Boolean.valueOf(MediaFile.getMimeTypeOfMediaAt$lambda$11$lambda$7((FileChannel) obj));
            case 19:
                return Boolean.valueOf(MediaFile.getMimeTypeOfMediaAt$lambda$11$lambda$8((FileChannel) obj));
            case 20:
                return Boolean.valueOf(MediaFile.getMimeTypeOfMediaAt$lambda$11$lambda$9((FileChannel) obj));
            case 21:
                return Boolean.valueOf(MediaFile.getMimeTypeOfMediaAt$lambda$11$lambda$10((FileChannel) obj));
            case 22:
                return MotionPhotoEditImpl.encodeImage$lambda$39((FileChannel) obj);
            case 23:
                return MotionScrap.prepareTasks$lambda$36((VideoTask) obj);
            case 24:
                return SEFEditImpl.commit$lambda$26((SEFDataInfo) obj);
            case 25:
                return HEIFMetaWriter._init_$lambda$1((FileDescriptor) obj);
            case 26:
                return C2paManifest.Builder.build$lambda$2((C2paAssertion) obj);
            case 27:
                return C2paManifest.Builder.build$lambda$3((Action) obj);
            case 28:
                return C2paManifestList.checkInvalid$lambda$22((ValidationStatus) obj);
            default:
                return VexFwkDocDewarper.configure$lambda$1((VexFwkDocDewarper) obj);
        }
    }
}
