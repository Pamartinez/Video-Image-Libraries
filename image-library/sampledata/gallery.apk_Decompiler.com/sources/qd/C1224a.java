package qd;

import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import java.util.function.Supplier;

/* renamed from: qd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1224a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f5030h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f5031i;

    public /* synthetic */ C1224a(Object obj, VexFwkImageEffectProcessor.SegMask segMask, VexFwkImageEffectProcessor vexFwkImageEffectProcessor, VexFwkImageEffectProcessor.ImageEffect imageEffect, boolean z) {
        this.d = 0;
        this.f = obj;
        this.g = segMask;
        this.f5030h = vexFwkImageEffectProcessor;
        this.f5031i = imageEffect;
        this.e = z;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                boolean z = this.e;
                return VexFwkImageEffectProcessor.applyEffectImpl$lambda$9(this.f, (VexFwkImageEffectProcessor.SegMask) this.g, (VexFwkImageEffectProcessor) this.f5030h, (VexFwkImageEffectProcessor.ImageEffect) this.f5031i, z);
            case 1:
                return VexFwkImageObjectRemover.removeObject$lambda$10(this.e, (VexFwkImageObjectRemover.ObjectMask) this.f, (String) this.g, (String) this.f5030h, (VexFwkImageObjectRemover) this.f5031i);
            default:
                return VexFwkVideoObjectRemover.removeObject$lambda$9(this.e, (VexFwkVideoObjectRemover.ObjectMask) this.f, (String) this.g, (String) this.f5030h, (VexFwkVideoObjectRemover) this.f5031i);
        }
    }

    public /* synthetic */ C1224a(boolean z, Object obj, String str, String str2, VexFwkSdkBase vexFwkSdkBase, int i2) {
        this.d = i2;
        this.e = z;
        this.f = obj;
        this.g = str;
        this.f5030h = str2;
        this.f5031i = vexFwkSdkBase;
    }
}
