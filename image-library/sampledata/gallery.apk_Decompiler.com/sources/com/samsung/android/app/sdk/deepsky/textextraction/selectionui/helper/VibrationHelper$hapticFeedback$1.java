package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import Ae.c;
import L2.a;
import Vf.A;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import p.C0251a;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.VibrationHelper$hapticFeedback$1", f = "VibrationHelper.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VibrationHelper$hapticFeedback$1 extends i implements c {
    int label;
    final /* synthetic */ VibrationHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VibrationHelper$hapticFeedback$1(VibrationHelper vibrationHelper, C1227c cVar) {
        super(2, cVar);
        this.this$0 = vibrationHelper;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new VibrationHelper$hapticFeedback$1(this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((VibrationHelper$hapticFeedback$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            if (this.this$0.isVibrationEnabled()) {
                Object systemService = this.this$0.context.getSystemService("vibrator_manager");
                j.c(systemService, "null cannot be cast to non-null type android.os.VibratorManager");
                C0251a.i(systemService).getDefaultVibrator().vibrate(this.this$0.createVibrationEffect(), VibrationHelper.BACKGROUND_VIBRATION_ATTRIBUTES);
            }
            return x.f4917a;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
