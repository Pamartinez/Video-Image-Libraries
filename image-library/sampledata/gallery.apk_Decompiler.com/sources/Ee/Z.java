package ee;

import E2.g;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z implements L, H {
    public static final byte[] e = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    public final /* synthetic */ int d;

    public /* synthetic */ Z(int i2) {
        this.d = i2;
    }

    public String a(Object obj) {
        return (String) obj;
    }

    public int b(Object obj) {
        switch (this.d) {
            case 3:
                ((C0967D) obj).getClass();
                return 5;
            default:
                ((T) obj).getClass();
                return 5;
        }
    }

    public String toString() {
        switch (this.d) {
            case 2:
                return "internal:health-check-consumer-listener";
            default:
                return super.toString();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m62a(Object obj) {
        switch (this.d) {
            case 0:
                byte[] bytes = ((String) obj).getBytes(g.b);
                int i2 = 0;
                while (i2 < bytes.length) {
                    byte b = bytes[i2];
                    if (b < 32 || b >= 126 || b == 37) {
                        byte[] bArr = new byte[(((bytes.length - i2) * 3) + i2)];
                        if (i2 != 0) {
                            System.arraycopy(bytes, 0, bArr, 0, i2);
                        }
                        int i7 = i2;
                        while (i2 < bytes.length) {
                            byte b5 = bytes[i2];
                            if (b5 < 32 || b5 >= 126 || b5 == 37) {
                                bArr[i7] = 37;
                                byte[] bArr2 = e;
                                bArr[i7 + 1] = bArr2[(b5 >> 4) & 15];
                                bArr[i7 + 2] = bArr2[b5 & 15];
                                i7 += 3;
                            } else {
                                bArr[i7] = b5;
                                i7++;
                            }
                            i2++;
                        }
                        return Arrays.copyOf(bArr, i7);
                    }
                    i2++;
                }
                return bytes;
            default:
                return Y.a(((a0) obj).f4290a);
        }
    }

    public Object c(String str) {
        return str;
    }
}
