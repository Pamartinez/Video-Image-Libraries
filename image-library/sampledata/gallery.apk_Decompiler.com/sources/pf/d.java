package pf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends c {
    public final String d;
    public final String e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(String str, String str2) {
        super(18);
        j.e(str, "name");
        j.e(str2, "desc");
        this.d = str;
        this.e = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (j.a(this.d, dVar.d) && j.a(this.e, dVar.e)) {
            return true;
        }
        return false;
    }

    public final String g() {
        return this.d + ':' + this.e;
    }

    public final int hashCode() {
        return this.e.hashCode() + (this.d.hashCode() * 31);
    }
}
