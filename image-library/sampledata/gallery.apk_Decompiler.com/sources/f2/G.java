package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends C0005a {
    public final /* synthetic */ int f = 1;
    public final Iterable g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public G(H h5, int i2) {
        super(i2, 0);
        this.g = h5;
    }

    public final Object a(int i2) {
        switch (this.f) {
            case 0:
                return ((H) this.g).d[i2].iterator();
            default:
                return ((U) this.g).get(i2);
        }
    }

    public G(int i2, U u) {
        super(u.size(), i2);
        this.g = u;
    }
}
