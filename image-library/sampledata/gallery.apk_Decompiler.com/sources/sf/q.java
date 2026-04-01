package Sf;

import Ae.a;
import android.app.RemoteAction;
import androidx.appfunctions.internal.SchemaAppFunctionInventory;
import androidx.appfunctions.service.internal.AggregatedAppFunctionInvoker;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkManagerImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.impl.ArcSoftObjectCaptureImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.impl.SribObjectCaptureImpl;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.action.ActionManager;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.CouponAction;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.WalletAction;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleFactory;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.SimpleCapsuleClickListener;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeHelper;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.android.motionphoto.utils.v2.SEFEditImpl;
import com.samsung.android.motionphoto.utils.v2.XMPInfoImpl;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.motionphoto.utils.v2.io.MP4MetaReader;
import com.samsung.android.motionphoto.utils.v2.play.GroupMPPlayback;
import com.samsung.android.vexfwk.param.VexFwkParamBaseParcelable;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import java.io.File;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ q(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return obj;
            case 1:
                return ArcSoftObjectCaptureImpl.instance_delegate$lambda$2((ArcSoftObjectCaptureImpl) obj);
            case 2:
                return SribObjectCaptureImpl.instance_delegate$lambda$2((SribObjectCaptureImpl) obj);
            case 3:
                return ObjectCaptureDrawHelperImpl.prepareToolbarActionManager$lambda$10((ObjectCaptureDrawHelperImpl) obj);
            case 4:
                return MediaFile.mimeType_delegate$lambda$5((MediaFile) obj);
            case 5:
                return MotionScrap.videoTranscoder_delegate$lambda$2((MotionScrap) obj);
            case 6:
                return SEFEditImpl.dataInfos_delegate$lambda$2((SEFEditImpl) obj);
            case 7:
                return XMPInfoImpl.xmp_delegate$lambda$0((XMPInfoImpl) obj);
            case 8:
                return ImageMetaReader.Box.data_delegate$lambda$0((ImageMetaReader.Box) obj);
            case 9:
                return MP4MetaReader.mpInfo_delegate$lambda$0((MP4MetaReader) obj);
            case 10:
                return VexFwkHelperBase.impl_delegate$lambda$0((VexFwkHelperBase) obj);
            case 11:
                return SchemaAppFunctionInventory.schemaFunctionsMap_delegate$lambda$0((SchemaAppFunctionInventory) obj);
            case 12:
                return Long.valueOf(CouponAction.expirationDate_delegate$lambda$3((CouponAction) obj));
            case 13:
                return WalletAction.title_delegate$lambda$0((WalletAction) obj);
            case 14:
                return CapsuleFactory.createAppCapsule$lambda$12((SimpleCapsuleClickListener) obj);
            case 15:
                return CapsuleFactory.createAiSuggestionCapsuleListener$lambda$18$lambda$17((ActionManager) obj);
            case 16:
                return CapsuleFactory.createEntityCapsuleListener$lambda$11$lambda$10((RemoteAction) obj);
            case 17:
                return VexFwkParamBaseParcelable.creator_delegate$lambda$0((VexFwkParamBaseParcelable) obj);
            case 18:
                return AggregatedAppFunctionInvoker.supportedFunctionIds_delegate$lambda$0((AggregatedAppFunctionInvoker) obj);
            case 19:
                return ((WorkContinuationImpl) obj).lambda$enqueue$0();
            case 20:
                return ((WorkManagerImpl) obj).lambda$rescheduleEligibleWork$0();
            case 21:
                return j.g((Object[]) obj);
            case 22:
                return ((Iterable) obj).iterator();
            case 23:
                return BarcodeHelper.showBarcodeDialog$lambda$1((r) obj);
            case 24:
                return GroupMPPlayback.mpInfo_delegate$lambda$0((File) obj);
            default:
                return Long.valueOf(GroupMPPlayback.captureTimestampUs_delegate$lambda$1((GroupMPPlayback) obj));
        }
    }
}
