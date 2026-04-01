package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C0867e0;
import Vf.D;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector$handleTouchEvent$1", f = "LongPressDetector.kt", l = {44}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LongPressDetector$handleTouchEvent$1 extends i implements c {
    final /* synthetic */ int $rawX;
    final /* synthetic */ int $rawY;
    final /* synthetic */ int $x;
    final /* synthetic */ int $y;
    int label;
    final /* synthetic */ LongPressDetector this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LongPressDetector$handleTouchEvent$1(LongPressDetector longPressDetector, int i2, int i7, int i8, int i10, C1227c cVar) {
        super(2, cVar);
        this.this$0 = longPressDetector;
        this.$rawX = i2;
        this.$rawY = i7;
        this.$x = i8;
        this.$y = i10;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new LongPressDetector$handleTouchEvent$1(this.this$0, this.$rawX, this.$rawY, this.$x, this.$y, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((LongPressDetector$handleTouchEvent$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C0867e0 access$getJob$p;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            long access$getLongPressTimeout$p = this.this$0.longPressTimeout;
            this.label = 1;
            if (D.e(access$getLongPressTimeout$p, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.this$0.isTouchedWithinSlop(this.$rawX, this.$rawY) && (access$getJob$p = this.this$0.job) != null && access$getJob$p.isActive()) {
            this.this$0.longPressListener.onLongPress(this.$x, this.$y);
        }
        this.this$0.job = null;
        return x.f4917a;
    }
}
