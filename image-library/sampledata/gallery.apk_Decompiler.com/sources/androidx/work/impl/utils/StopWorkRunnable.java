package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopToken;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0011R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/utils/StopWorkRunnable;", "Ljava/lang/Runnable;", "Landroidx/work/impl/Processor;", "processor", "Landroidx/work/impl/StartStopToken;", "token", "", "stopInForeground", "", "reason", "<init>", "(Landroidx/work/impl/Processor;Landroidx/work/impl/StartStopToken;ZI)V", "Lme/x;", "run", "()V", "Landroidx/work/impl/Processor;", "Landroidx/work/impl/StartStopToken;", "Z", "I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StopWorkRunnable implements Runnable {
    private final Processor processor;
    private final int reason;
    private final boolean stopInForeground;
    private final StartStopToken token;

    public StopWorkRunnable(Processor processor2, StartStopToken startStopToken, boolean z, int i2) {
        j.e(processor2, "processor");
        j.e(startStopToken, "token");
        this.processor = processor2;
        this.token = startStopToken;
        this.stopInForeground = z;
        this.reason = i2;
    }

    public void run() {
        boolean z;
        if (this.stopInForeground) {
            z = this.processor.stopForegroundWork(this.token, this.reason);
        } else {
            z = this.processor.stopWork(this.token, this.reason);
        }
        Logger logger = Logger.get();
        String tagWithPrefix = Logger.tagWithPrefix("StopWorkRunnable");
        logger.debug(tagWithPrefix, "StopWorkRunnable for " + this.token.getId().getWorkSpecId() + "; Processor.stopWork = " + z);
    }
}
