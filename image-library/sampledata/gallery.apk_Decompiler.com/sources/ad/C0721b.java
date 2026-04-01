package Ad;

import Ae.a;
import androidx.appfunctions.internal.Dependencies;
import androidx.appfunctions.internal.Dispatchers;
import androidx.appfunctions.service.internal.ServiceDependencies;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarActionManager;
import com.samsung.android.gallery.crossapp.function.C$AlbumFunctions_AppFunctionInvoker;
import com.samsung.android.gallery.crossapp.function.C$PhotoFunctions_AppFunctionInvoker;
import com.samsung.android.gallery.crossapp.function.C$SearchFunctions_AppFunctionInvoker;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder;
import com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum;
import com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum;
import com.samsung.android.sdk.moneta.travel.internal.model.TransportationBaseInternal;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner;
import com.samsung.android.vexfwk.sdk.unifiedDetector.VexFwkUnifiedDetector;
import java.lang.annotation.Annotation;
import kg.Q;

/* renamed from: Ad.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0721b implements a {
    public final /* synthetic */ int d;

    public /* synthetic */ C0721b(int i2) {
        this.d = i2;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return Boolean.valueOf(VexFwkUnifiedDetector.isAvailable_delegate$lambda$0());
            case 1:
                return VexFwkUnifiedDetector.supportedModes_delegate$lambda$2();
            case 2:
                return Q.d("com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum", EventCategoryEnum.values(), new String[]{"UNKNOWN", "SOCIAL", "BUSINESS", "TRIP", "RESERVATION", "COUPON"}, new Annotation[][]{null, null, null, null, null, null});
            case 3:
                return Q.d("com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum", EventSubCategoryEnum.values(), new String[]{"UNKNOWN", "BIRTHDAY", "WEDDING", "WEDDING_ANNIVERSARY", "ENTRANCE_CEREMONY", "GRADUATION", "ACCOMMODATION", "TRANSPORTATION", "RESTAURANT"}, new Annotation[][]{null, null, null, null, null, null, null, null, null});
            case 4:
                return TransportationBaseInternal._init_$_anonymous_();
            case 5:
                return ToolbarActionManager._init_$lambda$0();
            case 6:
                return Boolean.valueOf(CommonsKt.isJUnit_delegate$lambda$37());
            case 7:
                return Boolean.valueOf(CommonsKt.isAndroid_delegate$lambda$38());
            case 8:
                return Boolean.valueOf(CommonsKt.isAndroidTest_delegate$lambda$39());
            case 9:
                return Integer.valueOf(VideoTranscoder.MAX_QUEUED_INPUT_delegate$lambda$38());
            case 10:
                return Integer.valueOf(VexFwkProvider.ndkVersion_delegate$lambda$6());
            case 11:
                return Boolean.valueOf(VexFwkDocDetector.isAvailable_delegate$lambda$12());
            case 12:
                return VexFwkDocDetector.supportedOperationTypes_delegate$lambda$14();
            case 13:
                return VexFwkDocRefiner.supportedModes_delegate$lambda$24();
            case 14:
                return Dependencies.translatorSelector_delegate$lambda$0();
            case 15:
                return Dependencies.schemaAppFunctionInventory_delegate$lambda$0();
            case 16:
                return Dependencies.aggregatedAppFunctionInventory_delegate$lambda$0();
            case 17:
                return Dependencies.appFunctionInventory_delegate$lambda$0();
            case 18:
                return Dispatchers.Main_delegate$lambda$0();
            case 19:
                return Dispatchers.Worker_delegate$lambda$0();
            case 20:
                return C$AlbumFunctions_AppFunctionInvoker.unsafeInvoke$lambda$0();
            case 21:
                return C$AlbumFunctions_AppFunctionInvoker.unsafeInvoke$lambda$1();
            case 22:
                return C$AlbumFunctions_AppFunctionInvoker.unsafeInvoke$lambda$2();
            case 23:
                return C$PhotoFunctions_AppFunctionInvoker.unsafeInvoke$lambda$0();
            case 24:
                return C$PhotoFunctions_AppFunctionInvoker.unsafeInvoke$lambda$1();
            case 25:
                return C$PhotoFunctions_AppFunctionInvoker.unsafeInvoke$lambda$2();
            case 26:
                return C$PhotoFunctions_AppFunctionInvoker.unsafeInvoke$lambda$3();
            case 27:
                return C$SearchFunctions_AppFunctionInvoker.unsafeInvoke$lambda$0();
            case 28:
                return ServiceDependencies.aggregatedAppFunctionInvoker_delegate$lambda$0();
            default:
                return VexFwkPortraitBokehEffect.bokehEffectVersion_delegate$lambda$18();
        }
    }
}
