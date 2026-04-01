package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C0867e0;
import Vf.n0;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuManager$clearStickerId$1", f = "ToolbarMenuManager.kt", l = {142}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenuManager$clearStickerId$1 extends i implements c {
    int label;
    final /* synthetic */ ToolbarMenuManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ToolbarMenuManager$clearStickerId$1(ToolbarMenuManager toolbarMenuManager, C1227c cVar) {
        super(2, cVar);
        this.this$0 = toolbarMenuManager;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new ToolbarMenuManager$clearStickerId$1(this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((ToolbarMenuManager$clearStickerId$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            C0867e0 access$getInsertJob$p = this.this$0.insertJob;
            if (access$getInsertJob$p != null) {
                this.label = 1;
                if (((n0) access$getInsertJob$p).G(this) == aVar) {
                    return aVar;
                }
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.insertJob = null;
        this.this$0.stickerID = "";
        return x.f4917a;
    }
}
