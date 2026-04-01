package androidx.activity;

import N2.j;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B+\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/activity/BackEventCompat;", "", "", "touchX", "touchY", "progress", "", "swipeEdge", "<init>", "(FFFI)V", "Landroid/window/BackEvent;", "backEvent", "(Landroid/window/BackEvent;)V", "", "toString", "()Ljava/lang/String;", "F", "getTouchX", "()F", "getTouchY", "getProgress", "I", "getSwipeEdge", "()I", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BackEventCompat {
    public static final Companion Companion = new Companion((e) null);
    private final float progress;
    private final int swipeEdge;
    private final float touchX;
    private final float touchY;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/activity/BackEventCompat$Companion;", "", "()V", "EDGE_LEFT", "", "EDGE_RIGHT", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public BackEventCompat(float f, float f5, float f8, int i2) {
        this.touchX = f;
        this.touchY = f5;
        this.progress = f8;
        this.swipeEdge = i2;
    }

    public final float getProgress() {
        return this.progress;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BackEventCompat{touchX=");
        sb2.append(this.touchX);
        sb2.append(", touchY=");
        sb2.append(this.touchY);
        sb2.append(", progress=");
        sb2.append(this.progress);
        sb2.append(", swipeEdge=");
        return j.e(sb2, this.swipeEdge, '}');
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BackEventCompat(android.window.BackEvent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "backEvent"
            kotlin.jvm.internal.j.e(r5, r0)
            androidx.activity.Api34Impl r0 = androidx.activity.Api34Impl.INSTANCE
            float r1 = r0.touchX(r5)
            float r2 = r0.touchY(r5)
            float r3 = r0.progress(r5)
            int r5 = r0.swipeEdge(r5)
            r4.<init>(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.BackEventCompat.<init>(android.window.BackEvent):void");
    }
}
