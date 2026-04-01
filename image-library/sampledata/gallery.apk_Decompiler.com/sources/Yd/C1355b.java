package yd;

import Ae.b;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;

/* renamed from: yd.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1355b implements b {
    public final /* synthetic */ String d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ C1355b(String str, String str2, int i2) {
        this.d = str;
        this.e = str2;
        this.f = i2;
    }

    public final Object invoke(Object obj) {
        return VexFwkImageObjectRemover.removeObject$lambda$10$lambda$4(this.d, this.e, this.f, (VexFwkMetadataNative) obj);
    }
}
