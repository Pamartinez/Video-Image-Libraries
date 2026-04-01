package d1;

import Q0.k;
import V0.i;
import java.io.Serializable;

/* renamed from: d1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0179a extends i implements Serializable {
    public final String d;
    public final k e;

    public C0179a(k kVar) {
        this.d = kVar.f612h;
        this.e = kVar;
    }

    public final String a() {
        return this.d;
    }

    public final String b() {
        if (getClass() == C0179a.class) {
            return null;
        }
        return super.b();
    }

    public final k d() {
        return this.e;
    }

    public C0179a(k kVar, int i2) {
        this.d = "JacksonXmlModule";
        this.e = kVar;
    }
}
