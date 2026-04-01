package vd;

import Ad.f;
import Ae.b;
import Ae.d;
import L2.a;
import android.graphics.Bitmap;
import com.samsung.android.motionphoto.utils.v2.n;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends i implements d {
    public /* synthetic */ VexFwkSessionRequest d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ VexFwkOcrResult g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkOcrResultV2 f5152h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Bitmap f5153i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w(String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, Bitmap bitmap, C1227c cVar) {
        super(3, cVar);
        this.e = str;
        this.f = str2;
        this.g = vexFwkOcrResult;
        this.f5152h = vexFwkOcrResultV2;
        this.f5153i = bitmap;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        VexFwkSessionRequest vexFwkSessionRequest = (VexFwkSessionRequest) obj;
        VexFwkOcrResultV2 vexFwkOcrResultV2 = this.f5152h;
        Bitmap bitmap = this.f5153i;
        w wVar = new w(this.e, this.f, this.g, vexFwkOcrResultV2, bitmap, (C1227c) obj3);
        wVar.d = (VexFwkSessionRequest) obj2;
        x xVar = x.f4917a;
        wVar.invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkSessionRequest vexFwkSessionRequest = this.d;
        vexFwkSessionRequest.setSettingMetadata((b) new n(this.e, this.f, this.g, this.f5152h, 1));
        vexFwkSessionRequest.setInputBuffer(new f(21, (Object) this.f5153i));
        return x.f4917a;
    }
}
