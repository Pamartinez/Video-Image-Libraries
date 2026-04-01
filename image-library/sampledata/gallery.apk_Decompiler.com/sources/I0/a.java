package I0;

import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends IOException {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(long r3, long r5, int r7, java.lang.IndexOutOfBoundsException r8) {
        /*
            r2 = this;
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r0 = "Pos: "
            java.lang.String r1 = ", limit: "
            java.lang.StringBuilder r3 = N2.j.j(r3, r0, r1)
            r3.append(r5)
            java.lang.String r4 = ", len: "
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
            java.lang.String r3 = r4.concat(r3)
            r2.<init>(r3, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: I0.a.<init>(long, long, int, java.lang.IndexOutOfBoundsException):void");
    }

    public a(IndexOutOfBoundsException indexOutOfBoundsException) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", indexOutOfBoundsException);
    }

    public a(String str, IndexOutOfBoundsException indexOutOfBoundsException) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(str), indexOutOfBoundsException);
    }
}
