package w0;

import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class n extends C0304e {
    public static final n d = new n(o.BREAK);

    /* renamed from: c  reason: collision with root package name */
    public final o f1989c;

    public n(o oVar) {
        super(C0308i.SPECIAL);
        Objects.requireNonNull(oVar);
        this.f1989c = oVar;
    }

    public boolean equals(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (!super.equals(obj) || this.f1989c != nVar.f1989c) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f1989c) ^ super.hashCode();
    }

    public String toString() {
        return this.f1989c.name();
    }
}
