package w0;

/* renamed from: w0.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0311l extends n {
    public static final C0311l g = new C0311l(m.FALSE);

    /* renamed from: h  reason: collision with root package name */
    public static final C0311l f1986h = new C0311l(m.TRUE);

    /* renamed from: i  reason: collision with root package name */
    public static final C0311l f1987i = new C0311l(m.NULL);

    /* renamed from: j  reason: collision with root package name */
    public static final C0311l f1988j = new C0311l(m.UNDEFINED);
    public final m e;
    public final int f;

    public C0311l(m mVar) {
        super(o.SIMPLE_VALUE);
        this.f = mVar.a();
        this.e = mVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0311l) {
            C0311l lVar = (C0311l) obj;
            if (!super.equals(obj) || this.f != lVar.f) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.valueOf(this.f).hashCode() ^ super.hashCode();
    }

    public final String toString() {
        return this.e.toString();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0311l(int i2) {
        super(i2 <= 23 ? o.SIMPLE_VALUE : o.SIMPLE_VALUE_NEXT_BYTE);
        m mVar;
        this.f = i2;
        switch (i2 & 31) {
            case 20:
                mVar = m.FALSE;
                break;
            case 21:
                mVar = m.TRUE;
                break;
            case 22:
                mVar = m.NULL;
                break;
            case 23:
                mVar = m.UNDEFINED;
                break;
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
                mVar = m.RESERVED;
                break;
            default:
                mVar = m.UNALLOCATED;
                break;
        }
        this.e = mVar;
    }
}
