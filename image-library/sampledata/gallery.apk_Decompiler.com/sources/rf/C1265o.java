package rf;

/* renamed from: rf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1265o implements Comparable {
    public final int d;
    public final Q e;
    public final boolean f;

    public C1265o(int i2, Q q, boolean z) {
        this.d = i2;
        this.e = q;
        this.f = z;
    }

    public final int compareTo(Object obj) {
        return this.d - ((C1265o) obj).d;
    }
}
