package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util;

import Vf.C0867e0;
import android.content.Context;
import android.view.ViewConfiguration;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0019R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u0019R\u0016\u0010\u001f\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u0019¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressDetector;", "", "Landroid/content/Context;", "context", "", "longPressTimeout", "", "touchSlop", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressListener;", "longPressListener", "<init>", "(Landroid/content/Context;JILcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressListener;)V", "x", "y", "", "isTouchedWithinSlop", "(II)Z", "Landroid/view/MotionEvent;", "event", "handleTouchEvent", "(Landroid/view/MotionEvent;)Z", "Lme/x;", "cancelJob", "()V", "J", "I", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressListener;", "LVf/e0;", "job", "LVf/e0;", "lastTouchX", "lastTouchY", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LongPressDetector {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public C0867e0 job;
    private int lastTouchX;
    private int lastTouchY;
    /* access modifiers changed from: private */
    public final LongPressListener longPressListener;
    /* access modifiers changed from: private */
    public final long longPressTimeout;
    private final int touchSlop;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/LongPressDetector$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LongPressDetector(Context context, long j2, int i2, LongPressListener longPressListener2) {
        j.e(context, "context");
        j.e(longPressListener2, "longPressListener");
        this.longPressTimeout = j2;
        this.touchSlop = i2;
        this.longPressListener = longPressListener2;
        LibLogger.i("LongPressDetector", "LongPressDetector initialized with timeout(" + j2 + "), slop(" + i2 + ")");
    }

    /* access modifiers changed from: private */
    public final boolean isTouchedWithinSlop(int i2, int i7) {
        if (Math.abs(this.lastTouchX - i2) > this.touchSlop || Math.abs(this.lastTouchY - i7) > this.touchSlop) {
            return false;
        }
        return true;
    }

    public final void cancelJob() {
        this.longPressListener.onLongPressCancelled();
        C0867e0 e0Var = this.job;
        if (e0Var != null) {
            e0Var.a((CancellationException) null);
        }
        this.job = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0030, code lost:
        if (r11 != 5) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleTouchEvent(android.view.MotionEvent r11) {
        /*
            r10 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.j.e(r11, r0)
            float r0 = r11.getRawX()
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            int r4 = (int) r0
            float r0 = r11.getRawY()
            float r0 = r0 + r1
            int r5 = (int) r0
            float r0 = r11.getX()
            float r0 = r0 + r1
            int r6 = (int) r0
            float r0 = r11.getY()
            float r0 = r0 + r1
            int r7 = (int) r0
            int r11 = r11.getAction()
            r0 = 3
            r1 = 0
            r9 = 1
            if (r11 == 0) goto L_0x0047
            if (r11 == r9) goto L_0x003d
            r2 = 2
            if (r11 == r2) goto L_0x0033
            if (r11 == r0) goto L_0x003d
            r0 = 5
            if (r11 == r0) goto L_0x003d
            goto L_0x003c
        L_0x0033:
            boolean r11 = r10.isTouchedWithinSlop(r4, r5)
            if (r11 != 0) goto L_0x003c
            r10.cancelJob()
        L_0x003c:
            return r9
        L_0x003d:
            Vf.e0 r11 = r10.job
            if (r11 == 0) goto L_0x0044
            r11.a(r1)
        L_0x0044:
            r10.job = r1
            return r9
        L_0x0047:
            r10.lastTouchX = r4
            r10.lastTouchY = r5
            eg.f r11 = Vf.M.f3843a
            Wf.e r11 = cg.n.f4030a
            cg.c r11 = Vf.D.a(r11)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector$handleTouchEvent$1 r2 = new com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector$handleTouchEvent$1
            r8 = 0
            r3 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            Vf.u0 r10 = Vf.D.n(r11, r1, r1, r2, r0)
            r3.job = r10
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.LongPressDetector.handleTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LongPressDetector(Context context, long j2, int i2, LongPressListener longPressListener2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? (long) ViewConfiguration.getLongPressTimeout() : j2, (i7 & 4) != 0 ? ViewConfiguration.get(context).getScaledTouchSlop() : i2, longPressListener2);
    }
}
