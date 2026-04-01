package com.samsung.android.sum.core.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.vexfwk.docscan.VexFwkDocDewarpModes;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefineModes;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDewarper;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover;
import java.io.Serializable;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Serializable f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f4142h;

    public /* synthetic */ d(int i2, Serializable serializable, Object obj, Object obj2, Object obj3) {
        this.d = i2;
        this.e = obj;
        this.f = serializable;
        this.g = obj2;
        this.f4142h = obj3;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return ((ServiceProxySupplier) this.e).lambda$new$1((String) this.f, (Context) this.f4142h, (String) this.g);
            case 1:
                return VexFwkDocDewarper.dewarpDocumentImpl$lambda$12(this.e, (VexFwkDocumentCorners) this.f, (VexFwkDocDewarpModes.Companion.DewarpMode) this.g, (VexFwkDocDewarper) this.f4142h);
            case 2:
                return VexFwkDocRefiner.refineDocumentImpl$lambda$9((VexFwkDocRefineModes.Companion.EnhancementMode) this.e, (VexFwkDocRefineModes.Companion.ColorFilterType) this.f, this.g, (VexFwkDocRefiner) this.f4142h);
            default:
                return VexFwkObjRemover.removeObject$lambda$8((Rect) this.e, (int[]) this.f, (VexFwkObjRemover) this.g, (Bitmap) this.f4142h);
        }
    }

    public /* synthetic */ d(ServiceProxySupplier serviceProxySupplier, String str, Context context, String str2) {
        this.d = 0;
        this.e = serviceProxySupplier;
        this.f = str;
        this.f4142h = context;
        this.g = str2;
    }
}
