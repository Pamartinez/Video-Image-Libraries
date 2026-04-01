package w0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends C0303d {
    public final String d;

    public q(String str) {
        super(C0308i.UNICODE_STRING);
        this.d = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof q) || !super.equals(obj)) {
            return false;
        }
        String str = ((q) obj).d;
        String str2 = this.d;
        if (str2 != null) {
            return str2.equals(str);
        }
        if (str == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        String str = this.d;
        if (str == null) {
            return 0;
        }
        return str.hashCode() + super.hashCode();
    }

    public final String toString() {
        String str = this.d;
        if (str == null) {
            return "null";
        }
        return str;
    }
}
