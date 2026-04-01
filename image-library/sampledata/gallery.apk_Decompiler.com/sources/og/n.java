package og;

import N2.j;
import java.nio.charset.Charset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f5020a = Charset.forName("UTF-8");

    public static void a(long j2, long j3, long j8) {
        if ((j3 | j8) < 0 || j3 > j2 || j2 - j3 < j8) {
            StringBuilder j10 = j.j(j2, "size=", " offset=");
            j10.append(j3);
            j10.append(" byteCount=");
            j10.append(j8);
            throw new ArrayIndexOutOfBoundsException(j10.toString());
        }
    }
}
