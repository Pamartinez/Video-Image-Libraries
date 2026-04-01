package androidx.appfunctions.internal.serializableproxies;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u000f¨\u0006\u001a"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionInstant;", "", "", "epochSecond", "", "nanoAdjustment", "<init>", "(JI)V", "Ljava/time/Instant;", "toInstant", "()Ljava/time/Instant;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getEpochSecond", "()J", "I", "getNanoAdjustment", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionInstant {
    public static final Companion Companion = new Companion((e) null);
    private final long epochSecond;
    private final int nanoAdjustment;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionInstant$Companion;", "", "<init>", "()V", "fromInstant", "Landroidx/appfunctions/internal/serializableproxies/AppFunctionInstant;", "instant", "Ljava/time/Instant;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AppFunctionInstant fromInstant(Instant instant) {
            j.e(instant, "instant");
            return new AppFunctionInstant(instant.getEpochSecond(), instant.getNano());
        }

        private Companion() {
        }
    }

    public AppFunctionInstant(long j2, int i2) {
        this.epochSecond = j2;
        this.nanoAdjustment = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionInstant)) {
            return false;
        }
        AppFunctionInstant appFunctionInstant = (AppFunctionInstant) obj;
        if (this.epochSecond == appFunctionInstant.epochSecond && this.nanoAdjustment == appFunctionInstant.nanoAdjustment) {
            return true;
        }
        return false;
    }

    public final long getEpochSecond() {
        return this.epochSecond;
    }

    public final int getNanoAdjustment() {
        return this.nanoAdjustment;
    }

    public int hashCode() {
        return Integer.hashCode(this.nanoAdjustment) + (Long.hashCode(this.epochSecond) * 31);
    }

    public final Instant toInstant() {
        Instant ofEpochSecond = Instant.ofEpochSecond(this.epochSecond, (long) this.nanoAdjustment);
        j.d(ofEpochSecond, "ofEpochSecond(...)");
        return ofEpochSecond;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionInstant(epochSecond=");
        sb2.append(this.epochSecond);
        sb2.append(", nanoAdjustment=");
        return N2.j.e(sb2, this.nanoAdjustment, ')');
    }
}
