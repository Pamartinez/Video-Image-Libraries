package eg;

import Vf.D;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends j {
    public final Runnable f;

    public k(Runnable runnable, long j2, boolean z) {
        super(j2, z);
        this.f = runnable;
    }

    public final void run() {
        this.f.run();
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("Task[");
        Runnable runnable = this.f;
        sb2.append(runnable.getClass().getSimpleName());
        sb2.append('@');
        sb2.append(D.j(runnable));
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.d);
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (this.e) {
            str = "Blocking";
        } else {
            str = "Non-blocking";
        }
        return C0086a.m(sb2, str, ']');
    }
}
