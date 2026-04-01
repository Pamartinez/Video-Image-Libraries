package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C0867e0;
import Vf.n0;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelperImpl$addActionCapsule$1$1", f = "CapsuleHelperImpl.kt", l = {129}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleHelperImpl$addActionCapsule$1$1 extends i implements c {
    Object L$0;
    int label;
    final /* synthetic */ CapsuleHelperImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CapsuleHelperImpl$addActionCapsule$1$1(CapsuleHelperImpl capsuleHelperImpl, C1227c cVar) {
        super(2, cVar);
        this.this$0 = capsuleHelperImpl;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new CapsuleHelperImpl$addActionCapsule$1$1(this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((CapsuleHelperImpl$addActionCapsule$1$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        CapsuleHelperImpl capsuleHelperImpl;
        CapsuleHelperImpl capsuleHelperImpl2;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            C0867e0 access$getDrawingJob$p = this.this$0.drawingJob;
            if (access$getDrawingJob$p == null) {
                return null;
            }
            capsuleHelperImpl = this.this$0;
            if (access$getDrawingJob$p.isActive()) {
                LibLogger.d("CapsuleHelper", "AddActionCapsule calls in the middle of drawing, waiting");
                this.L$0 = capsuleHelperImpl;
                this.label = 1;
                if (((n0) access$getDrawingJob$p).G(this) == aVar) {
                    return aVar;
                }
                capsuleHelperImpl2 = capsuleHelperImpl;
            }
            LibLogger.d("CapsuleHelper", "inflating action capsule");
            capsuleHelperImpl.invalidateCapsuleLayout();
            return x.f4917a;
        } else if (i2 == 1) {
            capsuleHelperImpl2 = (CapsuleHelperImpl) this.L$0;
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        capsuleHelperImpl = capsuleHelperImpl2;
        LibLogger.d("CapsuleHelper", "inflating action capsule");
        capsuleHelperImpl.invalidateCapsuleLayout();
        return x.f4917a;
    }
}
