package androidx.window.layout;

import Ae.c;
import Yf.h;
import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"LYf/h;", "Landroidx/window/layout/WindowLayoutInfo;", "Lme/x;", "<anonymous>", "(LYf/h;)V"}, k = 3, mv = {1, 6, 0})
@C1273e(c = "androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1", f = "WindowInfoTrackerImpl.kt", l = {54, 55}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowInfoTrackerImpl$windowLayoutInfo$1 extends i implements c {
    final /* synthetic */ Activity $activity;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ WindowInfoTrackerImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WindowInfoTrackerImpl$windowLayoutInfo$1(WindowInfoTrackerImpl windowInfoTrackerImpl, Activity activity, C1227c cVar) {
        super(2, cVar);
        this.this$0 = windowInfoTrackerImpl;
        this.$activity = activity;
    }

    /* access modifiers changed from: private */
    /* renamed from: invokeSuspend$lambda-0  reason: not valid java name */
    public static final void m33invokeSuspend$lambda0(Xf.i iVar, WindowLayoutInfo windowLayoutInfo) {
        j.d(windowLayoutInfo, "info");
        iVar.e(windowLayoutInfo);
    }

    public final C1227c create(Object obj, C1227c cVar) {
        WindowInfoTrackerImpl$windowLayoutInfo$1 windowInfoTrackerImpl$windowLayoutInfo$1 = new WindowInfoTrackerImpl$windowLayoutInfo$1(this.this$0, this.$activity, cVar);
        windowInfoTrackerImpl$windowLayoutInfo$1.L$0 = obj;
        return windowInfoTrackerImpl$windowLayoutInfo$1;
    }

    public final Object invoke(h hVar, C1227c cVar) {
        return ((WindowInfoTrackerImpl$windowLayoutInfo$1) create(hVar, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0091, code lost:
        if (r5.emit((androidx.window.layout.WindowLayoutInfo) r1.c(), r10) == r0) goto L_0x0093;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0073 A[Catch:{ all -> 0x001e }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007f A[Catch:{ all -> 0x001e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 == r3) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            java.lang.Object r1 = r10.L$2
            Xf.b r1 = (Xf.b) r1
            java.lang.Object r4 = r10.L$1
            androidx.core.util.Consumer r4 = (androidx.core.util.Consumer) r4
            java.lang.Object r5 = r10.L$0
            Yf.h r5 = (Yf.h) r5
            L2.a.A(r11)     // Catch:{ all -> 0x001e }
        L_0x001b:
            r11 = r5
            r5 = r1
            goto L_0x0064
        L_0x001e:
            r11 = move-exception
            goto L_0x00a0
        L_0x0021:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0029:
            java.lang.Object r1 = r10.L$2
            Xf.b r1 = (Xf.b) r1
            java.lang.Object r4 = r10.L$1
            androidx.core.util.Consumer r4 = (androidx.core.util.Consumer) r4
            java.lang.Object r5 = r10.L$0
            Yf.h r5 = (Yf.h) r5
            L2.a.A(r11)     // Catch:{ all -> 0x001e }
            goto L_0x0077
        L_0x0039:
            L2.a.A(r11)
            java.lang.Object r11 = r10.L$0
            Yf.h r11 = (Yf.h) r11
            Xf.a r1 = Xf.a.DROP_OLDEST
            r4 = 4
            r5 = 10
            Xf.e r1 = He.F.c(r5, r1, r4)
            androidx.window.layout.a r4 = new androidx.window.layout.a
            r4.<init>(r1)
            androidx.window.layout.WindowInfoTrackerImpl r5 = r10.this$0
            androidx.window.layout.WindowBackend r5 = r5.windowBackend
            android.app.Activity r6 = r10.$activity
            j.b r7 = new j.b
            r8 = 0
            r7.<init>(r8)
            r5.registerLayoutChangeCallback(r6, r7, r4)
            Xf.b r5 = new Xf.b     // Catch:{ all -> 0x001e }
            r5.<init>(r1)     // Catch:{ all -> 0x001e }
        L_0x0064:
            r10.L$0 = r11     // Catch:{ all -> 0x001e }
            r10.L$1 = r4     // Catch:{ all -> 0x001e }
            r10.L$2 = r5     // Catch:{ all -> 0x001e }
            r10.label = r3     // Catch:{ all -> 0x001e }
            java.lang.Object r1 = r5.b(r10)     // Catch:{ all -> 0x001e }
            if (r1 != r0) goto L_0x0073
            goto L_0x0093
        L_0x0073:
            r9 = r5
            r5 = r11
            r11 = r1
            r1 = r9
        L_0x0077:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x001e }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x001e }
            if (r11 == 0) goto L_0x0094
            java.lang.Object r11 = r1.c()     // Catch:{ all -> 0x001e }
            androidx.window.layout.WindowLayoutInfo r11 = (androidx.window.layout.WindowLayoutInfo) r11     // Catch:{ all -> 0x001e }
            r10.L$0 = r5     // Catch:{ all -> 0x001e }
            r10.L$1 = r4     // Catch:{ all -> 0x001e }
            r10.L$2 = r1     // Catch:{ all -> 0x001e }
            r10.label = r2     // Catch:{ all -> 0x001e }
            java.lang.Object r11 = r5.emit(r11, r10)     // Catch:{ all -> 0x001e }
            if (r11 != r0) goto L_0x001b
        L_0x0093:
            return r0
        L_0x0094:
            androidx.window.layout.WindowInfoTrackerImpl r10 = r10.this$0
            androidx.window.layout.WindowBackend r10 = r10.windowBackend
            r10.unregisterLayoutChangeCallback(r4)
            me.x r10 = me.x.f4917a
            return r10
        L_0x00a0:
            androidx.window.layout.WindowInfoTrackerImpl r10 = r10.this$0
            androidx.window.layout.WindowBackend r10 = r10.windowBackend
            r10.unregisterLayoutChangeCallback(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
