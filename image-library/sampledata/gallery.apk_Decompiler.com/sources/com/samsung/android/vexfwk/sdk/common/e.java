package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Vf.A;
import fg.a;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends i implements c {
    public a d;
    public VexFwkHelperBase e;
    public VexFwkHelperConfigurationFuture f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperBase f4150h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperConfigurationFuture f4151i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(VexFwkHelperBase vexFwkHelperBase, VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(2, cVar);
        this.f4150h = vexFwkHelperBase;
        this.f4151i = vexFwkHelperConfigurationFuture;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new e(this.f4150h, this.f4151i, cVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        a aVar;
        VexFwkHelperBase vexFwkHelperBase;
        VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture;
        C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.g;
        if (i2 == 0) {
            L2.a.A(obj);
            vexFwkHelperBase = this.f4150h;
            a access$getConfigureFutureMutex$p = vexFwkHelperBase.configureFutureMutex;
            this.d = access$getConfigureFutureMutex$p;
            this.e = vexFwkHelperBase;
            VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture2 = this.f4151i;
            this.f = vexFwkHelperConfigurationFuture2;
            this.g = 1;
            fg.c cVar = (fg.c) access$getConfigureFutureMutex$p;
            if (cVar.c(this) == aVar2) {
                return aVar2;
            }
            aVar = cVar;
            vexFwkHelperConfigurationFuture = vexFwkHelperConfigurationFuture2;
        } else if (i2 == 1) {
            vexFwkHelperConfigurationFuture = this.f;
            vexFwkHelperBase = this.e;
            aVar = this.d;
            L2.a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            VexFwkHelperConfigurationFuture access$getConfigureFuture$p = vexFwkHelperBase.configureFuture;
            if (access$getConfigureFuture$p != null) {
                VexFwkHelperBase vexFwkHelperBase2 = (VexFwkHelperBase) access$getConfigureFuture$p.get();
            }
            vexFwkHelperBase.configureFuture = vexFwkHelperConfigurationFuture;
            vexFwkHelperConfigurationFuture.start();
            ((fg.c) aVar).d((Object) null);
            return x.f4917a;
        } catch (Throwable th) {
            ((fg.c) aVar).d((Object) null);
            throw th;
        }
    }
}
