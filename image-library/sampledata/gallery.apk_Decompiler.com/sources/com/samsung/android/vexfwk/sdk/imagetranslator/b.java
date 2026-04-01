package com.samsung.android.vexfwk.sdk.imagetranslator;

import Ae.c;
import L2.a;
import android.graphics.Bitmap;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ VexFwkOcrResult g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkOcrResultV2 f4181h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Bitmap f4182i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, Bitmap bitmap, C1227c cVar) {
        super(2, cVar);
        this.e = str;
        this.f = str2;
        this.g = vexFwkOcrResult;
        this.f4181h = vexFwkOcrResultV2;
        this.f4182i = bitmap;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        b bVar = new b(this.e, this.f, this.g, this.f4181h, this.f4182i, cVar);
        bVar.d = obj;
        return bVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((b) create((VexFwkHelperProcess) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) this.d;
        vexFwkHelperProcess.setRequest(new vd.x(this.e, this.f, this.g, this.f4181h, this.f4182i, (C1227c) null));
        vexFwkHelperProcess.setFinally(new a(this.f4181h, vexFwkHelperProcess, (C1227c) null));
        return x.f4917a;
    }
}
