package com.samsung.android.motionphoto.utils.v2.video;

import android.os.SemSystemProperties;
import com.samsung.android.sum.core.SLog;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0003R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\n¨\u0006\r"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/UniFormInterval;", "", "<init>", "()V", "Lme/x;", "update", "align", "reset", "", "periodTimeNs", "J", "lastTimestampNs", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UniFormInterval {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private long lastTimestampNs;
    private final long periodTimeNs = (SemSystemProperties.getLong("secmm.motionphoto.io-period-ms", 2) * ((long) 1000000));

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/UniFormInterval$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(UniFormInterval.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public final void align() {
        if (this.lastTimestampNs != 0) {
            long nanoTime = this.periodTimeNs - (System.nanoTime() - this.lastTimestampNs);
            if (nanoTime > 0) {
                try {
                    String str = TAG;
                    SLog.d(str, "io-period is set, so wait " + (nanoTime / ((long) 1000)) + " us");
                    Thread.sleep(nanoTime / ((long) 1000000), ((int) nanoTime) % 1000000);
                } catch (Exception unused) {
                }
            }
        }
    }

    public final void reset() {
        this.lastTimestampNs = 0;
    }

    public final void update() {
        this.lastTimestampNs = System.nanoTime();
    }
}
