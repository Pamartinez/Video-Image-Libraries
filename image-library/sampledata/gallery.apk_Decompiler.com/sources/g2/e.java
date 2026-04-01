package G2;

import Hf.B;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final int f290a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f291c;

    public e(MessageDigest messageDigest, int i2) {
        ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        this.f291c = messageDigest;
        this.f290a = i2;
    }

    public e(B b5, int i2, boolean z) {
        this.f291c = b5;
        this.f290a = i2;
        this.b = z;
    }
}
