package vd;

import Ae.c;
import L2.a;
import android.graphics.Bitmap;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ VexFwkOcrResult g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkOcrResultV2 f5154h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Bitmap f5155i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public x(String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, Bitmap bitmap, C1227c cVar) {
        super(2, cVar);
        this.e = str;
        this.f = str2;
        this.g = vexFwkOcrResult;
        this.f5154h = vexFwkOcrResultV2;
        this.f5155i = bitmap;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        x xVar = new x(this.e, this.f, this.g, this.f5154h, this.f5155i, cVar);
        xVar.d = obj;
        return xVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        me.x xVar = me.x.f4917a;
        ((x) create((VexFwkHelperProcess.Request) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperProcess.Request request = (VexFwkHelperProcess.Request) this.d;
        request.setUsecase(VexFwkUsecase.IMAGE_TRANSLATION);
        request.setOnRequestSetup(new w(this.e, this.f, this.g, this.f5154h, this.f5155i, (C1227c) null));
        return me.x.f4917a;
    }
}
