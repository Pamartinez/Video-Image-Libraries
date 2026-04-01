package androidx.work.impl.model;

import B1.a;
import androidx.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import e5.d;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b)\b\b\u0018\u0000 Q2\u00020\u0001:\u0002QRBõ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u000b\u0012\b\b\u0002\u0010 \u001a\u00020\u0011\u0012\b\b\u0002\u0010!\u001a\u00020\u0011\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b#\u0010$B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010%\u001a\u00020\u0002¢\u0006\u0004\b#\u0010&B\u0019\b\u0016\u0012\u0006\u0010'\u001a\u00020\u0002\u0012\u0006\u0010(\u001a\u00020\u0000¢\u0006\u0004\b#\u0010)J\u0015\u0010+\u001a\u00020*2\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b+\u0010,J\u001d\u0010+\u001a\u00020*2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b+\u0010-J\r\u0010.\u001a\u00020\u000b¢\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u0019¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0002H\u0016¢\u0006\u0004\b2\u00103J\u0002\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0003\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b6\u00107J\u001a\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b8\u00109R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010:R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010;R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010:R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010:R\u0016\u0010\t\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\t\u0010<R\u0016\u0010\n\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\n\u0010<R\u0016\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\f\u0010=R\u0016\u0010\r\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\r\u0010=R\u0016\u0010\u000e\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010=R\u0016\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010>R\u0016\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010?R\u0016\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010@R\u0016\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010=R\u0016\u0010\u0016\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010=R\u0016\u0010\u0017\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010=R\u0016\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010=R\u0016\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010AR\u0016\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010BR\"\u0010\u001d\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010?\u001a\u0004\bC\u00107\"\u0004\bD\u0010ER\u001a\u0010\u001e\u001a\u00020\u00118\u0006X\u0004¢\u0006\f\n\u0004\b\u001e\u0010?\u001a\u0004\bF\u00107R\"\u0010\u001f\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010=\u001a\u0004\bG\u0010/\"\u0004\bH\u0010,R\"\u0010 \u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b \u0010?\u001a\u0004\bI\u00107\"\u0004\bJ\u0010ER\u001a\u0010!\u001a\u00020\u00118\u0006X\u0004¢\u0006\f\n\u0004\b!\u0010?\u001a\u0004\bK\u00107R$\u0010\"\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\"\u0010:\u001a\u0004\bL\u00103\"\u0004\bM\u0010NR\u0011\u0010O\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\bO\u00101R\u0011\u0010P\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\bP\u00101¨\u0006S"}, d2 = {"Landroidx/work/impl/model/WorkSpec;", "", "", "id", "Landroidx/work/WorkInfo$State;", "state", "workerClassName", "inputMergerClassName", "Landroidx/work/Data;", "input", "output", "", "initialDelay", "intervalDuration", "flexDuration", "Landroidx/work/Constraints;", "constraints", "", "runAttemptCount", "Landroidx/work/BackoffPolicy;", "backoffPolicy", "backoffDelayDuration", "lastEnqueueTime", "minimumRetentionDuration", "scheduleRequestedAt", "", "expedited", "Landroidx/work/OutOfQuotaPolicy;", "outOfQuotaPolicy", "periodCount", "generation", "nextScheduleTimeOverride", "nextScheduleTimeOverrideGeneration", "stopReason", "traceTag", "<init>", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Ljava/lang/String;Ljava/lang/String;Landroidx/work/Data;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJJJZLandroidx/work/OutOfQuotaPolicy;IIJIILjava/lang/String;)V", "workerClassName_", "(Ljava/lang/String;Ljava/lang/String;)V", "newId", "other", "(Ljava/lang/String;Landroidx/work/impl/model/WorkSpec;)V", "Lme/x;", "setPeriodic", "(J)V", "(JJ)V", "calculateNextRunTime", "()J", "hasConstraints", "()Z", "toString", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Ljava/lang/String;Ljava/lang/String;Landroidx/work/Data;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJJJZLandroidx/work/OutOfQuotaPolicy;IIJIILjava/lang/String;)Landroidx/work/impl/model/WorkSpec;", "hashCode", "()I", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "Landroidx/work/WorkInfo$State;", "Landroidx/work/Data;", "J", "Landroidx/work/Constraints;", "I", "Landroidx/work/BackoffPolicy;", "Z", "Landroidx/work/OutOfQuotaPolicy;", "getPeriodCount", "setPeriodCount", "(I)V", "getGeneration", "getNextScheduleTimeOverride", "setNextScheduleTimeOverride", "getNextScheduleTimeOverrideGeneration", "setNextScheduleTimeOverrideGeneration", "getStopReason", "getTraceTag", "setTraceTag", "(Ljava/lang/String;)V", "isPeriodic", "isBackedOff", "Companion", "IdAndState", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkSpec {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    public static final Function<List<Object>, List<Object>> WORK_INFO_MAPPER = new d(13);
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    private final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    private long nextScheduleTimeOverride;
    private int nextScheduleTimeOverrideGeneration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    private int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public WorkInfo$State state;
    private final int stopReason;
    private String traceTag;
    public String workerClassName;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Je\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\n8\u0006XT¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R,\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001b0\u001a8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/work/impl/model/WorkSpec$Companion;", "", "<init>", "()V", "", "isBackedOff", "", "runAttemptCount", "Landroidx/work/BackoffPolicy;", "backoffPolicy", "", "backoffDelayDuration", "lastEnqueueTime", "periodCount", "isPeriodic", "initialDelay", "flexDuration", "intervalDuration", "nextScheduleTimeOverride", "calculateNextRunTime", "(ZILandroidx/work/BackoffPolicy;JJIZJJJJ)J", "SCHEDULE_NOT_REQUESTED_YET", "J", "", "TAG", "Ljava/lang/String;", "Landroidx/arch/core/util/Function;", "", "WORK_INFO_MAPPER", "Landroidx/arch/core/util/Function;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final long calculateNextRunTime(boolean z, int i2, BackoffPolicy backoffPolicy, long j2, long j3, int i7, boolean z3, long j8, long j10, long j11, long j12) {
            long j13;
            long j14;
            j.e(backoffPolicy, "backoffPolicy");
            if (j12 != Long.MAX_VALUE && z3) {
                if (i7 != 0) {
                    long j15 = j3 + 900000;
                    if (j12 < j15) {
                        return j15;
                    }
                }
                return j12;
            } else if (z) {
                if (backoffPolicy == BackoffPolicy.LINEAR) {
                    j14 = j2 * ((long) i2);
                } else {
                    j14 = (long) Math.scalb((float) j2, i2 - 1);
                }
                if (j14 > 18000000) {
                    j14 = 18000000;
                }
                return j3 + j14;
            } else if (z3) {
                if (i7 == 0) {
                    j13 = j3 + j8;
                } else {
                    j13 = j3 + j11;
                }
                if (j10 == j11 || i7 != 0) {
                    return j13;
                }
                return (j11 - j10) + j13;
            } else if (j3 == -1) {
                return Long.MAX_VALUE;
            } else {
                return j3 + j8;
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/model/WorkSpec$IdAndState;", "", "", "id", "Landroidx/work/WorkInfo$State;", "state", "<init>", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "Landroidx/work/WorkInfo$State;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IdAndState {
        public String id;
        public WorkInfo$State state;

        public IdAndState(String str, WorkInfo$State workInfo$State) {
            j.e(str, "id");
            j.e(workInfo$State, "state");
            this.id = str;
            this.state = workInfo$State;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            if (j.a(this.id, idAndState.id) && this.state == idAndState.state) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.state.hashCode() + (this.id.hashCode() * 31);
        }

        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + ')';
        }
    }

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkSpec");
        j.d(tagWithPrefix, "tagWithPrefix(\"WorkSpec\")");
        TAG = tagWithPrefix;
    }

    public WorkSpec(String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j2, long j3, long j8, Constraints constraints2, int i2, BackoffPolicy backoffPolicy2, long j10, long j11, long j12, long j13, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i7, int i8, long j14, int i10, int i11, String str4) {
        Constraints constraints3 = constraints2;
        BackoffPolicy backoffPolicy3 = backoffPolicy2;
        OutOfQuotaPolicy outOfQuotaPolicy3 = outOfQuotaPolicy2;
        j.e(str, "id");
        j.e(workInfo$State, "state");
        j.e(str2, "workerClassName");
        j.e(str3, "inputMergerClassName");
        j.e(data, "input");
        j.e(data2, "output");
        j.e(constraints3, "constraints");
        j.e(backoffPolicy3, "backoffPolicy");
        j.e(outOfQuotaPolicy3, "outOfQuotaPolicy");
        this.id = str;
        this.state = workInfo$State;
        this.workerClassName = str2;
        this.inputMergerClassName = str3;
        this.input = data;
        this.output = data2;
        this.initialDelay = j2;
        this.intervalDuration = j3;
        this.flexDuration = j8;
        this.constraints = constraints3;
        this.runAttemptCount = i2;
        this.backoffPolicy = backoffPolicy3;
        this.backoffDelayDuration = j10;
        this.lastEnqueueTime = j11;
        this.minimumRetentionDuration = j12;
        this.scheduleRequestedAt = j13;
        this.expedited = z;
        this.outOfQuotaPolicy = outOfQuotaPolicy3;
        this.periodCount = i7;
        this.generation = i8;
        this.nextScheduleTimeOverride = j14;
        this.nextScheduleTimeOverrideGeneration = i10;
        this.stopReason = i11;
        this.traceTag = str4;
    }

    /* access modifiers changed from: private */
    public static final List WORK_INFO_MAPPER$lambda$1(List list) {
        if (list == null) {
            return null;
        }
        Iterable iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        throw C0212a.h(it);
    }

    public static /* synthetic */ WorkSpec copy$default(WorkSpec workSpec, String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j2, long j3, long j8, Constraints constraints2, int i2, BackoffPolicy backoffPolicy2, long j10, long j11, long j12, long j13, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i7, int i8, long j14, int i10, int i11, String str4, int i12, Object obj) {
        String str5;
        int i13;
        BackoffPolicy backoffPolicy3;
        long j15;
        long j16;
        long j17;
        long j18;
        OutOfQuotaPolicy outOfQuotaPolicy3;
        int i14;
        int i15;
        long j19;
        WorkInfo$State workInfo$State2;
        int i16;
        boolean z3;
        String str6;
        String str7;
        Data data3;
        Data data4;
        long j20;
        long j21;
        long j22;
        Constraints constraints3;
        int i17;
        WorkSpec workSpec2 = workSpec;
        int i18 = i12;
        String str8 = (i18 & 1) != 0 ? workSpec2.id : str;
        WorkInfo$State workInfo$State3 = (i18 & 2) != 0 ? workSpec2.state : workInfo$State;
        String str9 = (i18 & 4) != 0 ? workSpec2.workerClassName : str2;
        String str10 = (i18 & 8) != 0 ? workSpec2.inputMergerClassName : str3;
        Data data5 = (i18 & 16) != 0 ? workSpec2.input : data;
        Data data6 = (i18 & 32) != 0 ? workSpec2.output : data2;
        long j23 = (i18 & 64) != 0 ? workSpec2.initialDelay : j2;
        long j24 = (i18 & 128) != 0 ? workSpec2.intervalDuration : j3;
        long j25 = (i18 & 256) != 0 ? workSpec2.flexDuration : j8;
        Constraints constraints4 = (i18 & 512) != 0 ? workSpec2.constraints : constraints2;
        int i19 = (i18 & 1024) != 0 ? workSpec2.runAttemptCount : i2;
        String str11 = str8;
        BackoffPolicy backoffPolicy4 = (i18 & 2048) != 0 ? workSpec2.backoffPolicy : backoffPolicy2;
        WorkInfo$State workInfo$State4 = workInfo$State3;
        long j26 = (i18 & 4096) != 0 ? workSpec2.backoffDelayDuration : j10;
        long j27 = (i18 & SerializeOptions.SORT) != 0 ? workSpec2.lastEnqueueTime : j11;
        long j28 = (i18 & 16384) != 0 ? workSpec2.minimumRetentionDuration : j12;
        long j29 = (i18 & 32768) != 0 ? workSpec2.scheduleRequestedAt : j13;
        boolean z7 = (i12 & 65536) != 0 ? workSpec2.expedited : z;
        long j30 = j29;
        OutOfQuotaPolicy outOfQuotaPolicy4 = (i12 & 131072) != 0 ? workSpec2.outOfQuotaPolicy : outOfQuotaPolicy2;
        int i20 = (i12 & 262144) != 0 ? workSpec2.periodCount : i7;
        OutOfQuotaPolicy outOfQuotaPolicy5 = outOfQuotaPolicy4;
        int i21 = (i12 & 524288) != 0 ? workSpec2.generation : i8;
        int i22 = i20;
        long j31 = (i12 & MediaDefs.Meta.SEF.SEF_MIN_SIZE) != 0 ? workSpec2.nextScheduleTimeOverride : j14;
        int i23 = (i12 & 2097152) != 0 ? workSpec2.nextScheduleTimeOverrideGeneration : i10;
        int i24 = (i12 & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) != 0 ? workSpec2.stopReason : i11;
        if ((i12 & 8388608) != 0) {
            i13 = i23;
            str5 = workSpec2.traceTag;
            j15 = j26;
            j16 = j27;
            j17 = j28;
            j18 = j30;
            outOfQuotaPolicy3 = outOfQuotaPolicy5;
            i14 = i22;
            i15 = i21;
            j19 = j31;
            workInfo$State2 = workInfo$State4;
            i16 = i24;
            z3 = z7;
            str6 = str9;
            str7 = str10;
            data3 = data5;
            data4 = data6;
            j20 = j23;
            j21 = j24;
            j22 = j25;
            constraints3 = constraints4;
            i17 = i19;
            backoffPolicy3 = backoffPolicy4;
        } else {
            str5 = str4;
            i13 = i23;
            backoffPolicy3 = backoffPolicy4;
            j15 = j26;
            j16 = j27;
            j17 = j28;
            j18 = j30;
            outOfQuotaPolicy3 = outOfQuotaPolicy5;
            i14 = i22;
            i15 = i21;
            j19 = j31;
            workInfo$State2 = workInfo$State4;
            i16 = i24;
            z3 = z7;
            str6 = str9;
            str7 = str10;
            data3 = data5;
            data4 = data6;
            j20 = j23;
            j21 = j24;
            j22 = j25;
            constraints3 = constraints4;
            i17 = i19;
        }
        return workSpec2.copy(str11, workInfo$State2, str6, str7, data3, data4, j20, j21, j22, constraints3, i17, backoffPolicy3, j15, j16, j17, j18, z3, outOfQuotaPolicy3, i14, i15, j19, i13, i16, str5);
    }

    public final long calculateNextRunTime() {
        return Companion.calculateNextRunTime(isBackedOff(), this.runAttemptCount, this.backoffPolicy, this.backoffDelayDuration, this.lastEnqueueTime, this.periodCount, isPeriodic(), this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride);
    }

    public final WorkSpec copy(String str, WorkInfo$State workInfo$State, String str2, String str3, Data data, Data data2, long j2, long j3, long j8, Constraints constraints2, int i2, BackoffPolicy backoffPolicy2, long j10, long j11, long j12, long j13, boolean z, OutOfQuotaPolicy outOfQuotaPolicy2, int i7, int i8, long j14, int i10, int i11, String str4) {
        String str5 = str;
        j.e(str5, "id");
        WorkInfo$State workInfo$State2 = workInfo$State;
        j.e(workInfo$State2, "state");
        String str6 = str2;
        j.e(str6, "workerClassName");
        String str7 = str3;
        j.e(str7, "inputMergerClassName");
        Data data3 = data;
        j.e(data3, "input");
        Data data4 = data2;
        j.e(data4, "output");
        Constraints constraints3 = constraints2;
        j.e(constraints3, "constraints");
        j.e(backoffPolicy2, "backoffPolicy");
        OutOfQuotaPolicy outOfQuotaPolicy3 = outOfQuotaPolicy2;
        j.e(outOfQuotaPolicy3, "outOfQuotaPolicy");
        OutOfQuotaPolicy outOfQuotaPolicy4 = outOfQuotaPolicy3;
        return new WorkSpec(str5, workInfo$State2, str6, str7, data3, data4, j2, j3, j8, constraints3, i2, backoffPolicy2, j10, j11, j12, j13, z, outOfQuotaPolicy4, i7, i8, j14, i10, i11, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        if (j.a(this.id, workSpec.id) && this.state == workSpec.state && j.a(this.workerClassName, workSpec.workerClassName) && j.a(this.inputMergerClassName, workSpec.inputMergerClassName) && j.a(this.input, workSpec.input) && j.a(this.output, workSpec.output) && this.initialDelay == workSpec.initialDelay && this.intervalDuration == workSpec.intervalDuration && this.flexDuration == workSpec.flexDuration && j.a(this.constraints, workSpec.constraints) && this.runAttemptCount == workSpec.runAttemptCount && this.backoffPolicy == workSpec.backoffPolicy && this.backoffDelayDuration == workSpec.backoffDelayDuration && this.lastEnqueueTime == workSpec.lastEnqueueTime && this.minimumRetentionDuration == workSpec.minimumRetentionDuration && this.scheduleRequestedAt == workSpec.scheduleRequestedAt && this.expedited == workSpec.expedited && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy && this.periodCount == workSpec.periodCount && this.generation == workSpec.generation && this.nextScheduleTimeOverride == workSpec.nextScheduleTimeOverride && this.nextScheduleTimeOverrideGeneration == workSpec.nextScheduleTimeOverrideGeneration && this.stopReason == workSpec.stopReason && j.a(this.traceTag, workSpec.traceTag)) {
            return true;
        }
        return false;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final long getNextScheduleTimeOverride() {
        return this.nextScheduleTimeOverride;
    }

    public final int getNextScheduleTimeOverrideGeneration() {
        return this.nextScheduleTimeOverrideGeneration;
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final int getStopReason() {
        return this.stopReason;
    }

    public final String getTraceTag() {
        return this.traceTag;
    }

    public final boolean hasConstraints() {
        return !j.a(Constraints.NONE, this.constraints);
    }

    public int hashCode() {
        int i2;
        int d = C0212a.d(C0212a.d((this.state.hashCode() + (this.id.hashCode() * 31)) * 31, 31, this.workerClassName), 31, this.inputMergerClassName);
        int c5 = C0212a.c(C0212a.c(C0212a.c((this.output.hashCode() + ((this.input.hashCode() + d) * 31)) * 31, 31, this.initialDelay), 31, this.intervalDuration), 31, this.flexDuration);
        int e = C0212a.e(C0212a.c(C0212a.c(C0212a.c(C0212a.c((this.backoffPolicy.hashCode() + C0212a.b(this.runAttemptCount, (this.constraints.hashCode() + c5) * 31, 31)) * 31, 31, this.backoffDelayDuration), 31, this.lastEnqueueTime), 31, this.minimumRetentionDuration), 31, this.scheduleRequestedAt), 31, this.expedited);
        int b = C0212a.b(this.stopReason, C0212a.b(this.nextScheduleTimeOverrideGeneration, C0212a.c(C0212a.b(this.generation, C0212a.b(this.periodCount, (this.outOfQuotaPolicy.hashCode() + e) * 31, 31), 31), 31, this.nextScheduleTimeOverride), 31), 31);
        String str = this.traceTag;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return b + i2;
    }

    public final boolean isBackedOff() {
        if (this.state != WorkInfo$State.ENQUEUED || this.runAttemptCount <= 0) {
            return false;
        }
        return true;
    }

    public final boolean isPeriodic() {
        if (this.intervalDuration != 0) {
            return true;
        }
        return false;
    }

    public final void setNextScheduleTimeOverride(long j2) {
        this.nextScheduleTimeOverride = j2;
    }

    public final void setNextScheduleTimeOverrideGeneration(int i2) {
        this.nextScheduleTimeOverrideGeneration = i2;
    }

    public final void setPeriodic(long j2) {
        int i2 = (j2 > 900000 ? 1 : (j2 == 900000 ? 0 : -1));
        if (i2 < 0) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        long j3 = i2 < 0 ? 900000 : j2;
        if (i2 < 0) {
            j2 = 900000;
        }
        setPeriodic(j3, j2);
    }

    public final void setTraceTag(String str) {
        this.traceTag = str;
    }

    public String toString() {
        return C0086a.m(new StringBuilder("{WorkSpec: "), this.id, '}');
    }

    public final void setPeriodic(long j2, long j3) {
        long j8 = 900000;
        int i2 = (j2 > 900000 ? 1 : (j2 == 900000 ? 0 : -1));
        if (i2 < 0) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        if (i2 >= 0) {
            j8 = j2;
        }
        this.intervalDuration = j8;
        if (j3 < 300000) {
            Logger.get().warning(TAG, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j3 > this.intervalDuration) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.warning(str, "Flex duration greater than interval duration; Changed to " + j2);
        }
        this.flexDuration = a.m(j3, 300000, this.intervalDuration);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WorkSpec(java.lang.String r36, androidx.work.WorkInfo$State r37, java.lang.String r38, java.lang.String r39, androidx.work.Data r40, androidx.work.Data r41, long r42, long r44, long r46, androidx.work.Constraints r48, int r49, androidx.work.BackoffPolicy r50, long r51, long r53, long r55, long r57, boolean r59, androidx.work.OutOfQuotaPolicy r60, int r61, int r62, long r63, int r65, int r66, java.lang.String r67, int r68, kotlin.jvm.internal.e r69) {
        /*
            r35 = this;
            r0 = r68
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000a
            androidx.work.WorkInfo$State r1 = androidx.work.WorkInfo$State.ENQUEUED
            r4 = r1
            goto L_0x000c
        L_0x000a:
            r4 = r37
        L_0x000c:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0018
            java.lang.Class<androidx.work.OverwritingInputMerger> r1 = androidx.work.OverwritingInputMerger.class
            java.lang.String r1 = r1.getName()
            r6 = r1
            goto L_0x001a
        L_0x0018:
            r6 = r39
        L_0x001a:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0022
            androidx.work.Data r1 = androidx.work.Data.EMPTY
            r7 = r1
            goto L_0x0024
        L_0x0022:
            r7 = r40
        L_0x0024:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x002c
            androidx.work.Data r1 = androidx.work.Data.EMPTY
            r8 = r1
            goto L_0x002e
        L_0x002c:
            r8 = r41
        L_0x002e:
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x0036
            r9 = r2
            goto L_0x0038
        L_0x0036:
            r9 = r42
        L_0x0038:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x003e
            r11 = r2
            goto L_0x0040
        L_0x003e:
            r11 = r44
        L_0x0040:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0046
            r13 = r2
            goto L_0x0048
        L_0x0046:
            r13 = r46
        L_0x0048:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0050
            androidx.work.Constraints r1 = androidx.work.Constraints.NONE
            r15 = r1
            goto L_0x0052
        L_0x0050:
            r15 = r48
        L_0x0052:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            r5 = 0
            if (r1 == 0) goto L_0x005a
            r16 = r5
            goto L_0x005c
        L_0x005a:
            r16 = r49
        L_0x005c:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0065
            androidx.work.BackoffPolicy r1 = androidx.work.BackoffPolicy.EXPONENTIAL
            r17 = r1
            goto L_0x0067
        L_0x0065:
            r17 = r50
        L_0x0067:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x006e
            r18 = 30000(0x7530, double:1.4822E-319)
            goto L_0x0070
        L_0x006e:
            r18 = r51
        L_0x0070:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            r20 = -1
            if (r1 == 0) goto L_0x0079
            r22 = r20
            goto L_0x007b
        L_0x0079:
            r22 = r53
        L_0x007b:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L_0x0080
            goto L_0x0082
        L_0x0080:
            r2 = r55
        L_0x0082:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x008b
            r24 = r20
            goto L_0x008d
        L_0x008b:
            r24 = r57
        L_0x008d:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0095
            r26 = r5
            goto L_0x0097
        L_0x0095:
            r26 = r59
        L_0x0097:
            r1 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00a1
            androidx.work.OutOfQuotaPolicy r1 = androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
            r27 = r1
            goto L_0x00a3
        L_0x00a1:
            r27 = r60
        L_0x00a3:
            r1 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00ab
            r28 = r5
            goto L_0x00ad
        L_0x00ab:
            r28 = r61
        L_0x00ad:
            r1 = 524288(0x80000, float:7.34684E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00b5
            r29 = r5
            goto L_0x00b7
        L_0x00b5:
            r29 = r62
        L_0x00b7:
            r1 = 1048576(0x100000, float:1.469368E-39)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00c4
            r20 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r30 = r20
            goto L_0x00c6
        L_0x00c4:
            r30 = r63
        L_0x00c6:
            r1 = 2097152(0x200000, float:2.938736E-39)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00ce
            r32 = r5
            goto L_0x00d0
        L_0x00ce:
            r32 = r65
        L_0x00d0:
            r1 = 4194304(0x400000, float:5.877472E-39)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x00da
            r1 = -256(0xffffffffffffff00, float:NaN)
            r33 = r1
            goto L_0x00dc
        L_0x00da:
            r33 = r66
        L_0x00dc:
            r1 = 8388608(0x800000, float:1.17549435E-38)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00ef
            r0 = 0
            r34 = r0
        L_0x00e4:
            r5 = r38
            r20 = r22
            r22 = r2
            r2 = r35
            r3 = r36
            goto L_0x00f2
        L_0x00ef:
            r34 = r67
            goto L_0x00e4
        L_0x00f2:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r11, r13, r15, r16, r17, r18, r20, r22, r24, r26, r27, r28, r29, r30, r32, r33, r34)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, androidx.work.WorkInfo$State, java.lang.String, java.lang.String, androidx.work.Data, androidx.work.Data, long, long, long, androidx.work.Constraints, int, androidx.work.BackoffPolicy, long, long, long, long, boolean, androidx.work.OutOfQuotaPolicy, int, int, long, int, int, java.lang.String, int, kotlin.jvm.internal.e):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WorkSpec(java.lang.String r37, java.lang.String r38) {
        /*
            r36 = this;
            java.lang.String r0 = "id"
            r2 = r37
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "workerClassName_"
            r4 = r38
            kotlin.jvm.internal.j.e(r4, r0)
            r34 = 16777210(0xfffffa, float:2.3509879E-38)
            r35 = 0
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r10 = 0
            r12 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r19 = 0
            r21 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r1 = r36
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r10, r12, r14, r15, r16, r17, r19, r21, r23, r25, r26, r27, r28, r29, r31, r32, r33, r34, r35)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WorkSpec(java.lang.String r38, androidx.work.impl.model.WorkSpec r39) {
        /*
            r37 = this;
            r0 = r39
            java.lang.String r1 = "newId"
            r3 = r38
            kotlin.jvm.internal.j.e(r3, r1)
            java.lang.String r1 = "other"
            kotlin.jvm.internal.j.e(r0, r1)
            java.lang.String r5 = r0.workerClassName
            androidx.work.WorkInfo$State r4 = r0.state
            java.lang.String r6 = r0.inputMergerClassName
            androidx.work.Data r7 = new androidx.work.Data
            androidx.work.Data r1 = r0.input
            r7.<init>((androidx.work.Data) r1)
            androidx.work.Data r8 = new androidx.work.Data
            androidx.work.Data r1 = r0.output
            r8.<init>((androidx.work.Data) r1)
            long r9 = r0.initialDelay
            long r11 = r0.intervalDuration
            long r13 = r0.flexDuration
            androidx.work.Constraints r15 = new androidx.work.Constraints
            androidx.work.Constraints r1 = r0.constraints
            r15.<init>(r1)
            int r1 = r0.runAttemptCount
            androidx.work.BackoffPolicy r2 = r0.backoffPolicy
            r16 = r1
            r17 = r2
            long r1 = r0.backoffDelayDuration
            r18 = r1
            long r1 = r0.lastEnqueueTime
            r20 = r1
            long r1 = r0.minimumRetentionDuration
            r22 = r1
            long r1 = r0.scheduleRequestedAt
            r24 = r1
            boolean r1 = r0.expedited
            androidx.work.OutOfQuotaPolicy r2 = r0.outOfQuotaPolicy
            r26 = r1
            int r1 = r0.periodCount
            r28 = r1
            r27 = r2
            long r1 = r0.nextScheduleTimeOverride
            r30 = r1
            int r1 = r0.nextScheduleTimeOverrideGeneration
            int r2 = r0.stopReason
            java.lang.String r0 = r0.traceTag
            r35 = 524288(0x80000, float:7.34684E-40)
            r36 = 0
            r29 = 0
            r34 = r0
            r32 = r1
            r33 = r2
            r2 = r37
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r11, r13, r15, r16, r17, r18, r20, r22, r24, r26, r27, r28, r29, r30, r32, r33, r34, r35, r36)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpec.<init>(java.lang.String, androidx.work.impl.model.WorkSpec):void");
    }
}
