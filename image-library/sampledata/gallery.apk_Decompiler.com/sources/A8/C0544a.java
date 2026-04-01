package A8;

import Ae.b;
import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.sum.core.controller.MediaFilterController;
import com.samsung.android.sum.core.descriptor.PluginDescriptorPair;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.service.ServiceProxySupplier;
import com.samsung.android.vexfwk.imageenhancer.EnhanceType;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferStyles;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor;
import com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor;
import com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer;
import com.samsung.android.vexfwk.sdk.imgstyletransfer.VexFwkImageStyleTransfer;
import java.util.function.Supplier;

/* renamed from: A8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0544a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0544a(PluginDescriptorPair pluginDescriptorPair, String str, Object obj) {
        this.d = 2;
        this.e = pluginDescriptorPair;
        this.g = str;
        this.f = obj;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return ((RemasterDetector) this.e).lambda$detectImage$0((MediaItem) this.f, (String) this.g);
            case 1:
                return ((MediaFilterController) this.e).lambda$sendMessage$4((Request) this.f, (Message) this.g);
            case 2:
                return ((PluginDescriptorPair) this.e).lambda$getExtra$6((String) this.g, this.f);
            case 3:
                return ((ServiceProxySupplier) this.e).lambda$new$0((Class) this.f, (Context) this.g);
            case 4:
                return VexFwkSdkBase.configureWith$lambda$11(this.e, (b) this.f, (VexFwkSdkBase) this.g);
            case 5:
                return VexFwkDepthMapProcessor.getDepthMap$lambda$2((VexFwkDepthMapProcessor) this.e, (Bitmap) this.f, (VexFwkDepthMapProcessor.DepthMapParam) this.g);
            case 6:
                return VexFwkFocalLengthProcessor.getFocalLength$lambda$2((VexFwkFocalLengthProcessor) this.e, (Bitmap) this.f, (VexFwkFocalLengthProcessor.FocalLengthParam) this.g);
            case 7:
                return VexFwkImageEnhancer.applyImageEnhancer$lambda$11((Bitmap) this.e, (EnhanceType) this.f, (VexFwkImageEnhancer) this.g);
            default:
                return VexFwkImageStyleTransfer.applyStyleTransferImpl$lambda$9(this.e, (VexFwkImageStyleTransferStyles.Companion.StyleTransferType) this.f, (VexFwkImageStyleTransfer) this.g);
        }
    }

    public /* synthetic */ C0544a(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }
}
