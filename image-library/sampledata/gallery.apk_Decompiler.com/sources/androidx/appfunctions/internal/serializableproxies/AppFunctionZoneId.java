package androidx.appfunctions.internal.serializableproxies;

import c0.C0086a;
import java.time.ZoneId;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionZoneId;", "", "", "zoneID", "<init>", "(Ljava/lang/String;)V", "Ljava/time/ZoneId;", "toZoneId", "()Ljava/time/ZoneId;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getZoneID", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionZoneId {
    public static final Companion Companion = new Companion((e) null);
    private final String zoneID;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionZoneId$Companion;", "", "<init>", "()V", "fromZoneId", "Landroidx/appfunctions/internal/serializableproxies/AppFunctionZoneId;", "zoneId", "Ljava/time/ZoneId;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AppFunctionZoneId fromZoneId(ZoneId zoneId) {
            j.e(zoneId, "zoneId");
            String id = zoneId.getId();
            j.d(id, "getId(...)");
            return new AppFunctionZoneId(id);
        }

        private Companion() {
        }
    }

    public AppFunctionZoneId(String str) {
        j.e(str, "zoneID");
        this.zoneID = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof AppFunctionZoneId) && j.a(this.zoneID, ((AppFunctionZoneId) obj).zoneID)) {
            return true;
        }
        return false;
    }

    public final String getZoneID() {
        return this.zoneID;
    }

    public int hashCode() {
        return this.zoneID.hashCode();
    }

    public String toString() {
        return C0086a.m(new StringBuilder("AppFunctionZoneId(zoneID="), this.zoneID, ')');
    }

    public final ZoneId toZoneId() {
        ZoneId of2 = ZoneId.of(this.zoneID);
        j.d(of2, "of(...)");
        return of2;
    }
}
