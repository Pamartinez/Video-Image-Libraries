package fe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f4357a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f4358c;

    public k(byte[] bArr, int i2, boolean z) {
        this.f4357a = bArr;
        this.b = i2;
        this.f4358c = z;
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("TransactionData[");
        sb2.append(this.b);
        sb2.append("b ");
        sb2.append("array");
        if (this.f4358c) {
            str = "(last)]";
        } else {
            str = "]";
        }
        sb2.append(str);
        return sb2.toString();
    }
}
